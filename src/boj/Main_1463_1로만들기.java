package boj;

import java.util.Scanner;

public class Main_1463_1로만들기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int count=0;
		while(n > 1) {
			int number = n;
			while(number > 1) {
				if(number % 2 == 0) {
					number /= 2;
				} else break;
			}
			if(number != 1) {
				if(n % 3 == 0) {
					n = n / 3;
				} else n--;
			} else n /= 2;
			count++;
		}
		if(count == 0) count++;
		System.out.println(count);
	}
}
