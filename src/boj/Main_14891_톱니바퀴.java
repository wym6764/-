package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {
	private static int[][] wheel;
	private static int k;
	private static int cycle[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		wheel = new int[5][8];
		for(int i = 1; i <= 4; i++) {
			String s = br.readLine();
			for(int j = 0; j < s.length(); j++) {
				wheel[i][j] = s.toCharArray()[j] - '0';				
			}
		}
		k = Integer.parseInt(br.readLine());
		cycle = new int[k][2];
		StringTokenizer st;
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			cycle[i][0] = Integer.parseInt(st.nextToken());
			cycle[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < k; i++) {
			visited = new boolean[5];
			visited[cycle[i][0]] = true;
			sol(cycle[i][0], cycle[i][1]);
		}
		
		int result = 0;
		for(int i = 1; i <= 4; i++) {
			result += wheel[i][0] * Math.pow(2, i-1);
		}
		System.out.println(result);
	}
	private static boolean visited[];
	//톱니는 12시부터 시계방향으로 0~7
	private static void sol(int wheelnum, int dir) {
		int prev = wheelnum - 1;
		int next = wheelnum + 1;
		int otherdir = dir > 0 ? -1 : 1;//다른 휠의 dir
		if(prev >= 1 && wheel[prev][2] != wheel[wheelnum][6]) {
//			cyclewheel(prev, otherdir);
			if(!visited[prev]) {
				visited[prev] = true;
				sol(prev, otherdir);
			}
		}
		if(next <= 4 && wheel[next][6] != wheel[wheelnum][2]) {
//			cyclewheel(next, otherdir);
			if(!visited[next]) {
				visited[next] = true;
				sol(next, otherdir);
			}
		}
		cyclewheel(wheelnum, dir);
	}

	private static void cyclewheel(int wheelnum, int dir) {
		int temp;
		if(dir == 1) {	//시계방향으로 돌때
			temp = wheel[wheelnum][7];
			for(int i = 7; i > 0; i--) {
				wheel[wheelnum][i] = wheel[wheelnum][i-1];
			}
			wheel[wheelnum][0] = temp;
		} else {
			temp = wheel[wheelnum][0];
			for(int i = 0; i < 7; i++) {
				wheel[wheelnum][i] = wheel[wheelnum][i+1];
			}
			wheel[wheelnum][7] = temp;
		}
	}
}
