package BOJ;

import java.util.Scanner;

public class Main_17825_주사위윷놀이 {
	private static int dice[];
	private static int mal[][];
	private static int board[][] = {
			{0,2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
			{0,10, 13, 16, 19, 25, 30, 35, 40},
			{0,20, 22, 24, 25, 30, 35, 40},
			{0,30, 28, 27, 26, 25, 30, 35, 40}
	};
	private static int result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		dice = new int[10];
		for(int i = 0; i < 10; i++) {
			dice[i] = sc.nextInt();
		}
		mal = new int[4][3];		//[말번호][0: 어느 보드위인지, 1: 도착했는지, 2: 인덱스];
		dfs(0, 0);
		System.out.println(result);
	}
	private static void dfs(int time, int score) {
		if(time == 10) {
			if(result < score) result = score;
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int newmal[] = new int[3];
			newmal[0] = mal[i][0];
			newmal[1] = mal[i][1];
			newmal[2] = mal[i][2];
			if(newmal[1] == 1) continue;	//도착했다면 다른말 이동
			if(newmal[0] == 0 && newmal[2] == 5) {
				newmal[0] = 1; newmal[2] = 1;
			}
			if(newmal[0] == 0 && newmal[2] == 10) {
				newmal[0] = 2; newmal[2] = 1;
			}
			if(newmal[0] == 0 && newmal[2] == 15) {
				newmal[0] = 3; newmal[2] = 1;
			}
			if(board[newmal[0]].length-1 > newmal[2] + dice[time]) {	//해당 게임판 안에서 이동할경우
				boolean dup = false;
				for(int m = 0; m < 4; m++) {
					if(m == i) continue;
					if(mal[m][0] == newmal[0] && mal[m][1] == 0 && mal[m][2] == newmal[2] + dice[time]) dup = true;
				}
				if(dup) continue;		//다른말이랑 중복될경우 continue;
				newmal[2] += dice[time];
				dfs(time + 1, score + board[newmal[0]][newmal[2]]);
			} else {	//게임판을 벗어난경우 도착
				newmal[1] = 1;
				dfs(time + 1, score);
			}
		}

	}
}
