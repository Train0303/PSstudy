import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek1753 {

    static class Node implements Comparable<Node>{
        int weight, end;

        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    private static ArrayList<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(br.readLine());

        list = new ArrayList[v + 1];
        int[] dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for(int i=1; i<v+1; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i< e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b,w));
        }
        dijacstra(target, dist);
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<v+1; i++){
            if (dist[i] == Integer.MAX_VALUE) sb.append("INF");
            else sb.append(dist[i]);
            sb.append('\n');
        }
        System.out.print(sb);
    }
    public static void dijacstra(int target, int[] dist){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(target,0));
        dist[target] = 0;
        while(!pq.isEmpty()){
            Node curNode = pq.peek();
            pq.poll();
            if (curNode.weight > dist[curNode.end]) continue;

            for (Node adjNode : list[curNode.end]){
                int weightDist = adjNode.weight + curNode.weight;
                if (dist[adjNode.end] > weightDist){
                    dist[adjNode.end] = weightDist;
                    pq.add(new Node(adjNode.end, dist[adjNode.end]));
                }
            }
        }
    }
}
