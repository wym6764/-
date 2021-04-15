package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_탈주범검거 {
	private static int n, m, r, c, l;
	private static int arr[][];
	private static int thief[][];
	private static int visited[][];
	private static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			arr = new int [n][m];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = 0;
			thief = new int[n][m];
			visited = new int[n][m];
			thief[r][c] = 1;
			sol(r, c, 0);
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(visited[i][j] == 1) result++;
				}
			}
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					System.out.print(thief[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("#" + t + " " + result);
		}
	}
	private static int dr[][] = {{0,0},{-1, 1, 0, 0}, {-1, 1}, {0, 0}, {-1, 0}, {1, 0}, {1, 0}, {-1, 0}};
	private static int dc[][] = {{0,0},{0, 0, -1, 1}, {0, 0}, {-1, 1}, {0, 1}, {0, 1}, {0, -1}, {0, -1}};
	
	private static void sol(int r, int c, int time) {
		if(time >= l) {
			return;	//시간이 지나면 리턴 
		}
		int type = arr[r][c];
		if(type== 0) return;
		for(int i = 0; i < dr[type].length; i++) {
			int nr = r + dr[type][i];
			int nc = c + dc[type][i];
			if(nr >= 0 && nc >= 0 && nr < n && nc < m && thief[nr][nc] == 0) {
				for(int j = 0; j < dr[arr[nr][nc]].length; j++) {
					if(nr + dr[arr[nr][nc]][j] == r && nc + dc[arr[nr][nc]][j] == c) {
						thief[nr][nc] = 1;
						sol(nr,nc,time+1);		
						thief[nr][nc] = 0;
					}
				}
			}
		}
		visited[r][c] = 1;
	}
}
