import java.util.Arrays;
import java.util.Scanner;

public class MaximizeRobbery {
	// There are n houses build in a line, each of which contains some value in it. 
	// A thief is going to steal the maximal value of these houses, 
	// but he can’t steal in two adjacent houses because the owner of the stolen houses 
	// will tell his two neighbours left and right side. What is the maximum stolen value?

	// Examples:

	// Input: 
	// n = number_of_houses = 7
	// house_val[] = {6, 7, 1, 3, 8, 2, 4}

	// Output: 19

	// Explanation: The thief will steal 6, 1, 8 and 4 from the house.

	// Input: 
	// n = number_of_houses = 5
	// house_val[] = {5, 3, 4, 11, 2}

	// Output: 16

	// Explanation: Thief will steal 5 and 11

	public static int maximizeRobberyProfitNaive(int start, int[] houses){
		if(start >= houses.length){
			return 0;
		}

		return Math.max(houses[start] + maximizeRobberyProfitNaive(start + 2, houses), maximizeRobberyProfitNaive(start + 1, houses));
	}

	public static int maximizeRobberyProfitMemoization(int start, int[] houses, int[] cache){
		// If you have robbed all houses, you can't make any more profit
		if(start >= houses.length){
			return 0;
		}

		// Check cache to see if house has been robbed before
		if(cache[start] != -1){
			return cache[start];
		}

		// Choose to ROB or NOT ROB current house, and
		// Save the maximum profit earned
		int profit = Math.max(houses[start] + maximizeRobberyProfitMemoization(start + 2, houses, cache), maximizeRobberyProfitMemoization(start + 1, houses, cache));

		// Store in cache
		cache[start] = profit;		

		// Return profit
		return profit;
	}

	public static int maximizeRobberyProfitTabulation(int[] houses){
		int numberOfHouses = houses.length;

		// Since each call looks forward to 2 houses following current house
		int[] dp = new int[numberOfHouses + 2];
		dp[numberOfHouses + 1] = 0;
		dp[numberOfHouses] = 0;

		for(int currentHouse = numberOfHouses - 1; currentHouse >= 0; currentHouse--){
			dp[currentHouse] = Math.max(houses[currentHouse] + dp[currentHouse + 2], dp[currentHouse + 1]);
		}		

		return dp[0];

	}

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of houses: ");
		int n  = sc.nextInt();						

		int[] houses = new int[n];

		System.out.println("Enter value of each of " + n + " houses: ");
		for(int i = 0; i < n; i++){
			houses[i] = sc.nextInt();
		}

		int[] cache = new int[n + 1];

		Arrays.fill(cache, -1);

		System.out.println(maximizeRobberyProfitNaive(0, houses));

		System.out.println(maximizeRobberyProfitMemoization(0, houses, cache));

		System.out.println(maximizeRobberyProfitTabulation(houses));
	}

}