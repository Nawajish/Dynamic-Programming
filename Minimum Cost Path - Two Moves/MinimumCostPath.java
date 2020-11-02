/*
	Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right 
	which minimizes the sum of all numbers along its path.

	Note: You can only move either down or right at any point in time.

	Example:

	Input:
	[
	  [1,3,1],
	  [1,5,1],
	  [4,2,1]
	]
	
	Output: 7
	Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

import java.util.Scanner;

public class MinimumCostPath{

	public static int minimumCostPathNaive(int r, int c, int[][] grid){
		// If out of bounds of grid, return positive infinity
		if(r >= grid.length && c >= grid[0].length)
			return Integer.MAX_VALUE;		
		// If at bottom-right position, only this coin can be picked
		else if(r == grid.length - 1 && c == grid[0].length - 1)
			return grid[r][c];		
		// If on the bottom row and NOT on the rightmost column
		else if(r == grid.length - 1 && c < grid[0].length - 1)
			return grid[r][c] + minimumCostPathNaive(r, c + 1, grid);
		// If on the rightmost column and NOT on the bottom row
		else if(r < grid.length - 1 && c == grid[0].length - 1)
			return grid[r][c] + minimumCostPathNaive(r + 1, c, grid);
		// General case
		else
			return grid[r][c] + Math.min(minimumCostPathNaive(r + 1, c, grid), minimumCostPathNaive(r, c + 1, grid));
	}

	public static int minimumCostPathMemoization(int r, int c, int[][] grid, int[][] cache){		
		// Base case - if out of grid bounds, return positive infinity		
		if(r >= grid.length && c >= grid[0].length)
			return Integer.MAX_VALUE;		

		// Cache lookup
		if(cache[r][c] != -1)
			return cache[r][c];		

		// If cache lookup failed, save calculation
		int retVal = -1;
		
		// If at last position, only this coin can be picked
		if(r == grid.length - 1 && c == grid[0].length - 1)			
			retVal = grid[r][c];				
		// If on the bottom row and NOT on the rightmost column
		else if(r == grid.length - 1 && c < grid[0].length - 1)
			retVal = grid[r][c] + minimumCostPathMemoization(r, c + 1, grid, cache);
		// If on the rightmost column and NOT on the bottom row
		else if(r < grid.length - 1 && c == grid[0].length - 1)
			retVal = grid[r][c] + minimumCostPathMemoization(r + 1, c, grid, cache);
		// General case
		else
			retVal = grid[r][c] + Math.min(minimumCostPathMemoization(r + 1, c, grid, cache), minimumCostPathMemoization(r, c + 1, grid, cache));

		// Store result in cache
		cache[r][c] = retVal;

		// Return result
		return retVal;		
	}

	public static int minimumCostPathTabulation(int[][] grid){
		// Grid bounds
		int maxRow = grid.length, maxCol = grid[0].length;

		// Create table (same dimensions as grid)
		int[][] dp = new int[maxRow][maxCol];

		// Fill table backwards - most trivial to least trivial
		// grid[maxRow - 1][maxCol - 1] being the last element is picked up directly (Most trivial state...start from here)		
		for(int r = maxRow - 1; r >= 0; r--){
			for(int c = maxCol - 1; c >= 0; c--){
				// If last element, pick it directly
				if(r == maxRow - 1 && c == maxCol - 1)
					dp[r][c] = grid[r][c];			
				// If at bottom row and NOT at rightmost column	
				else if(r == maxRow - 1 && c < maxCol - 1)
					dp[r][c] = grid[r][c] + Math.min(Integer.MAX_VALUE, dp[r][c + 1]);				
				// If at rightmost column and NOT at bottom row
				else if(r < maxRow - 1 && c == maxCol - 1)
					dp[r][c] = grid[r][c] + Math.min(Integer.MAX_VALUE, dp[r + 1][c]);					
				// General Case
				else
					dp[r][c] = grid[r][c] + Math.min(dp[r + 1][c], dp[r][c  + 1]);					
			}	
		}

		// Return final state (Least trivial state)
		return dp[0][0];
	}

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the number of rows: ");
		int rows = sc.nextInt();

		System.out.print("Enter the number of cols: ");
		int cols = sc.nextInt();

		int[][] grid = new int[rows][cols];

		System.out.println("Enter the values for " + (rows*cols) + " coins:");
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				grid[r][c] = sc.nextInt();
			}
		}

		int[][] cache = new int[rows][cols];
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				cache[r][c] = -1;
			}
		}

		System.out.println(minimumCostPathNaive(0, 0, grid));	

		System.out.println(minimumCostPathMemoization(0, 0, grid, cache));	

		System.out.println(minimumCostPathTabulation(grid));	

	}
}