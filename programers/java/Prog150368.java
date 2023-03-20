import java.util.ArrayList;
import java.util.List;
public class prog_150368 {
    public static class User{
        private final int wantDiscount;
        private final int buyPlusPrice;

        public User(int wantDiscount, int buyPlusPrice){
            this.wantDiscount = wantDiscount;
            this.buyPlusPrice = buyPlusPrice;
        }

        public int getWantDiscount() {
            return wantDiscount;
        }

        public int getBuyPlusPrice() {
            return buyPlusPrice;
        }
    }

    static int[] maxUserPlusAndSales = {0,0};
    static final int START_DEPTH = 0;
    static final int[] DISCOUNT_SET = {10,20,30,40};
    public static void BT(int depth, List<User> users, int[] emoticons, List<Integer> productEmoticons){
        if(depth == emoticons.length){
            int[] userPlusAndSales = {0,0};
            for (User user: users){
                List<Integer> buyEmoticons = new ArrayList<>();
                for(int i=0; i< emoticons.length; i++){
                    if(productEmoticons.get(i) >= user.getWantDiscount()){
                        buyEmoticons.add(emoticons[i]*(100-productEmoticons.get(i))/100);
                    }
                }
                int sumBuyEmoticons = buyEmoticons.stream()
                        .mapToInt(i->i).sum();

                if (sumBuyEmoticons >= user.getBuyPlusPrice()) userPlusAndSales[0]++;
                else userPlusAndSales[1] += sumBuyEmoticons;

            }
            if (userPlusAndSales[0] > maxUserPlusAndSales[0]) {
                maxUserPlusAndSales[0] = userPlusAndSales[0];
                maxUserPlusAndSales[1] = userPlusAndSales[1];
            }
            else if(userPlusAndSales[0] == maxUserPlusAndSales[0])
                maxUserPlusAndSales[1] = Math.max(maxUserPlusAndSales[1], userPlusAndSales[1]);
            return;
        }

        for(int discount :DISCOUNT_SET){
            productEmoticons.add(discount);
            BT(depth+1, users, emoticons, productEmoticons);
            productEmoticons.remove(productEmoticons.size()-1);
        }
    }

    public static int[] solution(int[][] users, int[] emoticons){
        List<User> userSet = new ArrayList<>();
        List<Integer> productEmoticons = new ArrayList<>();
        for(int[] user: users) userSet.add(new User(user[0], user[1]));

        BT(START_DEPTH, userSet, emoticons, productEmoticons);
        return maxUserPlusAndSales;
    }

    public static void main(String[] args) {
        int[][] users = {{40,10000}, {25, 10000}};
        int[] emoticons = {7000,9000};
        System.out.println(solution(users, emoticons)[0] + " " + solution(users, emoticons)[1]);
    }
}
