import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Baek1412 {
    public static void main(String[] args) throws IOException {
        Queue<Integer> q = new LinkedList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        char[][] matrix = new char[n][n];
        int[] degree = new int[n];
        for (int i=0; i<n; i++){
            String input = bf.readLine();
            for (int j=0; j<n; j++){
                matrix[i][j] = input.charAt(j);
                if(matrix[i][j] == 'Y') {
                    degree[j]++;
                }

                if(matrix[i][j] == matrix[j][i] && matrix[i][j] == 'Y'){
                    matrix[i][j] = matrix[j][i] = 'N';
                    degree[i]--;
                    degree[j]--;
                }
            }
        }

        for (int i=0; i<n; i++){
            if(degree[i] == 0) q.add(i);
        }

        int count = 0;
        while(!q.isEmpty()){
            int node = q.peek();
            q.poll();
            count++;
            for (int i=0; i<n; i++){
                if(matrix[node][i] == 'Y') {
                    degree[i]--;
                    if(degree[i] == 0) q.add(i);
                }
            }
        }
        if (count==n) System.out.println("YES");
        else System.out.println("NO");
    }
}
