package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_14888_연산자끼워넣기 {
	public static int arr[];
	public static int n;
	public static ArrayList<Integer> op;
	public static boolean isSelected[];
	public static int numbers[];
	public static int resultmax = Integer.MIN_VALUE;
	public static int resultmin = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		op = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			int a = Integer.parseInt(st.nextToken());
			for(int j = 0; j < a; j++) {
				op.add(i);
			}
		}
		isSelected = new boolean[op.size()];
		numbers = new int[op.size()];
		sol(0);
		System.out.println(resultmax);
		System.out.println(resultmin);
	}
	public static void sol(int cnt) {
		if(op.size() == cnt) {
			int number = arr[0];
			for(int i = 0; i < op.size(); i++) {
				switch(op.get(numbers[i])) {
				case 0:
					number += arr[i+1];
					break;
				case 1:
					number -= arr[i+1];
					break;
				case 2:
					number *= arr[i+1];
					break;
				case 3:
					number /= arr[i+1];
					break;
				}
			}
			if(number < resultmin) resultmin = number;
			if(number > resultmax) resultmax = number;
			return;
		}
		
		for(int i = 0; i < op.size(); i++) {
			if(isSelected[i]) continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			sol(cnt + 1);
			isSelected[i] = false;
		}
	}
}
