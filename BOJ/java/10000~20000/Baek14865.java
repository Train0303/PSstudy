import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek14865 {
    static class Peak{
        int left;
        int right;
        boolean isParent = false;

        public Peak(int left, int right){
            this.left = left;
            this.right = right;
        }

    }
    static List<Peak> peaks = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        int n = Integer.parseInt(bf.readLine());
        int[][] data = new int[n][2];
        int containCount = 0;
        int notContainCount = 0;
        int minIndex = 0;
        for (int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            data[i][0] = x;
            data[i][1] = y;
            if(data[minIndex][0] > data[i][0]){
                minIndex = i;
            }else if(data[minIndex][1] > data[i][1]){
                minIndex = i;
            }
        }
        for (int i=minIndex; i<n+minIndex; i++){
            int idx = i%n;
            int nextIdx = (i+1)%n;
            if (data[idx][0]==data[nextIdx][0] && data[idx][1] < 0 && data[nextIdx][1] > 0) {
                stack.add(new int[]{data[idx][0], 1});
            }else if(data[idx][0]==data[nextIdx][0] && data[idx][1] > 0 && data[nextIdx][1] < 0) {
                if (!stack.isEmpty() && stack.peek()[1] == 1) {
                    int[] d = stack.peek();
                    stack.pop();
                    peaks.add(new Peak(Math.min(d[0], data[idx][0]), Math.max(d[0], data[idx][0])));
                } else {
                    stack.add(new int[]{data[idx][0], 0});
                }
            }
        }

        ArrayDeque<Peak> peakStack = new ArrayDeque<>();
        peaks.sort(Comparator.comparing((Peak p) -> p.left));
        int totalPeak = peaks.size();

        for (Peak p: peaks){
            if(peakStack.isEmpty()){
                peakStack.push(p);
                notContainCount++;
            }else{
                while(!peakStack.isEmpty() && !(peakStack.peek().right > p.left)){
                    Peak peak = peakStack.peek();
                    containCount += peak.isParent ? 1 : 0;
                    peakStack.pop();
                }
                if (peakStack.isEmpty()){
                    notContainCount++;
                }else if(peakStack.peek().right > p.left){
                    peakStack.peek().isParent = true;
                }
                peakStack.push(p);
            }
        }
        while(!peakStack.isEmpty()){
           containCount += peakStack.peek().isParent ? 1 : 0;
           peakStack.pop();
        }

        System.out.println(notContainCount + " " + (totalPeak-containCount));
    }
}

