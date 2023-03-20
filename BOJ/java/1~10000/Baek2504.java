import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek2504 {
    public static boolean isValid(String str){
        int firstConditionCount = 0;
        int secondConditionCount = 0;
        for (char c:str.toCharArray()){
            if (c == '(') firstConditionCount++;
            else if(c == ')') firstConditionCount --;
            if (firstConditionCount < 0) return false;

            if (c == '[') secondConditionCount++;
            else if (c == ']') secondConditionCount--;
            if (secondConditionCount < 0) return false;
        }
        return firstConditionCount == 0 && secondConditionCount == 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> st = new Stack<>();
        int result = 0;
        if (!isValid(str)){
            System.out.println(0);
            return;
        }

        int temp = 1;
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == '(') {
                st.push('(');
                temp *= 2;
            }
            else if(str.charAt(i) == '[') {
                st.push('[');
                temp *= 3;
            }

            else if (str.charAt(i) == ')'){
                if (str.charAt(i-1) == '(') result += temp;
                st.pop();
                temp /= 2;
            }
            else{
                if (str.charAt(i-1) == '[') result += temp;
                st.pop();
                temp /= 3;
            }
        }
        System.out.println(result);
    }
}
