import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] matrix;
	static int[][] copyMatrix;
	static int answer;
	public static void main(String[] args) throws IOException{
		input();
		solve();
	}
	public static void solve() {
		dfs(0, new ArrayList<Integer>());
		System.out.println(answer);
	}
	
	public static void dfs(int cnt, List<Integer> moveList) {
		if (cnt == 5) {
			move(moveList, copyMatrix);

			answer = Math.max(answer, 
					Arrays.stream(copyMatrix)
						.flatMapToInt( (int[] m) -> Arrays.stream(m) )
						.max().orElse(0));
			
			// 되돌리기
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					copyMatrix[i][j] = matrix[i][j];
				}
			}
			return ;
		}
		
		for (int i=0; i<4; i++) {
			// 좌우상하 움직임
			moveList.add(i);
			dfs(cnt+1, moveList);
			moveList.remove(cnt);
		}
	}
	
	
	public static void move(List<Integer> moveList, int[][] gameBoard) {
		
		for(int m : moveList) {
			switch(m) {
			case 0:
				for (int i=0; i<n; i++) {
					int block = 0;
					boolean flag = true;
					for (int j=0; j<n; j++) {
						if (gameBoard[i][j] != 0 ) {
							if(flag && block != 0 && gameBoard[i][block-1] == gameBoard[i][j]){
								gameBoard[i][block-1] *= 2;
								gameBoard[i][j] = 0;
								flag = false;
							} else {
								int temp = gameBoard[i][j];
								gameBoard[i][j] = 0;
								gameBoard[i][block] = temp;
								block++;
								flag = true;
							}	
						}
					}
				}
				break;
			case 1:
				for (int i=0; i<n; i++) {
					int block = n-1;
					boolean flag = true;
					for (int j=n-1; j>=0; j--) {
						if (gameBoard[i][j] != 0 ) {
							if(flag && block != n-1 && gameBoard[i][block+1] == gameBoard[i][j]){
								gameBoard[i][block+1] *= 2;
								gameBoard[i][j] = 0;
								flag = false;
							}
							else {
								int temp = gameBoard[i][j];
								gameBoard[i][j] = 0;
								gameBoard[i][block] = temp;
								block--;
								flag = true;
							}
						}
					}
				}
				
				break;
			case 2:
				for (int i=0; i<n; i++) {
					int block = 0;
					boolean flag = true;
					for (int j=0; j<n; j++) {
						if (gameBoard[j][i] != 0 ) {
							if(flag && block != 0 && gameBoard[block-1][i] == gameBoard[j][i]){
								gameBoard[block-1][i] *= 2;
								gameBoard[j][i] = 0;
								flag = false;
							} else {
								int temp = gameBoard[j][i];
								gameBoard[j][i] = 0;
								gameBoard[block][i] = temp;
								block++;
								flag = true;
							}
						}
					}
				}
				break;
			case 3:
				for (int i=0; i<n; i++) {
					int block = n-1;
					boolean flag = true;
					for (int j=n-1; j>=0; j--) {
						if (gameBoard[j][i] != 0 ) {
							if(flag && block != n-1 && gameBoard[block+1][i] == gameBoard[j][i]){
								gameBoard[block+1][i] *= 2;
								gameBoard[j][i] = 0;
								flag = false;
							} else{
								int temp = gameBoard[j][i];
								gameBoard[j][i] = 0;
								gameBoard[block][i] = temp;
								block--;
								flag = true;
							}
						}
					}
				}
				break;
			}
		}
		
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		matrix = new int[n][n];
		copyMatrix = new int[n][n];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				copyMatrix[i][j] = matrix[i][j];
			}
		}
	}

}
