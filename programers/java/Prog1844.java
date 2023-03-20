import java.util.LinkedList;
import java.util.Queue;

public class prog_1844 {
    static class Node{
        private final int x;
        private final int y;
        private final int time;
        public Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;

        }
        public int getX() {return x;}
        public int getY() {return y;}
        public int getTime() {return time;}
    }

    public static int solution(int[][] maps){
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        int targetX = maps.length;
        int targetY = maps[0].length;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,1));
        visited[0][0] = false;
        while (!q.isEmpty()){
            Node curNode = q.peek();
            q.poll();

            if (curNode.getX() == targetX-1 && curNode.getY() == targetY-1){
                return curNode.getTime();
            }

            for(int i=0; i<4; i++){
               int newX = dx[i] + curNode.getX();
               int newY = dy[i] + curNode.getY();

               if( (0<=newX && newX<targetX) && (0<=newY && newY<targetY)){
                   if(!visited[newX][newY] && maps[newX][newY] == 1){
                       visited[newX][newY] = true;
                       q.add(new Node(newX,newY,curNode.getTime()+1));
                   }
               }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println(solution(maps));
    }
}
