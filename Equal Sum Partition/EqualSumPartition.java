// Partition problem is to determine whether a given set can be partitioned into two subsets such that the sum of elements in both subsets is the same. 

// Examples: 

// arr[] = {1, 5, 11, 5}
// Output: true 
// The array can be partitioned as {1, 5, 5} and {11}

// arr[] = {1, 5, 3}
// Output: false 
// The array cannot be partitioned into equal sum sets.

// The solution consists of 2 major steps:
// 1) Calculate sum of the array. If sum is odd, there can not be two subsets with equal sum, so return false 
//    because an odd number can't be split into two equal halves. 

// 2) If sum of array elements is even, calculate sum/2 (the other halve) and find if a subset of given array sums to sum/2 
//    which is essentially the 'Target Sum' problem. 

public class EqualSumPartition{

	public static boolean targetSumTabulation(int[] A, int target){
		int n = A.length;
		boolean[][] dp = new boolean[2][target + 1];		

		for(int r = 0; r <= n; r++){
			for(int c = 0; c <= target; c++){				
				if(c == 0){
					dp[r%2][c] = true;
				}
				else if(r == 0){
					dp[r%2][c] = false;
				}
				else if(A[r - 1] <= c)
					dp[r%2][c] = dp[(r - 1)%2][c - A[r - 1]] || dp[(r - 1)%2][c];
				else
					dp[r%2][c] = dp[(r - 1)%2][c];
			}
		}

		return dp[n%2][target];
	}

	public static boolean equalSumSubsetsNaive(int[] A){
		int sum = 0;
		for(int val : A){
			sum += val;
		}

		if(sum % 2 != 0)
			return false;

		return targetSumTabulation(A, sum/2);
	}

	public static void main(String args[]){
		int[] A = {1, 5, 11, 5};
		System.out.println(equalSumSubsetsNaive(A));

		A = new int[]{1, 5, 5};
		System.out.println(equalSumSubsetsNaive(A));

		A = new int[]{10, 5};
		System.out.println(equalSumSubsetsNaive(A));

		A = new int[]{1};
		System.out.println(equalSumSubsetsNaive(A));

		A = new int[]{};
		System.out.println(equalSumSubsetsNaive(A));
	}

}