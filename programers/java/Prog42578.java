import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class prog_42578 {
    public static int solution(String[][] clothes){
        int answer = 1;
        HashMap<String, HashSet<String>> clothMap = new HashMap<>();
        for(String[] cloth:clothes){
            HashSet<String> temp = clothMap.get(cloth[1]);
            if (temp == null){
                clothMap.put(cloth[1], new HashSet<String>(Arrays.asList(cloth[0])));
            }
            else {
                temp.add(cloth[0]);
                clothMap.put(cloth[1], temp);
            }
        }
        for(String s: clothMap.keySet()){
            answer *= (clothMap.get(s).size() + 1);
        }
        return answer-1;
    }

    public static void main(String[] args) {
        String[][] clothes = {
                {"yellow_hat", "headgear"},
                {"blue_sunglasses", "eyewear"},
                {"green_turban", "headgear"}
        };
        System.out.println(solution(clothes));
    }
}
