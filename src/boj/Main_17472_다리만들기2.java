package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472_다리만들기2 {
	public static int n, m;
	public static int arr[][];
	public static int result;
	public static int adjMatrix[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int index = 2;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 1)
					bfs(i, j, index++);	
			}
		}
		adjMatrix = new int[index+3][index+3];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] != 0) {
					bridge(i, j, arr[i][j]);
				}
			}
		}
		
		int[] minEdge = new int[index+3];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		boolean[] visited = new boolean[index+3];
		minEdge[2] = 0;
		
		for(int c = 2; c < index+2; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = 2;
			
			for(int i = 2; i < index+2; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			result += min;
			visited[minVertex] = true;
			
			for(int i = 2; i < index+2; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}
		
		/*
		int start = 2;
		int end = index+2;
		int[] distance = new int[index+3];
		boolean[] visited = new boolean[index+3];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		for(int i = 2; i < index+3; i++) {
			int min = Integer.MAX_VALUE;
			int current = 0;
			
			for(int j = 2; j < index+3; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					current = j;
				}
			}
			
			visited[current] = true;
			if(current == end) break;
			
			for(int j = 2; j < index+3; j++) {
				if(!visited[j] && adjMatrix[current][j] != 0 && distance[j] > min + adjMatrix[current][j]) {
					distance[j] = min + adjMatrix[current][j];
				}
			}
		}
		*/
		
		result = 0;
		for(int i = 3; i < index; i++) {
			if(minEdge[i] != Integer.MAX_VALUE) {
				result += minEdge[i];
				if(minEdge[i] == 0) {
					System.out.println("-1");
					return;
				}
			}
		}
		if(result == 0)
			System.out.println("-1");
		else
		System.out.println(result);
	}
	public static int dr[] = {0, 0, -1, 1};
	public static int dc[] = {-1, 1, 0, 0};
	public static void bfs(int row, int col, int num) {
		Queue<int[]> q = new LinkedList<>();
		boolean visited[][] = new boolean[n][m];
		q.offer(new int[] {row, col});
		arr[row][col] = num;
		visited[row][col] = true;
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.poll()[1];
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr >= 0 && nc >= 0 && nr < n && nc < m && !visited[nr][nc] && arr[nr][nc] == 1) {
					q.offer(new int[] {nr, nc});
					arr[nr][nc] = num;
					visited[nr][nc] = true;
				}
				
			}
		}
	}


	public static void bridge(int row, int col, int num) {	//num은 섬 번호
		for(int i = 0; i < 4; i++) {
			int len = 0;
			while(true) {
				int nr = dr[i] * (len+1) + row;
				int nc = dc[i] * (len+1) + col;
				if(nr >= 0 && nc >= 0 && nr < n && nc < m && arr[nr][nc] != num) {
					if(arr[nr][nc] == 0) {
						len++; continue;
					}
					else {
						if(len == 1) break;
						if(adjMatrix[num][arr[nr][nc]] == 0) {
							adjMatrix[num][arr[nr][nc]] = len;
						}
						if(adjMatrix[num][arr[nr][nc]] != 0 && adjMatrix[num][arr[nr][nc]] > len) {	//len 값이 더 작으면 업데이트
							adjMatrix[num][arr[nr][nc]] = len;
						}
						break;
					}
				} else break;	//아니면 다리가 아니므로 나감
			}
			
		}
		
	}
}
