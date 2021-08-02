package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15685_드래곤커브 {
	private static int n;
	private static int dragonCurve[][];
	private static int arr[][];
	private static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		dragonCurve = new int[n][4];
		arr = new int[101][101];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			dragonCurve[i][0] = Integer.parseInt(st.nextToken());
			dragonCurve[i][1] = Integer.parseInt(st.nextToken());
			dragonCurve[i][2] = Integer.parseInt(st.nextToken());
			dragonCurve[i][3] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n; i++) {
			makeCurve();
		}
	}
	private static void makeCurve() {
		
		
	}
	
}
