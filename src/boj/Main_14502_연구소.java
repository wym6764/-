package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14502_연구소 {
	public static int row, col;
	public static int[][] arr;
	public static int result;
	public static ArrayList<Integer> list;
	public static ArrayList<Integer> virus;
	public static int[] numbers;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		arr = new int[row][col];
		list = new ArrayList<>();
		virus = new ArrayList<>();
		numbers = new int[3];
		for(int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < col; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0)
					list.add(i*col + j);
				if(arr[i][j] == 2)
					virus.add(i*col + j);
			}
		}
		
		comb(0, 0);
		System.out.println(result);
	}
	//numbers[0] == 6 && numbers[1] ==13 && numbers[2] ==20
	public static void comb(int cnt, int start) {
		if(cnt == 3) {
			int[][] map = new int[row][col];
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					map[i][j] = arr[i][j];
				}
			}
			//numbers에 벽을 세울 위치 번호가 저장된상태
			
			for(int i = 0; i < 3; i++) {
				map[numbers[i]/col][numbers[i]%col] = 1;	//벽을 세개 세움
			}
			for(int i = 0; i < virus.size(); i++) {		//바이러스 각각 퍼뜨림
				dfs(map, virus.get(i)/col, virus.get(i)%col);
			}
			int count = 0;
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					if(map[i][j] == 0) count++;
				}
			}
			if(count > result) result = count;
			return;
		}
		for(int i = start; i < list.size(); i++) {
			numbers[cnt] = list.get(i);
			comb(cnt+1, i+1);
		}
	}
	
	public static int[] dr = {0, 0, 1, -1};
	public static int[] dc = {1, -1, 0, 0};
	public static void dfs(int[][] map, int r, int c) {
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr >= 0 && nc >= 0 && nr < row && nc < col && map[nr][nc] == 0) {
				map[nr][nc] = 2;
				dfs(map, nr, nc);
			}
		}
	}
}
