import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek11053 {
    static int[] array;
    public static int binarySearch(int left ,int right, int target){

        int mid;
        while(left<right){
            mid = (left+right)/2;
            if(array[mid] < target)
                left = mid+1;
            else
                right = mid;
        }
        return right;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        array = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int j =0;
        for (int i=0; i<n; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (i==0) {
                array[j] = a;
                j++;
            }
            else if(array[j-1] < a) {
                array[j] = a;
                j++;
            }
            else {
                int idx = binarySearch(0, j, a);
                array[idx] = a;
            }
        }

        System.out.println(j);

    }
}
