package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20061_모노미노도미노 {
	private static int result;
	private static int order[][];
	private static int arr[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		order = new int[n][3];
		arr = new int[10][10];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			order[i][0] = Integer.parseInt(st.nextToken());
			order[i][1] = Integer.parseInt(st.nextToken());
			order[i][2] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < n; i++) {
			switch(order[i][0]) {
			case 1: 
				block1(order[i][1], order[i][2]);
				break;
			case 2: 
				block2(order[i][1], order[i][2]);
				break;
			case 3: 
				block3(order[i][1], order[i][2]);
				break;
			}
			while(true) {
				if(!getScore()) break;
			}
			if(arr[0][5] == 1 || arr[1][5] == 1 || arr[2][5] == 1 || arr[3][5] == 1) {
				moveBlock(9, true);	//마지막줄 기준으로 한줄씩 옮김
			}
			if(arr[0][5] == 1 || arr[1][5] == 1 || arr[2][5] == 1 || arr[3][5] == 1) {
				moveBlock(9, true);	//한번 더 실행
			}
			if(arr[5][0] == 1 || arr[5][1] == 1 || arr[5][2] == 1 || arr[5][3] == 1) {
				moveBlock(9, false);
			}
			if(arr[5][0] == 1 || arr[5][1] == 1 || arr[5][2] == 1 || arr[5][3] == 1) {
				moveBlock(9, false);
			}
		}
		System.out.println(result);
		System.out.println(getCount());
		System.out.println();
	}
	private static int getCount() {
		int count = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 6; j < 10; j++) {
				count += arr[i][j];
				count += arr[j][i];
			}
		}
		return count;
	}
	private static boolean getScore() {		//점수 계산 후 블록정리
		boolean isscored = false;
		for(int i = 9; i >= 6; i--) {
			if(arr[0][i] == 1 && arr[1][i] == 1 && arr[2][i] == 1 && arr[3][i] == 1) {
				result++;	//점수 획득
				isscored = true;
				moveBlock(i, true);
			}
		}
		
		for(int i = 9; i >= 6; i--) {
			if(arr[i][0] == 1 && arr[i][1] == 1 && arr[i][2] == 1 && arr[i][3] == 1) {
				result++;
				isscored = true;
				moveBlock(i, false);
			}
		}
		return isscored;
	}
	private static void moveBlock(int start, boolean isblue) {
		if(isblue) {		//파란블록 경우
			for(int i = start; i >= 4; i--) {
				arr[0][i] = arr[0][i-1];
				arr[1][i] = arr[1][i-1];
				arr[2][i] = arr[2][i-1];
				arr[3][i] = arr[3][i-1];
			}
		} else {			//초록블록 경우
			for(int i = start; i >= 4; i--) {
				arr[i][0] = arr[i-1][0];
				arr[i][1] = arr[i-1][1];
				arr[i][2] = arr[i-1][2];
				arr[i][3] = arr[i-1][3];
			}
		}
	}
	private static void block3(int x, int y) {
		int r1 = x, r2 = x+1, c = y;
		while(true) {
			int nc = c + 1;
			if(nc < 10 && arr[r1][nc] != 1 && arr[r2][nc] != 1) {
				c = nc;
			} else break;
		}
		arr[r1][c] = 1;
		arr[r2][c] = 1;
		
		int r = x+1; c = y;
		while(true) {
			int nr = r + 1;
			if(nr < 10 && arr[nr][c] != 1) {
				r = nr;
			} else break;
		}
		arr[r][c] = 1;
		arr[r-1][c] = 1;
	}
	private static void block2(int x, int y) {
		int r = x, c = y+1; 
		while(true) {
			int nr = r;
			int nc = c + 1;
			if(nc < 10 && arr[nr][nc] != 1) {
				r = nr; c = nc;
			} else break;
		}
		arr[r][c] = 1;
		arr[r][c-1] = 1;
		

		r = x; int c1 = y, c2 = y+1;
		while(true) {
			int nr = r + 1;
			if(nr < 10 && arr[nr][c1] != 1 && arr[nr][c2] != 1) {
				r = nr;
			} else break;
		}
		arr[r][c1] = 1; arr[r][c2] = 1;
	}
	private static void block1(int x, int y) {	//1x1 블럭 놓았을경우
		int r = x, c = y;
		while(true) {	//파란블록으로 이동시
			int nr = r;
			int nc = c + 1;
			if(nc < 10 && arr[nr][nc] != 1) {	//넘어서지 않고 블럭이 없다면
				r = nr; c = nc;
			} else break;
		}
		arr[r][c] = 1;	//블록을 놓음.
		
		r = x; c = y;
		while(true) {
			int nr = r + 1;
			int nc = c;
			if(nr < 10 && arr[nr][nc] != 1) {
				r = nr; c = nc;
			} else break;
		}
		arr[r][c] = 1;
	}
}









