package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17822_원판돌리기 {
	private static int n, m, t;
	private static int circle[][];	//원판
	private static int order[][];	//x, d, k
	private static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	//반지름이 1,,2,,n인 원판
		m = Integer.parseInt(st.nextToken());	//정수의 개수
		t = Integer.parseInt(st.nextToken());	//몇번 회전시킬지
		circle = new int[n+1][m+1];
		order = new int[t][3];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= m; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			order[i][0] = Integer.parseInt(st.nextToken());
			order[i][1] = Integer.parseInt(st.nextToken());
			order[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < t; i++) {	///t번 회전시킴.
			cycle(order[i][0], order[i][1], order[i][2]);
			delete();
		}
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				result += circle[i][j];
			}
		}
		System.out.println(result);
	}
	private static void delete() {
		int copy[][] = new int[n+1][m+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				copy[i][j] = circle[i][j];
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(j == 1) {
					if(circle[i][1] != 0 && circle[i][1] == circle[i][m] || circle[i][1] == circle[i][2]) {
						copy[i][1] = 0;
					}
				} else if(j == m) {
					if(circle[i][m] != 0 && circle[i][m] == circle[i][m-1] || circle[i][m] ==circle[i][1]) {
						copy[i][m] = 0;
					}
				} else {
					if(circle[i][j] != 0 && circle[i][j-1] == circle[i][j] || circle[i][j+1] == circle[i][j]) {
						copy[i][j] = 0;
					}
				}
			}
		}
		
		for(int j = 1; j <= m; j++) {
			for(int i = 1; i <= n; i++) {
				if(i == 1) {
					if(circle[1][j] != 0 && circle[1][j] == circle[2][j]) {
						copy[1][j] = 0;
					}
				} else if(i == n) {
					if(circle[n][j] != 0 && circle[n][j] == circle[n-1][j]) {
						copy[n][j] = 0;
					}
				} else {
					if(circle[i][j] != 0 && circle[i][j] == circle[i-1][j] || circle[i][j] == circle[i+1][j]) {
						copy[i][j] = 0;
					}
				}
			}
		}
		
		//이제부터 평균구하고 빼기
		
		boolean isdelected = false;
		int sum = 0, count = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(circle[i][j] != copy[i][j]) {
					circle[i][j] = copy[i][j];
					isdelected = true;
				}
				if(circle[i][j] != 0) {
					sum += circle[i][j];
					count++;
				}
			}
			
		}
		if(!isdelected) {
			double avg = (double)sum / count;
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= m; j++) {
					if(circle[i][j] != 0) {
						if(circle[i][j] > avg) {
							circle[i][j]--;
						} else if( circle[i][j] < avg) circle[i][j]++;
					}
				}
			}
		}
	}
	private static void cycle(int x, int d, int k) {
		if(d == 0) {	//시계방향
			for(int i = 1; i <= n; i++) {
				if(i % x == 0) {		//원판이 x의 배수이면
					for(int a = 0; a < k; a++) {
						int temp = circle[i][m];
						for(int j = m; j > 1; j--) {
							circle[i][j] = circle[i][j-1];
						}
						circle[i][1] = temp;
					}
				}
			}
		} else {
			for(int i = 1; i <= n; i++) {
				if(i % x == 0) {
					for(int a = 0; a < k; a++) {
						int temp = circle[i][1];
						for(int j = 1; j < m; j++) {
							circle[i][j] = circle[i][j+1];
						}
						circle[i][m] = temp; 
					}
				}
			}
		}
	}
}
