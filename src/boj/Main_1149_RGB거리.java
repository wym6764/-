package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1149_RGB거리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int d[][] = new int[n+1][3];
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(d[i-1][1] < d[i-1][2]) d[i][0] += d[i-1][1] + r;
			else d[i][0] += d[i-1][2] + r;
			if(d[i-1][0] < d[i-1][2]) d[i][1] += d[i-1][0] + g;
			else d[i][1] += d[i-1][2] + g;
			if(d[i-1][0] < d[i-1][1]) d[i][2] += d[i-1][0] + b;
			else d[i][2] += d[i-1][1] + b;
		}
		System.out.println(Math.min(Math.min(d[n][0], d[n][1]), d[n][2]));
	}
}
