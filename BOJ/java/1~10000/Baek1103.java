import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1103 {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int n;
    static int m;
    static char[][] matrix;
    static int[][] DP;
    static boolean[][] visited;

    public static int dfs(int x, int y){
        if(x<0 || x>=n || y<0 || y>=m || matrix[x][y]=='H') return 0;

        if(visited[x][y]){
            System.out.println(-1);
            System.exit(0);
        }

        if(DP[x][y] != -1) return DP[x][y];

        for(int i=0; i<4; i++){
            int newX = x + dx[i] * (matrix[x][y]-'0');
            int newY = y + dy[i] * (matrix[x][y]-'0');
            visited[x][y] = true;
            DP[x][y] = Math.max(DP[x][y], dfs(newX, newY)+1);
        }
        visited[x][y] = false;
        return DP[x][y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sc = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(sc.nextToken());
        m = Integer.parseInt(sc.nextToken());
        matrix = new char[n][m];
        visited = new boolean[n][m];
        DP = new int[n][m];

        for(int i=0; i<n; i++){
            String data = bf.readLine();
            for(int j=0; j<m; j++){
                matrix[i][j] = data.charAt(j);
                DP[i][j] = -1;
            }
        }

        System.out.println(dfs(0,0));
    }
}
