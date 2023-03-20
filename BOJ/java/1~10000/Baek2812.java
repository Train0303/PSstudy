import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Baek2812 {
    public static void main(String[] args) throws IOException {
        ArrayDeque<Character> s = new ArrayDeque<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int stackSize = n-k;

        String number = bf.readLine();
        s.add(number.charAt(0));
        for(int i=1; i<number.length();i++){
            char num = number.charAt(i);
            int remain = n-i;
            while(!s.isEmpty()){
                if(s.peekLast()<num && (s.size()-1+remain)>=stackSize) {
                    s.pollLast();
                }
                else break;
            }
            if(s.size() < stackSize)
                s.add(num);
        }
        String answer = s.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
        System.out.println(answer);
    }
}
