import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek1238 {
    static class Road{
        int e;
        int t;
        public Road(int e, int t){
            this.e = e;
            this.t = t;
        }
    }

    static int n;
    static int m;
    static int x;



    public static int[] dijkstra(int start, List<List<Road>> graph){
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        PriorityQueue<Road> q = new PriorityQueue<>(Comparator.comparingInt((Road r) -> r.t));
        q.add(new Road(start,0));
        while (!q.isEmpty()){
            Road node = q.peek(); q.poll();
            if(node.t > distance[node.e]) continue;

            for(Road adjRoad: graph.get(node.e)){
                int weighTime = adjRoad.t + node.t;
                if(weighTime < distance[adjRoad.e]) {
                    distance[adjRoad.e] = weighTime;
                    q.add(new Road(adjRoad.e, weighTime));
                }
            }
        }
        return distance;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        List<List<Road>> graph = new ArrayList<>();
        List<List<Road>> revGraph = new ArrayList<>();
        for(int i=0; i<n+1; i++) {
            graph.add(new ArrayList<>());
            revGraph.add(new ArrayList<>());
        }


        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Road(e,t));
            revGraph.get(e).add(new Road(s,t));
        }

        int answer = 0;
        int[] arriveDistance = dijkstra(x, graph);
        int[] departureDistance = dijkstra(x, revGraph);
        for(int i=1; i<n+1; i++)
            answer = Math.max(answer, arriveDistance[i] + departureDistance[i]);

        System.out.println(answer);
    }
}
