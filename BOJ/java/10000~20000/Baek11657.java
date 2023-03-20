import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Baek11657 {
    static class Road{
        int s;
        int e;
        int t;
        public Road(int s, int e, int t){
            this.s = s;
            this.e = e;
            this.t = t;
        }
    }
    static int n;
    static int m;

    static List<Road> graph;
    static long[] dist;
    public static boolean bellmanFord(int start){
        dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;
        for(int i=0; i<n; i++){
            for(Road node: graph){
                if(dist[node.s] != Long.MAX_VALUE && dist[node.e] > dist[node.s] + node.t){
                    if(i==n-1) return true;
                    else dist[node.e] = dist[node.s] + node.t;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();


        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.add(new Road(s,e,t));
        }

        if(bellmanFord(1)) System.out.println(-1);
        else {
            for (int i = 2; i < n + 1; i++) {
                if (dist[i] == Long.MAX_VALUE)
                    sb.append(-1);
                else
                    sb.append(dist[i]);
                sb.append("\n");
            }
            System.out.print(sb);
        }
    }
}
