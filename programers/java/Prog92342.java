public class prog_92342 {

    static int maxDiff = 0;
    static int[] answer;

    public static int calScore(int[] lion, int[] info){
        int apeachScore = 0;
        int lionScore = 0;
        for(int i=0; i<11; i++){
            if (lion[i] == 0 && info[i] == 0) continue;


            if (info[i] - lion[i] >= 0) apeachScore += (10-i);
            else lionScore += (10-i);
        }
        return lionScore - apeachScore;
    }
    public static void BT(int idx, int remain, int[] lion, int[] info){
        if (remain < 0) return;

        if (idx > 10) {
            int diff = calScore(lion, info);
            if(diff > maxDiff){
                answer = lion.clone();
                maxDiff = Integer.max(maxDiff, diff);
                answer[10] += remain;
            }
            return;
        }

        lion[10-idx] = info[10-idx] + 1;
        BT(idx+1, remain-lion[10-idx], lion, info);
        lion[10-idx] = 0;
        BT(idx+1, remain, lion, info);
    }

    public static int[] solution(int n, int[] info) {
        int[] lion = new int[11];

        BT(0,n,lion,info);

        return answer;
    }

    public static void main(String[] args) {
        int[] info = {2,1,1,1,0,0,0,0,0,0,0};
        int n = 5;
        solution(n,info);
        for(int i:answer){
            System.out.println(i);
        }
    }
}
