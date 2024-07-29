import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] weights;
	public static void main(String[] args) throws IOException{
		input();
		solve();
	}
	
	public static void solve() {
		Arrays.sort(weights);
		if (weights[0] != 1) {
			System.out.println(1);
		} else {
			int sum = weights[0];
			for (int i=1; i<n; i++) {
				if (weights[i] > sum+1) {
					System.out.println(sum+1);
					return;
				}
				sum += weights[i];
			}
			
			System.out.println(sum+1);
		}

	}
	
	public static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		weights = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		for(int i=0; i<n; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
	
	}

}
