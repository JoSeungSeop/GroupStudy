/*
  (0) --1--- (1)
	 |        / |
	 4     2    6      대충 이런느낌의 경로에서 각 정점으로 가는 최소 비용 구하기
	 |  /       |
	(2) --3--- (3)
*/

import java.util.*; // 자바에서 제공하는 유틸리티 클래스 (PriorityQueue, List 등) 사용

public class DijkstraExample {
    static class Node implements Comparable<Node> {
        int vertex, cost; //vertex = 정점, cost = 비용(=가중치=거리)

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override //PriorityQueue에서 사용할 비교 기준 만듬
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) {
        int n = 4; // 정점 개수
        List<Node>[] graph = new ArrayList[n]; //4개의 Node 타입이 들어가는 List 생성
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>(); // 각각 ArrayList 생성

        // 그래프 초기화
        graph[0].add(new Node(1, 1)); //출발지(0)에서 1로 갈 때 1만큼 비용이 든다.
        graph[0].add(new Node(2, 4)); //출발지(0)에서 2로 갈 때 4만큼 비용이 든다.
        graph[1].add(new Node(2, 2)); //1에서 2로 갈 때 2
        graph[1].add(new Node(3, 6)); //1 -> 3 일 때 6
        graph[2].add(new Node(3, 3)); //2 -> 3 일 때 3
       

        // 다익스트라 실행
        int[] distance = new int[n]; //각 정점별 최소거리를 담을 배열 선언
        Arrays.fill(distance, Integer.MAX_VALUE); //모두 무한대로 초깃값 설정
        distance[0] = 0; //출발점은 0으로 가정 : 현재 {0, INF, INF, INF}

        PriorityQueue<Node> pq = new PriorityQueue<>(); //가장 짧은 거리의 정점을 고르기 위해 우선순위 큐 생성
        pq.add(new Node(0, 0)); //출발점을 일단 우선순위큐에 넣어

        while (!pq.isEmpty()) { //큐가 비어있을 때까지 반복
            Node current = pq.poll(); //가장 가까운 정점의 위치를 꺼내
            int vertex = current.vertex; //해당 정점과, 비용을 넣어
            int cost = current.cost; 

            if (cost > distance[vertex]) continue; 
            //현재 넣은 비용이 distance 배열에 저장된 최솟값보다 크다? 이미 최솟값을 갱신한 곳을 왔다는거지 그래서 continue로 스킵

            for (Node neighbor : graph[vertex]) { //현재 정점에 연결되있는 Node를 하나씩 다 꺼내
                int newCost = cost + neighbor.cost; //지금 이 정점까지 온 비용과 연결된 node 가는 비용을 더해 
                if (newCost < distance[neighbor.vertex]) { //만약 newCost가 distance 배열에 저장된 최솟값보다 작다?
                    distance[neighbor.vertex] = newCost; //최솟값을 갱신해
                    pq.add(new Node(neighbor.vertex, newCost)); //우선순위 큐에 그 정점과 갱신한 최솟값을 넣어
                }
            }
        }

        System.out.println("최단 거리: " + Arrays.toString(distance)); //초단 거리 : [0, 1, 3, 6]
    }
}
