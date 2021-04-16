package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1647_도시분할계획 {
	static class Node{
		int vertex;
		int weight;
		Node next;
		Node(Node next, int vertex, int weight){
			this.next = next;
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	private static int n, m;
	private static Node adjList[];
	private static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adjList = new Node[n+1];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(adjList[from], to, weight);
			adjList[to] = new Node(adjList[to], from, weight);
		}
		prim();
		System.out.println(result);
	}
	private static void prim() {
		int minEdge[] = new int[n+1];
		boolean visited[] = new boolean[n+1];
		int minVertex = 0;
		int max = 0;
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;
		for(int c = 1; c <= n; c++) {
			int min = Integer.MAX_VALUE;
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			if(max < min) {
				max = min;
			}
			result += min;
			visited[minVertex] = true;
			
			for(Node temp = adjList[minVertex]; temp.next != null ; temp = temp.next) {
				if(!visited[temp.vertex] && temp.weight < minEdge[temp.vertex]) {
					minEdge[temp.vertex] = temp.weight;
				}
			}
			
			
		}
		result -= max;
	}
}
