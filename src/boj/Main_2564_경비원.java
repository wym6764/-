package boj;

import java.util.Scanner;

public class Main_2564_경비원 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int n = sc.nextInt();
		int result = 0;
		int[][] arr = new int[n+1][2];
		int[][] pos = new int[n+1][2];	//r, c 순서
		for(int i = 0; i <= n; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		int circum = x*y*2;
		for(int i = 0; i < n; i++) {
			switch(arr[i][0]) {
			case 1:
				pos[i][0] = 0;
				pos[i][1] = arr[i][1];
				break;
			case 2:
				pos[i][0] = x-1;
				pos[i][1] = arr[i][1];
				break;
			case 3:
				pos[i][0] = arr[i][1];
				pos[i][1] = 0;
				break;
			case 4:
				pos[i][0] = arr[i][1];
				pos[i][1] = y-1;
				break;
			}
		}
		for(int i = 0; i < n; i++) {
			if((arr[n][0] + arr[i][0]) != 3 && (arr[n][0] + arr[i][0]) != 7) {	//북남 혹은 동서가 아니면
				int dist1 = Math.abs(pos[i][0] - pos[n][0]) + Math.abs(pos[i][1] - pos[n][1]);
				int dist2 = circum - dist1;
				result += dist1 < dist2 ? dist1 : dist2;
			} else {	//북남 혹은 동서일경우
				int dist1 = pos[i][0] + pos[i][1] + pos[n][0] + pos[n][1];
				int dist2 = circum - dist1;
				result += dist1 < dist2 ? dist1 : dist2;
			}
		}
		System.out.println(result);
	}
}
