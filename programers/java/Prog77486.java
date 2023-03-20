import java.util.HashMap;
import java.util.Map;

public class Prog77486 {

    static Map<String, Integer> sales = new HashMap<>();
    static Map<String, String> dadanTree = new HashMap<>();
    public static void dadan(String seller, int profit){
        int distribution = profit/10;
        if(distribution == 0) return;

        sales.put(seller, sales.get(seller)-distribution);

        if(dadanTree.get(seller).equals("-")) return;

        String higher = dadanTree.get(seller);
        sales.put(higher, sales.get(higher) + distribution);
        dadan(higher, distribution);
    }
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        for (int i=0; i<enroll.length; i++){
            sales.put(enroll[i], 0);
            dadanTree.put(enroll[i],referral[i]);
        }

        for (int i=0; i<seller.length; i++){
            int profit = amount[i] * 100;
            sales.put(seller[i], sales.get(seller[i])+profit);
            dadan(seller[i], profit);
        }

        int[] answer = new int[enroll.length];
        for(int i=0; i<enroll.length; i++){
            answer[i] = sales.get(enroll[i]);
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
        System.out.println(solution(enroll, referral, seller, amount));
    }
}
