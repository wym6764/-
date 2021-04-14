package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1197_최소스패닝트리 {
	static class Node{
		Node next;
		int vertex;
		int weight;
		Node(Node next, int vertex, int weight){
			this.vertex = vertex;
			this.next = next;
			this.weight = weight;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		Node[] adjList = new Node[v+1];
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjList[a] = new Node(adjList[a], b, c);
			adjList[b] = new Node(adjList[b], a, c);
		}
		
		int minEdge[] = new int[v + 1];
		boolean visited[] = new boolean[v + 1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		minEdge[1] = 0;
		
		int result = 0;
		int cnt = 0;
		
		while(true) {
			int min = Integer.MAX_VALUE;
			int minNo = 0;
			for(int i = 1; i <= v; i++) {
				if(!visited[i] && min > minEdge[i]) {
					minNo = i;
					min = minEdge[i];
				}
			}
			
			visited[minNo] = true;
			result += min;
			
			if(++cnt == v) break;
			
			for(int i = 1; i <= v; i++) {
				if(!visited[i]) {
					for(Node temp = adjList[minNo]; temp != null; temp = temp.next) {
						if(temp.vertex == i && minEdge[i] > temp.weight) {
							minEdge[i] = temp.weight;
						}
					}
				}
			}
		}
		System.out.println(result);
	}
}
