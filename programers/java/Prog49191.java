import java.util.*;

public class prog_49191 {

    public static int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] graph = new boolean[n][n];
        for (int[] result: results){
            graph[result[0]-1][result[1]-1] = true;
        }

        for (int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(graph[i][k] && graph[k][j]){
                        graph[i][j] = true;
                    }
                }
            }
        }

        for (int i=0; i<n; i++){
            int count = 0;
            for (int j=0; j<n; j++){
                if(graph[i][j]) count++;
                if(graph[j][i]) count++;
            }
            if(count == n-1) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] results = {
                {4,3},
                {4,2},
                {3,2},
                {1,2},
                {2,5}
        };
        System.out.println(solution(n,results));
    }
}
