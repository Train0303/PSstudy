import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Baek1865 {
    static List<int[]> graph;

    public static boolean bellmanFord(int n){
        int[] dist = new int[n+1];
        Arrays.fill(dist, 987654321);
        for(int i=0; i<n;i++){
            for(int[] node: graph){
                int start = node[0];
                int end = node[1];
                int weight = node[2];

                if (dist[start] != Integer.MAX_VALUE && dist[end] > dist[start] + weight){
                    if(i==n-1) return true;
                    dist[end] = dist[start] + weight;
                }
            }
        }

        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i=0; i<t; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();

            for (int j=0;j<m;j++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.add(new int[] {start, end, weight});
                graph.add(new int[] {end, start, weight});
            }

            for (int j=0;j<w;j++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.add(new int[] {start, end, -weight});
            }

            boolean flag = true;
            if (bellmanFord(n)) sb.append("YES");
            else sb.append("NO");
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
