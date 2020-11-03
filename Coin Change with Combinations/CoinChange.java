// 1. You are given a number n, representing the count of coins.
// 2. You are given n numbers, representing the denominations of n coins.
// 3. You are given a number "amt".
// 4. You are required to calculate and print the number of combinations of the n coins using which the 
//    amount "amt" can be paid.

// Note : 
// 1. You have an infinite supply of each coin denomination i.e. same coin denomination can be 
//    used for many installments in payment of "amt"
// 2. You are required to find the count of combinations and not permutations 
//    i.e. 2 + 2 + 3 = 7 and 2 + 3 + 2 = 7 and 3 + 2 + 2 = 7 are different permutations of same 
//    combination. You should treat them as 1 and not 3.

// Input Format
// A number n
// n1
// n2
// .. n number of elements
// A number amt

// Output Format
// A number representing the count of combinations of coins which can be used to pay the amount "amt"

// Constraints
// 1 <= n <= 30
// 0 <= n1, n2, .. n elements <= 20
// 0 <= amt <= 50

// Sample Input
// 4
// 2
// 3
// 5
// 6
// 7

// Sample Output
// 2

import java.util.Scanner;
import java.util.Arrays;

public class CoinChange{

	public static int coinChangeWithCombinations(int[] A, int n, int amount){
		if(n == 0)
			return 0;
		if(amount == 0)
			return 1;
		if(A[n - 1] > amount)
			return coinChangeWithCombinations(A, n - 1, amount);

		return coinChangeWithCombinations(A, n - 1, amount) + coinChangeWithCombinations(A, n, amount - A[n - 1]);
	}

	public static int coinChangeWithCombinationsMemoization(int[] A, int n, int amount, int[] cache){
		if(n == 0)
			return 0;

		if(amount == 0)
			return 1;

		if(cache[amount] != 0)
			return cache[amount];

		int retVal = 0;

		if(A[n - 1] > amount)
			retVal = coinChangeWithCombinationsMemoization(A, n - 1, amount, cache);			
		else
			retVal = coinChangeWithCombinationsMemoization(A, n - 1, amount, cache) + coinChangeWithCombinationsMemoization(A, n, amount - A[n - 1], cache);
			
		cache[amount] = retVal;

		return retVal;
	}

	// public static int coinChangeWithCombinationsTabulation(int[] coins, int amount){
	// 	int[] dp = new int[amount + 1];
	// 	dp[0] = 1;

	// 	for(int i = 0; i < coins.length; i++){
	// 		for(int j = coins[i]; j < dp.length; j++)
	// 			dp[j] += dp[j - coins[i]];
	// 	}		

	// 	return dp[amount];
	// }	

	// Cleaner Code
	public static int coinChangeWithCombinationsTabulation(int[] coins, int finalAmt){
		int[] combinations = new int[finalAmt + 1];
		combinations[0] = 1;

		for(int coin : coins){
			// for each coin, consider amounts >= coin
			for(int amt = coin; amt <= finalAmt; amt++){
				combinations[amt] += combinations[amt - coin];
			}	
		}

		return combinations[finalAmt];
	}


	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter total coins: ");
		int totalCoins = sc.nextInt();


		int[] A = new int[totalCoins];	

		System.out.println("Enter values for " + totalCoins + " coins: ");
		for(int i = 0; i < totalCoins; i++){
			A[i] = sc.nextInt();
		}

		System.out.println("Enter the target sum: ");
		int target = sc.nextInt();

		int[] cache = new int[target + 1];
		Arrays.fill(cache, 0);

		Arrays.fill(cache, 0);
		cache[0] = 1;

		System.out.println(coinChangeWithCombinations(A, A.length, target));
		System.out.println(coinChangeWithCombinationsMemoization(A, A.length, target, cache));
		System.out.println(coinChangeWithCombinationsTabulation(A, target));
	}	
}