import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        long x;
        int time;
        int beforeMove;
        public Node(long x, int time, int beforeMove) {
            this.x = x;
            this.time = time;
            this.beforeMove = beforeMove;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(bf.readLine());
        for(int i=0; i<t; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dist = y-x;
            double sqrtDist = Math.sqrt(dist);
            if( sqrtDist == (int)sqrtDist){
                sb.append(2*(int)sqrtDist-1 + "\n");
            }else{
                int perSquareDist = (int)sqrtDist * (int)sqrtDist;
                int count = 2*(int)sqrtDist + (dist-perSquareDist-1)/(int)sqrtDist;
                sb.append(count + "\n");
            }
        }
        System.out.print(sb);
    }
}
