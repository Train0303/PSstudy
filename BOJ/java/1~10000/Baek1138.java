import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baek1138 {
//    static int[] condition;
//    static int n;
//    static int[] perm;
//    static boolean[] visited;
//
//    public static void BT(int cnt){
//        if (cnt == n) {
//            for(int i=0; i<n; i++){
//                int tallerCount = 0;
//                for(int j=0; j<i; j++){
//                    if(perm[j] > perm[i]) tallerCount++;
//                }
//                if (condition[perm[i]-1] != tallerCount)
//                    return;
//            }
//            for (int p : perm) {
//                System.out.print(p + " ");
//            }
//            System.out.println();
//            System.exit(0);
//        }
//
//        for (int i=0; i<n; i++){
//            if (!visited[i]){
//                visited[i] = true;
//                perm[cnt] = i+1;
//                BT(cnt+1);
//                visited[i] = false;
//            }
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        n = Integer.parseInt(bf.readLine());
//        condition = new int[n];
//        perm = new int[n];
//        visited = new boolean[n];
//
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        for (int i=0; i<n; i++){
//            condition[i] = Integer.parseInt(st.nextToken());
//        }
//
//        BT(0);
//    }

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] men = new int[n];
        List<Integer> arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0; i<n ;i++){
            men[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=n-1; i>=0; i--){
            arr.add(men[i], i+1);
        }

        for (int a : arr) {
            System.out.print(a+" ");
        }
        System.out.println();
    }
}
