import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class prog_49189 {

    class Queue{
        int dist;
        int node;
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<Integer> distance = new ArrayList<>();
        for (int i=0; i<n+1; i++) distance.add(Integer.MAX_VALUE);
        return answer;
    }

    public static void main(String[] args) {
        List<List<Integer>> a = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        a.add(new ArrayList<>());
        a.get(0).add(5);

    }
}
