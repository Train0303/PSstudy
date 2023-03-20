import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1520 {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[][] matrix;
    static int[][] DP;
    static int n;
    static int m;
    public static int dfs(int x, int y){
        if(x==n-1 && y==m-1) return 1;
        if(DP[x][y] != -1) return DP[x][y];

        DP[x][y] = 0;
        for(int i=0; i<4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(newX>=0 && newX<n && newY>=0 && newY<m && matrix[newX][newY] < matrix[x][y]){
                DP[x][y] += dfs(newX, newY);
            }
        }
        return DP[x][y] ;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        DP = new int[n][m];


        for(int i=0; i<n; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                DP[i][j] = -1;
            }
        }
        dfs(0,0);
        System.out.println(DP[0][0]);
    }
}
