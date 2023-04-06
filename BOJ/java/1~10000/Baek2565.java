import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Comparator.comparingInt;

public class Baek2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        List<int[]> lines = new ArrayList<>();
        int[] length = new int[n+1];

        for (int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines.add(new int[]{a,b});
        }

        lines.sort(comparingInt( (int[] o) -> o[0]));

        for(int i=0; i<lines.size(); i++){
            length[i] = 1;
            for(int j=0; j<i; j++){
                if(lines.get(i)[1] > lines.get(j)[1])
                    length[i] = Math.max(length[j]+1, length[i]);
            }
        }
        int maxLine = Arrays.stream(length).max().getAsInt();
        System.out.println(n-maxLine);
    }
}
