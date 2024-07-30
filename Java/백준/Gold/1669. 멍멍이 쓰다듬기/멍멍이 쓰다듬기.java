import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int x, y;
	public static void main(String[] args) throws IOException{
		input();
		solve();
	}
	public static void solve() {
		int answer = 0;
		int diff = y-x;
		if(diff != 0){
			int maxGrow = (int) Math.sqrt(diff);
			int sqrtDiff = diff - maxGrow*maxGrow;
			if( maxGrow == Math.sqrt(diff)) {
				answer = 2*maxGrow-1;
			} else {
				answer = 2*maxGrow + (sqrtDiff-1)/maxGrow;
			}
		}
		
		System.out.println(answer);
	}
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
	}
}
