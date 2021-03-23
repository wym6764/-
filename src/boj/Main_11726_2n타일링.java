package boj;

import java.util.Scanner;

public class Main_11726_2n타일링 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int f[] = new int[n+1];
		f[1] = 1;
		f[2] = 2;
		for(int i = 3; i <= n; i++) {
			f[i] = f[i-1] % 10007 + f[i-2] % 10007;
		}
		System.out.println(f[n] % 10007);
	}
}
