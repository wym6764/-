package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13460_구슬탈출2 {
	private static int n, m;
	private static char arr[][];
	private static int result;
	private static int[] red;	//r, c
	private static int[] blue;	//r, c
	private static int[] hole;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new char[n][m];
		red = new int[2]; blue = new int[2];
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j);
				if(arr[i][j] == 'R') {
					red[0] = i; red[1] = j;
				}
				if(arr[i][j] == 'B') {
					blue[0] = i; blue[1] = j;
				}
				if(arr[i][j] == 'O') {
					hole[0] = i; hole[1] = j;
				}
			}
		}
		int i = 1;
		while(true) {
			sol(0);
			
			i++;
		}
		
		System.out.println(i);
	}
	
	private static int dr[] = {0, 0, -1, 1};
	private static int dc[] = {-1, 1, 0, 0};
	
	private static void sol(int cnt) {
		for(int i = 0; i < 4; i++) {
			int nr = red[0] + dr[i];
			int nc = red[1] + dc[i];
			if(arr[nr][nc] != '#' && arr[nr][nc] != 'r' && arr[nr][nc] != 'B') {
				int len = 1;
				int d = arr[red[0] + dr[i]*len][red[1] + dc[i]*len];
				while(d != '#' && d != 'r'
						&& d != 'B') {
					arr[nr*len][nc*len] = 'r'; len++;
				}
				len--;
				red[0] = nr*len; red[1] = nc*len;
			}
			nr = blue[0] + dr[i];
			nc = blue[1] + dc[i];
			if(arr[nr][nc] != '#' && arr[nr][nc] != 'R') {
				int len = 1;
				while(arr[nr*len][nc*len] != '#' && arr[nr*len][nc*len] != 'R') {
					len++;
				}len--;
				blue[0] = nr*len; blue[1] = nc*len;
			}
		}
	}
}
