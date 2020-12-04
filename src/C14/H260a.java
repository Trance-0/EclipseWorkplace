package C14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H260a {
	private int[][] grassland;
	private int cowsize;

	public H260a() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		cowsize = Integer.parseInt(st.nextToken());
		grassland = new int[Integer.parseInt(st.nextToken())][2];
		for (int i = 0; i < grassland.length; i++) {
			st = new StringTokenizer(br.readLine());
			grassland[i][0] = Integer.parseInt(st.nextToken());
			grassland[i][1] = Integer.parseInt(st.nextToken());
		}
		int i = 0;
		int j = 10000;
		while (i != j) {
			int mid = (i + j) / 2;
//			System.out.println(comparedis(mid)+"mid:"+mid);
			int test = comparedis(mid);
			if (test > 0) {
				i=mid+1;
			}else if(test < 0) {
				j=mid-1;
			}
			else {
				i++;
				j++;
			}
		}
		System.out.println(i);
	}

	private int comparedis(int mid) {
		int result = 1;
		int pointer = grassland[0][0];
		for (int i = 0; i < grassland.length; i++) {
			int l = grassland[i][0];
			int r = grassland[i][1];
			if(pointer + mid <=r) {
				result += (r-l)/mid;
				pointer=r;
			}
		}
		return result - cowsize;
	}

	public static void main(String[] args) throws IOException {
		H260a a = new H260a();
	}
}
