import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x;
		int y;
		int k;
		int t;
		public Node(int x, int y, int k, int t) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.t = t;
		}
	}
	static int n, m, k;
	static boolean[][][] visited;
	static boolean[][] matrix;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		input();
		solve();
	}
	public static void solve() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0,0,k,1));
		visited[0][0][k] = true;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.x == n-1 && cur.y == m-1) {
				System.out.println(cur.t);
				return;
			}
			
			for (int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(canMove(nx, ny)) {
					if (matrix[nx][ny] && cur.k != 0 && !visited[nx][ny][cur.k-1]) {
						q.add(new Node(nx, ny, cur.k-1, cur.t+1));
						visited[nx][ny][cur.k-1] = true;
					} else if(!matrix[nx][ny] && !visited[nx][ny][cur.k]) {
						q.add(new Node(nx, ny, cur.k, cur.t+1));
						visited[nx][ny][cur.k] = true;
					}
				}
			}
		}
		System.out.println(-1);
	}
	public static boolean canMove(int nx, int ny) {
		return 0<=nx && nx < n && 0<=ny && ny < m;
	}
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		matrix = new boolean[n][m];
		visited = new boolean[n][m][k+1];
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for (int j=0; j<m; j++) {
				matrix[i][j] = s.charAt(j) == '1' ? true : false;
			}
		}
	}
}
