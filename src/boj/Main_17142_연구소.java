package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17142_연구소 {
	private static int n, m;
	private static int arr[][];
	private static LinkedList<int[]> virus;
	private static int numbers[];
	private static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		
		virus = new LinkedList<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 2)  virus.add(new int[] {i, j});
			}
		}
		numbers = new int[m];
		comb(0, 0);
	}
	private static int visited[][];
	private static int[][] map;
	private static Queue<int[]> queue;
	private static void comb(int cnt, int start) {
		if(cnt == m) {
			map = new int[n][n];
			visited = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(arr[i][j] == 1) map[i][j] = -1;	//벽을 -1로 복사함
					if(arr[i][j] == 2) map[i][j] = -2;	//비활성바이러스를 -2로 취급함
				}
			}
			for(int i = 0; i < 3; i++) {
				map[virus.get(i)[0]][virus.get(i)[1]] = 0;
			}
			queue = new LinkedList<>();
			for(int i = 0; i < 3; i++) {
				queue.offer(new int[] {virus.get(i)[0], virus.get(i)[1], 1});	//큐에 넣음
				visited[virus.get(i)[0]][virus.get(i)[1]] = 1;
			}
			bfs();
			return;
		}
		for(int i = start; i < virus.size(); i++) {
			numbers[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
	
	private static int dr[] = {-1, 1, 0, 0};
	private static int dc[] = {0, 0, -1, 1};
	private static void bfs() {
		while(!queue.isEmpty()) {
			int r = queue.poll()[0];
			int c = queue.poll()[1];
			int time = queue.poll()[2];
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr >= 0 && nc >= 0 && nr < n && nc < n && map[nr][nc] != -1) {
					map[nr][nc] = time+1;
				}
			}
		}
		
	}

	
}
