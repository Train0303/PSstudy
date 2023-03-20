import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek9328 {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int n,m;
    static char[][] matrix;
    static Set<Character> keySet;

    static boolean[][] visited;
    static int count;
    static Queue<int[]> q;
    static Queue<int[]> tempQ;
    static boolean isNeedUpdate;

    public static void bfs(){
        while(!q.isEmpty()){
            int[] node = q.peek(); q.poll();
            int x = node[0];
            int y = node[1];
            if(matrix[x][y] == '$'){
                matrix[x][y] = '.';
                count++;
            }else if(Character.isUpperCase(matrix[x][y]) && keySet.contains(Character.toLowerCase(matrix[x][y]))){
                matrix[x][y] = '.';
            }
            else if(Character.isUpperCase(matrix[x][y])){
                tempQ.add(new int[]{x,y});
                continue;
            }else if(Character.isLowerCase(matrix[x][y])) {
                keySet.add(matrix[x][y]);
                matrix[x][y] = '.';
                isNeedUpdate = true;
            }

            for(int i=0; i<4; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];
                if(newX>=0 && newX<n && newY>=0 && newY<m && matrix[newX][newY] != '*' && !visited[newX][newY]){
                    visited[newX][newY] = true;
                    q.add(new int[] {newX,newY});
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t=0; t<TC; t++){
            count = 0;
            keySet = new HashSet<>();
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            matrix = new char[n][m];
            visited = new boolean[n][m];
            for(int i=0; i<n; i++){
                String data = bf.readLine();
                for(int j=0; j<m; j++){
                    matrix[i][j] = data.charAt(j);
                }
            }
            String keys = bf.readLine();
            if(!keys.equals("0")){
                for(char c: keys.toCharArray()){
                    keySet.add(c);
                }
            }

            tempQ = new ArrayDeque<>();
            q = new ArrayDeque<>();
            for(int i=0; i<n; i++){
                if(matrix[i][0] != '*' && !visited[i][0]){
                    q.add(new int[]{i,0});
                    visited[i][0] = true;
                }
                if(matrix[i][m-1] != '*' && !visited[i][m-1]) {
                    q.add(new int[]{i, m - 1});
                    visited[i][m - 1] = true;
                }
            }

            for(int i=0; i<m; i++){
                if(matrix[0][i] != '*' && !visited[0][i]){
                    q.add(new int[]{0,i});
                    visited[0][i] = true;
                }
                if(matrix[n-1][i] != '*' && !visited[n-1][i]){
                    q.add(new int[]{n-1,i});
                    visited[n-1][i] = true;
                }
            }
            do{
                isNeedUpdate = false;
                bfs();
                while(!tempQ.isEmpty()){
                    q.add(tempQ.peek()); tempQ.poll();
                }
            }while(isNeedUpdate);
            sb.append(count);
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
