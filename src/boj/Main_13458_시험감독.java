package boj;

import java.util.Scanner;

public class Main_13458_시험감독 {
	private static int n;
	private static int[] testArea;
	private static int b, c;
	private static long result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		testArea = new int[n];
		for(int i = 0; i < n;  i++) {
			testArea[i] = sc.nextInt();
		}
		b = sc.nextInt();
		c = sc.nextInt();
		for(int i = 0; i < n; i++) {
			int count = 1;
			if(testArea[i] - b > 0) {
				count += (testArea[i]-b) / c;
				if((testArea[i]-b) % c != 0) count++;
			}
			result += count;
		}
		System.out.println(result);
	}
}
