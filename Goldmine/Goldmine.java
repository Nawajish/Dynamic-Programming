import java.util.Scanner;

// Path with Maximum Gold

// 1. You are given a number n, representing the number of rows.
// 2. You are given a number m, representing the number of columns.
// 3. You are given n*m numbers, representing elements of 2d array a, which represents a gold mine.
// 4. You are standing in front of left wall and are supposed to dig to the right wall. You can start from 
//      any row in the left wall.
// 5. You are allowed to move 1 cell right-up (d1), 1 cell right (d2) or 1 cell right-down(d3).
// 6. Each cell has a value that is the amount of gold available in the cell.
// 7. You are required to identify the maximum amount of gold that can be dug out from the mine.

// Input Format
// A number n
// A number m
// e11
// e12..
// e21
// e22..
// .. n * m number of elements

// Output Format
// An integer representing Maximum gold available.

// Constraints
// 1 <= n <= 10^2
// 1 <= m <= 10^2
// 0 <= e1, e2, .. n * m elements <= 1000

// Sample Input
// 6
// 6
// 0 1 4 2 8 2
// 4 3 6 5 0 4
// 1 2 4 1 4 6
// 2 0 7 3 2 2
// 3 1 5 9 2 4
// 2 7 0 8 5 1

// Sample Output
// 33

// Hint

// Moves allowed : UP-RIGHT, RIGHT, DOWN-RIGHT
// UP-RIGHT 	- row - 1, col + 1
// RIGHT 		- row, col + 1
// DOWN-RIGHT	- row + 1, col + 1

// Start digging at each row / level
// Return maximum 


public class Goldmine{

	public static int goldMineTabulation(int[][] grid){
		// Grid bounds
		int maxRow = grid.length, maxCol = grid[0].length;

		// Create table (same dimensions as grid)
		int[][] dp = new int[maxRow][maxCol];

		// Fill table backwards - independent -> dependent
		// Last Col -> First Col
		// Row can be solved in any order
		for(int c = maxCol - 1; c >= 0; c--){
			for(int r = maxRow - 1; r >= 0; r--){		
				// If on first row and NOT on last col, only RIGHT, DOWN-RIGHT moves are allowed
				if(r == 0 && c < grid[0].length - 1)
					dp[r][c] = grid[r][c] + Math.max(dp[r][c + 1], dp[r + 1][c + 1]);
				// If on last row and NOT on last col, only UP-RIGHT, RIGHT moves are allowed
				else if(r == grid.length - 1 && c < grid[0].length - 1)
					dp[r][c] = grid[r][c] + Math.max(dp[r - 1][c + 1], dp[r][c + 1]);
				// If at last col, no moves can be made
				else if(c == grid[0].length - 1)
					dp[r][c] = grid[r][c];		
				// General case - all three moves are allowed
				else
					dp[r][c] = grid[r][c] + Math.max(Math.max(dp[r - 1][c + 1], dp[r][c + 1]), dp[r + 1][c + 1]);				
			}	
		}

		int maxSoFar = dp[0][0];		
		for(int r = 1; r < dp.length; r++){		
				maxSoFar = Math.max(maxSoFar, dp[r][0]);		
		}
		
		// Return maxPossiblePath
		return maxSoFar;
	}

	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the number of rows: ");
		int rows = sc.nextInt();

		System.out.println("Enter the number of cols: ");
		int cols = sc.nextInt();

		int[][] grid = new int[rows][cols];

		System.out.println("Enter the " + (rows*cols) + " values : ");
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				grid[r][c] = sc.nextInt();
			}
		}

		System.out.println(goldMineTabulation(grid));	
	}
}