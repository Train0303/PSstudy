import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Baek2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<int[]> stack = new Stack<>();

        ArrayList<Integer> answer = Stream.iterate(0, i -> i)
                .limit(n).collect(Collectors.toCollection(ArrayList::new));

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++){
            int num = Integer.parseInt(st.nextToken());
            if(stack.isEmpty()) stack.add(new int[] {i,num});
            else if(stack.peek()[1] >= num){
                answer.set(i,stack.peek()[0]+1);
                stack.add(new int[] {i,num});

            }
            else{
                while(!stack.isEmpty() && stack.peek()[1] < num){
                    stack.pop();
                }

                if (!stack.isEmpty()) answer.set(i,stack.peek()[0]+1);
                stack.add(new int[] {i,num});
            }
        }
        StringBuilder sb = new StringBuilder();
        answer.stream()
                .forEach(a ->{
                    sb.append(a);
                    sb.append(" ");
                });
        System.out.print(sb.toString());
    }
}
