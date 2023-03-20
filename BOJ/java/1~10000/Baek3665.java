import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek3665 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(bf.readLine());
        for(int k=0; k<t; k++){
            List<List<Integer>> graph = new ArrayList<>();
            int n = Integer.parseInt(bf.readLine());
            int[] beforeRace = new int[n+1];
            int[] degree = new int[n+1];
            List<Integer> visit = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int i=0; i<n+1; i++) graph.add(new ArrayList<>());
            for(int i=1; i<n+1; i++) beforeRace[i] = Integer.parseInt(st.nextToken());
            for(int i=1; i<n; i++) {
                for(int j=i+1; j<n+1; j++){
                    degree[beforeRace[j]]++;
                    graph.get(beforeRace[i]).add(beforeRace[j]);
                }
            }

            int m = Integer.parseInt(bf.readLine());
            for(int i=0; i<m; i++){
                st = new StringTokenizer(bf.readLine());
                int a1 = Integer.parseInt(st.nextToken());
                int a2 = Integer.parseInt(st.nextToken());

                if(graph.get(a1).contains(Integer.valueOf(a2))){
                    graph.get(a1).remove(Integer.valueOf(a2));
                    degree[a1]++;
                    graph.get(a2).add(a1);
                    degree[a2]--;
                }else{
                    graph.get(a2).remove(Integer.valueOf(a1));
                    degree[a2]++;
                    graph.get(a1).add(a2);
                    degree[a1]--;
                }

            }
            Queue<Integer> q = new ArrayDeque<>();
            for(int i=1; i<n+1; i++){
                if(degree[i] == 0) q.add(i);

            }
            boolean flag = false;
            while(!q.isEmpty()){
                if(q.size() > 1 ){
                    flag = true;
                    break;
                }
                int node = q.peek(); q.poll();
                visit.add(node);
                for(int adjNode: graph.get(node)){
                    degree[adjNode]--;
                    if(degree[adjNode] == 0) q.add(adjNode);
                }
            }
            if(flag) sb.append("?");
            else if(visit.size() != n) sb.append("IMPOSSIBLE");
            else{
                for(int v : visit) {
                    sb.append(v);
                    sb.append(" ");
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
