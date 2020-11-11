// Given two integer arrays to represent weights and profits of ‘N’ items, we need to find a subset of these items 
// which will give us maximum profit such that their cumulative weight is not more than a given number ‘C’. Each 
// item can only be selected once, which means either we put an item in the knapsack or we skip it.

// Input : W = 60
//        val[] = {60, 100, 120}
//        wt[]  = {10, 20, 30}       
// Output : 100 
// We get maximum value = 280


import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class Knapsack{

	public static int knapsackRecursive(int[] weights, int[] values, int remCapacity, int index){

		if(remCapacity < 0){
			return Integer.MIN_VALUE;
		}

		if(index < 0 || remCapacity == 0){
			return 0;
		}

		int profit1 = knapsackRecursive(weights, values, remCapacity - weights[index], index - 1) + values[index];
		int profit2 = knapsackRecursive(weights,values, remCapacity, index - 1);

		return Math.max(profit1, profit2);

	}

	public static int knapsackMemoized(int[] weights, int[] values, int remCapacity, int index, int[][] cache){	

		if(remCapacity < 0){
			return Integer.MIN_VALUE;
		}

		if(index < 0 || remCapacity == 0){
			return 0;
		}

		if(cache[index][remCapacity] != -1)
			return cache[index][remCapacity];

		int profit1 = knapsackMemoized(weights, values, remCapacity - weights[index], index - 1, cache) + values[index];
		int profit2 = knapsackMemoized(weights, values, remCapacity, index - 1, cache);

		int retVal = Math.max(profit1, profit2);

		cache[index][remCapacity] = retVal;

		return retVal;
	}

	public static int knapsackTabulation(int[] weights, int[] values, int W){
		// number of items
		int n = values.length;

		int[][] dp = new int[n + 1][W + 1];

		for(int i = 0; i <= n; i++){
			for(int w = 0; w <= W; w++){
				if(i == 0 || w == 0)
					dp[i][w] = 0;
				else if(weights[i - 1] <= w)
					dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);				
				else
					dp[i][w] = dp[i - 1][w];
			}
		}

		return dp[n][W];
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

		System.out.println(knapsackRecursive(weights, values, W, values.length - 1));			
		System.out.println(knapsackMemoized(weights, values, W, values.length - 1, cache));		
		System.out.println(knapsackTabulation(weights, values, W));	
	}
}