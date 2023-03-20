import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek1701 {
    public static int makeTable(String subString){
        int n = subString.length();
        int[] table = new int[n];
        int j = 0;
        int max = 0;
        for(int i=1; i<n; i++){
            while(j>0 && subString.charAt(i) != subString.charAt(j)){
                j = table[j-1];
            }
            if(subString.charAt(i) == subString.charAt(j)){
                j++;
                table[i] = j;
                max = Math.max(max, j);
            }
        }
        return max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        int n = s.length();
        int answer = 0;
        for(int i=0; i<n; i++){
            String subString = s.substring(i);
            int maxTable = makeTable(subString);
            answer = Math.max(answer, maxTable);
        }
        System.out.println(answer);
    }
}
