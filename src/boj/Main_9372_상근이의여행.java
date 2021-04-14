package boj;

import java.util.Arrays;
import java.util.Scanner;

public class Main_9372_상근이의여행 {
	private static int n, m;
	private static int[][] adjMatrix;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			n = sc.nextInt();	//국가 수
			m = sc.nextInt();	//비행기의 수
			adjMatrix = new int[n+1][n+1];
			for(int i = 0 ; i < m; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				adjMatrix[a][b] = 1;
				adjMatrix[b][a] = 1;
			}
			
			int minEdge[] = new int[n + 1];
			boolean visited[] = new boolean[n + 1];
			Arrays.fill(minEdge, Integer.MAX_VALUE);
			
			minEdge[1] = 0;
			
			int result = 0;
			int cnt = 0;
			
			while(true) {
				int min = Integer.MAX_VALUE;
				int minNo = 0;
				for(int i = 1; i <= n; i++) {
					if(!visited[i] && min > minEdge[i]) {
						minNo = i;
						min = minEdge[i];
					}
				}
				
				visited[minNo] = true;
				result += min;
				
				if(++cnt == n) break;
				
				for (int i = 1; i <= n; i++) {
					if(!visited[i] && adjMatrix[minNo][i] != 0 && minEdge[i] > adjMatrix[minNo][i]) {
						minEdge[i] = adjMatrix[minNo][i];
					}
				}
				
			}
			System.out.println(result);
			
		}
	}
}	
