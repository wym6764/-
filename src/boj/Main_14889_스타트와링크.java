package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14889_스타트와링크 {
	static int arr[][];
	static int numbers[];
	static int n;
	static int diff = 987654321;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		numbers = new int[n/2];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		sol(0, 0);
		System.out.println(diff);
	}
	public static void sol(int cnt, int start) {
		if(cnt == n/2) {
			int other[] = new int[n/2];
			int index = 0;
			for(int i = 0; i < n; i++) {
				boolean isinclude = false;
				for(int j = 0; j < n/2; j++) {
					if(numbers[j] == i) {
						isinclude = true; 
					}
				}
				if(!isinclude)
					other[index++] = i;
			}
			int sum1 = 0, sum2 = 0;
			for(int i = 0; i < n/2; i++) {
				for(int j = i+1; j < n/2; j++) {
					sum1 += arr[numbers[i]][numbers[j]] + arr[numbers[j]][numbers[i]];
				}
			}
			for(int i = 0; i < n/2; i++) {
				for(int j = i+1; j < n/2; j++) {
					sum2 += arr[other[i]][other[j]] + arr[other[j]][other[i]];
				}
			}
			if(Math.abs(sum1 - sum2) < diff) diff = Math.abs(sum1 - sum2);
			
			return;
		}
		for(int i = start; i < n; i++) {
			numbers[cnt] = i;
			sol(cnt + 1, i + 1);
		}
	}
}
