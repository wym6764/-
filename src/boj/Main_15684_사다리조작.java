package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684_사다리조작 {
	private static int n, m, h;
	private static int[][] line;
	private static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		line = new int[n+1][h+1];
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	//a번 위치에서 연결
			int b = Integer.parseInt(st.nextToken());	//b와 b+1이 연결
			line[b][a] = 1;
			line[b+1][a] = 1;
		}
		
		for(int i = 0; i <= 3; i++) {
			
		}
		System.out.println(result);

	}

	
}
