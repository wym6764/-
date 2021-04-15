package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//11:26
public class Main_20056_마법사상어와파이어볼 {
	private static int n, m, k;
	private static int arr[][];
	private static LinkedList<int[]> fireballs;	//r, c, 질량, 속도, 방향
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());	//파이어볼갯수
		k = Integer.parseInt(st.nextToken());
		arr = new int[n + 1][n + 1];
		fireballs = new LinkedList<>();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireballs.add(new int[] {r, c, m, s, d});
			arr[r][c] = 1;
		}
		
		for(int i = 0; i < k; i++) {
			move();
			divide();
			changeMap();
		}
		
		int msum = 0;
		for(int i = 0; i < fireballs.size(); i++) {
			msum += fireballs.get(i)[2];
		}
		System.out.println(msum);
	}
	private static void changeMap() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				arr[i][j] = 0;
			}
		}
		for(int i = 0; i < fireballs.size(); i++) {
			arr[fireballs.get(i)[0]][fireballs.get(i)[1]]++;
		}
		
	}
	private static void divide() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(arr[i][j] >= 2) {
					LinkedList<int[]> temp = new LinkedList<>();
				
					for(int f = 0; f < fireballs.size(); f++) {
						if(fireballs.get(f)[0] == i && fireballs.get(f)[1] == j) {//
							temp.add(fireballs.get(f));
							fireballs.remove(f);
							f--;
						}
					}
					
					int msum=temp.get(0)[2], ssum=temp.get(0)[3];
					boolean isEven = temp.get(0)[4]%2 == 0 ? true : false;
					boolean isEqual = true;
					for(int f = 1; f < temp.size(); f++) {
						msum += temp.get(f)[2];
						ssum += temp.get(f)[3];
						boolean Even = temp.get(f)[4]%2 == 0 ? true : false;
						isEqual = isEven == Even ? true : false;
					}
					
					for(int f = 0; f < 4; f++) {
						if(isEqual) {		//방향이 모두 홀수거나 짝수일경우 0 2 4 6
							fireballs.add(new int[]{i, j, msum/5, ssum/temp.size(), f*2});
						} else {			// 1 3 5 7
							fireballs.add(new int[]{i, j, msum/5, ssum/temp.size(), f*2+1});
						}
					}
					
					for(int f = 0; f < fireballs.size(); f++) {
						if(fireballs.get(f)[2] == 0) {
							fireballs.remove(f);
							f--;
						}
					}
				}
				
			}
		}
		
	}
	private static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	private static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	private static void move() {
		for(int i = 0; i < fireballs.size(); i++) {
			int fireball[] = fireballs.get(i);
			int nr = fireball[0] + dr[fireball[4]] * fireball[3];
			int nc = fireball[1] + dc[fireball[4]] * fireball[3];
			if(nr > n) nr = nr - n * (nr/n);
			if(nc > n) nc = nc - n * (nc/n);
			if(nr <= 0) nr = n * (Math.abs(nr)/n+1) + (nr);
			if(nc <= 0) nc = n * (Math.abs(nc)/n+1) + (nc);
			
			arr[fireball[0]][fireball[1]]--;
			arr[nr][nc]++;
			fireballs.get(i)[0] = nr;
			fireballs.get(i)[1] = nc;
		}
		
	}
}
