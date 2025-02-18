package week3_250222.baekjoon.problem_1149;

/* https://www.acmicpc.net/problem/1149
    RGB거리

    문제
    RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.

    집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다.
    각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.

    - 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
    - N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
    - i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.

    입력
    첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다.
    둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다.
    집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.

    출력
    첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //집의 개수
        int[][] cost = new int[N][3]; //색칠 비용을 담을 배열


        for (int i = 0; i < N; i++){ //색칠 비용을 담는 반복문
            int red = sc.nextInt();
            int green = sc.nextInt();
            int blue = sc.nextInt();

            cost[i][0] = red;
            cost[i][1] = green;
            cost[i][2] = blue;
        }

        int PrevRed = cost[0][0], PrevGreen = cost[0][1], PrevBlue = cost[0][2]; //맨 처음 색칠 비용 지정

        for (int i = 1; i < N; i++) { //1부터 N-1까지 현재 색 비용 + 이전 다른 색중 작은 비용을 누적해서 더하는 반복문

            int NowRed = cost[i][0] + Math.min(PrevGreen, PrevBlue); //현재 빨강 비용 + 이전 파랑/초록중 작은 값
            int NowGreen = cost[i][1] + Math.min(PrevRed, PrevBlue);
            int NowBlue = cost[i][2] + Math.min(PrevRed, PrevGreen);

            PrevRed = NowRed; //이전 빨강에 현재 빨강을 골랐을 때의 누적 합을 대입
            PrevGreen = NowGreen;
            PrevBlue = NowBlue;
        }

        System.out.println(Math.min(PrevRed, Math.min(PrevGreen, PrevBlue))); //누적 합이 제일 작은 색을 골라 출력

    }
}
