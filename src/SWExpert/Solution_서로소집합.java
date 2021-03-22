package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_서로소집합 {
	static int N;
	static int parents[];
	
	static void make() {
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			parents = new int[N+1];
			make();
			System.out.print("#" + t + " ");
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				if(st.nextToken().equals("0")) {
					union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				} else {	//1인경우
					if(findSet(Integer.parseInt(st.nextToken())) == findSet(Integer.parseInt(st.nextToken()))) {
						System.out.print("1");
					} else System.out.print("0");
				}
				
			}
			System.out.println();
		}
	}
}
