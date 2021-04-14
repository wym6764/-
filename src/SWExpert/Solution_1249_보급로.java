package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1249_보급로 {
	private static int n, INF = Integer.MAX_VALUE;
	private static int[][] arr;
	private static int result = Integer.MAX_VALUE;
	private static boolean visited[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			result = Integer.MAX_VALUE;
			visited = new boolean[n][n];
			StringTokenizer st;
			for(int i = 0; i < n; i++) {
				char[] s = br.readLine().toCharArray();
				for(int j = 0 ; j < n; j++) {
					arr[i][j] = s[j] - '0';
				}
			}
			System.out.println("#" + t + " " + dijkstra(0,0));
			
		}
	}
	
	private static int dijkstra(int startR, int startC) {
		
		boolean[][] visited = new boolean[n][n];
		int[][] minTime = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				minTime[i][j] = INF;
			}
		}
		
		minTime[startR][startC] = 0;	//출발지를 0으로 해서 얘부터 나오게함
		
		int r=0,c=0,cost=0,nr,nc;
		while (true) {
			
			cost = INF;
			// 방문하지 않은 정점 중 출발지에서 자신으로 오는 비용이 최소인 정점 선택
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(!visited[i][j] && cost > minTime[i][j]) {
						cost = minTime[i][j];
						r = i;
						c = j;
					}
				}
			}
			
			visited[r][c] = true;
			if(r==n-1 && c == n-1) return cost;
			// 선택된 정점 기준으로 방문하지 않은 나머지 정점들 자신과의 경유시의 비용과 기존 최소 비용 비교하여 최소값 업데이트
			for (int d = 0; d < 4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if(nr>= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && minTime[nr][nc] > cost+arr[nr][nc]) {
					minTime[nr][nc] = cost + arr[nr][nc];
				}
			}
			
		}

	}

	
	private static int dr[] = {0, 0, 1, -1};
	private static int dc[] = {-1, 1, 0, 0};
	
	/*
	private static void sol() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0, 0});
		visited[0][0] = true;
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.peek()[1];
			int cost = q.poll()[2];
			if(r == n-1 && c == n-1) {
				if(result > cost) result = cost;
			}
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc]) {
					q.offer(new int[] {nr, nc, cost + arr[r][c]});
					visited[nr][nc] = true;
				}
			}
		}
	}
	*/
	
	/*
	

	private static void sol(int r, int c, int cost) {
		if(cost > result) return;
		
		if(r == n-1 && c == n-1) {
			if(result > cost) result = cost;
			return;
		}
		for(int i = 0; i < 4; i++) {
			int nr = dr[i] + r;
			int nc = dc[i] + c;
			if(nr >= 0 && nc >= 0 && nr < n && nc < n) {
				if(!visited[nr][nc]) {
					visited[nr][nc] = true;
					sol(nr, nc, cost + arr[r][c]);
					visited[nr][nc] = false;
				}
			}
		}
	}
	*/
}
