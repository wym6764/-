package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_조합 {
	private static int n, r;
	private static long f[];
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     int T = Integer.parseInt(br.readLine());
	     for (int t = 1; t <= T; t++) {
	    	 StringTokenizer st = new StringTokenizer(br.readLine());
	    	 n = Integer.parseInt(st.nextToken());
	    	 r = Integer.parseInt(st.nextToken());
	    	 f = new long[n+1];
	    	 f[0] = 1;
	    	 for(int i = 1; i <= n; i++) {
	    		 f[i] = (f[i-1] * i) % 1234567891;
	    	 }
	    	 long bottom = (f[r] * f[n-r] % 1234567891);
	    	 long reBottom = sol(bottom, 1234567891 - 2);
	    	 
	    	 System.out.println("#" + t + " " + (f[n] * reBottom) % 1234567891);
	     }
	}
	
	private static long sol(long n, int x) {
		if(x == 0) return 1;
		long temp = sol(n, x/2);
		long ret = (temp * temp) % 1234567891;
		if (x % 2 == 0) return ret;
		else return (ret * n) % 1234567891;
	}
}
