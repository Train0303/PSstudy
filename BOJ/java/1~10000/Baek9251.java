import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str1 = bf.readLine();
        String str2 = bf.readLine();
        int l1 = str1.length();
        int l2 = str2.length();
        int[][] DP = new int[l1+1][l2+1];

        for(int i=1; i<l1+1; i++){
            for (int j=1; j<l2+1; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1))
                    DP[i][j] = DP[i-1][j-1] + 1;
                else
                    DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]);
            }
        }

        System.out.println(DP[l1][l2]);
    }
}
