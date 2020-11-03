import java.util.Scanner;
import java.util.Arrays;

public class MinimumCostPath{
	// Given a m x n grid filled with non-negative numbers, find a path from top left to 
	// bottom right which minimizes the sum of all numbers along its path.

	// Constraint: 
	// You cannot revisit a cell
	// Example - If you came to a cell from UP, you cannot go back UP

	// Note: You can only move either up, down, or right at any point in time.

	// Example:

	// Input:
	// [
	//   [1,3,1],
	//   [1,5,1],
	//   [4,2,1]
	// ]

	// Output: 7
	// Explanation: Because the path 1→3→1→1→1 minimizes the sum.
	
	// Direction of entry into next cell to be visited
	enum Direction{
		UP, LEFT, DOWN;
	}
	
	public static int minimumCostPathNaive(int r, int c, int[][] grid, Direction d){		
		if(r < 0 || r >= grid.length || c >= grid[0].length)
			return 0;
		else if(d == Direction.UP)
			// If you entered current cell from UP, visit cell to your RIGHT (LEFT for that cell) and DOWN (UP for that cell)
			return grid[r][c] + Math.min(minimumCostPathNaive(r, c + 1, grid, Direction.LEFT), minimumCostPathNaive(r + 1, c, grid, Direction.UP));
		else if(d == Direction.DOWN)
			return grid[r][c] + Math.min(minimumCostPathNaive(r, c + 1, grid, Direction.LEFT), minimumCostPathNaive(r - 1, c, grid, Direction.DOWN));		
		else// if(d == Direction.RIGHT)
			return grid[r][c] + Math.min(Math.min(minimumCostPathNaive(r, c + 1, grid, Direction.LEFT), minimumCostPathNaive(r - 1, c, grid, Direction.DOWN)), minimumCostPathNaive(r + 1, c, grid, Direction.UP));		
	}

	public static int minimumCostPathMemoization(int r, int c, int[][] grid, int[][] cache, Direction d){				
		int retVal;

		if(r < 0 || r >= grid.length || c >= grid[0].length)
			return 0;		
		else if(cache[r][c] != -1)
			return cache[r][c];		
		else if(d == Direction.UP)
			retVal = grid[r][c] + Math.min(minimumCostPathMemoization(r, c + 1, grid, cache, Direction.LEFT), minimumCostPathMemoization(r + 1, c, grid, cache, Direction.UP));		
		else if(d == Direction.DOWN)			
			retVal = grid[r][c] + Math.min(minimumCostPathMemoization(r, c + 1, grid, cache, Direction.LEFT), minimumCostPathMemoization(r - 1, c, grid, cache, Direction.DOWN));		
		else			
			retVal = grid[r][c] + Math.min(Math.min(minimumCostPathMemoization(r, c + 1, grid, cache, Direction.LEFT), minimumCostPathMemoization(r - 1, c, grid, cache, Direction.DOWN)), minimumCostPathMemoization(r + 1, c, grid, cache, Direction.UP));		

		cache[r][c] = retVal;		
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
				if(r == maxRow - 1 && c == maxCol - 1)
					dp[r][c] = grid[r][c];			
				else if(r == 0 && c < grid[0].length - 1)
					dp[r][c] = grid[r][c] + Math.min(dp[r][c + 1], dp[r + 1][c]);				
				else if(r == grid.length - 1 && c < grid[0].length - 1)
					dp[r][c] = grid[r][c] + Math.min(dp[r - 1][c], dp[r][c + 1]);
				else if(r > 0 && r < grid.length - 1 && c == grid[0].length - 1)
					dp[r][c] = grid[r][c] + Math.min(dp[r - 1][c], dp[r + 1][c]);
				else if(r == 0 && c == grid[0].length - 1)						
					dp[r][c] = grid[r][c] + dp[r + 1][c];
				else		
					dp[r][c] = grid[r][c] + Math.min(Math.min(dp[r - 1][c], dp[r + 1][c]), dp[r][c + 1]);
			}	
		}


		for(int r = 0; r < maxRow; r++){
			for(int c = 0; c < maxCol; c++){				
				System.out.print(dp[r][c] + " ");
			}
			System.out.println();
		}		

		return dp[0][0];
	}

	public static void main(String args[]){	
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter rows: ");
		int rows  = sc.nextInt();		
		
		System.out.println("Enter cols: ");
		int cols  = sc.nextInt();

		int[][] grid = new int[rows][cols];
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				grid[r][c] = sc.nextInt();
			}
		}		

		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){				
				System.out.print(grid[r][c] + " ");
			}
			System.out.println();
		}		

		int[][] cache = new int[rows][cols];
		for(int r = 0; r < rows; r++){
			Arrays.fill(cache[r], -1);
		}		

		// Note: We start at the top-left cell. We can enter from either LEFT or UP. It does not matter.
		System.out.println("minimumCostPathNaive  = " + minimumCostPathNaive(0, 0, grid, Direction.LEFT));	
		System.out.println("minimumCostPathMemoization = " + minimumCostPathMemoization(0, 0, grid, cache, Direction.UP));	
		System.out.println("minimumCostPathTabulation = " + minimumCostPathTabulation(grid));		
	}
}