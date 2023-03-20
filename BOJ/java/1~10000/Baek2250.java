import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2250 {
    static int maxDepth=0;
    static int count=0;
    static int[] nodeCount;
    static int[][] tree;
    static int[][] treeCal;

    public static void main(String[] args) throws IOException {
        int ansWidth = 0;
        int ansDepth = 100000;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int treeRoot = 1;
        nodeCount = new int[n+1];
        tree = new int[n+1][2];
        treeCal = new int[n+1][2];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int root = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            nodeCount[root]++;
            if (left != -1) nodeCount[left]++;
            if (right != -1) nodeCount[right]++;
            tree[root][0] = left;
            tree[root][1] = right;
        }
        for (int i=1; i<n+1; i++){
            if (nodeCount[i] == 1) treeRoot = i;
        }
        dfs(treeRoot,1);


        int[] maxWidth = new int[maxDepth+1];
        int[] minWidth = new int[maxDepth+1];
        Arrays.fill(minWidth, 100000);
        for (int i=1; i<n+1; i++){
            int width = treeCal[i][0];
            int depth = treeCal[i][1];
            if(minWidth[depth] > width) minWidth[depth] = width;
            if(maxWidth[depth] < width) maxWidth[depth] = width;

        }
        for (int i=1; i<maxDepth+1; i++){
            int dist = maxWidth[i] - minWidth[i] + 1;
            if(ansWidth < dist){
                ansDepth = i;
                ansWidth = dist;
            }
        }
        System.out.println(ansDepth + " " + ansWidth);
        bf.close();
    }
    public static void dfs(int idx, int depth){
        if(tree[idx][0] != -1) dfs(tree[idx][0], depth+1);
        count++;
        treeCal[idx][0] = count;
        treeCal[idx][1] = depth;
        maxDepth = Integer.max(maxDepth, depth);
        if(tree[idx][1] != -1) dfs(tree[idx][1], depth+1);
    }
}
