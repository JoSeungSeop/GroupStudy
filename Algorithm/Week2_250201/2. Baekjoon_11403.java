/*

https://www.acmicpc.net/problem/11403
*/

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) { //초기값 설정
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        for (int k = 0; k < n; k++){ //플루이드 알고리즘
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    //i->k 가 1이고 k->j가 1이면 i->j = 1
                    if (graph[i][k] + graph[k][j] == 2){
                       graph[i][j] = 1;
                   }
                }
            }
        }

        for (int i = 0; i < n; i++){ //출력
            for (int j = 0; j < n; j++){
                System.out.print(graph[i][j] + " "); //공백 부분을 \t로 하니까 출력 형식 오류 발생
            }
            System.out.println();
        }
        sc.close();
    }
}
