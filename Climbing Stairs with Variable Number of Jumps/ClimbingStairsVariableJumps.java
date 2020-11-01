import java.util.Arrays;
import java.util.Scanner;

// Climbing Stairs with Variable Number of Jumps
// 1. You are given a number n, representing the number of stairs in a staircase.
// 2. You are on the 0th step and are required to climb to the top.
// 3. You are given n numbers, where ith element's value represents - till how far from the step you 
//      could jump to in a single move.  
//      You can of course jump fewer number of steps in the move.
// 4. You are required to print the number of different paths via which you can climb to the top.

// Input Format
// A number n representing number of steps
// An array of n more elements

// Output Format
// A number representing the number of ways to climb the stairs from 0 to top.

// Constraints
// 0 <= n <= 20
// 0 <= n1, n2, .. <= 20

// Sample Input
// 10
// 3
// 3
// 0
// 2
// 1
// 2
// 4
// 2
// 0
// 0

// Sample Output
// 5

public class ClimbingStairsVariableJumps{
	public static int climbingStairsWithVariableJumpsMemoization(int index, int[] cache, int[] jumps){				
		if(index >= cache.length){
			return 0;
		}
		
		if(cache[index] != -1){
			return cache[index];
		}
		
		int retVal = 0;
		for(int i = 1; i <= jumps[index]; i++){
			retVal += climbingStairsWithVariableJumpsMemoization(index + i, cache, jumps);
		}
		
		cache[index] = retVal;
		
		return retVal;
	}

	public static int climbingStairsWithVariableJumpsTabulation(int n, int[] jumps){
		int[] dp = new int[n + 1];

		dp[n] = 1;

		for(int i = n-1; i >= 0; i--){			
			for(int j = 1; j <= jumps[i]; j++){
				if(i + j <= n)
					dp[i] += dp[i + j];
			}					
		}

		return dp[0];
	}

	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);		

		System.out.println("Enter the number of steps: ");
		int n  = sc.nextInt();

		int[] jumps = new int[n];

		for(int i = 0; i < n; i++){
			jumps[i] = sc.nextInt();
		}

		System.out.println("Result from Tabulation = " + climbingStairsWithVariableJumpsTabulation(n, jumps));	

		int[] cache = new int[n + 1];
		Arrays.fill(cache, -1);
		cache[n] = 1;

		System.out.println("\nResult from Memoization = " + climbingStairsWithVariableJumpsMemoization(0, cache, jumps));
	}
}