
public class FibonacciNumber{
	
	public static int fibNaive(int n){
		if(n == 0 || n == 1){
			return n;
		}
		return fibNaive(n-1) + fibNaive(n-2);
	}

	public static int fibMemoization(int n, int[] cache){		
		if(cache[n] != -1){
			return cache[n];
		}
		int retVal = fibMemoization(n-1, cache) + fibMemoization(n-2, cache);
		cache[n] = retVal;
		return retVal;
	}

	public static int fibTabulation(int n){		
		int[] table = new int[n+1];
		table[0] = 0;
		table[1] = 1;
		for(int i = 2; i <= n; i++){
			table[i] = table[i-1] + table[i-2];
		}		
		return table[n];	
	}

	public static int fibMostOptimized(int n){
		int t1 = 0, t2 = 1, result = -1;
		for(int i = 2; i <= n; i++){
			result = t1 + t2;
			t1 = t2;
			t2 = result;
		}
		return result;
	}

	public static void main(String args[]){		

		int[] cache = new int[n + 1];
		Arrays.fill(cache, -1);
		cache[0] = 0;
		cache[1] = 1;

		System.out.println(fibRecursive(n));
		System.out.println(fibMemoization(n, cache));
		System.out.println(fibTabulation(n));
		System.out.println(fibMostOptimized(n));
	}
}