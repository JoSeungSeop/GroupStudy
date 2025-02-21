

/*
 try1과 똑같은 결과 나옴..
 ...

 배달/수거를 할때 lastindex부터 차례대로 검사를 하는데 이 자체를 하면 안될것같음
*/

public class Solution_2try {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int lastIndex = n - 1; // 마지막 유효한 위치

        while (lastIndex >= 0) {
            // 마지막 유효한 위치 찾기
            while (lastIndex >= 0 && deliveries[lastIndex] == 0 && pickups[lastIndex] == 0) {
                lastIndex--;
            }
            if (lastIndex < 0) break; // 배달, 수거할 것이 없으면 종료

            int deliver_sum = 0;
            int pickup_sum = 0;
            int i = lastIndex;

            while (i >= 0) {
                // 배달
                if (deliveries[i] > 0) {
                    if (deliver_sum + deliveries[i] <= cap) {
                        deliver_sum += deliveries[i];
                        deliveries[i] = 0;
                    } else {
                        deliveries[i] -= (cap - deliver_sum);
                        deliver_sum = cap;
                    }
                }

                // 수거
                if (pickups[i] > 0) {
                    if (pickup_sum + pickups[i] <= cap) {
                        pickup_sum += pickups[i];
                        pickups[i] = 0;
                    } else {
                        pickups[i] -= (cap - pickup_sum);
                        pickup_sum = cap;
                    }
                }

                // 용량이 꽉 찼으면 종료
                if (deliver_sum == cap && pickup_sum == cap) break;

                i--;
            }

            answer += (lastIndex + 1) * 2L; // 왕복 거리 추가
        }

        return answer;
    }
}
