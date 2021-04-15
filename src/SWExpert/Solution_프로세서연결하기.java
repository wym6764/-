package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_프로세서연결하기{
	
	static int N, max, totalCnt, min, map[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static ArrayList<int[]> list; //고려해야하는 코어만 담고있는 리스트
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>();
			max = 0;
			min = Integer.MAX_VALUE;
			totalCnt = 0;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i == 0 || j == 0 || i == N-1 || j == N-1) continue;
					if(map[i][j] == 1) {
						list.add(new int[] {i,j});
						totalCnt++;
					}
				}
			}
			
			go(0, 0);
			System.out.println("#" + tc + " " + min);
		}
	}
	
	private static void go(int index, int cCnt) {
		
		if(index == totalCnt) {
			int res = getLength();	//놓아진 전선의 길이 구하기
			
			if(max < cCnt) {
				max = cCnt;
				min = res;
			} else if(max == cCnt) {
				if(res < min) min = res;
			}
			
			return;
		}
		
		int[] cur = list.get(index);
		int r = cur[0];
		int c = cur[1];
		for(int d = 0; d < 4; d++) {
			if(isAvailable(r, c, d)) {
				setStatus(r, c, d, 2);
				go(index+1, cCnt+1);
				setStatus(r, c, d, 0);
			}
			
		}
		
		go(index+1, cCnt);
	}
	
	private static void setStatus(int r, int c, int d, int s) {
		int nr = r, nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr >= N || nc<0 || nc>=N) break;
			map[nr][nc] = s;
		}
	}
	
	private static boolean isAvailable(int r, int c, int d) {
		int nr = r, nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr >= N || nc<0 || nc>=N) break;
			if(map[nr][nc] >= 1) return false;
		}
		return true;
	}
	
	private static int getLength() {
		int lCnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 2) lCnt++;
			}
		}
		return lCnt;
	}
	
}


/*
public class Solution_프로세서연결하기 {
	private static int n;
	private static int arr[][];
	private static LinkedList<int[]> core;
	private static int maxCorenum;
	private static int corenum;
	private static int result;
	private static int length;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			core = new LinkedList<>();
			maxCorenum = 0;
			corenum = 0;
			length = 0;
			result = 987654321;
			StringTokenizer st;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 1) {
						core.add(new int[] {i, j});
					}
				}
			}
			for(int i = 0; i < core.size(); i++) {
				if(core.get(i)[0] == 0 || core.get(i)[1] == 0 ||
						core.get(i)[0] == n-1 || core.get(i)[1] == n-1) {
					core.remove(i);
				}
			}
			sol(0);
			
			System.out.println("#" + t + " " + result);
		}
		
	}
	
	private static int dr[] = {0, 0, -1, 1};
	private static int dc[] = {-1, 1, 0, 0};
	private static void sol(int corenumber) {
		if(corenumber >= core.size()) {
			if(corenum >= maxCorenum && result >= length) {
				maxCorenum = corenum;
				result = length;
			}
			return;
		}
		int r = core.get(corenumber)[0];
		int c = core.get(corenumber)[1];
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr >= 0 && nc >= 0 && nr < n && nc < n && arr[nr][nc] != 1 && arr[nr][nc] != 2) {
				if(isConnected(r, c, i)) {
					connect(r, c, i);
					corenum++;
					sol(corenumber + 1);
					corenum--;
					disConnect(r, c, i);
				}else {
					sol(corenumber + 1);
					
				}
			}
		}
		if(corenum >= maxCorenum && result >= length) {
			maxCorenum = corenum;
			result = length;
		}
		
	}
	private static void disConnect(int r, int c, int i) {
		int num = 1;
		while(true) {
			int nr = r + dr[i] * num;
			int nc = c + dc[i] * num;
			if(nr >= 0 && nc >= 0 && nr < n && nc < n && arr[nr][nc] == 2) {
				arr[nr][nc] = 0;
				length--;
			} else break;
			num++;
		}
		
	}
	private static void connect(int r, int c, int i) {
		int num = 1;
		while(true) {
			int nr = r + dr[i] * num;
			int nc = c + dc[i] * num;
			if(nr >= 0 && nc >= 0 && nr < n && nc < n) {
				arr[nr][nc] = 2;
				length++;
			} else break;
			num++;
		}
	}
	private static boolean isConnected(int r, int c, int i) {
		int num = 1;
		while(true) {
			int nr = r + dr[i] * num;
			int nc = c + dc[i] * num;
			if(arr[nr][nc] == 1 || arr[nr][nc] == 2) break;
			if(nr == 0 || nc == 0 || nr == n-1 || nc == n-1) {
				return true;
			}
			num++;
		}
		return false;
	}
}

*/