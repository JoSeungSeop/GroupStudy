/*
백준 4485 : 녹색 옷 입은 애가 젤다지?(https://www.acmicpc.net/problem/4485)

젤다의 전설 게임에서 화폐의 단위는 루피(rupee)다. 그런데 간혹 '도둑루피'라 불리는 검정색 루피도 존재하는데, 이걸 획득하면 오히려 소지한 루피가 감소하게 된다!

젤다의 전설 시리즈의 주인공, 링크는 지금 도둑루피만 가득한 N x N 크기의 동굴의 제일 왼쪽 위에 있다. 

[0][0]번 칸이기도 하다. 왜 이런 곳에 들어왔냐고 묻는다면 밖에서 사람들이 자꾸 "젤다의 전설에 나오는 녹색 애가 젤다지?"라고 물어봤기 때문이다.

링크가 녹색 옷을 입은 주인공이고 젤다는 그냥 잡혀있는 공주인데, 게임 타이틀에 젤다가 나와있다고 자꾸 사람들이 이렇게 착각하니까 정신병에 걸릴 위기에 놓인 것이다.

하여튼 젤다...아니 링크는 이 동굴의 반대편 출구, 제일 오른쪽 아래 칸인 [N-1][N-1]까지 이동해야 한다. 

동굴의 각 칸마다 도둑루피가 있는데, 이 칸을 지나면 해당 도둑루피의 크기만큼 소지금을 잃게 된다. 

링크는 잃는 금액을 최소로 하여 동굴 건너편까지 이동해야 하며, 한 번에 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.

링크가 잃을 수밖에 없는 최소 금액은 얼마일까?

*/

import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int problemCount = 1;

        while (true) {
            int n = sc.nextInt(); 
            if (n == 0) break;

            int[][] grid = new int[n][n]; //블랙 루피를 담을 맵 생성
            int[][] distance = new int[n][n]; //최솟값 2차원 배열 생성
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt(); //블랙 루피를 채움
                    distance[i][j] = Integer.MAX_VALUE; //최솟값에 INF 채움
                }
            }

            // 다익스트라 알고리즘
            int[] dx = {-1, 1, 0, 0}; //인접한 곳을 방문하려고 
            int[] dy = {0, 0, -1, 1};
            PriorityQueue<Node> pq = new PriorityQueue<>(); 
            pq.offer(new Node(0, 0, grid[0][0])); //큐에 시작점과, 시작점의 블랙 루피를 담는다.
            distance[0][0] = grid[0][0]; //시작점의 블랙 루피를 최솟값 배열에 넣는다.

            while (!pq.isEmpty()) { 
                Node current = pq.poll();
                int x = current.x;
                int y = current.y;
                int cost = current.cost;

                if (cost > distance[x][y]) continue; //이미 갱신된 곳이라면 continue

                for (int i = 0; i < 4; i++) { //
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < n && ny < n) { //맵을 벗어났는지 판단
                        int newCost = cost + grid[nx][ny]; //newCost에 원래 비용 + 현재 정점 비용 
                        if (newCost < distance[nx][ny]) { //기존 최솟값보다 작다면 
                            distance[nx][ny] = newCost; //갱신하고
                            pq.offer(new Node(nx, ny, newCost)); //큐에 넣음
                        }
                    }
                }
            }

            System.out.println("Problem " + problemCount + ": " + distance[n - 1][n - 1]);
            problemCount++;
        }

        sc.close();
    }
}

//스터디원 피드백 : 코테를 위해서라면 스캐너 말고 BufferedReader 사용 추천 (실행 속도 차이)
