import java.util.*;

public class prog_150365 {
    private static final String IMPOSSIBLE = "impossible";

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        int[][] directory= {{1,0},{0,-1},{0,1},{-1,0}};
        char[] directoryChar = {'d' ,'l', 'r', 'u'};

        StringBuilder sb = new StringBuilder();
        int minDist = Math.abs(x-r) + Math.abs(y-c);
        if (Math.abs(minDist-k)%2 != 0 || minDist > k) return IMPOSSIBLE;

        while(minDist != k){
            int new_x = 0;
            int new_y = 0;
            for(int i=0; i<4; i++) {
                new_x = x + directory[i][0];
                new_y = y + directory[i][1];
                if ((1 <= new_x && new_x <= n) && (1 <= new_y && new_y <= m)) {
                    sb.append(directoryChar[i]);
                    break;
                }
            }
            x = new_x;
            y = new_y;
            minDist = Math.abs(x-r) + Math.abs(y-c);
            k -= 1;
        }
        int d_count = Math.max(r - x, 0);
        int u_count = Math.max(x - r, 0);
        int l_count = Math.max(y - c, 0);
        int r_count = Math.max(c - y, 0);

        sb.append("d".repeat(d_count));
        sb.append("l".repeat(l_count));
        sb.append("r".repeat(r_count));
        sb.append("u".repeat(u_count));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(3,4,2,3,3,1,5));
    }
}
