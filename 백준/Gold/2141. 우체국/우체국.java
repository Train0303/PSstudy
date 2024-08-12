import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 int N  = Integer.parseInt(br.readLine());
		 long [][] villages = new long[N][2];
		 
		 long totalPopulation = 0;
		 for(int i = 0; i<N; i++) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 // 마을 위치 : X[i]
			 villages [i][0] = Long.parseLong(st.nextToken());
			 // 마을 인구: A[i]
			 villages [i][1] = Long.parseLong(st.nextToken());
			 	 
			 totalPopulation += villages[i][1];
			 
		 }
		 
		 // 마을 위치기준으로 정렬
		 Arrays.sort(villages, Comparator.comparingLong(o -> o[0]));
		 
		
		 long cumulativePopulation = 0;
		 long medianLocation = 0;
		 for(int i =0; i<N; i++) {
			 cumulativePopulation += villages[i][1];
			 if(cumulativePopulation>=(totalPopulation+1)/2) {
				 medianLocation = villages[i][0];
				 break;
			 }
		 }
		 System.out.println(medianLocation);
	}
}
