/*
    https://www.acmicpc.net/problem/1197

    그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.
    최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.

    첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다.
    다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다.
    이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.
    그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다.
    최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.
*/

package week4_250308.baekjoon.problem_1197;
import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

    //두 정점을 하나의 집합으로 합치는 함수 (유니온 연산)
    public static void union(int parent[], int x, int y){
        x = find(parent, x);
        y = find(parent, y);

        if(x < y) parent[y] = x; //작은 값을 부모로 설정
        else parent[x] = y;
    }

    //부모 노드를 찾는 함수
    public static int find(int[] parent, int x){
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]); //경로 압축 적용
        }
        return parent[x];
    }

    //크루스칼 알고리즘
    public static void kruskal(int[][] graph, int[] parent){
        Arrays.sort(graph, (a, b) -> Integer.compare(a[2], b[2])); //가중치 기준 정렬

        int cost = 0;
        for(int i = 0; i < graph.length; i++) {
            if (find(parent, graph[i][0]) != find(parent, graph[i][1])) { //서로 다른 집합에 속하면
                cost += graph[i][2]; //비용 추가
                union(parent, graph[i][0], graph[i][1]); //두 정점 연결함
            }
        }
        System.out.println(cost); //최소 신장 트리의 가중치 출력
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int node = sc.nextInt();
        int line = sc.nextInt();

        int[] parent = new int[node + 1]; //유니온파인드 알고리즘 부모 배열
        int[][] graph = new int[line][3]; //간선 정보

        for (int i = 1; i <= node; i++){ //parent 배열 초기화
            parent[i] = i;
        }

        for (int j = 0; j < line; j++){ //간선 정보 초기화
            for (int i = 0; i < 3; i++){
                graph[j][i] = sc.nextInt();
            }
        }

        kruskal(graph, parent);
    }
}
