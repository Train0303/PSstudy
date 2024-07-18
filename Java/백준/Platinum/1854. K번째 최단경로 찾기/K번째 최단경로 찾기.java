import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int e;
		int cost;
		public Node(int e, int cost) {
			this.e = e;
			this.cost = cost;
		}
	}
	
	static int n, m, k;
	static List<List<Node>> graph = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		input();
		solve();
	}
	
	public static void solve() {
		
		List<PriorityQueue<Integer>> distances = new ArrayList<>();
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing((int[] a) -> a[0]));
		for (int i=0; i<=n; i++)
			distances.add(new PriorityQueue<>(Comparator.comparing( a -> -a)));
		
		pq.add(new int[] {0, 1});
		distances.get(1).add(0);
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			
			for (Node adjNode : graph.get(cur[1])) {
				int weight = adjNode.cost + cur[0];
				if ( distances.get(adjNode.e).size() < k) {
					pq.add(new int[] {weight, adjNode.e});
					distances.get(adjNode.e).add(weight);
				} else if(distances.get(adjNode.e).peek() > weight) {
					pq.add(new int[] {weight, adjNode.e});
					distances.get(adjNode.e).poll();
					distances.get(adjNode.e).add(weight);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=n; i++) {
			if (distances.get(i).size() < k) {
				sb.append(-1).append('\n');
			} else {
				sb.append(distances.get(i).peek()).append('\n');
			}
		}
		System.out.print(sb);
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<=n; i++) 
			graph.add(new ArrayList<>());
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Node(e, t));
		}
	}
}
