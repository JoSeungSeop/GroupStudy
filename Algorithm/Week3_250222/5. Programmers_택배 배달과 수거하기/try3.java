
/*
 스택 사용법 참고 : https://inpa.tistory.com/entry/JCF-%F0%9F%A7%B1-Stack-%EA%B5%AC%EC%A1%B0-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%A0%95%EB%A6%AC
 스택을 사용하니 매끄럽게 성공하였ㄷr..
 어떻게든 배열로 풀려고 했던 내 자신이 한심..
*/

import java.util.Stack;

public class Solution_3try {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Stack<int[]> deliverStack = new Stack<>();
        Stack<int[]> pickupStack = new Stack<>();

        //배달, 수거할 물건이 있는 집의 위치와 물건 개수를 stack에 저장
        for (int i = 0; i < n; i++) {
            if (deliveries[i] > 0) deliverStack.push(new int[]{i, deliveries[i]});
            if (pickups[i] > 0) pickupStack.push(new int[]{i, pickups[i]});
        }

        //배달 및 수거 처리
        while (!deliverStack.isEmpty() || !pickupStack.isEmpty()) { //둘다 비어있으면 탈출
            int maxDistance = 0;  //현재 목적지 (가장 먼 거리 기준)

            int deliverCap = cap;
            int pickupCap = cap;

            //배달
            while (!deliverStack.isEmpty() && deliverCap > 0) {
                int[] current = deliverStack.pop();
                int index = current[0], amount = current[1];
                maxDistance = Math.max(maxDistance, index + 1);

                if (amount <= deliverCap) {
                    deliverCap -= amount;
                } else {
                    deliverStack.push(new int[]{index, amount - deliverCap});
                    deliverCap = 0;
                }
            }

            //수거
            while (!pickupStack.isEmpty() && pickupCap > 0) {
                int[] current = pickupStack.pop();
                int index = current[0], amount = current[1];
                maxDistance = Math.max(maxDistance, index + 1);

                if (amount <= pickupCap) {
                    pickupCap -= amount;
                } else {
                    pickupStack.push(new int[]{index, amount - pickupCap});
                    pickupCap = 0;
                }
            }

            //왕복이므로 x 2
            answer += (maxDistance * 2L);
        }

        return answer;
    }
}
