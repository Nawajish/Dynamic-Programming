// Given a knapsack weight W and a set of n items with certain value vali and weight wti, we need to calculate the maximum amount that could make up this quantity exactly. This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.
// Examples: 

// Input : W = 100
//        val[]  = {1, 30}
//        wt[] = {1, 50}
// Output : 100
// There are many ways to fill knapsack.
// 1) 2 instances of 50 unit weight item.
// 2) 100 instances of 1 unit weight item.
// 3) 1 instance of 50 unit weight item and 50
//    instances of 1 unit weight items.
// We get maximum value with option 2.

// Input : W = 8
//        val[] = {10, 40, 50, 70}
//        wt[]  = {1, 3, 4, 5}       
// Output : 110 
// We get maximum value with one unit of
// weight 5 and one unit of weight 3.

import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class UnboundedKnapsack{
		public static int unboundedKnapsackNaive(int[] weights, int[] values, int W, int n){
		
		if(n < 0 || W <= 0 || weights.length == 0 || values.length == 0 || weights.length != values.length)
			return 0;

		int profit1 = 0;
		if(weights[n] <= W)
			profit1 = values[n] + unboundedKnapsackNaive(weights, values, W - weights[n], n);
		
		int profit2 = unboundedKnapsackNaive(weights, values, W, n - 1);

		return Math.max(profit1, profit2);
	}

	public static int unboundedKnapsackMemoized(int[] weights, int[] values, int W, int n, int[][] cache){

		if(n < 0 || W <= 0 || weights.length == 0 || values.length == 0 || weights.length != values.length)
			return 0;

		if(cache[n][W] != -1)
			return cache[n][W];

		int profit1 = 0;
		if(weights[n] <= W)
			profit1 = values[n] + unboundedKnapsackMemoized(weights, values, W - weights[n], n, cache);

		int profit2 =  unboundedKnapsackMemoized(weights, values, W, n - 1, cache);

		return cache[n][W] = Math.max(profit1, profit2);
	}

	// Using a 2D table
	public static int unboundedKnapsackTabulation(int[] weights, int[] values, int W){
		// number of items
		int n = values.length;

		int[][] dp = new int[n + 1][W + 1];

		for(int i = 0; i <= n; i++){
			for(int w = 0; w <= W; w++){
				if(i == 0 || w == 0)
					dp[i][w] = 0;
				else if(weights[i - 1] <= w)
					dp[i][w] = Math.max(values[i - 1] + dp[i][w - weights[i - 1]], dp[i - 1][w]);				
				else
					dp[i][w] = dp[i - 1][w];
			}
		}

		return dp[n][W];
	}

	// Using a 1D table
	public static int unboundedKnapsackTabulation2(int[] weights, int[] values, int W){
		// number of items
		int n = values.length;

		int[] dp = new int[W + 1];

		Arrays.fill(dp, 0);
		
		for(int i = 0; i < n; i++){
			for(int w = 0; w <= W; w++){
				if(weights[i] <= w)
					dp[w] = Math.max(values[i] + dp[w - weights[i]], dp[w]);							
			}
		}

		return dp[W];
	}

	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the number of items: ");
		int n = sc.nextInt();

		System.out.println("Enter the knapsack capacity: ");
		int W = sc.nextInt();


		int[] values = new int[n];
		System.out.println("Enter the values for the " + n + " items: ");
		for(int i = 0; i < n; i++){
			values[i] = sc.nextInt();
		}

		int[] weights = new int[n];
		System.out.println("Enter the weights for the " + n + " items: ");
		for(int i = 0; i < n; i++){
			weights[i] = sc.nextInt();
		}

		int[][] cache = new int[values.length + 1][W + 1];

		for(int i = 0; i < cache.length; i++){			
			Arrays.fill(cache[i], -1);
		}		

		System.out.println(unboundedKnapsackNaive(weights, values, W, values.length - 1));			
		System.out.println(unboundedKnapsackMemoized(weights, values, W, values.length - 1, cache));		
		System.out.println(unboundedKnapsackTabulation(weights, values, W));	
		System.out.println(unboundedKnapsackTabulation2(weights, values, W));		
	}
}