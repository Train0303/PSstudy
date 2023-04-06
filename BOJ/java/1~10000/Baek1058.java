import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek1058 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int maxCount = 0;
        int n = Integer.parseInt(bf.readLine());
        char[][] matrix = new char[n][n];
        for (int i=0; i<n; i++){
            String line = bf.readLine();
            for(int j=0; j<n; j++)
                matrix[i][j] = line.charAt(j);
        }
        for(int i=0; i<n; i++){
            int count = 0;
            for(int j=0; j<n; j++){
                boolean flag = false;
                if(i != j) {
                    for (int k = 0; k < n; k++) {
                        if (matrix[i][j] == 'Y' || (matrix[i][k] == 'Y' && matrix[k][j] == 'Y')) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) count++;
            }
            maxCount = Math.max(maxCount, count);
        }
        System.out.println(maxCount);
    }
}
