import java.util.Arrays;
import java.util.Scanner;

public class ClimbingStairsWithMinJumps{

	// Climbing Stairs with Minimum Moves / Jumps

	// 1. You are given a number n, representing the number of stairs in a staircase.
	// 2. You are on the 0th step and are required to climb to the top.
	// 3. You are given n numbers, where ith element's value represents - till how far from the step you 
	//      could jump to in a single move.  You can of-course fewer number of steps in the move.
	// 4. You are required to print the number of minimum moves in which you can reach the top of 
	//      staircase.
	// Note -> If there is no path through the staircase print null.

	// Input Format
	// A number n
	// .. n more elements

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
	// 4

	public static int climbingStairsWithMinimumJumps(int n, int[] moves){
		Integer[] dp = new Integer[n + 1];
		// There are zero moves to reach nth step since you ARE at nth step
		dp[n] = 0;

		for(int currentStep = n - 1; currentStep >= 0; currentStep--){
			// Skips zero moves
			if(moves[currentStep] > 0){
				int minMoves = Integer.MAX_VALUE;

				// Find minimum number of jump
				for(int j = 1; j <= moves[currentStep] && currentStep + j < dp.length; j++){
					// If (i + j)th step is reachable
					if(dp[currentStep + j] != null){						
						minMoves = Math.min(minMoves, dp[currentStep + j]);
					}
				}

				if(minMoves != Integer.MAX_VALUE){
					dp[currentStep] = minMoves + 1;
				}else{
					dp[currentStep] = null;
				}
			}		
		}

		return dp[0];
	}

	public static int climbingStairsWithMinimumJumps2(int numberOfSteps, int[] possibleMoves){
		// Our cache
		// dp[i] represents min number of moves to reach destination step from ith step
		int[] dp = new int[numberOfSteps + 1];

		// Initially all moves are impossible i.e. -1
		Arrays.fill(dp, -1);

		// Destination Step -> Destination Step can be done in 0 steps
		dp[numberOfSteps] = 0;		

		// Start climbing down the steps
		for(int currentStep = numberOfSteps - 1; currentStep >= 0; currentStep--){
			// If moves are possible
			if(possibleMoves[currentStep] > 0){				
				int minMoves = Integer.MAX_VALUE;

				// Make 'exploratory' leaps to find shortest route (min moves to destination step)
				for(int leap = 1; leap <= possibleMoves[currentStep] && currentStep + leap < dp.length; leap++){
					if(dp[currentStep + leap] != -1)
						minMoves = Math.min(minMoves, dp[currentStep + leap]);																
				}
				// If min moves to reach destination is found, save it!
				if(minMoves != Integer.MAX_VALUE)
					dp[currentStep] = minMoves + 1;				
			}
		}

		// Minimum moves to reach destination step from 1st step
		return dp[0];

	}

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of steps: ");
		int n = sc.nextInt();

		int[] moves = new int[n];
		System.out.println("Enter possible moves: ");
		for(int i = 0; i < n; i++){
			moves[i] = sc.nextInt();
		}

		System.out.println(climbingStairsWithMinimumJumps(n, moves));
		System.out.println(climbingStairsWithMinimumJumps2(n, moves));
	}
}