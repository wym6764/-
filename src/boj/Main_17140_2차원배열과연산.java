package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17140_2차원배열과연산 {
	private static int r, c, k;
	private static int[][] arr;
	private static int rLength = 3, lLength = 3;
	private static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[101][101];
		for(int i = 1; i <= 3; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j = 1; j <= 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			if(arr[r][c] == k) break;
			if(rLength >= lLength) {
				operR();
			} else {
				operL();
			}
			
			result++;
			if(result > 100) {
				System.out.println("-1");
				return;
			}
		}
		System.out.println(result);
	}
	
	private static void operR() {
		for(int k = 1; k <= rLength; k++) {
			ArrayList<Integer> list = new ArrayList<>();
			int count[] = new int[101];
			
			for(int j = 1; j <= lLength; j++) {
				count[arr[k][j]]++;
			}
	
			boolean isSelected[] = new boolean[101];
			while(true) {
				int mincount = 101;
				int minindex = 101;
				for(int i = 1; i <= 100; i++) {	//넣을거 고름
					if(count[i] != 0 && count[i] < mincount && !isSelected[i]) {
						mincount = count[i];
						minindex = i;
					}
					if(count[i] != 0 && count[i] == mincount && !isSelected[i]) {
						if(minindex > i) {
							mincount = count[i];
							minindex = i;
						}
					}
				}
				if(mincount == 101 || minindex == 101) {
					break;
				}
				
				list.add(minindex);	//ex)2가
				list.add(count[minindex]);	//ex)1번
				isSelected[minindex] = true;
			}
			for(int i = 0; i < list.size(); i++) {
				arr[k][i+1] = list.get(i);
			}
			lLength = lLength > list.size() ? lLength : list.size();
			for(int i = list.size(); i < 100; i++) {
				arr[k][i+1] = 0;
			}
		}
	}
	
	private static void operL() {
		for(int k = 1; k <= lLength; k++) {
			ArrayList<Integer> list = new ArrayList<>();
			int count[] = new int[101];
			
			for(int j = 1; j <= rLength; j++) {
				count[arr[j][k]]++;
			}
	
			boolean isSelected[] = new boolean[101];
			while(true) {
				int mincount = 101;
				int minindex = 101;
				for(int i = 1; i <= 100; i++) {	//넣을거 고름
					if(count[i] != 0 && count[i] < mincount && !isSelected[i]) {
						mincount = count[i];
						minindex = i;
					}
					if(count[i] != 0 && count[i] == mincount && !isSelected[i]) {
						if(minindex > i) {
							mincount = count[i];
							minindex = i;
						}
					}
				}
				if(mincount == 101 || minindex == 101) {
					break;
				}
				
				list.add(minindex);	//ex)2가
				list.add(count[minindex]);	//ex)1번
				isSelected[minindex] = true;
			}
			for(int i = 0; i < list.size(); i++) {
				arr[i+1][k] = list.get(i);
			}
			rLength = rLength < list.size() ? list.size() : rLength;
			for(int i = list.size(); i < 100; i++) {
				arr[i+1][k] = 0;
			}
		}
	}
	
}
