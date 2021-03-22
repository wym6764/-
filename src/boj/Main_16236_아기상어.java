package boj;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_16236_아기상어 {
	static int N;
	static int[][] map;
//	static int[] shark = new int[4];  // 0 : x, 1 : y, 2 : size, 3 : eat
	static class Shark {
		int x, y, size = 2, eat;
		Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}
		void eat() {
			map[x][y] = 0;  // 물고기를 먹기 전의 상어 좌표를 0으로
			x = fish[0];
			y = fish[1];
			if (size == ++eat) {
				size++;
				eat = 0;
			}
		}
	}
	static Shark shark;
	static int[] fish;  // 상어가 먹을 물고기
	static int moveCnt;  // 상어가 물고기를 먹기 위해 움직인 총 시간
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {			
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {  // 상어 위치 찾기
					shark = new Shark(i, j);
				}
			}
		}
		
		// 상어가 물고기를 먹을때까지 계속 호출한다.
		while(bfs());
		
		System.out.println(moveCnt);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {shark.x, shark.y});  // 탐색의 시작점은 상어 위치 부터
		boolean[][] visited = new boolean[N][N];
		visited[shark.x][shark.y] = true;
		
		int lvl = 0;
		while (!q.isEmpty()) {
			int size = q.size();  // 움직이는 시간이 같은 애들의 크기
			lvl++;
			for (int i = 0; i < size; i++) {
				int[] loc = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = loc[0] + dx[d];
					int ny = loc[1] + dy[d];
					// 범위 밖에 있다면 건너뛰기
					if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
					// 기존 방문했던 위치
					if (visited[nx][ny]) continue;
					// 물고기의 크기가 상어의 크기보다 크다면 이동 불가능
					if (map[nx][ny] > shark.size) continue;
					
					visited[nx][ny] = true;
					
					// 움직이는 것만 가능한 경우
					if (map[nx][ny] == 0 || map[nx][ny] == shark.size) {
						q.offer(new int[] {nx, ny});
					}
					// 먹을수 있는 물고기인 경우
					else {
						// 처음 찾은 물고기 일 경우
						if (fish == null) {
							fish = new int[] {nx, ny};
						} else {
							if (fish[0] > nx || (fish[0] == nx && fish[1] > ny)) {
								fish[0] = nx;
								fish[1] = ny;
							}
						}
					}
				}
			}
			if (fish != null) {  // 먹을 물고기가 있는 경우
				shark.eat();
				moveCnt += lvl;
				fish = null;
				return true;
			}
		}
		return false;  // 모든 위치에 먹을 물고기가 없는 경
	}
}

