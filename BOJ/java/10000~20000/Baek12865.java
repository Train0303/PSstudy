import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.*;


public class Baek12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] bag = new int [n+1][k+1];
        List<int[]> inputData = new ArrayList<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(bf.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            inputData.add(new int[]{w,v});
        }
        for(int i=1 ;i<=inputData.size(); i++){
            int w = inputData.get(i-1)[0];
            int v = inputData.get(i-1)[1];
            for(int j=1; j<=k; j++){
                if(j >= w)
                    bag[i][j] = max(bag[i-1][j-w] + v, bag[i-1][j]);
                else
                    bag[i][j] = bag[i-1][j];
            }
        }
        System.out.println(bag[n][k]);
    }
}
