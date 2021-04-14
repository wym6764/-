package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7576_토마토 {
	private static int m, n;
	private static int arr[][];
	private static int result;
	private static boolean change;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			change = false;
			if(checkFullTomato()) break;
			tomato();
			if(!change) {
				System.out.println(-1); return;
			}
			result++;
		}
		System.out.println(result);
	}
	
	private static boolean checkFullTomato() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if (arr[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	private static void tomato() {
		int copy[][] = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(copy[i][j] == 1) sol(i,j);
			}
		}
	}
	private static int dr[] = {0, 0, -1, 1};
	private static int dc[] = {-1, 1, 0, 0};
	private static void sol(int r, int c) {
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr >= 0 && nc >= 0 && nr < n && nc < m && arr[nr][nc] == 0) {
				arr[nr][nc] = 1;
				change = true;
			}
		}
	}
	
	
}
