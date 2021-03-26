package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_최장증가부분수열 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			int result = 0;
			int arr[] = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int d[] = new int[n];
			for(int i = 0; i < n; i++) {
				d[i] = 1;
				for(int j = 0; j < i; j++) {
					if(arr[j]<arr[i] && d[j]+1 > d[i]) d[i] = d[j]+1;
				}
				result = Math.max(d[i], result);
			}
			System.out.println("#" + t + " " + result);
		}
		
	}
}
