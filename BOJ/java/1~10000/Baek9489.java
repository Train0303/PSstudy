import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek9489 {
    static int[] people;
    static int[] parent;


    public static int countSibling(int targetIdx){
        int p = parent[targetIdx];
        if(p == -1) return 0;

        Set<Integer> uncle = new HashSet<>();
        int grandParent = parent[p];
        for(int i=1; i< parent.length; i++){
            if(parent[i] == grandParent && i != parent[targetIdx]){
                uncle.add(i);
            }
        }
        if (uncle.isEmpty()) return 0;

        int count = 0;
        for (int i=1; i< parent.length; i++){
            if(uncle.contains(parent[i])) count++;
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = 0;

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            people = new int[n+1];
            parent = new int[n+1];
            if (n == 0 && k == 0) break;

            st = new StringTokenizer(br.readLine());
            int previousNum = 0;
            int targetIdx = 0;
            for(int i=1; i<n+1; i++){
                int num = Integer.parseInt(st.nextToken());
                if (num == k) targetIdx = i;
                if (i==1) parent[i] = -1;

                else{
                    if(previousNum+1 != num) idx++;
                    parent[i] = idx;
                }
                people[i] = num;
                previousNum = num;
            }
            sb.append(countSibling(targetIdx));
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
