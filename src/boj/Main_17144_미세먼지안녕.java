package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17144_미세먼지안녕 {
	public static int r, c, t;
	public static int room[][];
	public static int result;
	public static int airr1 = 100, airr2 = 100;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		room = new int[r][c];
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < c; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j] == -1) {
					if(airr1 == 100) {
						airr1 = i;
					} else {
						airr2 = i;
					}
				}
			}
		}
		for(int i = 0; i < t; i++) {
			ext();
			clean();
		}
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				result += room[i][j];
			}
		}
		result += 2;
		System.out.println(result);
	}
	public static void ext() {
		int dr[] = {0, 0, -1, 1};
		int dc[] = {-1, 1, 0, 0};
		int temp[][] = new int[r][c];
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				temp[i][j] = 0;
			}
		}
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				int cnt = 0;
				for(int k = 0; k < 4; k++) {
					int nr = dr[k] + i;
					int nc = dc[k] + j;
					if(nr >= 0 && nc >= 0 && nr < r && nc < c) {
						if( ! ((r == airr1 && c == 0) || (r == airr2 && c == 0) ||
								(nr == airr1 && nc == 0) || (nr == airr2 && nc == 0))) {//공기청정기가 아니면 확산
							temp[nr][nc] += room[i][j]/5;
							temp[i][j] -= room[i][j]/5;
						}
					}
				}
			}
		}
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				room[i][j] += temp[i][j];
			}
		}
	}
	
	public static void clean() {
		for(int i = airr1-1; i >= 1; i--) {
			room[i][0] = room[i-1][0];
		}
		for(int i = 0; i < c-1; i++) {
			room[0][i] = room[0][i+1];
		}
		for(int i = 0; i < airr1; i++) {
			room[i][c-1] = room[i+1][c-1];
		}
		for(int i = c-1; i >= 1; i--) {
			if(i == 1) {
				room[airr1][i] = 0; continue;
			} 
			room[airr1][i] = room[airr1][i-1];
		}
		
		for(int i = airr2+1; i < r-1; i++) {
			room[i][0] = room[i+1][0];
		}
		for(int i = 0; i < c-1; i++) {
			room[r-1][i] = room[r-1][i+1];
		}
		for(int i = r-1; i > airr2; i--) {
			room[i][c-1] = room[i-1][c-1];
		}
		for(int i = c-1; i >= 1; i--) {
			if(i == 1) {
				room[airr2][i] = 0; continue;
			} 
			room[airr2][i] = room[airr2][i-1];
		}
	}
}
