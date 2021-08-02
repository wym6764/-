package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19236_청소년상어 {
	static class Fish{
		int r, c, num, dir;
		Fish(int r, int c, int num, int dir){
			this.r = r;
			this.c = c;
			this.num = num;
			this.dir = dir;
		}
	}
	private static int result;
	private static Fish arr[][];
	private static Fish shark;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new Fish[4][4];
		for(int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());			
			for(int j = 0; j < 4; j++) {
				int num =  Integer.parseInt(st.nextToken());
				int dir =  Integer.parseInt(st.nextToken());
				arr[i][j] = new Fish(i, j, num, dir);
			}
		}
		shark = new Fish(0, 0, 0, arr[0][0].dir);
		int n = arr[0][0].num;
		int d = arr[0][0].dir;
		arr[0][0] = null;
		fishMove();
		
		dfs(0, 0, d, n);
		System.out.println(result);
	}
	private static void dfs(int r, int c, int dir, int eatcount) {
		shark.r = r; shark.c = c;	//상어 위치 저장
		
		int len = 1;
		while(true) {
			int nr = r + dr[dir] * len;
			int nc = c + dc[dir] * len;
			if(nr < 0 || nc < 0 || nr >= 4 || nc >= 4) break;	//범위를 벗어나면 break
			if(arr[nr][nc] == null) {		//먹을 물고기가 없다면 다음범위 탐색
				len++; continue;
			}
			
			Fish map[][] = new Fish[4][4];
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					if(arr[i][j] == null) {
						map[i][j] = null; continue;
					}
					map[i][j] = new Fish(i,j,arr[i][j].num,arr[i][j].dir);	//map 만들어서 복사
				}
			}
			
			int eatnum = arr[nr][nc].num;
			int eatdir = arr[nr][nc].dir;
			arr[nr][nc] = null;
			fishMove();	//물고기 이동
			dfs(nr, nc, eatdir, eatcount + eatnum);	
			arr = map;	//원상복구 시킴
			len++;
		}
		
		if(eatcount > result) result = eatcount;
		
	}
	private static int dr[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	private static int dc[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	private static void fishMove() {
		int index = 1;
		while(index <= 16) {
			boolean ischange = false;
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					if(arr[i][j] != null && arr[i][j].num == index && !ischange) {
						changeFish(i, j);
						ischange = true;
						break;
					}
				}
			}
			index++;
		}
		
		
	}
	private static void changeFish(int r, int c) {		//물고기가 방향에 있는 물고기랑 스왑
		int firstdir = arr[r][c].dir;	//처음 방향 저장
		while(true) {
			int nr = r + dr[arr[r][c].dir];
			int nc = c + dc[arr[r][c].dir];
			if(nr >= 0 && nc >= 0 && nr < 4 && nc < 4 && !(nr == shark.r && nc == shark.c)) {
				Fish temp = arr[r][c];
				arr[r][c] = arr[nr][nc];
				arr[nr][nc] = temp;
				return;
			}
			if(arr[r][c].dir == 8) arr[r][c].dir = 1;
			else arr[r][c].dir++;
			if(arr[r][c].dir == firstdir) break;
		}
		
		
	}
}
