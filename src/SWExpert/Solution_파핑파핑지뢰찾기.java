package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution_파핑파핑지뢰찾기 {
	private static int result;
	private static int n;
	private static char arr[][];
	private static int numbers[][];
	private static boolean visited[][];	//dfs
	private static ArrayList<int[]> zerolist;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			arr = new char[n][n];
			numbers = new int[n][n];
			zerolist = new ArrayList<>();
			visited = new boolean[n][n];
			result = 0;
			for(int i = 0; i < n; i++) {
				String s = br.readLine();
				for(int j = 0; j < n; j++) {
					arr[i][j] = s.charAt(j);
				}
			}
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(arr[i][j] == '.') {
						findNum(i, j);
					}
				}
			}
			
			for(int i = 0; i < zerolist.size(); i++) {
				if(!visited[zerolist.get(i)[0]][zerolist.get(i)[1]]) {
					visited[zerolist.get(i)[0]][zerolist.get(i)[1]] = true;
					dfs(zerolist.get(i)[0], zerolist.get(i)[1]);
					result++;
				}
			}
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(!visited[i][j] && arr[i][j] == '.') {
						result++;
					}
				}
			}
			
			System.out.println("#" + t + " " + result);
		}
	}
	private static void dfs(int r, int c) {
		for(int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc] && arr[nr][nc] != '*') {
				visited[nr][nc] = true;
				if(numbers[nr][nc] == 0) dfs(nr, nc);
			}
		}
	}
	private static int dr[] = {-1, -1, -1, 0, 0, 1, 1, 1};
	private static int dc[] = {-1, 0, 1, -1, 1, -1, 0, 1};
	private static void findNum(int r, int c) {
		for(int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr >= 0 && nc >= 0 && nr < n && nc < n) {
				if(arr[nr][nc] == '*') {
					numbers[r][c]++;
				}
			}
		}
		if(numbers[r][c] == 0) zerolist.add(new int[] {r, c});
	}
}
