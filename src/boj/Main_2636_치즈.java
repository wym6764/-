package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2636_치즈 {
	public static int row, col;
	public static int[][] arr;
	public static boolean visited[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		arr = new int[row][col];
		for(int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < col; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int count = 0, time = 0;
		while(true) {
			visited = new boolean[row][col];
			sol(0, 0);
			time++;
			count = 0;
			boolean ismelted = true;
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					if(arr[i][j] == 1) ismelted = false;
					else if(arr[i][j] == 2) {
						arr[i][j] = 0; count++;
					}
				}
			}
			if(ismelted) break;		//다 녹으면 빠져나옴
		}
		System.out.println(time);
		System.out.println(count);
	}
	public static int dr[] = {0, 0, 1, -1};
	public static int dc[] = {1, -1, 0, 0};

	public static void sol(int r, int c) {
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr >= 0 && nc >= 0 && nr < row && nc < col) {
				if(visited[nr][nc]) continue;
				if(arr[nr][nc] == 1) {
					arr[nr][nc] = 2;
					continue;
				} else if(arr[nr][nc] == 2) continue;
				visited[r][c] = true;
				sol(nr, nc);
			}
			
		}
	}

}
