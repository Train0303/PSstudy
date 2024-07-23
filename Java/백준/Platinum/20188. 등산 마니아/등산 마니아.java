import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static List<List<Integer>> graph = new ArrayList<>();
	static long result = 0;
	public static void main(String[] args) throws IOException {
		input();
		solve();
	}
	
	public static void solve() {
		dfs(1, 1);
		System.out.println(result);
	}
	public static int dfs(int v, int prev) {
		
		int childNodes = 1;
		for (int adj : graph.get(v)) {
			if (adj != prev) {
				childNodes += dfs(adj, v);
			}
		}
		if (v != 1) {
			result += getCount(n) - getCount(n-childNodes);
		} 
		return childNodes;
	}
	public static long getCount(int node) {
		return (long)node*(long)(node-1)/2;	
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for (int i=0; i<=n; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i=0; i<n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
	}
}
