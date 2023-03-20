import java.util.ArrayList;
import java.util.HashSet;

public class prog_92343 {
    static int[] infos;
    static ArrayList<ArrayList<Integer>> graph;
    static int max_sheep=-1;
    public static int solution(int[] info, int[][] edges){
        infos = info;
        graph = new ArrayList<>();
        for (int i=0; i<17; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge: edges){

            graph.get(edge[0]).add(edge[1]);
        }
        HashSet<Integer> next_set = new HashSet<>(graph.get(0));

        System.out.println(graph.get(0));
        BT(0,0,0,next_set);
        return max_sheep;
    }

    public static void main(String[] args) {
        int[] info = new int[] {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = new int[][] {{0,1}, {1,2},{1,4}, {0,8}, {8,7}, {9,10}, {9,11}, {4,3}, {6,5}, {4,6},{8,9}};
        System.out.println(solution(info,edges));
    }

    public static void BT(int node, int sheep, int wolf, HashSet<Integer> next_set){

        sheep += infos[node]^1;
        wolf += infos[node];
        if (sheep <= wolf) {
            return;
        }
        max_sheep = Math.max(max_sheep, sheep);

        for(int i=0; i<17; i++){
            if (!next_set.contains(i)) continue;

            next_set.addAll(graph.get(i));
            next_set.remove(i);
            BT(i,sheep,wolf,next_set);
            next_set.add(i);

            for(int j:graph.get(i)){
                next_set.remove(j);
            }
        }
    }
}
