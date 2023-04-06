import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek1068 {
    static int rootNode;
    static List<List<Integer>> graph = new ArrayList<>();
    static int removeNode;
    public static int dfs(int curNode){
        if (curNode == removeNode) return 0;

        int count = 0;
        if (graph.get(curNode).size() == 0) return 1;
        else{
            for(int adjNode: graph.get(curNode)){
                if (adjNode == removeNode && graph.get(curNode).size() == 1) return 1;
                else count += dfs(adjNode);
            }
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0; i<n; i++) graph.add(new ArrayList<>());

        for (int i=0; i<n; i++) {
            int node = Integer.parseInt(st.nextToken());
            if(node != -1){
                graph.get(node).add(i);
            }else rootNode = i;
        }
        removeNode = Integer.parseInt(bf.readLine());

        int result = dfs(rootNode);
        System.out.println(result);
    }
}
