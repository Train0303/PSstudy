import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Baek14226 {
    static int WINDOW = 0;
    static int CLIP = 1;
    static int TIME = 2;

    public static int BFS(int target){
        boolean[] visited = new boolean[1001];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {1,0,0});
        while (!q.isEmpty()){
            int[] node = q.peek();
            q.poll();

            if(node[WINDOW] == target){
                return node[TIME];
            }
            if(node[WINDOW] > 1 && !visited[node[WINDOW]-1]){
                q.add(new int[]{node[WINDOW]-1, node[CLIP], node[TIME]+1});
            }

            if(node[CLIP] != 0 && (node[WINDOW] + node[CLIP]) < 1001){
                if(!visited[node[WINDOW]+node[CLIP]])
                    q.add(new int[]{node[WINDOW]+node[CLIP], node[CLIP], node[TIME]+1});
            }

            if(node[WINDOW] != 0 && !visited[node[WINDOW]]) {
                q.add(new int[]{node[WINDOW], node[WINDOW], node[TIME]+1});
            }

            visited[node[WINDOW]] = true;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(bf.readLine());

        System.out.println(BFS(s));
    }
}
