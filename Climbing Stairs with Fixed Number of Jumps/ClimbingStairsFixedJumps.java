import java.util.Scanner;
import java.util.Arrays;

// Climbing Stairs with Fixed Number of Jummps 
// 1. You are given a number n, representing the number of stairs in a staircase.
// 2. You are on the 0th step and are required to climb to the top.
// 3. In one move, you are allowed to climb 1, 2 or 3 stairs.
// 4. You are required to print the number of different paths via which you can climb to the top.

// Input Format
// A number n

// Output Format
// A number representing the number of ways to climb the stairs from 0 to top.

// Constraints
// 0 <= n <= 20

// Sample Input
// 4

// Sample Output
// 7

public class ClimbingStairsFixedJumps{	

	public static int climbingStairsNaive(int n){		
		if(n < 0){
			return 0;
		}

		if(n == 0){
			return 1;
		}

		return  climbingStairsNaive(n - 1) + climbingStairsNaive(n - 2) + climbingStairsNaive(n - 3);
	}

	public static int climbingStairsMemoization(int n, int[] cache){
		if(n < 0){
			return 0;
		}

		if(n == 0){
			return 1;
		}

		if(cache[n] != -1){
			return cache[n];
		}

		int retVal = climbingStairsMemoization(n - 1, cache) + climbingStairsMemoization(n - 2, cache) + climbingStairsMemoization(n - 3, cache);

		cache[n] = retVal;

		return retVal;
	}

	public static int climbingStairsTabulation(int n){
	 if(n < 0) { return 0; } // creates table of 0 size

		if(n == 0 || n == 1){
			return 1;
		}

		int[] table = new int[n + 1];
		table[0] = 1;
		table[1] = 1;
		table[2] = 2;

		for(int i = 3; i <= n; i++){
			table[i] = table[i - 1] + table[i - 2] + table[i - 3];
		}

		return table[n];
	}

	// Cleaner Code
	public static int climbingStairsTabulation2(int n){		
	 if( n < 0){ return 0; } // creates table of 0 size

		int[] table = new int[n + 1];

		table[0] = 1;

		for(int i = 1; i <= n; i++){
			if(i == 1){
				table[i] = table[i - 1];
			}
			else if(i == 2){
				table[i] = table[i - 1] + table[i - 2];
			}
			else{
				table[i] = table[i - 1] + table[i - 2] + table[i - 3];
			}			
		}

		return table[n];
	}

	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of steps: ");
		int n  = sc.nextInt();

		int[] cache = new int[n + 1];
		Arrays.fill(cache, -1);

		System.out.println(climbingStairsNaive(n));		
		System.out.println(climbingStairsMemoization(n, cache));
		System.out.println(climbingStairsTabulation(n));
	}

}