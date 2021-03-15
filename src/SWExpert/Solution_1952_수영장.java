package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952_수영장 {
	public static int day, month, threemonth, year;
	public static int arr[];
	public static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			threemonth = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			result = 987654321;
			arr = new int[13];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= 12; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			sol(1, 0);
			if(result > year) result = year;
			System.out.println("#" + t + " " + result);
		}
	}
	public static void sol(int m, int sum) {
		if(m >= 13) {
			if(result > sum) result = sum;
			return;
		}
		if(arr[m] == 0) {
			sol(m+1, sum);
			return;
		}
		sum += arr[m]*day;
		sol(m+1, sum);
		sum-=arr[m]*day;
		
		sum += month;
		sol(m+1, sum);
		sum-= month;
			
		sum += threemonth;
		sol(m+3, sum);
		sum -= threemonth;
		
	}
}
