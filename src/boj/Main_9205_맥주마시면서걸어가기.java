package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_9205_맥주마시면서걸어가기 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] d = new int[n + 2][n + 2];
			boolean[][] visited = new boolean[n + 2][n + 2];
			ArrayList<int[]> p = new ArrayList<>();
			for (int i = 0; i <= n+1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				p.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			for (int i = 0; i <= n+1; i++) {
				for (int j = 0; j <= n + 1; j++) {
                    int[] p1 = p.get(i), p2 = p.get(j);
                    d[i][j] = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);

                    if (d[i][j] <= 50 * 20) {
                        visited[i][j] = true;
                    }
                }
			}
			for(int k = 0; k <= n+1; k++) {
				for(int i = 0; i <= n+1; i++) {
					for(int j = 0; j <= n+1; j++) {
						if(visited[i][k] && visited[k][j])
							visited[i][j] = true;
					}
				}
			}
			if(visited[0][n+1])
				System.out.println("happy");
			else
				System.out.println("sad");
		}
	}
}
