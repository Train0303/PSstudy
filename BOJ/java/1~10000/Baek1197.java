import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Baek1197 {
    static int V;
    static int E;
    static int[][] graph;
    static int[] parent;

    public static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int pA = find(a);
        int pB = find(b);
        if (pA > pB){
            parent[pA] = pB;
        }
        else{
            parent[pB] = pA;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new int[E][3];
        parent = IntStream.range(0,V+1)
                .toArray();

        for (int i=0; i<E; i++){
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[i][0] = a;
            graph[i][1] = b;
            graph[i][2] = c;
        }

        Arrays.sort(graph, Comparator.comparingInt((int[] a) -> a[2]));

        int answer = 0;
        int count = 0;
        for (int[] e: graph){
            int pA = find(e[0]);
            int pB = find(e[1]);
            if (pA == pB) continue;

            answer += e[2];
            union(e[0], e[1]);
            count++;
            if (count == V-1) break;
        }
        System.out.println(answer);
    }
}
