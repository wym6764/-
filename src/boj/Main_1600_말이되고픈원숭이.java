package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600_말이되고픈원숭이 {
	public static int[][] arr;
	public static int k, width, height;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());	//k번 움직일수있음
		StringTokenizer st = new StringTokenizer(br.readLine());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		arr = new int[height][width];
		for(int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < width; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(sol());
		
	}
	
	public static int dh8[] = {-1, -2, -2, -1, 1, 2, 2, 1};
	public static int dw8[] = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static int dh4[] = {0, 0, 1, -1};
	public static int dw4[] = {1, -1, 0, 0};
	public static int sol() {
		boolean visited[][][] = new boolean[height][width][k+1];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0,k,0});
		visited[0][0][k] = true;
		while(!q.isEmpty()) {
			int h = q.peek()[0];
			int w = q.peek()[1];
			int horse = q.peek()[2];	//말로 움직일 수 있는 횟수
			int time = q.poll()[3];		//움직인 횟수
			if(h == height - 1 && w == width - 1) {
				return time;
			}
			for(int i = 0; i < 8; i++) {
				int nh = h + dh8[i];
				int nw = w + dw8[i];
				if(nh >= 0 && nw >= 0 && nh < height && nw < width && horse > 0 && !visited[nh][nw][horse-1] && arr[nh][nw] != 1 ) {
					visited[nh][nw][horse-1] = true;
					q.offer(new int[] {nh, nw, horse-1, time+1});
				}
			}
			for(int i = 0; i < 4; i++) {
				int nh = h + dh4[i];
				int nw = w + dw4[i];
				if(nh >= 0 && nw >= 0 && nh < height && nw < width && !visited[nh][nw][horse] && arr[nh][nw] != 1) {
					visited[nh][nw][horse] = true;
					q.offer(new int[] {nh, nw, horse, time+1});
				}
			}
		}
		return -1;
	}
}
