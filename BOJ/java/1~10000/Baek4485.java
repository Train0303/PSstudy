import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek4485 {
    static class Node{
        int x;
        int y;
        int cost;
        public Node(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[][] matrix;
    static int[][] distance;
    static int n;

    public static void dijkstra(int x, int y){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt((Node n)->n.cost));
        distance[x][y] = matrix[x][y];
        pq.add(new Node(x,y,matrix[x][y]));
        while(!pq.isEmpty()){
            Node current = pq.peek();pq.poll();

            if(current.cost > distance[current.x][current.y]) continue;
            if(current.x == n-1 && current.y == n-1) break;

            for(int i=0; i<4; i++){
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];
                if(newX>=0 && newX<n && newY>=0 && newY<n){
                    int newCost = matrix[newX][newY] + current.cost;
                    if(distance[newX][newY] > newCost){
                        distance[newX][newY] = newCost;
                        pq.add(new Node(newX, newY, newCost));
                    }
                }
            }
        }
    }

    public static void print(int t){
        sb.append("Problem ");
        sb.append(t);
        sb.append(": ");
        sb.append(distance[n-1][n-1]);
        sb.append('\n');
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = 0;
        while(true){
            n = Integer.parseInt(bf.readLine());
            if(n == 0) break;
            matrix = new int[n][n];
            distance = new int[n][n];
            for(int i=0; i<n; i++){
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for(int j=0; j<n; j++){
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
            dijkstra(0,0);
            print(++t);
        }
        System.out.print(sb);
    }
}
