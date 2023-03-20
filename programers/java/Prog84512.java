public class prog_84512 {
    static String alpha = "AEIOU";
    static int count = -1;
    static int answer = 0;
    public static void BT(String str, String target){
        count ++;
        if(str.equals(target)) answer = count;
        if(str.length() == 5) return;
        for (char c:alpha.toCharArray()) BT(str+c,target);
    }
    public static int solution(String word){
        BT("", word);
        return answer;
    }
    public static void main(String[] args) {
        String str1 = "AAAAE";
        System.out.println(solution(str1));
    }
}
