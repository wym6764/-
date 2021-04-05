package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503_로봇청소기 {
	public static int n, m, r, c, d;
	public static int arr[][];
	public static int result;
	public static int[] dr = {0, 0, -1, 1};
	public static int[] dc = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int count = 0;
		while(true) {
			if(arr[r][c] == 0){
				arr[r][c] = 2;
				result++;
			}		
			if(solA()) {	//a을 수행했다면
				continue;		//1번부터 진행
			} else {		//왼쪽방향에 공간이 없다면
				boolean isout = false;
				for(int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if(nr >= 0 && nc >= 0 && nr < n&& nc < m && arr[nr][nc] == 0) {
						isout = true;
					} 
				}
				if(isout) {
					if(d == 0) d = 3;
					else d--;		//왼쪽을 바라보게 함.
					continue;		//2번으로 돌아간다.
				} else {
					int nr = 0, nc = 0;
					switch(d) {
					case 0:
						nr = r + 1;
						nc = c; 
						break;
					case 1: 
						nr = r;
						nc = c - 1;
						break;
					case 2:
						nr = r - 1;
						nc = c;
						break;
					case 3: 
						nr = r;
						nc = c + 1;
						break;
					}
					if(nr >= 0 && nc >= 0 && nr < n && nc < m && arr[nr][nc] != 1) {
						r = nr;
						c = nc;
						continue;
					} else break;
				}
				
			}

		}
		System.out.println(result);
	}
	public static boolean solA() {
		int nr = 0, nc = 0;
		switch(d) {
		case 0:
			nr = r;
			nc = c - 1; 
			break;
		case 1: 
			nr = r - 1;
			nc = c;
			break;
		case 2:
			nr = r;
			nc = c + 1;
			break;
		case 3: 
			nr = r + 1;
			nc = c;
			break;
		}
		if(nr >= 0 && nc >= 0 && nr < n && nc < m) {
			if(arr[nr][nc] == 0) {	//청소하지 않은 공간이 존재한다면
				if(d == 0) d = 3;
				else d--;		//왼쪽을 바라보게 함.
				r = nr; c = nc;	//한칸 전진
				return true;
			}
		}
		
		return false;
	}
}
