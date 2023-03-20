import java.util.*;

public class Prog72413 {


    class Solution {
        class Node{
            int e;
            int t;
            public Node(int e, int t){
                this.e = e;
                this.t = t;
            }
        }
        int[][] distance;
        List<List<Node>> graph;
        public void dijkstra(int s, int n){
            PriorityQueue<Node> q = new PriorityQueue(Comparator.comparingInt((Node node) -> node.t));
            q.add(new Node(s, 0));
            distance[s][s] = 0;
            while(!q.isEmpty()){
                Node curNode = q.peek(); q.poll();
                if(curNode.t > distance[s][curNode.e]) continue;

                for(Node adjNode: graph.get(curNode.e)){
                    int weight = curNode.t + adjNode.t;
                    if(weight < distance[s][adjNode.e]){
                        distance[s][adjNode.e] = weight;
                        q.add(new Node(adjNode.e, weight));
                    }
                }
            }
        }
        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = Integer.MAX_VALUE;
            distance = new int[n+1][n+1];
            for(int i=0; i<n+1; i++)
                Arrays.fill(distance[i], 99999999);

            graph = new ArrayList<>();
            for(int i=0; i<n+1; i++) graph.add(new ArrayList<>());

            for(int[] fare : fares){
                graph.get(fare[0]).add(new Node(fare[1], fare[2]));
                graph.get(fare[1]).add(new Node(fare[0], fare[2]));
            }

            for(int i=1; i<n+1; i++)
                dijkstra(i,n);

            for(int i=1 ;i<n+1; i++)
                answer = Math.min(answer, distance[s][i] + distance[i][a] + distance[i][b]);

            return answer;
        }
    }
}
