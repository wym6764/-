package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16234_인구이동 {
	private static int n, left, right;
	private static int arr[][];
	private static int map[][];
	private static boolean visited[][];
	private static ArrayList<int[]> list;
	private static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		left = Integer.parseInt(st.nextToken());
		right = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			visited = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0 ; j < n; j++) {
					map[i][j] = arr[i][j];
				}
			}
			boolean ischange = false;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(!visited[i][j]) {
						if(bfs(i,j)) ischange = true;
					}
				}
			}
			for(int i = 0; i < n; i++) {
				for(int j = 0 ; j < n; j++) {
					arr[i][j] = map[i][j];
				}
			}
			if(!ischange) break;
			result++;
		}
		System.out.println(result);
	}
	
	private static int dr[] = {0, 0, -1, 1};
	private static int dc[] = {-1, 1, 0, 0};
	
	private static boolean bfs(int y, int x) {
		Queue<int[]> queue = new LinkedList<>();
		list = new ArrayList<>();
		queue.offer(new int[] {y,x});
		visited[y][x] = true;
		int sum = arr[y][x];
		int sum_first = sum;
		list.add(new int[] {y, x});
		while(!queue.isEmpty()) {
			int r = queue.peek()[0];
			int c = queue.poll()[1];
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc] &&
						Math.abs(arr[r][c] - arr[nr][nc]) >= left && Math.abs(arr[r][c] - arr[nr][nc]) <= right) {
					sum += arr[nr][nc];
					queue.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					list.add(new int[] {nr, nc});
				}
			}
		}
		if(sum == sum_first) return false;
		
		for(int i = 0; i < list.size(); i++) {
			map[list.get(i)[0]][list.get(i)[1]] = sum/list.size();
		}
		return true;
	}
}
