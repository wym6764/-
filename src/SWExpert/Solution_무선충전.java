package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_무선충전 {
	public static int m, a; 	//총 이동 시간, BC의 개수
	public static int[] moveA, moveB;
	public static int[][] bc;
	public static int[] userA, userB;
	public static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());	//총 이동 시간
			a = Integer.parseInt(st.nextToken());	//bc의 개수
			moveA = new int[m];
			moveB = new int[m];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			bc = new int[a][4];
			for(int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 4; j++) {
					bc[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//
			result = 0;	//결과값 초기화
			userA = new int[]{1, 1};
			userB = new int[]{10, 10};
			
			for(int k = 0; k < m; k++) {	//이동
				charge();
				move(k);
			}
			charge();
			System.out.println(result);
		}
	}
	
	private static void charge() {
		boolean selectA[] = new boolean[a];	
		for(int i = 0; i < a; i++) {
			if(getDistance(userA[0], userA[1], bc[i][0], bc[i][1]) <= bc[i][2]) {	//거리 내이면
				selectA[i] = true;				
			}
		}
		boolean selectB[] = new boolean[a];	
		for(int i = 0; i < a; i++) {
			if(getDistance(userB[0], userB[1], bc[i][0], bc[i][1]) <= bc[i][2]) {
				selectB[i] = true;
			}
		}
		int maxA = 0;
		int maxindex = -1;
		for(int i = 0; i < a; i++) {
			if(selectA[i] && maxA < bc[i][3]) {
				maxA = bc[i][3]; maxindex = i;
			}
		}
		int maxB = 0;
		for(int i = 0; i < a; i++) {
			if(selectB[i] && maxB < bc[i][3] && i != maxindex)
				maxB = bc[i][3];
		}

		int max = 0;
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < a; j++) {
				if(i != j && selectA[i] && selectB[j] && max < bc[i][3]+bc[j][3])
					max = bc[i][3] + bc[j][3];
			}
		}
		
		
		result += (maxA + maxB) < max ? max : maxA + maxB;
	}
	
	private static double getDistance(int x1, int y1, int x2, int y2) {		//거리구하는 함수
		return Math.abs(x1-x2) + Math.abs(y1 - y2);
	}
	
	private static int[] dx = {0, 0, 1, 0, -1};
	private static int[] dy = {0, -1, 0, 1, 0};
	private static void move(int index) {
		userA[0] += dx[moveA[index]];
		userA[1] += dy[moveA[index]];
		userB[0] += dx[moveB[index]];
		userB[1] += dy[moveB[index]];
	}
}
