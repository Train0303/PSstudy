import java.util.ArrayList;


public class prog_42842 {
    public static ArrayList<int[]> commonNumbers(int number){
        ArrayList<int []> result = new ArrayList<>();
        for (int i = 3; i<(int)Math.sqrt(number)+1; i++){
            if(number % i == 0) result.add(new int[] {number%i,i});
        }
        System.out.println(result.get(0)[0]);
        return result;
    }
    public static int[] solution(int brown, int yellow){
        int[] answer = {0,0};
        ArrayList<int[]> commons = commonNumbers(brown + yellow);

        for(int[] common : commons){
            if(2*common[0] + 2*common[1] -4 == brown) return new int[] {common[0],common[1]};
        }
        return answer;
    }
    public static void main(String[] args) {
        solution(10,2);
    }
}
