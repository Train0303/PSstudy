import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Baek2933 {
    static char[][] matrix;
    static int r;
    static int c;
    public static void throwBar(int bar, int turn){
        if (turn%2==0) {
            for (int i = 0; i < c; i++) {
                if (matrix[bar][i] == 'x'){
                    matrix[bar][i] = '.';
                    return;
                }
            }
        }
        else{
            for (int i = c-1; i >= 0; i--){
                if (matrix[bar][i] == 'x'){
                    matrix[bar][i] = '.';
                    return;
                }
            }
        }
    }
    public static void downCluster(List<int[]> Blocks){

        while(true){
            Map<Object, Optional<int[]>> lowestBlocks = Blocks.stream()
                    .collect(groupingBy(b->b[1],
                            maxBy(comparing(b->b[0]))));

            boolean checkDown = lowestBlocks.values().stream()
                    .map(Optional::get)
                    .allMatch(l -> {
                        if (l[0] == r-1) return false;
                        return matrix[l[0] + 1][l[1]] != 'x';
                    });

            if (!checkDown) {
                return;
            }
            for (int[] block : Blocks) {
                int x = block[0];
                int y = block[1];
                matrix[x][y] = '.';
                block[0] += 1;
            }
            for (int[] block : Blocks) {
                int x = block[0];
                int y = block[1];
                matrix[x][y] = 'x';
            }
        }
    }
    public static List<int[]> separate(){
        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};
        Queue<int[]> q = new LinkedList<>();
        List<int[]> result = new ArrayList<>();
        boolean[][] visited = new boolean[r][c];

        for (int i=0;i<c;i++){
            if(matrix[r-1][i] == 'x'){
                q.add(new int[] {r-1,i});
                visited[r-1][i] = true;
            }
        }
        while (!q.isEmpty()){
            int[] node = q.peek();
            q.poll();

            for(int i=0; i<4; i++){
                int new_x = dx[i] + node[0];
                int new_y = dy[i] + node[1];
                if(0<=new_x && new_x < r && 0<=new_y && new_y <c && !visited[new_x][new_y] && matrix[new_x][new_y] == 'x'){
                    visited[new_x][new_y] = true;
                    q.add(new int[] {new_x, new_y});
                }
            }
        }

        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++){
                if(matrix[i][j] == 'x' && !visited[i][j]) {
                    result.add(new int[] {i,j});
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        matrix = new char[r][c];
        for (int i=0; i<r; i++){
            String line = bf.readLine();
            for(int j=0; j<c; j++){
                matrix[i][j] = line.charAt(j);
            }
        }

        int n = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<n; i++){
            int bar = Integer.parseInt(st.nextToken());
            throwBar(r-bar,i);
            List<int[]> sepBlock = separate();
            if(!sepBlock.isEmpty()){
                downCluster(sepBlock);

            }
        }

        StringBuilder sb = new StringBuilder();
        for (char[] chars : matrix) {
            for (char c : chars) {
                sb.append(c);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
