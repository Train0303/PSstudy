import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek9466 {
    static int[] graph;
    static int[] degree;

    public static void main(String[] args) throws IOException {
        Queue<Integer> q = new ArrayDeque<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testcase = Integer.parseInt(bf.readLine());
        for (int t=0; t<testcase; t++){
            int n = Integer.parseInt(bf.readLine());
            int answer = 0;
            graph = new int[n+1];
            degree = new int[n+1];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i=1; i<n+1; i++){
                graph[i] = Integer.parseInt(st.nextToken());
                degree[graph[i]]++;
            }
            for(int i=1; i<n+1;i++){
                if(degree[i] == 0) q.add(i);
            }
            while (!q.isEmpty()){
                int node = q.peek();
                answer++;
                q.poll();
                int adjNode = graph[node];
                degree[adjNode]--;
                if(degree[adjNode] == 0) q.add(adjNode);
            }

            sb.append(answer);
            sb.append('\n');
        }
        System.out.print(sb);
    }
}

