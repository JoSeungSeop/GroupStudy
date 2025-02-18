
/* https://www.acmicpc.net/problem/21278
 컴공 출신은 치킨집을 하게 되어있다. 현실을 부정하지 말고 받아들이면 마음이 편하다.
 결국 호석이도 2050년에는 치킨집을 하고 있다. 치킨집 이름은 "호석이 두마리 치킨"이다.
 이번에 키친 도시로 분점을 확보하게 된 호석이 두마리 치킨은 도시 안에 2개의 매장을 지으려고 한다.
 도시는 N 개의 건물과 M 개의 도로로 이루어져 있다. 건물은 1번부터 N번의 번호를 가지고 있다.
 i 번째 도로는 서로 다른 두 건물 Ai 번과 Bi 번 사이를 1 시간에 양방향으로 이동할 수 있는 도로이다.

 키친 도시에서 2개의 건물을 골라서 치킨집을 열려고 한다. 이 때 아무 곳이나 열 순 없어서 모든 건물에서의 접근성의 합을 최소화하려고 한다.
 건물 X 의 접근성은 X 에서 가장 가까운 호석이 두마리 치킨집까지 왕복하는 최단 시간이다.
 즉, "모든 건물에서 가장 가까운 치킨집까지 왕복하는 최단 시간의 총합"을 최소화할 수 있는 건물 2개를 골라서 치킨집을 열려고 하는 것이다.

 컴공을 졸업한 지 30년이 넘어가는 호석이는 이제 코딩으로 이 문제를 해결할 줄 모른다.
 알고리즘 퇴물 호석이를 위해서 최적의 위치가 될 수 있는 건물 2개의 번호와 그 때의 "모든 건물에서 가장 가까운 치킨집까지 왕복하는 최단 시간의 총합"을 출력하자.
 만약 이러한 건물 조합이 여러 개라면, 건물 번호 중 작은 게 더 작을수록, 작은 번호가 같다면 큰 번호가 더 작을수록 좋은 건물 조합이다.


 첫 번째 줄에 건물의 개수 N과 도로의 개수 M 이 주어진다. 이어서 M 개의 줄에 걸쳐서 도로의 정보 Ai , Bi 가 공백으로 나뉘어서 주어진다.
 같은 도로가 중복되어 주어지는 경우는 없다. 어떤 두 건물을 잡아도 도로를 따라서 오고 가는 방법이 존재함이 보장된다.

 한 줄에 건물 2개가 지어질 건물 번호를 오름차순으로 출력하고, 그때 모든 도시에서의 왕복 시간의 합을 출력한다.
 만약 건물 조합이 다양하게 가능하면, 작은 번호가 더 작은 것을, 작은 번호가 같다면 큰 번호가 더 작은 걸 출력한다.
*/

/*
  1. sum 변수 선언을 min과 같이 이중 for문 밖에서 하게 되면 테스트 19개중 6개만 정답됨 (반복 할 때마다 sum을 초기화 해줘야함) 
*/

import java.util.Scanner;

public class Main {

        static int INF = 100_000_000; 

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int n = sc.nextInt(); //건물의 개수 n
            int m = sc.nextInt(); //도로의 개수 m

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

            for (int i = 0; i < m; i++){ //도로 정보 그래프에 담기
                int start = sc.nextInt();
                int end = sc.nextInt();

                graph[start - 1][end - 1] = 1;
                graph[end - 1][start - 1] = 1;
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

            int min = INF; //모든 도시에서의 왕복 시간의 합 중 최솟값
            int index1 = 0, index2 = 0; //최솟값일때의 건물 번호

            //최적의 치킨집 건물 고르기
            for (int i = 0; i < n; i++){
                for (int j = i + 1; j < n; j++){//i, j를 치킨집으로 가정하고 최솟값을 갱신하는 반복문

                    int sum = 0;

                    for (int k = 0; k < n; k++){ 
                        sum += Math.min(graph[k][i], graph[k][j]) * 2; //왕복이라 * 2
                    }

                    if (sum < min){
                        min = sum;
                        index1 = i + 1;
                        index2 = j + 1;
                    }
                }
            }

            System.out.println(index1 + " " + index2 + " " + min );
        }
}
