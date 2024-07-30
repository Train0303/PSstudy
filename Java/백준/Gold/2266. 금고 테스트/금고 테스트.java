import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, k;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		input();
		solve();
	}
	
	public static void solve() {
		for (int i=1; i<=n; i++) dp[i][1] = i;
		for (int i=1; i<=n; i++) {
			for (int j = 2; j<=k; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int m =1; m <= i; m++) {
					dp[i][j] = Math.min(dp[i][j], Math.max(dp[m-1][j-1], dp[i-m][j])+1);
				}
			}
		}
		System.out.println(dp[n][k]);
	}
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new int[n+1][k+1];
	}
}

