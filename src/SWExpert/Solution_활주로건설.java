package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_활주로건설 {
	private static int n, x;
	private static int[][] arr;
	private static int result;
	private static boolean slope[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			arr = new int[n][n];
			slope = new boolean[n][n];
			result = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sol();
			slope = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(i < j) {
						int temp = arr[i][j];
						arr[i][j] = arr[j][i];
						arr[j][i] = temp;
					}
				}
			}
			sol();
			System.out.println(result);
		}
	}
	
	private static void sol() {
		for(int i = 0; i < n; i++) {
			boolean runway = true;
			for(int j = 0; j < n-1; j++) {
				if(arr[i][j] != arr[i][j+1]) {	//j와 그 다음값이 다르면
					if(Math.abs(arr[i][j] - arr[i][j+1]) > 1) {	//차이가 1보다 크면 불가능
						runway = false;						
					} else {									//차이가 1보다 작다면 경사로 가능여부 확인
						if(arr[i][j] > arr[i][j+1]) {	//왼쪽이 더 클경우 오른쪽에 경사로 놓아야함
							if(j + x < n) {		//활주로를 놓을 수 있는 길이일경우
								int first = arr[i][j+1];
								boolean s = true;
								for(int k = 2; k <= x; k++) {	//이어진 부분이 높이가 다르면 경사로를 놓을수없음
									if(first != arr[i][j+k]) s = false; 
								}
								if(s) {	//경사로를 놓을 수 있으면 놓고 j를 경사로의 끝 위치부터 검색
									for(int k = 1; k <= x; k++) {
										slope[i][j+k] = true;	//경사로를 놓음
									}
									j = j+x-1;
								} else runway = false;	
							} else runway = false;
						}
						else {	//오른쪽이 더 클경우 왼쪽에 경사로 놓아야함
							if(j + 1 >= x) {	//j+1한 길이가 x 이상일경우 경사로를 놓을 수 있음.
								int first = arr[i][j];
								boolean s = true;
								for(int k = 0; k < x; k++) {
									if(first != arr[i][j-k] || slope[i][j-k]) s = false;
									//경사로를 놓아야 할 위치가 다르고, 경사로가 이미 존재한다면 경사로를 놓을 수 없음.
								}
								if(s) {
									for(int k = 0; k < x; k++) {
										slope[i][j-k] = true;
									}
								} else runway = false;
							} else runway = false;
						}
					}
				}
			}
			
			if(runway) result++;
		}
		
		
	}

	
	
	
	
}
