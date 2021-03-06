package SWExpert;

import java.util.Scanner;

public class Solution_8382_방향전환 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			int result = 0;
			int xdif = Math.abs(x1 - x2);		//x 차이
			int ydif = Math.abs(y1 - y2);		//y 차이
			if(Math.abs(xdif - ydif) <= 1) {
				result = xdif + ydif;
			}
			else {
				int sum = Math.abs(xdif + ydif);	//x차이와 y차이를 더함
				if(sum % 2 == 0) {	//더한게 짝수일경우
					result = xdif + ydif + Math.abs(xdif-ydif);
				}
				else {			//더한게 홀수일경우
					result = xdif + ydif + Math.abs(xdif-ydif)-1;
				}
			}
			System.out.println("#" + t + " " + result);
		}
	}
}
