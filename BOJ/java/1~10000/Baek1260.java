import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Baek1260 {

    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static StringBuilder sb;
    public static void dfs(int v){
        visited[v] = true;
        sb.append(v);
        sb.append(" ");
        for (int node : graph.get(v)){
            if(!visited[node]){
                dfs(node);
            }
        }
    }

    public static void bfs(int v){
        visited[v] = true;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(v);
        while (!q.isEmpty()){
            int node = q.peek();
            q.poll();
            sb.append(node);
            sb.append(" ");
            for(int adjNode : graph.get(node)){
                if(!visited[adjNode]){
                    visited[adjNode] = true;
                    q.add(adjNode);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        for (int i=0; i<n+1; i++) graph.add(new ArrayList<>());

        for (int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        for(List<Integer> g : graph){
            g.sort(Integer::compare);
        }

        sb = new StringBuilder();
        visited = new boolean[n+1];
        dfs(v);
        System.out.println(sb);

        sb = new StringBuilder();
        visited = new boolean[n+1];
        bfs(v);
        System.out.println(sb);
    }
}
