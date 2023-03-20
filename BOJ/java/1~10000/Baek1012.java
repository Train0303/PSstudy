import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1012 {
    static int[][] matrix;
    static int M, N;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void dfs(int x, int y){
        for(int i=0; i<4; i++){
            int new_x = x+dx[i];
            int new_y = y+dy[i];
            if (0<=new_x && new_x<M && 0<=new_y && new_y<N){
                if(matrix[new_x][new_y] == 1) {
                    matrix[new_x][new_y] = 0;
                    dfs(new_x, new_y);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bf.readLine());
        for(int t=0; t<testCase; t++) {
            int answer = 0;
            StringTokenizer st = new StringTokenizer(bf.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            matrix = new int[M][N];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                matrix[x][y] = 1;
            }

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][j] == 1) {
                        matrix[i][j] = 0;
                        dfs(i, j);
                        answer++;
                    }
                }
            }
            System.out.println(answer);
        }
    }
}
