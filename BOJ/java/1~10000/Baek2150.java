import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.min;
import static java.util.Comparator.comparingInt;

public class Baek2150 {
    static StringBuilder sb = new StringBuilder();
    static int v,e;
    static List<List<Integer>> graph = new ArrayList<>();
    static List<List<Integer>> scc = new ArrayList<>();
    static Deque<Integer> stack = new ArrayDeque<>();
    static int[] ids;
    static int id,sccNum;
    static boolean[] isSCC;
    public static void sbAppend(List<Integer> s){
        for(int n: s){
            sb.append(n);
            sb.append(" ");
        }
        sb.append(-1);
        sb.append('\n');
    }

    public static int dfs(int node){
        ids[node] = ++id;
        stack.add(node);
        int result = id;
        for(int adjNode: graph.get(node)){
            if(ids[adjNode] == 0) result = min(result, dfs(adjNode));
            else if(!isSCC[adjNode])result = min(result, ids[adjNode]);
        }

        int top;
        if(result == ids[node]){
            scc.add(new ArrayList<>());
            do {
                top = stack.peekLast();
                isSCC[top] = true;
                stack.pollLast();
                scc.get(sccNum).add(top);
            }while(top != node);
            scc.get(sccNum).sort(comparingInt(Integer::intValue));
            sccNum++;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        isSCC = new boolean[v+1];
        ids = new int[v+1];
        for (int i = 0; i < v + 1; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(s).add(e);
        }

        for (int i=1; i<v+1; i++){
            if(ids[i] == 0 ) dfs(i);
        }
        System.out.println(sccNum);
        scc.sort(comparingInt((List<Integer> s) -> s.get(0)));
        for (List<Integer> s : scc) {
            sbAppend(s);
        }
        System.out.print(sb);
    }
}
