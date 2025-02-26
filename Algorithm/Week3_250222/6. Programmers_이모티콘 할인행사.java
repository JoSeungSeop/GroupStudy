/*
https://school.programmers.co.kr/learn/courses/30/lessons/150368
*/

class Solution {
    static int[] discounts = {10, 20, 30, 40}; //가능한 할인율
    static int maxMember = 0; //최대 가입자 수
    static int maxSales = 0; //최대 매출액

    public int[] solution(int[][] users, int[] emoticons) {
        int n = emoticons.length;
        int[] discountCombine = new int[n]; //각 이모티콘에 적용할 할인율 조합 저장

        findDiscount(0, users, emoticons, discountCombine);

        return new int[]{maxMember, maxSales};
    }

    //완전 검색
    private void findDiscount(int index, int[][] users, int[] emoticons, int[] discountRates) {
        if (index == emoticons.length) {
            //모든 이모티콘의 할인율을 결정했으면 결과 계산
            calculateResult(users, emoticons, discountRates);
            return;
        }

        //현재 이모티콘에 대해 가능한 모든 할인율 선택 (10%, 20%, 30%, 40%)
        for (int discount : discounts) {
            discountRates[index] = discount;  //현재 이모티콘의 할인율 설정
            findDiscount(index + 1, users, emoticons, discountRates);
        }
    }

    //현재 할인율 조합으로 가입자 수와 매출 계산
    private void calculateResult(int[][] users, int[] emoticons, int[] discountRates) {
        int Member = 0;
        int Sales = 0;

        //각 사용자별 이모티콘 구매
        for (int[] user : users) {
            int minDiscount = user[0]; //최소 할인율 조건
            int maxPrice = user[1]; //구매 한도
            int userSpending = 0;

            //이모티콘 구매 판단
            for (int i = 0; i < emoticons.length; i++) {
                if (discountRates[i] >= minDiscount) {  //사용자의 최소 할인율을 만족하면
                    userSpending += emoticons[i] * (100 - discountRates[i]) / 100;
                }
            }

            //서비스 가입 판단
            if (userSpending >= maxPrice) {
                Member++;
            } else {
                Sales += userSpending;
            }
        }

        //최댓값 갱신
        if (Member > maxMember || (Member == maxMember && Sales > maxSales)) {
            maxMember = Member;
            maxSales = Sales;
        }
    }
}
