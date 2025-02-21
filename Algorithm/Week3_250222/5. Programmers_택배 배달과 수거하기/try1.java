package week3_250222.programmers.DeliverAndPickup;

/* https://school.programmers.co.kr/learn/courses/30/lessons/150369#qna
 [택배 배달과 수거하기]
 당신은 일렬로 나열된 n개의 집에 택배를 배달하려 합니다.
 배달할 물건은 모두 크기가 같은 재활용 택배 상자에 담아 배달하며, 배달을 다니면서 빈 재활용 택배 상자들을 수거하려 합니다.
 배달할 택배들은 모두 재활용 택배 상자에 담겨서 물류창고에 보관되어 있고, i번째 집은 물류창고에서 거리 i만큼 떨어져 있습니다.
 또한 i번째 집은 j번째 집과 거리 j - i만큼 떨어져 있습니다. (1 ≤ i ≤ j ≤ n)
 트럭에는 재활용 택배 상자를 최대 cap개 실을 수 있습니다. 트럭은 배달할 재활용 택배 상자들을 실어 물류창고에서 출발해 각 집에 배달하면서,
 빈 재활용 택배 상자들을 수거해 물류창고에 내립니다. 각 집마다 배달할 재활용 택배 상자의 개수와 수거할 빈 재활용 택배 상자의 개수를 알고 있을 때,
 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 구하려 합니다.
 각 집에 배달 및 수거할 때, 원하는 개수만큼 택배를 배달 및 수거할 수 있습니다.
*/

/*
  20개의 테스트 중 2개 시간초과로 실패 (test 16, 17)
  -> for를 while로 바꿔 필요없는 탐색을 줄이기, 
  CheckZero() 함수는 매번 전체 배열을 탐색하면서 모든 값이 0인지 확인하는데 어떻게 잘 만지면 시간을 줄일 수 있을듯..
*/

public class Solution_1try {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        while (!CheckZero(deliveries) || !CheckZero(pickups)) {
            int LastIndex = Math.max(CheckLastIndex(deliveries), CheckLastIndex(pickups));
            int deliver_sum = 0;
            int pickup_sum = 0;

            for (int i = LastIndex; i >= 0; i--) {
                if (deliver_sum + deliveries[i] <= cap) {
                    deliver_sum += deliveries[i];
                    deliveries[i] = 0;
                } else {
                    deliveries[i] -= (cap - deliver_sum);
                    deliver_sum = cap;
                }

                if (pickup_sum + pickups[i] <= cap) {
                    pickup_sum += pickups[i];
                    pickups[i] = 0;
                } else {
                    pickups[i] -= (cap - pickup_sum);
                    pickup_sum = cap;
                }

                // 용량이 꽉 찼으면 더 이상 배달/수거할 필요 없음.
                if (deliver_sum == cap && pickup_sum == cap) break;
            }

            answer += (LastIndex + 1) * 2L;  // 왕복 거리 추가
        }

        return answer;
    }

    public boolean CheckZero(int[] arr) {
        for (int a : arr) {
            if (a != 0) return false;
        }
        return true;
    }

    public int CheckLastIndex(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                return i;  // 마지막 값이 있는 인덱스 반환
            }
        }
        return -1;  // 모든 값이 0일 경우
    }
}
