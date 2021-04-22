package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_구간합 {
	private static long arr[];
	private static long result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			arr = new long[10];
			long digits = 1;
			result = 0;
			if(a == 0) a = 1;
			while(a <= b) {
				while(a <= b && a % 10 != 0) {
					sol(a++, digits);
				}
				if(a > b) break;
				
				while(a <= b && b % 10 != 9) {
					sol(b--, digits);
				}
				result += digits * (b/10 - a/10 + 1) * 45;
				a /= 10;
				b /= 10;
				digits *= 10;
				
				for (int i = 0; i < 10; i++) {
					result += arr[i] * i;
				}
				
				System.out.println("#" + t + " " + result);
			}
		}
	}
	private static void sol(long num, long n) {
		while(num > 0) {
			int number = (int) (num%10);
			arr[number] += n;
			num /= 10;
		}
		
	}
}
