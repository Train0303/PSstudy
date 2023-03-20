import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[][] matrix = new long[m+1][n+1];
        for(int i=0; i<n+1; i++) matrix[1][i] = 1L;
        for(int i=0; i<m+1; i++) matrix[i][0] = 1L;

        for(int i=1; i<m+1; i++){
            for(int j=1; j<n+1; j++){
                matrix[i][j] = (matrix[i-1][j] + matrix[i][j-1]) % 1000000000L;
            }
        }

        System.out.println(matrix[m][n]);
    }
}
