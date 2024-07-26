import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n, m;
	static boolean[][] matrix;
	static boolean[][] visited;
	static byte[][] dp;
	static int[][] region;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		input();
		solve();
	}
	
	public static void solve() {
		int r = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if(!matrix[i][j] && !visited[i][j]) {
					bfs(i, j, r);
					r++;
				}
			}
		}

		Set<Integer> regionSet = new HashSet<>();
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (matrix[i][j]) {
					int count = 0;
					for (int k=0; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (canMove(nx, ny) && !matrix[nx][ny] && !regionSet.contains(region[nx][ny])) {
							count += dp[nx][ny];
							regionSet.add(region[nx][ny]);
						}
					}
					count++;
					dp[i][j] = (byte) (count%10);
					regionSet.clear();
				}
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (matrix[i][j]) sb.append(dp[i][j]);
				else sb.append(0);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}

	public static void bfs(int x, int y, int r) {
		List<Node> blocks = new ArrayList<>();
		Queue<Node> q = new ArrayDeque<>();
		Node start = new Node(x, y);
		q.add(start);
		int count = 1;
		blocks.add(start);
		visited[x][y] = true;
		region[x][y] = r;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (canMove(nx, ny) && !matrix[nx][ny] && !visited[nx][ny]) {
					visited[nx][ny] = true;
					count++;
					Node newNode = new Node(nx, ny);
					blocks.add(newNode);
					q.add(newNode);
				}
			}
		}
		
		for(Node block : blocks) {
			dp[block.x][block.y] = (byte) (count % 10);
			region[block.x][block.y] = r;
		}
	}
	
	public static boolean canMove(int nx, int ny) {
		return 0 <= nx && nx < n && 0<=ny && ny < m;
	}
 	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		matrix = new boolean[n][m];
		visited = new boolean[n][m];
		region = new int[n][m];
		dp = new byte[n][m];
		for (int i=0; i<n; i++) {
			String s = br.readLine();
			for (int j=0; j<m; j++) {
				matrix[i][j] = s.charAt(j) == '1';
			}
		}
	}
}

