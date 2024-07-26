import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[] parent;
	static long[] weight;
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean flag;
		do {
			flag = false;
			input(br);
			if (n != 0 && m != 0) {
				flag = true;
			}
			sb.append(solve(br));
		} while (flag);
		System.out.print(sb);
	}

	public static StringBuilder solve(BufferedReader br) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String query = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (query.charAt(0) == '!') {
				int w = Integer.parseInt(st.nextToken());
				union(a, b, w);
			} else {
				if (find(a) == find(b)) {
					sb.append(weight[b] - weight[a]).append('\n');
				} else {
					sb.append("UNKNOWN").append('\n');
				}
			}
		}
		return sb;
	}

	public static int find(int a) {
		if ( a == parent[a]) {
			return parent[a];
		}
		int result = find(parent[a]);
		weight[a] += weight[parent[a]];
		return parent[a] = result;
	}

	public static void union(int a, int b, int w) {
		int aParent = find(a);
		int bParent = find(b);
		if (aParent != bParent) {
			parent[bParent] = aParent;
			weight[bParent] += weight[a] + w - weight[b];
		}
	}

	public static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n+1];
		weight = new long[n+1];
		for (int i=1; i<=n; i++) {
			parent[i] = i;
			weight[i] = 0;
		}
	}
}
