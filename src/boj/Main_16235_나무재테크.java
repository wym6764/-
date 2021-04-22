package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_16235_나무재테크 {
	private static int n, m, k;
	private static int arr[][];
	private static ArrayList<Integer> tree[][];
	private static int nut[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());	
		arr = new int[n+1][n+1];	//양분의 값
		nut = new int[n+1][n+1];
		tree = new ArrayList[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				arr[i][j] = 5;
				nut[i][j] = Integer.parseInt(st.nextToken());
				tree[i][j] = new ArrayList<>();
			}
		}
		for(int i = 0 ; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			tree[x][y].add(z);
		}
		for(int i = 0; i < k; i++) {
			spring();
			fall();
			winter();
		}
		int result = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				result += tree[i][j].size();
			}
		}
		System.out.println(result);
	}
	
	private static void winter() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				arr[i][j] += nut[i][j];
			}
		}
		
	}

	private static int dr[] = {-1, -1, -1, 0, 0, 1, 1, 1};
	private static int dc[] = {-1, 0, 1, -1, 1, -1, 0, 1};
	private static void fall() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				for(int t = 0; t < tree[i][j].size(); t++) {
					if(tree[i][j].get(t) % 5 == 0 && tree[i][j].get(t) > 0) {
						for(int k = 0; k < 8; k++) {
							int nr = i + dr[k];
							int nc = j + dc[k];
							if(nr >= 1 && nc >= 1 && nr <= n && nc <= n) {
								tree[nr][nc].add(1);
							}
						}
					}
				}
			}
		}
		
	}

	private static void spring() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				int sum = 0;
				Collections.sort(tree[i][j]);
				for(int t = 0; t < tree[i][j].size(); t++) {
					if(tree[i][j].get(t) <= arr[i][j]) {
						arr[i][j] -= tree[i][j].get(t);
						tree[i][j].set(t, tree[i][j].get(t)+1);
					} else {
						sum += tree[i][j].get(t) / 2;
						tree[i][j].remove(t);
						t--;
					}
				}
				arr[i][j] += sum;
			}
			
		}
		
	}

}
