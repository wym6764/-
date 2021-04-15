package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14499_주사위굴리기 {
	private static int n, m, r, c, k;
	private static int[][] arr;
	private static int[] order;
	private static int[] dice;
	private static int top, bottom;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order = new int[k];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		dice = new int[7];	//1~6 쓸 예정
		for(int i = 0; i < k; i++) {
			roll(order[i]);
		}
	}
	private static int dr[] = {0, 0, 0, -1, 1};
	private static int dc[] = {0, 1, -1, 0, 0};
	private static void roll(int dir) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		if(nr < 0 || nc < 0 || nr >= n || nc >= m) {	//범위밖이면 암것도안함
			return;
		}
		swap(dir);	//주사위를 굴림
		if(arr[nr][nc] == 0) {
			arr[nr][nc] = dice[6];
		} else {
			dice[6] = arr[nr][nc];
			arr[nr][nc] = 0;
		}
		r = nr; c = nc;
		System.out.println(dice[1]);
	}
	private static void swap(int dir) {
		int temp;
		switch(dir) {
		case 1:
			temp = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = dice[1];
			dice[1] = temp;
			break;
		case 2: 
			temp = dice[4];
			dice[4] = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = temp;
			break;
		case 3: 
			temp = dice[2];
			dice[2] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = temp;
			break;
		case 4: 
			temp = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = dice[1];
			dice[1] = temp;
			break;
		}
	}

	
}
