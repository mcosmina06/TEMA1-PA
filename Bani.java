import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bani {
	static class Task {
		public static final String INPUT_FILE = "bani.in";
		public static final String OUTPUT_FILE = "bani.out"; 
		private static final int MOD = 1000000007;
		int n;
		int tip;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				tip = sc.nextInt();
				n = sc.nextInt();
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(long result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		private long getResult() {
			long sum = 0;
			long [][] dp = new long[5][n];
			dp[0][0] = 1; //10 
			dp[1][0] = 1; //50
			dp[2][0] = 1; //100
			dp[3][0] = 1; //200
			dp[4][0] = 1; //500
			for (int i = 1; i < n; i++) {
				dp[0][i] = (dp[1][i - 1] + dp[2][i - 1] + dp[4][i - 1]) % MOD;
				dp[1][i] = (dp[0][i - 1] + dp[3][i - 1]) % MOD;
				if (tip == 1) {
					dp[2][i] = (dp[0][i - 1] + dp[2][i - 1]) % MOD;
				} else {
					dp[2][i] = (dp[0][i - 1] + dp[2][i - 1] + dp[3][i - 1]) % MOD;				
				}
				dp[3][i] = (dp[1][i - 1] + dp[4][i - 1]) % MOD;
				dp[4][i] = dp[3][i - 1];
			}

			for (int i = 0; i < 5; i++) {
				sum = (sum + dp[i][n - 1]) % MOD;
			}  

			return sum;
		}
		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}
	public static void main(String[] args) {
		new Task().solve();
	}
}