import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.Comparator.comparingInt;

public class Baek15922 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<int[]> lines = new ArrayList<>();
        int totalLength = 0;
        int n = Integer.parseInt(bf.readLine());
        for (int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lines.add(new int[] {start, end});
        }

        lines.sort(comparingInt((int[] d)->d[0]).
                thenComparingInt((int[] d) -> d[1]));

        int start = lines.get(0)[0];
        int end = lines.get(0)[1];
        for(int i=1; i<lines.size();i++){
            int s = lines.get(i)[0];
            int e = lines.get(i)[1];
            if(s > end){
                totalLength += (end - start);
                start = s;
                end = e;
            } else if (e > end) {
                end = e;
            }
        }
        totalLength += (end - start);
        System.out.println(totalLength);
    }
}
