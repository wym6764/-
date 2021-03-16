package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1759_암호만들기 {
	public static int l, c;
	public static char arr[];
	public static char result[];
	public static boolean isSelected[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new char[c];
		result = new char[l];
		isSelected = new boolean[c];
		for(int i = 0; i < c; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		sol(0, 0);
	}
	public static void sol(int len, int start) {
		if(len == l) {
			int m = 0, j = 0;	//모음
			for(int i = 0; i < l; i++) {
				if(result[i] == 'a' || result[i] == 'e' || result[i] == 'i' || result[i] == 'o' || result[i] == 'u') {
					m++;
				} else j++;
			}
			if(m != 0 && j >= 2) {				
				for(int i = 0; i < l; i++) {
					System.out.print(result[i]);
				}
				System.out.println();
			}
			
			return;
		}
		for(int i = start; i < c; i++) {
			result[len] = arr[i];
			sol(len + 1, i + 1);
		}
	}
}
