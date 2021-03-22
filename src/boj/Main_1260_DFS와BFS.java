package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main_1260_DFS와BFS {
	static class Node{
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
		public Node(int vertex) {
			super();
			this.vertex = vertex;
		}
		public String toString() {
			return "Node [vertex=" + vertex + ", next=" + next + "]";
		}
	}
	public static int n, m, v;
	public static Node adjList[];
	public static boolean visited[]; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		adjList = new Node[n+1];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
		}
		visited = new boolean[m+1];
		visited[0] = true;
		dfs(0);
		System.out.println();
		visited = new boolean[m+1];
		bfs();
	}
	private static void dfs(int current) {
		visited[current] = true;
		System.out.print(current + " ");
		for(Node temp = adjList[current]; temp != null; temp = temp.next) {
			if(visited[temp.vertex]) {
				dfs(temp.vertex);
			}
		}
		
	}
	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		visited = new boolean[m];
		
		queue.offer(v);
		visited[v] = true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			System.out.print(current + " ");
			
			for(Node temp = adjList[current]; temp != null; temp = temp.next) {
				
				if(!visited[temp.vertex]) {
					queue.offer(temp.vertex);
					visited[temp.vertex] = true;
				}
				
			}
			
		}
		
		
	}
	
	
}
