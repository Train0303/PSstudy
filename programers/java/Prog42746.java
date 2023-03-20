import java.util.Arrays;

public class prog_42746 {
    public static String solution(int[] numbers){
        String[] strArray = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((s1,s2)-> -Integer.compare(Integer.parseInt(s1+s2), Integer.parseInt(s2+s1)))
                .toArray(String[]::new);

        String answer = String.join("",strArray);
        if(answer.charAt(0) == '0') return "0";
        return answer;
    }
    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9};
        System.out.println(solution(numbers));
        numbers = new int[] {0, 0, 0, 0, 0};
        System.out.println(solution(numbers));
    }
}
