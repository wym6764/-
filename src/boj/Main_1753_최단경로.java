package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1753_최단경로 {
	static class Node{
		int vertex, weight;
		Node next;
		public Node(int vertex, Node next, int weight) {
			this.vertex = vertex;
			this.next = next;
			this.weight = weight;
		}
		public Node(int vertex) {
			this.vertex = vertex;
		}
		public String toString() {
			return "Node [vertex=" + vertex + ", next=" + next + "]";
		}
	}
	
	static int N;
	static Node[] adjList;
	static boolean visited[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		adjList = new Node[V+1];
		int start = Integer.parseInt(br.readLine());	//출발점
		int end = V+1;  //도착점
		
		for(int i = 0; i < E; i++) {	//간선 갯수만큼
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from], weight);
		}
		
		int[] distance = new int[V+1];
		boolean[] visited = new boolean[V+1];
		
		Arrays.fill(distance,  Integer.MAX_VALUE);
		distance[start] = 0;
		
		for (int i = 1; i <= V; i++) {
			int min = Integer.MAX_VALUE;
			int current = 0; //min 최소비용에 해당하는 정점 번호
			// step1. : 처리하지 않은 정점중에 출발지에서 가장 가까운(최소비용) 정점 선택
			for (int j = 1; j <= V; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					current = j;
				}
			}
			visited[current] = true;
			if(current == end) break;
			
			// step2. 선택된 current를 경유지로 하여 아직 처리하지 않은 다른 정점으로의 최소비용 따져본다.
			for (Node temp = adjList[current]; temp != null; temp = temp.next) {
				if(!visited[temp.vertex] && distance[temp.vertex] > min + temp.weight) {
					distance[temp.vertex] = min + temp.weight;
				}
			}
		}
		for(int i = 1; i <= V; i++) {
			if(distance[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else
				System.out.println(distance[i]);
		}

	}
}
