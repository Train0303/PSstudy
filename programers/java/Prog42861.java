import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Prog_42861 {
    static int[] parent;

    public static int find(int a){
        if (parent[a] == a) return a;
        return find(parent[a]);
    }
    public static void union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        if (parentA > parentB) parent[parentA] = parent[parentB];
        else parent[parentB] = parent[parentA];
    }


    public static int solution(int n, int[][] costs) {
        int answer = 0;
        parent = IntStream.iterate(0, i->i+1)
                        .limit(n)
                        .toArray();

        Arrays.sort(costs, Comparator.comparingInt((int[] o) -> o[2]));

        for (int[] cost: costs){
            int a = cost[0];
            int b = cost[1];
            int c = cost[2];
            if (find(a) == find(b)) continue;

            union(a,b);
            answer += c;
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {
                {0,1,1}, {0,2,2}, {1,2,5}, {1,3,1}, {2,3,8}
        };
        System.out.println(solution(n,costs));
    }
}
