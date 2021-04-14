package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_12100_2048{
	private static int n;
	private static int[][] map;
	private static boolean isSelected[];
	private static int numbers[];
	private static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		numbers = new int[5];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		sol(0);
		System.out.println(result);
	}
	private static void sol(int cnt) {
		if(cnt == 5) {
			move();
			return;
		}
		for(int i = 0; i < 4; i++) {
			numbers[cnt] = i;
			sol(cnt + 1);
		}
	}
	private static int dr[] = {-1, 1, 0, 0};
	private static int dc[] = {0, 0, -1, 1};
	private static void move() {		//0:상  1:하  2:좌  3:우
		int arr[][] = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				arr[i][j] = map[i][j];
			}
		}
		for(int m = 0; m < 5; m++) {
			switch(numbers[m]) {	
			case 0:
				for(int j = 0; j < n; j++) {
					int[] temp = new int[n];
					int index = 0;
					for(int i = 0; i < n; i++) {
						if(temp[index] != 0) {
							if(arr[i][j] == 0) continue;
							if(temp[index] == arr[i][j])
								temp[index++] += arr[i][j];
							else
								temp[++index] = arr[i][j];
						} else temp[index] = arr[i][j];
					}
					for(int i = 0; i < n; i++) {
						arr[i][j] = temp[i];
					}
				}
				break;
			case 1: 
				for(int j = 0; j < n; j++) {
					int[] temp = new int[n];
					int index = n-1;
					for(int i = n-1; i >= 0; i--) {
						if(temp[index] != 0) {
							if(arr[i][j] == 0) continue;
							if(temp[index] == arr[i][j])
								temp[index--] += arr[i][j];
							else
								temp[--index] = arr[i][j];
						} else temp[index] = arr[i][j];		
					}

					for(int i = 0; i < n; i++) {
						arr[i][j] = temp[i];
					}
				}
				break;
			case 2: 
				for (int i = 0; i < n; i++) {
					int[] temp = new int[n];
					int index = 0;
					for(int j = 0; j < n; j++) {
						if(temp[index] != 0) {
							if(arr[i][j] == 0) continue;
							if(temp[index] == arr[i][j])
								temp[index++] += arr[i][j];
							else
								temp[++index] = arr[i][j];
						} else temp[index] = arr[i][j];
					}
					for(int j = 0; j < n; j++) {
						arr[i][j] = temp[j];
					}
				}
				break;
			case 3: 
				for(int i = 0; i < n; i++) {
					int[] temp = new int[n];
					int index = n-1;
					for(int j = n-1; j >= 0; j--) {
						if(temp[index] != 0) {
							if(arr[i][j] == 0) continue;
							if(temp[index] == arr[i][j]) {
								temp[index--] += arr[i][j];	
							} else {
								temp[--index] = arr[i][j];
							}
						} else temp[index] = arr[i][j];	//0일경우
					}
					for(int j = 0; j < n; j++) {
						arr[i][j] = temp[j];
					}
				}
				break;
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(result < arr[i][j]) {
					result = arr[i][j];
				}
			}
		}
	}
}
