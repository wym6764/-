package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15683_감시 {
	private static int n, m;
	private static int arr[][];
	private static ArrayList<int[]> cctv;
	private static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		int[][] map = new int[n][m];
		cctv = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 6) {
					map[i][j] = arr[i][j];
				}
				if(arr[i][j] != 6 && arr[i][j] != 0) {
					cctv.add(new int[] {i, j, arr[i][j]});
					map[i][j] = arr[i][j];
				}
			}
		}
		
		sol(0, map);
		System.out.println(result);
	}
	private static int dr[][][] = {		//cctv 타입, 방향, 위치
			{{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {-1, -1, -1, -1}},
			{{0, 0, 0, 0}, {1, -1, 1, -1}, {0, 0, 0, 0}, {1, -1, 1, -1}},
			{{-1, 0, -1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}, {0, -1, 0, -1}},
			{{1, 1, 0, 0}, {-1, 1, 0, 0}, {-1, -1, 0, 0}, {-1, 1, 0, 0}},
			{{-1, 1, 0, 0}, {-1, 1, 0, 0}, {-1, 1, 0, 0}, {-1, 1, 0, 0}}
	};
	private static int dc[][][] = {
			{{1, 1, 1, 1}, {0, 0, 0, 0}, {-1, -1, -1 ,-1}, {0, 0, 0, 0}},
			{{1, -1, 1, -1}, {0, 0, 0, 0}, {1, -1, 1, -1}, {0, 0, 0, 0}},
			{{0, 1, 0, 1}, {1, 0, 1, 0}, {0, -1, 0, -1}, {-1, 0, -1, 0}},
			{{0, 0, -1, 1}, {0, 0, 1, 1}, {0, 0, -1, 1}, {0, 0, -1, -1}},
			{{0, 0, -1, 1}, {0, 0, -1, 1}, {0, 0, -1, 1}, {0, 0, -1, 1}}
	};
	
	private static void sol(int cctvnum, int map[][]) {
		if(cctvnum == cctv.size()) {
			int count = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(map[i][j] == 0) {
						count++;
					}
				}
			}
			if(result > count) result = count;
			return;
		}
		int[][] copymap = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				copymap[i][j] = map[i][j];	//copymap에 복사를 해 놓음.
			}
		}
		
		int r = cctv.get(cctvnum)[0];
		int c = cctv.get(cctvnum)[1];
		int type= cctv.get(cctvnum)[2];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				int count = 1;
				while(true) {
					int nr = r + dr[type-1][i][j] * count;
					int nc = c + dc[type-1][i][j] * count;
					if(nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 6) break;
					map[nr][nc] = 9;
					count++;
				}
			}
			sol(cctvnum+1, map);
			for(int k = 0; k < n; k++) {
				for(int j = 0; j < m; j++) {
					map[k][j] = copymap[k][j];
				}
			}
			/*
			for(int j = 0; j < 4; j++) {
				int count = 1;
				while(true) {
					int nr = r + dr[type-1][i][j] * count;
					int nc = c + dc[type-1][i][j] * count;
					if(nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 6) break;
					arr[nr][nc] = 0;
					count++;
				}
			}
			*/
		}
	}
}
