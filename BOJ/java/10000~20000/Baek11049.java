import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baek11049 {
    static class Coord{
        int x;
        int y;
        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        List<Coord> coord = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[][] DP = new int[n][n];


        for (int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            coord.add(new Coord(r,c));
        }

        for(int d=0; d<n-1; d++){
            for(int i=0; i<n-1-d; i++){
                int j = i+d+1;
                DP[i][j] = Integer.MAX_VALUE;
                for(int k=i; k<j; k++){
                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k+1][j] + coord.get(i).x * coord.get(k).y * coord.get(j).y);

                }
            }
        }
        System.out.println(DP[0][n-1]);
    }
}
