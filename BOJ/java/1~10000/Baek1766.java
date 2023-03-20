import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] degree = new int[n+1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i<n+1; i++){
            graph.add(new ArrayList<>());
        }

        for (int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            degree[b]++;
        }

        for (int i=1; i<n+1; i++){
            if(degree[i] == 0){
                pq.add(i);
            }
        }

        while(!pq.isEmpty()){
            int node = pq.peek();
            pq.poll();
            sb.append(node);
            sb.append(" ");
            for (int adjNode : graph.get(node)) {
                degree[adjNode]--;
                if(degree[adjNode] == 0) pq.add(adjNode);
            }
        }

        System.out.println(sb);
    }
}