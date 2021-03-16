package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_Contact {
	public static int[][] arr;
	public static int result;
	public static int len, start;
	public static boolean visited[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 1; t<= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			len = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			int temp[] = new int[len];
			st = new StringTokenizer(br.readLine());
			int max = 0;
			for(int i = 0; i < len; i++) {
				temp[i] = Integer.parseInt(st.nextToken());
				if(max < temp[i]) max = temp[i];
			}
			arr = new int[max+1][max+1];
			for(int i = 0; i < len; i += 2) {
				arr[temp[i]][temp[i+1]] = 1;
			}
			result = 0;
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(start);
			int res[] = new int[0];
			visited = new boolean[max+1];
			while(true) {
				int size = queue.size();
				
				for(int i = 0; i < size; i++) {
					int a = queue.poll();
					for(int j = 0; j < max+1; j++) {
						if(arr[a][j] == 1 && !visited[j]) {
							queue.offer(j);
							visited[a] = true;
							res[i] = j;
						}
					}
					
				}
				if(queue.isEmpty()) {
					break;
				}
				res = new int[size];
			}
			for(int i = 0; i < res.length; i++) {
				if(result < res[i]) result = res[i];
			}
			
			System.out.println("#" + t + " " + result);
		}
	}
}
