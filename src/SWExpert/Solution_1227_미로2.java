package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1227_미로2 {
	public static int arr[][];
	public static int ey, ex;	//도착점
	public static int result;
	public static boolean visited[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 1; t <= 10; t++) {
			result = 0;			//결과 초기화
			br.readLine();		//번호 지우기
			arr = new int[100][100];
			visited = new boolean[100][100];
			for(int i = 0; i < 100; i++) {
				String s = br.readLine();
				for(int j = 0; j < 100; j++) {
					arr[i][j] = s.toCharArray()[j] - 48;
					if(arr[i][j] == 3) {
						ey = i; ex = j;
					}
				}
			}
			sol(1, 1);
			System.out.println("#" + t + " " + result);
		}
		
	}
	
	
	public static void sol(int y, int x) {
		if(arr[y][x] == 3) {
			result = 1;
			return;
		}
		
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(nx >= 0 && ny >= 0 && nx < 100 && ny < 100 && arr[ny][nx] != 1 && !visited[ny][nx]) {
				visited[y][x] = true;
				sol(ny, nx);
				visited[y][x] = false;
			}
		}
	}
}
