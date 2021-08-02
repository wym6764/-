package programmers;
import java.util.*;
public class 더맵게_42626 {

    public static void main(String[] args) {
        System.out.println("테스트");
    }
    class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;
            PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
            for(int i = 0; i < scoville.length; i++){
                heap.add(scoville[i]);
            }
            while(true){
                int a = heap.poll();
                if(heap.isEmpty()) {
                    if(a >= K) return answer;
                    else
                        return -1;
                }

                int b = heap.poll();
                if(a >= K){
                    break;
                }
                heap.add(a + b*2);
                answer++;
            }
            return answer;
        }
    }
}
