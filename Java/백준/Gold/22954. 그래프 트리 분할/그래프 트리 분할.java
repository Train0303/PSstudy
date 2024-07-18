import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		int e;
		int idx;
		public Edge (int e, int idx) {
			this.e = e;
			this.idx = idx;
		}
	
	}
	static int n, m;
	static boolean[] visited;
	static List<List<Edge>> graph = new ArrayList<>();
	static List<List<Edge>> edgeGroup = new ArrayList<>();
	static List<List<Integer>> nodeGroup = new ArrayList<>();
	static boolean[] leafNodes;
	public static void main(String[] args) throws IOException{
		input();
		solve();
	}
	public static void solve() {
		if(n<=2) {
			System.out.println(-1);
			return;
		}
		
		for (int i=1; i<=n; i++) {
			if ( !visited[i] ) {
				nodeGroup.add(new ArrayList<>());
				edgeGroup.add(new ArrayList<>());
				dfs(i);
			}
		}
		
		if (edgeGroup.size() > 2) {
			System.out.println(-1);
			return ;
		} else if (edgeGroup.size() == 1) {
			int remove = -1;
			for (int node : nodeGroup.get(0)) {
				if (leafNodes[node]) 
					remove = node;
			}
			
			Arrays.fill(visited, false);
			nodeGroup.clear();
			edgeGroup.clear();
			visited[remove] = true;
			for (int i=1; i<=n; i++) {
				if ( !visited[i] ) {
					nodeGroup.add(new ArrayList<>());
					edgeGroup.add(new ArrayList<>());
					dfs(i);
				}
			}
			nodeGroup.add(List.of(remove));
			edgeGroup.add(List.of());
		}
		if (edgeGroup.get(0).size() == edgeGroup.get(1).size()) {
			System.out.println(-1);
		} else {
			System.out.println(getAnswer());
		}
	}

	public static void dfs(int curNode) {
		boolean isLeaf = true;
		visited[curNode] = true;
		nodeGroup.get(nodeGroup.size()-1).add(curNode);
		for (Edge edge : graph.get(curNode)) {
			if (!visited[edge.e]) {
				edgeGroup.get(edgeGroup.size()-1).add(edge);
				dfs(edge.e);
				isLeaf = false;
			}
		}
		
		if (isLeaf) leafNodes[curNode] = true;
	}
	
	public static String getAnswer() {
		StringBuilder sb = new StringBuilder();
		sb.append(nodeGroup.get(0).size()).append(" ");
		sb.append(nodeGroup.get(1).size()).append(" ");
		sb.append('\n');
		
		for (int node:nodeGroup.get(0)) {
			sb.append(node).append(" ");
		}
		sb.append('\n');
		for (Edge edge: edgeGroup.get(0)) {
			sb.append(edge.idx).append(" ");
		}
		sb.append('\n');
		
		for (int node:nodeGroup.get(1)) {
			sb.append(node).append(" ");
		}
		sb.append('\n');
		for (Edge edge: edgeGroup.get(1)) {
			sb.append(edge.idx).append(" ");
		}
		sb.append('\n');
		
		return sb.toString();
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		leafNodes = new boolean[n+1]; 
		for(int i=0; i<=n+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Edge(e, i));
			graph.get(e).add(new Edge(s, i));
		}
	}
	

}