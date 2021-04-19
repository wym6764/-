package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14501_퇴사 {
	private static int n;
	private static int arr[][];
	private static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int [n+1][2];
		StringTokenizer st;
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		sol(1, 0);
		System.out.println(result);
	}
	private static void sol(int day, int sum) {
		if(day >= n) {
			if(day == n && arr[day][0] == 1) sum += arr[day][1];
			if(result < sum) result = sum;
			return;
		}
		if(day + arr[day][0] <= n+1)
			sol(day + arr[day][0], arr[day][1] + sum);
		sol(day+1, sum);
	}
}
