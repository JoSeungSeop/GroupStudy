/*
https://www.acmicpc.net/problem/17472
*/

package week4_250308.baekjoon.problem_17472;

import java.util.*;

class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<int[]> edges = new ArrayList<>(); //다리 정보를 저장하는 리스트
    static int Count = 0; //섬의 개수
    static int[] parent;

    static void islands() {
        int label = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, label++);
                }
            }
        }
        Count = label - 1;
    }

    static void bfs(int x, int y, int label) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = label;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    map[nx][ny] = label;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    static void findBridges() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    for (int d = 0; d < 4; d++) {
                        int length = 0, nx = i, ny = j;
                        while (true) {
                            nx += dx[d];
                            ny += dy[d];
                            if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == map[i][j]) break;
                            if (map[nx][ny] > 0) {
                                if (length >= 2) {
                                    edges.add(new int[]{map[i][j], map[nx][ny], length});
                                }
                                break;
                            }
                            length++;
                        }
                    }
                }
            }
        }
    }

    static boolean union(int a, int b) {
        int routeA = find(a);
        int routeB = find(b);
        if (routeA == routeB) return false;
        parent[routeB] = routeA;
        return true;
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static int kruskal() {
        edges.sort(Comparator.comparingInt(o -> o[2]));
        parent = new int[Count + 1];
        for (int i = 1; i <= Count; i++) parent[i] = i;

        int totalWeight = 0, count = 0;
        for (int[] edge : edges) {
            if (union(edge[0], edge[1])) {
                totalWeight += edge[2];
                count++;
                if (count == Count - 1) return totalWeight;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        //BFS로 섬 구분
        islands();

        //다리 경우의 수 찾기
        findBridges();

        //크루스칼 알고리즘
        System.out.println(kruskal());
    }

}
