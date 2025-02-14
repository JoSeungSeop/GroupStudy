
/*
 https://www.acmicpc.net/problem/11404
 
 n(2 ≤ n ≤ 100)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다. 각 버스는 한 번 사용할 때 필요한 비용이 있다.
 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.

 첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다.
 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다.
 시작 도시와 도착 도시가 같은 경우는 없다. 비용은 100,000보다 작거나 같은 자연수이다.
 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.

 n개의 줄을 출력해야 한다. i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다. 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.
*/

/*
    개선하면 좋을 듯!
    1. INF를 사용할 거라면 문제에서 나올 수 있는 최악의 경우의 수보다 크게 설정할 것
    2. 입력 개수가 많은 문제라면 Scanner 대신 BufferedReader 사용해보기 -> 실제로 해보니 메모리 6배, 3배 정도 시간 차이 발생
    3. 플루이드 비교 과정에서 덧셈할 때 오버플로우가 발생할 수 있으므로 이를 방지할 조건문 달기 (덧셈하는 두 수 모두 INF가 아닐 경우에만 비교 진행)
*/

import java.util.Scanner;

public class Main {

    static int INF = 100_000_000; //최악의 경우 100 x 100,000

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); //도시의 개수
        int m = sc.nextInt(); //버스의 개수

        int graph[][] = new int[n][n];

        for (int i = 0; i < n; i++){ //그래프 무한대값 설정
            for (int j = 0; j < n; j++){
                if (i==j) {
                    graph[i][j] = 0;
                    continue;
                }
                graph[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++){ //버스의 정보 그래프에 담기
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();

            if (graph[start - 1][end - 1] > cost){ //노선이 겹칠 경우 비용이 더 적은 버스의 정보를 담음
                graph[start - 1][end - 1] = cost;
            }

        }

        //플루이드 알고리즘
        for (int k = 0; k < n; k++){
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        //그래프 출력
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (graph[i][j] == INF){ //갈 수 없는 경우에 0을 출력
                    System.out.print("0 ");
                }
                else System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
