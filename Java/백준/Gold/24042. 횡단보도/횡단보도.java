import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Traffic {
		int s;
		long cost;
		public Traffic(int s, long cost) {
			this.s = s;
			this.cost = cost;
		}
	}
	static int n, m;
	static List<List<Traffic>> graph = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		 input();
		 solve();
	}
	public static void solve() {
		long[] distance = new long[n+1];
		Arrays.fill(distance, Long.MAX_VALUE);
		PriorityQueue<Traffic> pq = new PriorityQueue<>(Comparator.comparing((Traffic a) -> a.cost));
		
		pq.add(new Traffic(1, 0));
		distance[1] = 0;
		while (!pq.isEmpty()) {
			Traffic cur = pq.poll();
			
			if (cur.cost > distance[cur.s]) continue;

			for (Traffic adj : graph.get(cur.s)) {	
                long tmp = cur.cost / m;
                
                if (cur.cost % m > adj.cost) 
                	tmp++;
                long weight = adj.cost+m*tmp+1L;
				if (weight < distance[adj.s]) {
					distance[adj.s] = weight;
					pq.add(new Traffic(adj.s, weight));
				}
			}
		}
		System.out.println(distance[n]);
	}
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for (int i=0; i<=n; i++) 
			graph.add(new ArrayList<>());
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Traffic(e, i));
			graph.get(e).add(new Traffic(s, i));
		}
	}
}
