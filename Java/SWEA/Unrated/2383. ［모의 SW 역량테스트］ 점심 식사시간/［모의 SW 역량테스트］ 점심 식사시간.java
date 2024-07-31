import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution
{
	static int[][] matrix = new int[10][10];
	static int[][] people = new int[10][2];
	static int[][] stairs = new int[2][2];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=1; t<=tc; t++) {
			int n = Integer.parseInt(br.readLine());
			for (int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<n; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append(String.format("#%d %d\n", t, solve(n)));
		}
		System.out.print(sb);
	}
	
	
	public static int solve(int n) {
		List<List<Integer>> wait = new ArrayList<>();
		for (int i=0; i<2; i++) 
			wait.add(new ArrayList<>());
		
		int pCount = -1;
		int sCount = -1;
		int answer = Integer.MAX_VALUE;
		int[] times = new int[2];
		// 사람, 계단 구분
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (matrix[i][j] == 1) {
					pCount++;
					people[pCount][0] = i;
					people[pCount][1] = j;
					
				} else if(matrix[i][j] >= 2) {
					sCount++;
					stairs[sCount][0] = i;
					stairs[sCount][1] = j;
				}
			}
		}
		
		
		// 나올 수 있는 모든 경우
		for (int i=0; i< 1<<(pCount+1); i++) {
			
			// 계단 별 사람 인원 배치
			for (int j=0; j<pCount+1; j++) {
				int kind = (1<<j & i) == 1<<j ? 1 : 0;
				wait.get(kind).add(getDistance(j, kind));
			}
			
			// 빨리 도착하는 순 구하기 위한 정렬 
			wait.get(0).sort((o1, o2) -> o1.compareTo(o2));
			wait.get(1).sort((o1, o2) -> o1.compareTo(o2));
			// 각 계단에서 걸리는 시간 측정
			// 0 0 0 1 1 1 1
			for (int j=0; j<2; j++) {
				int time = 0;
				int waiting = matrix[stairs[j][0]][stairs[j][1]];
				Queue<Integer> q = new ArrayDeque<>();
				for (int dist : wait.get(j)) {
					if(q.isEmpty()) {
						time = dist;
						q.add(dist + waiting);
						continue;
					}
					
					if (q.size() == 3) {
						int t = q.poll();
						time = dist > t ? dist : t;
						q.add(time + waiting);
					} else {
						q.add(dist + waiting);
					}
				}
				
				while (!q.isEmpty() ) {
					time = q.poll() + 1;
				}
				times[j] = time;
			}
			
			answer = Math.min(answer, Math.max(times[0], times[1]));
			Arrays.fill(times, 0);
			wait.get(0).clear();
			wait.get(1).clear();
		}
		
		return answer;
	}
	
	public static int getDistance(int person, int kind) {
		return Math.abs(people[person][0] - stairs[kind][0]) + Math.abs(people[person][1] - stairs[kind][1]); 
	}
}