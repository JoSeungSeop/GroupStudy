/*

https://www.acmicpc.net/problem/1389

1.친구 관계는 양방향이라고 나와있으니 반대로도 1을 설정해줘야함
2.Integer.MAX_VALUE 더하기 연산 시 오버플로우 발생 (Integer.MAX_VALUE + 1 하면 음수가 되므로, 이상한 값이 나올 수 있음)

*/

import java.util.Scanner;

public class Main {

    static int INF = 10000; //최대값을 Integer.MAX_VALUE로 하니 플루이드 알고리즘 비교 과정에서 오버플로우가 발생해서 10000으로 설정함

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int [][] array = new int[n1][n1];

        for (int i = 0; i < n1; i++){ //배열 무한대값 지정
            for (int j = 0; j < n1; j++){
                if (i==j){
                    array[i][j] = 0;
                    continue;
                }
                else array[i][j] = INF;
            }
        }

        for (int i = 0; i < n2; i++){ //친구 관계 1로 설정
            int a = sc.nextInt();
            int b = sc.nextInt();
            array[a - 1][b - 1] = 1;
            array[b - 1][a - 1] = 1; //양방향이므로 반대로도 1 설정
        }


        for (int k = 0; k < n1; k++){ //플루이드 알고리즘
            for (int i = 0; i < n1; i++) {
                for (int j = 0; j < n1; j++) {

                    if (array[i][k] + array[k][j] < array[i][j]){
                        array[i][j] = array[i][k] + array[k][j];

                    }

                }
            }
        }
        System.out.println(findMinIndex(array));
    }

    public static int findMinIndex(int[][] arr) { //각 행의 합중 최소값인 인덱스를 반환하는 함수


        int sum2 = INF;
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum1 = 0;

            for (int j = 0; j < arr.length; j++){
                sum1 += arr[i][j];
            }
            if (sum2 > sum1){
                sum2 = sum1;
                index = i;
            }
        }
        return index + 1;  // 최소값을 가진 가장 앞의 인덱스 반환
    }
}
