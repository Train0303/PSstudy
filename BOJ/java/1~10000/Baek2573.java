import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek2573 {
    static class Iceberg{
        public int x;
        public int y;
        public int discount;
        public Iceberg(int x, int y, int discount){
            this.x = x;
            this.y = y;
            this.discount = discount;
        }
    }
    static int[][] matrix;
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int n;
    static int m;
    public static void bfs(int x, int y){
        List<Iceberg> result = new ArrayList<>();
        Queue<Iceberg> q = new ArrayDeque<>();
        q.add(new Iceberg(x,y,0));
        visited[x][y] = true;
        while (!q.isEmpty()){
            Iceberg ice = q.peek();
            q.poll();
            int count = 0;
            for(int i=0; i<4; i++){
                int new_x = ice.x + dx[i];
                int new_y = ice.y + dy[i];
                if(new_x>=0 && new_x<n && new_y>=0 && new_y<m){
                    if(matrix[new_x][new_y] == 0) count++;
                    if(matrix[new_x][new_y] != 0 && !visited[new_x][new_y]){
                        visited[new_x][new_y] = true;
                        q.add(new Iceberg(new_x, new_y, 0));
                    }
                }
            }
            ice.discount += count;
            result.add(ice);
        }
        for (Iceberg ice:result){
            int num = matrix[ice.x][ice.y]-ice.discount;
            matrix[ice.x][ice.y] = Math.max(num, 0);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int year = 0;

        while (true){
            visited = new boolean[n][m];
            int cluster = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(!visited[i][j] && matrix[i][j] != 0){
                        bfs(i,j);
                        cluster++;
                    }
                }
            }
            if(cluster > 1){
                System.out.println(year);
                break;
            }else if(cluster == 0){
                System.out.println(0);
                break;
            }
            year++;
        }

        bf.close();
    }
}
