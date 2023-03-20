import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class prog_86971 {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static int BFS(int node){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;
        int count = 1;
        while (!queue.isEmpty()){
            int cur_node = queue.peek();
            queue.poll();
            for (int adj_node:graph.get(cur_node)){
                if (!visited[adj_node]){
                    visited[adj_node] = true;
                    queue.add(adj_node);
                    count ++;
                }
            }
        }
        return count;
    }
    public static int solution(int n, int[][] wires){
        int answer = n+1;

        for (int i=0; i< wires.length; i++){
            visited = new boolean[n+1];
            graph = new ArrayList<>();
            for (int j=0; j<n+1; j++) graph.add(new ArrayList<>());

            for (int j=0; j<wires.length; j++){
                if (j==i) continue;
                graph.get(wires[j][0]).add(wires[j][1]);
                graph.get(wires[j][1]).add(wires[j][0]);
            }

            answer = Math.min(Math.abs(BFS(wires[i][0]) - BFS(wires[i][1])), answer);
        }
        return answer;
    }
    public static void main(String[] args) {
        int n = 9;
        int [][]wires = new int[][] {
                {1,3}, {2,3}, {3,4}, {4,5}, {4,6}, {4,7}, {7,8}
        };
        System.out.println(solution(n,wires));
        n = 4;
        wires = new int[][] {
                {1,2}, {2,3}, {3,4}
        };
        System.out.println(solution(n,wires));
        n=7;
        wires = new int[][] {
                {1,2}, {2,7}, {3,7},{3,4},{4,5},{6,7}
        };
        System.out.println(solution(n,wires));
    }
}
