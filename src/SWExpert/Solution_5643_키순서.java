package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5643_키순서 {
	private static int n, m, arr[][];
	private static int gtCnt, ltCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			arr = new int[n+1][n+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int i, j;
			for(int k = 0; k < m; k++) {
				st = new StringTokenizer(br.readLine());
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				arr[i][j] = 1;
			}
			
			int result = 0;
			for(int k = 1; k <= n; k++) {
				gtCnt = 0; ltCnt = 0;
				gtBFS(k, new boolean[n+1]);
				ltBFS(k, new boolean[n+1]);
				if(gtCnt + ltCnt == n-1) ++result;
			}
			System.out.println("#" + t + " " + result);
		}
	}
	
	private static void gtBFS(int start, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		while(!queue.isEmpty()) {
			int k = queue.poll();
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && arr[k][i] == 1) {
					queue.offer(i);
					visited[i] = true;
					gtCnt++;
				}
			}
		}
		
	}
	private static void ltBFS(int start, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		while(!queue.isEmpty()) {
			int k = queue.poll();
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && arr[i][k] == 1) {
					queue.offer(i);
					visited[i] = true;
					ltCnt++;
				}
			}
		}
		
	}
}
