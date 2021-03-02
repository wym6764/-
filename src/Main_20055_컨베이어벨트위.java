import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_20055_컨베이어벨트위 {
	static class Belt{
		int dur;	//내구도
		boolean robot;	//로봇 여부
		Belt(int dur, boolean robot){
			this.dur = dur;
			this.robot = robot;
		}
	}
	public static Queue<Belt> belttop;
	public static Queue<Belt> beltbottom;
	public static int n, k;
	public static int result = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		belttop = new LinkedList<Belt>();	
		beltbottom = new LinkedList<Belt>();	
		Stack<Belt> temp = new Stack<Belt>();		//큐에 반대로 넣기위한 스택
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			temp.push(new Belt(Integer.parseInt(st.nextToken()), false));		
		}
		for(int i = 0; i < n; i++) {
			belttop.offer(temp.pop());
		}
		for(int i = 0; i < n; i++) {
			temp.push(new Belt(Integer.parseInt(st.nextToken()), false));	
		}
		for(int i = 0; i < n; i++) {
			beltbottom.offer(temp.pop());
		}
		while(true) {
			cycle();
			move();
		
			if(count() >= k) {
				break;
			}
			result++;
		}
		System.out.println(result);

	}
	
	public static void cycle() {
		if(belttop.peek().robot) {	//내려가는 위치에 로봇이 있다면
			belttop.peek().robot = false;		//로봇이 내림.
		}
		belttop.offer(beltbottom.poll());
		beltbottom.offer(belttop.poll());		//벨트 회전
	}
	
	public static void move() {
		if(belttop.peek().robot)	//N위치에 로봇이 있다면
			belttop.peek().robot = false;	//로봇이 내림
		for(int i = 0; i < n-1; i++) {
			Belt temp = belttop.poll();	
			if(belttop.peek().robot && !temp.robot && temp.dur != 0) {	//N-1 위치에 로봇이 있고, N위치에 로봇이 없고, N위치 내구도가 0이 아니라면
 				belttop.peek().robot = false;	//N-1위치 로봇이
 				temp.robot = true;				//N위치로 이동
 				temp.dur--;						//N위치 내구도 1 감소
			}
			belttop.offer(temp);			//맨뒤로 이동
		}
		if(!belttop.peek().robot && belttop.peek().dur != 0) {
			belttop.peek().robot = true;	//로봇이 없다면 로봇을 하나 올린다.
			belttop.peek().dur--;
		}
		belttop.offer(belttop.poll());
	}
	
	
	
	public static int count() {
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			if(belttop.peek().dur <= 0) {
				cnt++;
			}
			belttop.offer(belttop.poll());
			
			if(beltbottom.peek().dur <= 0) {
				cnt++;
			}
			beltbottom.offer(beltbottom.poll());
		}
		return cnt;
	}
	
}
