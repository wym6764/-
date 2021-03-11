package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1949_등산로조성 {
	public static int n, k;
	public static boolean isk;
	public static int result;
	public static int max;
	public static boolean isVisited[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int arr[][] = new int[n][n];
			max = 0;
			k = Integer.parseInt(st.nextToken());
			isVisited = new boolean[n][n];
			result = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(max < arr[i][j]) max = arr[i][j];
				}
			}
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(arr[i][j] == max) sol(arr, i, j, 1);
				}
			}
			
			System.out.println("#" + t + " " + result);
		}
	}
	
	public static void sol(int[][] arr, int y, int x, int len) {
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		for(int i = 0; i < 4; i++) {
			int ny = dy[i] + y;
			int nx = dx[i] + x;
			if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
				if(arr[ny][nx] < arr[y][x]) {
					if(!isVisited[ny][nx]) {
						isVisited[y][x] = true;
						sol(arr, ny, nx, len+1);
						isVisited[y][x] = false;
					}
				} else {	//높을때
					if(!isVisited[ny][nx]) {
						if(!isk) {		//k가 사용되지 않았다면
							for(int j = 1; j <= k; j++) {
								if(arr[ny][nx]-j < arr[y][x]) {
									isk = true;
									arr[ny][nx] -= j;
									isVisited[y][x] = true;
									sol(arr, ny, nx, len+1);
									arr[ny][nx] += j;
									isVisited[y][x] = false;
									isk = false;
									break;
								}
							}
						}
					}
				}
			}
		}
		if(result < len) result = len;
		
	}
	
	
	
	
}
