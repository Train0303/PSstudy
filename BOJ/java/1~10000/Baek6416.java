import java.io.IOException;
import java.util.*;

public class Baek6416 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int testCase = 1;
        boolean result = true;
        while (true){
            int edge = 0;
            Map<Integer, Integer> graph = new HashMap<>();
            while (true) {

                int start = sc.nextInt();
                int end = sc.nextInt();

                if (start == 0 && end == 0) break;
                if (start == -1 && end == -1) return;

                graph.put(start, graph.getOrDefault(start, 0));
                graph.put(end, graph.getOrDefault(end,0)+1);
                edge++;
            }

            int rootCount = 0;
            int vertex = graph.size();
            for(int num : graph.values()){
                if (num > 1) result = false;
                else if (num == 0) rootCount++;
            }
            result = rootCount != 1 ? false : true;
            if (vertex == 0) System.out.println("Case " + testCase + " is a tree.");
            else if(result && edge == vertex -1)  System.out.println("Case " + testCase + " is a tree.");
            else System.out.println("Case " + testCase + " is not a tree.");
            testCase++;
        }
    }
}
