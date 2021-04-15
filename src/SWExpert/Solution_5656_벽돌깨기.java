package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기 {
	private static int n, w, h;
	private static int[][] arr;
	private static int numbers[];
	private static int result = 987654321;
	public static void main(String[] args) throws IOException{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			arr = new int[h][w];
			numbers = new int[n];
			result = 987654321;
			for(int i = 0; i < h ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			comb(0, 0);
			System.out.println("#" + t + " " + result);
		}
	}
	private static int map[][];
	private static void comb(int cnt, int start) {
		if(cnt == n) {
			map = new int[h][w];
			for(int i = 0; i < h ; i++) {
				for(int j = 0; j < w; j++) {
					map[i][j] = arr[i][j];
				}
			}
			for(int i = 0; i < n; i++) {
				crashBlock(numbers[i]);
			}
			int count = 0;
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(map[i][j] != 0) count++;
				}
			}
			result = result < count ? result : count;
			return;
		}
		for(int i = 0; i < w; i++) {
			numbers[cnt] = i; 
			comb(cnt+1, i + 1);
		}
		
	}
	private static void crashBlock(int num) {
		for(int i = 0; i < h; i++) {
			if(map[i][num] != 0) {	//첫번째 블록
				bomb(i, num);		//블록 터뜨리기
				sort();
				break;
			}
		}
		
	}
	private static void sort() {
		for(int j = 0; j < w; j++) {
			int index = h-1;
			for(int i = h-1; i >= 0; i--) {
				if(map[i][j] != 0) {
					int temp = map[i][j];
					map[i][j] = 0;
					map[index--][j] = temp;
				}
			}
		}
		
	}
	private static int dr[] = {0, 0, -1, 1};
	private static int dc[] = {-1, 1, 0, 0};
	private static void bomb(int r, int c) {
		if(map[r][c] == 0) return;
		if(map[r][c] == 1) {
			map[r][c] = 0; return;
		}
		int len = map[r][c]-1;
		map[r][c] = 0;
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			for(int j = 1; j <= len; j++) {
				nr = r + dr[i] * j;
				nc = c + dc[i] * j;
				if(nr >= 0 && nc >= 0 && nr < h && nc < w) {
					bomb(nr, nc);
				}
			}
		}
		
	}
}
