
import java.util.Arrays;
import java.util.OptionalInt;


public class proj_42840 {
    public static int[] solution(int[] answers){
        int[] first = {1, 2, 3, 4, 5};
        int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] counts = {0,0,0};
        for(int i=0; i<answers.length;i++){
            counts[0] = answers[i] == first[i%5] ? counts[0] + 1 : counts[0];
            counts[1] = answers[i] == second[i%8] ? counts[1] + 1 : counts[1];
            counts[2] = answers[i] == third[i%10] ? counts[2] + 1 : counts[2];
        }
        OptionalInt max_cnt = Arrays.stream(counts).max();
        int[] answer = Arrays.stream(new int[] {1,2,3})
                .filter(c -> (counts[c-1] == max_cnt.getAsInt()))
                .toArray();

        return answer;
    }

    public static void main(String[] args) {
        int[] answers = {1,3,2,4,2};

        for(int a:solution(answers)){
            System.out.print(a + " ");
        }

    }
}
