package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1486_장훈이의높은선반{
	public static int n;
	public static int b;
	public static int[] arr;
	public static boolean isSelected[];
	public static int result = 987654321;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			result = 987654321;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			arr = new int[n];
			isSelected = new boolean[n];
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			subset(0);
			System.out.println("#" + t + " " + (result-b));
		}
	}
	
	public static void subset(int cnt) {
		if(cnt == n) {
			int sum = 0;
			for(int i = 0; i < n; i++) {
				if(isSelected[i]) {
					sum += arr[i];
				}
				if(sum >= b && sum <= result) {
					result = sum;
				}
			}
			
			return;
		}
		isSelected[cnt] = true;
		subset(cnt+1);
		isSelected[cnt] = false;
		subset(cnt+1);
	}
	
}
