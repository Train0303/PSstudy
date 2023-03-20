import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek26128 {

    public static void main(String[] args) throws IOException {
        Queue<Integer> q = new LinkedList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        long answer = 0;
        boolean isCycle = true;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] graph = new int[n+1];
        int[] degree = new int[n+1];
        int[] member = new int[n+1];

        st = new StringTokenizer(bf.readLine());
        for (int i=1; i<n+1; i++){
            int num = Integer.parseInt(st.nextToken());
            graph[i] = num;
            degree[num]++;
        }

        st = new StringTokenizer(bf.readLine());
        for (int i=0; i<m; i++){
            int num = Integer.parseInt(st.nextToken());
            member[num]++;
        }

        for(int i=1; i<n+1; i++){
            if(degree[i] == 0) q.add(i);
        }

        while(!q.isEmpty()){
            int node = q.peek();
            q.poll();
            if(member[node] == m){
                isCycle = false;
                break;
            }
            member[graph[node]] += member[node];
            answer += member[node];
            member[node] = 0;
            degree[graph[node]]--;
            if(degree[graph[node]] == 0){
                q.add(graph[node]);
            }
        }

        if(!isCycle) {
            System.out.println(answer);
            return;
        }

        ArrayList<Long> numInCycle = new ArrayList<>();
        for(int i=1; i<n+1;i++){
            if(member[i] != 0){
                int nextNode = graph[i];
                numInCycle.add((long) member[i]);
                while(nextNode!=i){
                    numInCycle.add((long) member[nextNode]);
                    nextNode = graph[nextNode];
                }
                break;
            }
        }

        long sum = numInCycle.stream()
                .reduce(0L, Long::sum);

        if(sum < m) {
            System.out.println(-1);
            return ;
        }

        sum = 0;
        for (int i=1; i<numInCycle.size(); i++) {
            sum += (numInCycle.size() - i) * numInCycle.get(i);
        }

        long minSum = sum;
        for (int i=1; i<numInCycle.size(); i++){
            sum += m - numInCycle.size() * numInCycle.get(i);
            minSum = Long.min(minSum,sum);
        }
        System.out.println(answer + minSum);
    }
}
