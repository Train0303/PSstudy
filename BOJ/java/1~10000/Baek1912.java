import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int sum = Integer.parseInt(st.nextToken());
        int maxV = sum;
        sum = sum < 0 ? 0 : sum;
        for (int i=1; i<n; i++){
            sum += Integer.parseInt(st.nextToken());
            maxV = Math.max(maxV, sum);
            sum = sum < 0 ? 0 : sum;
        }
        System.out.println(maxV);
    }
}
