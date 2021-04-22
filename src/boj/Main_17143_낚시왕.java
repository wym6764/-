package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17143_낚시왕 {
	static class Shark{
		int s, d, z;
		boolean ismove;
		Shark(int s, int d, int z, boolean ismove) {
			this.s = s;
			this.d = d;
			this.z = z;
			ismove = false;
		}
	}
	private static int r, c, m;
	private static int arr[][];
	private static int result;
	private static ArrayList<Shark> shark[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		shark = new ArrayList[r+1][c+1];
		for(int i = 1; i <= r; i++) {
			for(int j = 1; j <= c; j++) {
				shark[i][j] = new ArrayList<>();
			}
		}
		arr = new int[r+1][c+1];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			shark[r][c].add(new Shark(s, d, z, false));
		}
		for(int i = 1; i <= c; i++) {
			fishing(i);
			move();
		}
		System.out.println(result);
	}
	private static int dr[] = {0, -1, 1, 0, 0};
	private static int dc[] = {0, 0, 0, 1, -1};
	private static void move() {
		for(int i = 1; i <= r; i++) {
			for(int j = 1; j <= c; j++) {
				for(int s = 0; s < shark[i][j].size(); s++) {
					Shark nowshark = shark[i][j].get(s);
					if(nowshark.ismove) continue;
					int nr = i;
					int nc = j;
					for(int k = 0; k < nowshark.s; k++) {
						if(nr == 1 && nowshark.d == 1) nowshark.d = 2;
						if(nc == 1 && nowshark.d == 4) nowshark.d = 3;
						if(nr == r && nowshark.d == 2) nowshark.d = 1;
						if(nc == c && nowshark.d == 3) nowshark.d = 4;
						nr += dr[nowshark.d];
						nc += dc[nowshark.d];
					}
					nowshark.ismove = true;
					shark[nr][nc].add(shark[i][j].remove(s));
					s--;
				}
			}
		}
		
		for(int i = 1; i <= r; i++) {
			for(int j = 1; j <= c; j++) {
				if(shark[i][j].size() > 1) {
					int maxz = 0;
					Shark temp = null;
					for(int s = 0; s < shark[i][j].size(); s++) {
						if(maxz < shark[i][j].get(s).z) {
							maxz = shark[i][j].get(s).z;
							temp = shark[i][j].get(s);
						}
					}
					shark[i][j].clear();
					shark[i][j].add(temp);
				}
			}
		}
		for(int i = 1; i <= r; i++) {
			for(int j = 1; j <= c; j++) {
				if(shark[i][j].size() == 1)
				shark[i][j].get(0).ismove = false;
			}
		}
	}
	private static void fishing(int j) {
		for(int i = 1; i <= r; i++) {
			if(!shark[i][j].isEmpty()) {	//상어가 있다면
				result += shark[i][j].get(0).z;
				shark[i][j].clear();		//잡아 없앰
				return;
			}
		}
	}
}
