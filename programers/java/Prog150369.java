import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class prog_150369 {
    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliveryDist = n;
        int pickupDist = n;

        List<Integer> deliveryList = Arrays.stream(deliveries)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> pickupList = Arrays.stream(pickups)
                .boxed()
                .collect(Collectors.toList());


        while (!deliveryList.isEmpty() && !pickupList.isEmpty()){
            int deleteDeliveryListCount = 0;
            int deletePickupListCount = 0;
            int havingBoxes = cap;
            boolean flag = false;

            for(int i=deliveryList.size()-1; i >= 0; i--){
                if (deliveryList.get(i) == 0){
                    deliveryList.remove(i);
                    deleteDeliveryListCount++;
                }
                else if (havingBoxes >= deliveryList.get(i)){
                    havingBoxes -= deliveryList.get(i);
                    deleteDeliveryListCount ++;
                    deliveryList.remove(i);
                    flag = true;
                }
                else{
                    deliveryList.set(i,deliveryList.get(i) - havingBoxes);
                    flag = true;
                    break;
                }
            }

            havingBoxes = 0;
            for(int i=pickupList.size()-1; i >= 0; i--){
                if (pickupList.get(i) == 0){
                    pickupList.remove(i);
                    deletePickupListCount++;
                }
                else if (havingBoxes+pickupList.get(i) <= cap){
                    havingBoxes += pickupList.get(i);
                    deletePickupListCount ++;
                    pickupList.remove(i);
                    flag = true;
                }
                else{
                    pickupList.set(i,pickupList.get(i) - (cap - havingBoxes));
                    flag = true;
                    break;
                }
            }

            if (flag) answer += 2*(long)Math.max(deliveryDist, pickupDist);

            deliveryDist -= deleteDeliveryListCount;
            pickupDist -= deletePickupListCount;

        }

        return answer;
    }

    public static void main(String[] args) {
        int cap = 4;
        int n = 5;
        int[] deliveries = {1, 0, 3, 1, 2};
        int[] pickups = {0, 3, 0, 4, 0};
        System.out.println(solution(cap, n, deliveries, pickups));
    }
}
