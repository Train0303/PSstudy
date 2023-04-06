import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1074 {
    static int N;
    static int r;
    static int c;

    public static int recursive(int n, int r, int c){
        int result;
        if (n == 1)
            return 2*r+c;

        int num = (int) Math.pow(2,n-1);
        int squareNum = (int) Math.pow(num,2);

        if (num > r && num > c){
            result = recursive(n-1, r, c);
        } else if (num > r && num <= c) {
            result = squareNum + recursive(n-1, r, c-num);
        } else if (num <= r && num > c) {
            result = squareNum*2 + recursive(n-1, r-num, c);
        } else{
            result = squareNum*3 + recursive(n-1, r-num, c-num);
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        System.out.println(recursive(N,r,c));
    }
}
