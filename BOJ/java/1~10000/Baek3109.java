import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek3109 {
    static int[] dx = {-1,0,1};
    static int R;
    static int C;
    static char[][] matrix;
    static int answer = 0;
    public static char dfs(int x, int y){
        if(y == C-1){
            answer++;
            return '@';
        }

        for (int i=0; i<3; i++){
            int newX = x + dx[i];
            int newY = y + 1;
            if(newX>=0 && newX < R && newY<C){
                if(matrix[newX][newY] != 'x' && matrix[newX][newY] != '@'){
                    matrix[newX][newY] = dfs(newX, newY);
                    if (matrix[newX][newY] == '@') return '@';
                }
            }
        }
        return 'x';
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        matrix = new char[R][C];
        for (int i=0; i<R; i++){
            String line = bf.readLine();
            for (int j=0; j<C; j++){
                matrix[i][j] = line.charAt(j);
            }
        }

        for(int i=0; i<R; i++){
            dfs(i,0);
        }
        System.out.println(answer);
    }
}