/* 
백준 5972 : 택배 배송 (https://www.acmicpc.net/problem/5972)

농부 현서는 농부 찬홍이에게 택배를 배달해줘야 합니다. 그리고 지금, 갈 준비를 하고 있습니다. 

평화롭게 가려면 가는 길에 만나는 모든 소들에게 맛있는 여물을 줘야 합니다. 물론 현서는 구두쇠라서 최소한의 소들을 만나면서 지나가고 싶습니다.

농부 현서에게는 지도가 있습니다. N (1 <= N <= 50,000) 개의 헛간과, 소들의 길인 M (1 <= M <= 50,000) 개의 양방향 길이 그려져 있고, 

각각의 길은 C_i (0 <= C_i <= 1,000) 마리의 소가 있습니다. 소들의 길은 두 개의 떨어진 헛간인 A_i 와 B_i (1 <= A_i <= N; 1 <= B_i <= N; A_i != B_i)를 잇습니다. 

두 개의 헛간은 하나 이상의 길로 연결되어 있을 수도 있습니다. 농부 현서는 헛간 1에 있고 농부 찬홍이는 헛간 N에 있습니다.

다음 지도를 참고하세요.

농부 현서가 선택할 수 있는 최선의 통로는 1 -> 2 -> 4 -> 5 -> 6 입니다. 왜냐하면 여물의 총합이 1 + 0 + 3 + 1 = 5 이기 때문입니다.

농부 현서의 지도가 주어지고, 지나가는 길에 소를 만나면 줘야할 여물의 비용이 주어질 때 최소 여물은 얼마일까요? 농부 현서는 가는 길의 길이는 고려하지 않습니다.
*/

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int node;
        int weight;

        Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        // 다익스트라 알고리즘
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentNode = current.node;
            int currentWeight = current.weight;

            if (currentWeight > dist[currentNode]) continue;

            for (Node neighbor : graph.get(currentNode)) {
                int nextNode = neighbor.node;
                int weight = neighbor.weight;
                int newDist = currentWeight + weight;

                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    pq.add(new Node(nextNode, newDist));
                }
            }
        }

        System.out.println(dist[N]);
    }
}
