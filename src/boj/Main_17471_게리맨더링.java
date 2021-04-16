package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17471_게리맨더링 {
	private static int n;
	private static int adjMatrix[][];
	private static int people[];
	private static boolean isSelected[];
	private static int result = 987654321;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		adjMatrix = new int[n+1][n+1];
		people = new int[n+1];
		isSelected = new boolean[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1; i <= n; i++){
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j = 0; j < num; j++) {
				int temp = Integer.parseInt(st.nextToken());
				adjMatrix[i][temp] = 1;
				adjMatrix[temp][i] = 1;
			}
		}
		sol(1);
		if(result == 987654321) result = -1;
		System.out.println(result);
	}
	private static void sol(int cnt) {
		if(cnt == n+1) {
			int count = 0;
			for(int i = 1; i <= n; i++) {
				if(isSelected[i]) count++;
			}
			if(count == 0 || count == n) return;
			int index1 = 0;
			int index2 = 0;
			int area1[] = new int[count];
			int area2[] = new int[n-count];
			for(int i = 1; i <= n; i++) {
				if(isSelected[i]) area1[index1++] = i;
				else area2[index2++] = i;
			}
			if(!isConnected(area1) || !isConnected(area2)) return;
			
			int sum1 = 0;
			int sum2 = 0;
			for(int i = 0; i < area1.length; i++) {
				sum1 += people[area1[i]];
			}
			for(int i = 0; i < area2.length; i++) {
				sum2 += people[area2[i]];
			}
			if(result >= Math.abs(sum1-sum2)) result = Math.abs(sum1-sum2);
			return;
		}
		sol(cnt + 1);
		isSelected[cnt] = true;
		sol(cnt + 1);
		isSelected[cnt] = false;
	}
	private static boolean isConnected(int[] area) {
		Queue<Integer> q = new LinkedList<>();
		boolean visited[] = new boolean[n+1];
		visited[area[0]] = true;
		q.offer(area[0]);
		while(!q.isEmpty()) {
			int num = q.poll();
			for(int i = 1; i <= n; i++) {
				if(adjMatrix[num][i] == 1 && !visited[i]) {
					for(int k = 0; k < area.length; k++) {
						if(area[k] == i) {
							visited[i] = true;
							q.offer(i);
						}
					}
					
				}
			}
		}
		boolean isEqual = true;
		for(int j = 0; j < area.length; j++) {
			if(!visited[area[j]]) {
				isEqual = false;
			}
		}
		
		
		return isEqual;
	}
}







