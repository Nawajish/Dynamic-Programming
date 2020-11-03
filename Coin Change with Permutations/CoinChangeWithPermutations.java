// 1. You are given a number n, representing the count of coins.
// 2. You are given n numbers, representing the denominations of n coins.
// 3. You are given a number "amt".
// 4. You are required to calculate and print the number of permutations of the n coins using which the 
//      amount "amt" can be paid.

// Note1 -> You have an infinite supply of each coin denomination i.e. same coin denomination can be 
//                   used for many installments in payment of "amt"
// Note2 -> You are required to find the count of permutations and not combinations i.e.
//                   2 + 2 + 3 = 7 and 2 + 3 + 2 = 7 and 3 + 2 + 2 = 7 are different permutations of same 
//                   combination. You should treat them as 3 and not 1.
// Input Format
// A number n
// n1
// n2
// .. n number of elements
// A number amt
// Output Format
// A number representing the count of combinations of coins which can be used to pay the amount "amt"
// Constraints
// 1 <= n <= 20
// 0 <= n1, n2, .. n elements <= 20
// 0 <= amt <= 30
// Sample Input
// 4
// 2
// 3
// 5
// 6
// 7
// Sample Output
// 5

import java.util.Scanner;
import java.util.Arrays;

public class CoinChangeWithPermutations{

	public static int coinChangeWithPermutations(int[] coins, int amount){
		if(amount == 0)
			return 1;

		if(amount < 0)
			return 0;

		int count = 0;

		for(int i = 0; i < coins.length; i++)
			count += coinChangeWithPermutations(coins, amount - coins[i]);	

		return count;
	}

	public static int coinChangeWithPermutationsMemoization(int[] coins, int amount, int[] cache){
		if(amount == 0)
			return 1;

		if(amount < 0)
			return 0;

		if(cache[amount] != 0)
			return cache[amount];

		int retVal = 0;

		for(int i = 0; i < coins.length; i++)
			retVal += coinChangeWithPermutationsMemoization(coins, amount - coins[i], cache);		

		cache[amount] = retVal;

		return retVal;
	}

	public static int coinChangeWithPermutationsTabulation(int[] coins, int finalAmt){
		int[] permutations = new int[finalAmt + 1];
		permutations[0] = 1;

		for(int amt = 1; amt <= finalAmt; amt++){
			for(int coin : coins){
				if(amt >= coin){
					permutations[amt] += permutations[amt - coin];
				}
			}
		}

		return permutations[finalAmt];
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
		cache[0] = 1;

		System.out.println(coinChangeWithPermutations(A, target));
		System.out.println(coinChangeWithPermutationsMemoization(A, target, cache));
		System.out.println(coinChangeWithPermutationsTabulation(A, target));
	}	
}