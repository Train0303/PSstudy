import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek13460 {
    public static class Marble{
        public int x;
        public int y;
        public Marble(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static class Data{
        public Marble r;
        public Marble b;
        public int t;

        public Data(Marble r, Marble b, int t){
            this.r = r;
            this.b = b;
            this.t = t;
        }
    }
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    static int n,m;
    static char[][] matrix;
    static Marble red;
    static Marble blue;
    static int[] goal;

    public static Marble sliding(int x, int y, int i){
        while(matrix[x+dx[i]][y+dy[i]] != '#'){
            x += dx[i];
            y += dy[i];
            if(x == goal[0] && y == goal[1]) break;
        }
        return new Marble(x,y);
    }
    public static void bfs(){
        Queue<Data> q = new ArrayDeque<>();
        q.add(new Data(red, blue, 0));
        while(!q.isEmpty()){
            Data data = q.peek(); q.poll();

            if(data.t > 10){
                System.out.println(-1);
                return;
            }

            if( data.r.x == goal[0] && data.r.y == goal[1]) {
                System.out.println(data.t);
                return;
            }
            for(int i=0; i<4; i++){
                Marble newRed = sliding(data.r.x, data.r.y, i);
                Marble newBlue = sliding(data.b.x, data.b.y, i);

                if( newBlue.x == goal[0] && newBlue.y == goal[1]) continue;

                if(newRed.x == newBlue.x && newRed.y == newBlue.y){
                    if(i==0){
                        if(data.r.y > data.b.y) newRed.y++;
                        else newBlue.y++;
                    } else if (i==1) {
                        if(data.r.y > data.b.y) newBlue.y--;
                        else newRed.y--;
                    } else if (i==2) {
                        if(data.r.x > data.b.x) newRed.x++;
                        else newBlue.x++;
                    } else{
                        if(data.r.x > data.b.x) newBlue.x--;
                        else newRed.x--;
                    }
                }
                q.add(new Data(newRed, newBlue, data.t+1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        matrix = new char[n][m];
        for(int i=0; i<n; i++){
            String input = bf.readLine();
            for(int j=0; j<m; j++){
                matrix[i][j] = input.charAt(j);
                if(matrix[i][j] == 'R') red = new Marble(i,j);
                else if(matrix[i][j] == 'B') blue = new Marble(i,j);
                else if(matrix[i][j] == 'O') goal = new int[] {i,j};
            }
        }
        bfs();
    }

}

