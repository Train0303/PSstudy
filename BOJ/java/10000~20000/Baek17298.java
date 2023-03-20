import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek17298 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        Deque<int[]> stack = new ArrayDeque<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        for(int i=0; i<n; i++){
            int num = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty() && stack.peekLast()[0] < num){
                answer[stack.peekLast()[1]] = num;
                stack.pollLast();
            }
            stack.addLast(new int[] {num,i});

        }

        for(int a :answer){
            sb.append(a);
            sb.append(" ");
        }
        System.out.println(sb);
    }
}
