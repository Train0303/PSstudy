import java.util.ArrayList;

public class prog_87946 {
    static int[][] globalDungeons;

    static ArrayList<int[]> dungeonList;
    static boolean[] visited;
    static int maxCount = -1;
    public static void BT(int cnt, int k){
        if (cnt == globalDungeons.length){
            maxCount = Math.max(maxCount,counting(k));
            return;
        }

        for(int i=0; i<globalDungeons.length; i++){
            if (!visited[i]){
                visited[i] = true;
                dungeonList.add(globalDungeons[i]);
                BT(cnt+1, k);
                visited[i] = false;
                dungeonList.remove(dungeonList.size()-1);
            }
        }
    }
    public static int solution(int k, int[][] dungeons){
        globalDungeons = dungeons;
        dungeonList = new ArrayList<>();
        visited = new boolean[dungeons.length];
        BT(0,k);
        return maxCount;
    }
    public static int counting(int num){
        int count = 0;

        for (int[] dungeon:dungeonList){
            if (num >= dungeon[0]){
                count++;
                num -= dungeon[1];
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int[][] dungeons = new int[][] {
                {80, 20}, {50, 40}, {30, 10}
        };
        solution(80,dungeons);
        System.out.println(maxCount);
    }
}
