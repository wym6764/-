package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_3190_뱀 {

	private static int n, k, l;
	private static int r = 1, c = 1;
	private static Queue<int[]> snake;
	private static int head[];
	private static int[][] apple;
	private static int[] changeTime;
	private static char[] dir;
	private static int[][] arr;
	private static int index;	//snake 방향 전환시 인덱스
	private static int nowdir;	//1:상 2:하 3:좌 4:우
	private static int time = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();	//보드의 크기
		k = sc.nextInt();	//사과의 개수
		apple = new int[k][2];
		arr = new int[n+1][n+1];
		for(int i = 0; i < k; i++) {
			apple[i][0] = sc.nextInt();
			apple[i][1] = sc.nextInt();
			arr[apple[i][0]][apple[i][1]] = 2;	//2 : 사과
		}
		l = sc.nextInt();
		changeTime = new int[l];
		dir = new char[l];
		for(int i = 0; i < l; i++) {
			changeTime[i] = sc.nextInt();
			dir[i] = sc.next().charAt(0);
		}
		snake = new LinkedList<>();
		snake.offer(new int[] {r, c});
		head = new int[] {r, c};
		nowdir = 4;
		while(true) {
			if(move()) break;		//게임이 종료되면 break;
			time++;
			changeDir();
		}
		
		System.out.println(time+1);
	}
	private static void changeDir() {
		if(index >= l ||changeTime[index] != time) return;	//바꿀 시간이 아니면 리턴
		if(dir[index] == 'D') {	//오른쪽으로 90도
			switch(nowdir) {
			case 1:
				nowdir = 4;
				break;
			case 2: 
				nowdir = 3;
				break;
			case 3: 
				nowdir = 1;
				break;
			case 4: 
				nowdir = 2;
				break;
			}
		} else {
			switch(nowdir) {
			case 1:
				nowdir = 3;
				break;
			case 2:
				nowdir = 4;
				break;
			case 3:
				nowdir = 2;
				break;
			case 4:
				nowdir = 1;
				break;
			}
		}
		index++;
	}
	
	private static int dr[] = {0, -1, 1, 0, 0};
	private static int dc[] = {0, 0, 0, -1, 1};
	private static boolean move() {
		int nr = head[0] + dr[nowdir];
		int nc = head[1] + dc[nowdir];
		if(nr < 1 || nc < 1 || nr >= n+1 || nc >= n+1 || arr[nr][nc] == 1) {
			return true;
		}
		if(arr[nr][nc] == 2) {//사과를 먹은경우
			arr[nr][nc] = 1;
			head[0] = nr; head[1] = nc;
			snake.offer(new int[] {nr, nc});
		}
		else {									//사과를 안먹은 경우
			arr[nr][nc] = 1;
			head[0] = nr;
			head[1] = nc;
			snake.offer(new int[] {nr, nc});
			arr[snake.peek()[0]][snake.poll()[1]] = 0;
		}
		
		return false;
	}
}
