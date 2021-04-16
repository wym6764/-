package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500_테트로미노 {
	private static int n, m;
	private static int[][] arr;
	private static int result;
	private static boolean visited[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new boolean[n][m]; 
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				visited[i][j] = true;
				sol(0, 0, i, j);
				visited[i][j] = false;
			}
		}
		System.out.println(result);
	}
	private static int dr[] = {0, 0, -1, 1};
	private static int dc[] = {-1, 1, 0, 0};
	
	private static void sol(int cnt, int sum, int r, int c) {
		if(cnt == 4) {
			if(result < sum) result = sum;
			return;
		}
		int nr, nc;
		int nr2, nc2;
		if(cnt == 1) {
			for(int i = 0; i < 4; i++) {
				for(int j = i+1; j < 4; j++) {
					if(i != j) {
						nr = r + dr[i]; nc = c + dc[i];
						nr2 = r + dr[j]; nc2 = c + dc[j];
						if(nr >= 0 && nc >= 0 && nr < n && nc < m &&
							nr2 >= 0 && nc2 >= 0 && nr2 < n && nc2 < m &&
							!visited[nr][nc] && !visited[nr2][nc2]) {
							
							visited[nr][nc] = true;
							visited[nr2][nc2] = true;
							sol(cnt + 3, sum +arr[r][c] + arr[nr][nc] + arr[nr2][nc2], nr,nc);
							visited[nr][nc] = false;
							visited[nr2][nc2] = false;
						}
					}
				}
			}
		}
		for(int i = 0; i < 4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(nr >= 0 && nc >= 0 && nr < n && nc < m && !visited[nr][nc]) {
				visited[nr][nc] = true;
				sol(cnt + 1, sum + arr[r][c], nr, nc);
				visited[nr][nc] = false;
			}
		}
		
	}
}
