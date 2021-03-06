package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1219_길찾기 {
	public static int result;
	public static int size;
	public static int[] data1;
	public static int[] data2;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 1; t <= 10; t++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken();	//번호 지움
			size = Integer.parseInt(st.nextToken());
			
			data1 = new int[100]; data2 = new int[100];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < size; i++) {
				int index = Integer.parseInt(st.nextToken());
				if(data1[index] == 0) data1[index] = Integer.parseInt(st.nextToken());
				else data2[index] = Integer.parseInt(st.nextToken());

			}
			sol(0);
			System.out.println("#" + t + " " + result);
		}
	}
	public static void sol(int num) {
		if(num == 99) {
			result = 1;
			return;
		}
		if(data1[num] != 0) {
			int temp = data1[num];
			data1[num] = 0;
			sol(temp);
		}
		if(data2[num] != 0) {
			int temp = data2[num];
			data2[num] = 0;
			sol(temp);
		}
		
	}
}
