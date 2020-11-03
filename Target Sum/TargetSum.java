// Target Sum
// Given an array of n elements and a target sum k
// Return true if any subset of given array sums to k. Else return false

// Note
// Number of subsets = 2^n (since we have two choices for each element - either include or exclude it) 

import java.util.List;
import java.util.ArrayList;

public class TargetSum{

	static List<List<Integer>> list = new ArrayList<>();
		
	public static void targetSumNaive1(int[] A, int startIndex, List<Integer> subset, int curSum, int target){
		if(curSum == target)
			return;
		
		if(startIndex == A.length || curSum > target)
			return;

		targetSumNaive1(A, startIndex + 1, subset, curSum, target);	
		subset.add(A[startIndex]);		
		curSum += A[startIndex];		
		targetSumNaive1(A, startIndex + 1, subset, curSum + A[startIndex], target);		
	}

	public static boolean targetSumNaive2(int[] A, int index, int remainingSum){
		if(remainingSum == 0)
			return true;

		if(remainingSum < 0 || index < 0)
			return false;

		boolean include = targetSumNaive2(A, index - 1, remainingSum - A[index]);
		boolean exclude = targetSumNaive2(A, index - 1, remainingSum);

		return include || exclude;
	}

	public static boolean targetSumMemoization(int[] A, int index, int sum, int target, int[][] cache){				
		if(sum == target)
			return true;
		if(sum > target || index >= A.length)
			return false;

		if(cache[index][sum] != -1)
			return cache[index][sum] == 1 ? true : false;

		boolean include = targetSumMemoization(A, index + 1, sum + A[index], target, cache);
		boolean exclude = targetSumMemoization(A, index + 1, sum, target, cache);

		int retVal = (include || exclude) ? 1 : 0;
		

		cache[index][sum] = retVal;

		return retVal == 1;
	}

	public static boolean targetSumTabulation(int[] A, int target){
		boolean[][] dp = new boolean[A.length + 1][target + 1];		

		for(int r = 0; r < dp.length; r++){
			for(int c = 0; c < dp[0].length; c++){
				if(r == 0 && c == 0)
					dp[r][c] = true;
				else if(r == 0)
					dp[r][c] = false;
				else if (c == 0)
					dp[r][c] = true;		
				else if(c - A[r - 1] >= 0)
					dp[r][c] = dp[r - 1][c] || dp[r - 1][c - A[r - 1]];
				else
					dp[r][c] = dp[r - 1][c];
				
			}
		}

		return dp[A.length][target];
	}

	public static void main(String args[]){
		int[] A = {4, 2, 7, 3, 8};
		int target = 10;		
	
		if(targetSumNaive2(A, A.length - 1, target) == true)
			System.out.println("YES");
		else
			System.out.println("NO");
		

		int[][] cache = new int[A.length][target];
		for(int r = 0; r < cache.length; r++){
			for(int c = 0; c < cache[0].length; c++){
				cache[r][c] = -1;
			}
		}

		if(targetSumMemoization(A, 0, 0, target, cache) == true)
			System.out.println("YES");
		else
			System.out.println("NO");				
		
		System.out.println(targetSumTabulation(A, target));
	}
}