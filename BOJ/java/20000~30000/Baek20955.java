import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Baek20955 {
    static int[] parent;

    public static int find(int findNum){
        if(parent[findNum] == findNum) return findNum;
        parent[findNum] = find(parent[findNum]);
        return parent[findNum];
    }

    public static boolean isCycle(int a, int b){
        int p_a = find(a);
        int p_b = find(b);
        if( p_a == p_b ) return true;
        parent[p_b] = p_a;
        return false;
    }

    public static void main(String[] args) throws IOException {
        int cycle = 0;
        int isolate = 0;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = IntStream
                .rangeClosed(0,n)
                .toArray();

        for (int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(isCycle(u,v)){
                cycle++;
            }
        }

        for (int i=1; i<n+1; i++){
            if(i == parent[i]) isolate++;
        }

        System.out.println(cycle+isolate-1);
    }
}
