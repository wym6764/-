package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1922_네트워크연결 {
	private static int n, m;
	private static int adjMatrix[][];
	private static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		adjMatrix = new int[n+1][n+1];
		StringTokenizer st;
		for(int i = 0; i < m; i++) {
			st  = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = c;
			adjMatrix[b][a] = c;
		}
		
		prim3();
		System.out.println(result);
	}
	private static void prim3() {
		int minEdge[] = new int[n+1];
		boolean visited[] = new boolean[n+1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;
		
		for(int c = 1; c <= n; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex= i;
				}
			}
			
			visited[minVertex] = true;
			result += min;
			
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && adjMatrix[minVertex][i] < minEdge[i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		
		}
		
	}
	private static void prim() {
		int minEdge[] = new int[n+1];
		boolean visited[] = new boolean[n+1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;
		for(int c = 1; c <= n; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			result += min;
			visited[minVertex] = true;
			
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}
		
	}

	private static void prim1() {
		int minEdge[] = new int[n+1];
		boolean visited[] = new boolean[n+1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;
		for(int c = 0; c < n; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			result += min;
			visited[minVertex] = true;
			
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i])
					minEdge[i] = adjMatrix[minVertex][i];
			}
		}
	}

	private static void prim2() {
		int minEdge[] = new int[n+1];
		boolean visited[] = new boolean[n+1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;
		for(int c = 1; c <= n; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && minEdge[i] < min) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			result += min;
			visited[minVertex] = true;
			
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && adjMatrix[minVertex][i] < minEdge[i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
			
		}
	}

	private static void prim4() {
		
	}
}
