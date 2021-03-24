package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1251_하나로 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			double[][] adjMatrix = new double[n][n];
			boolean[] visited = new boolean[n];
			double[] minEdge = new double[n];
			
			int[][] arr = new int[n][2];
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					int width = Math.abs(arr[i][0] - arr[j][0]);
					int height = Math.abs(arr[i][1] - arr[j][1]);
					adjMatrix[i][j] = Math.sqrt(width*width + height*height);
				}
				minEdge[i] = Double.MAX_VALUE;
			}
			
			double result = 0;
			minEdge[0] = 0;
			
			for(int c = 0; c < n; c++) {
				double min = Double.MAX_VALUE;
				int minVertex = 0;
			
				for (int i = 0; i < n; i++) {
					if(!visited[i] && min > minEdge[i]) {
						min = minEdge[i];
						minVertex = i;
					}
				}
				result += min*min;
				visited[minVertex] = true;
				
				for(int i = 0; i < n; i++) {
					if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
						minEdge[i] = adjMatrix[minVertex][i];
					}
				}
				
			}
			Double d = Double.parseDouble(br.readLine());
			long r = (long)Math.round(result * d);
			
			
			System.out.println("#" + t + " " + r);
		}
	}
}











