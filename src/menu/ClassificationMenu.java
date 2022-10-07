package menu;

import java.util.Scanner;

/**
 * @author 홍수희
 * 고객 분류 메뉴
 */
public class ClassificationMenu {
    /**
     * 고객을 등급별로 분류하는 메뉴를 출력하는 함수
     */
    public void printClassificationMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("+++++++++++ 등급별 분류 +++++++++++");
            System.out.println("1. 등급별 분류");
            System.out.println("2. 등급별 분류 : 이름순 정렬");
            System.out.println("3. 등급별 분류 : 이용시간순 정렬");
            System.out.println("4. 등급별 분류 : 결제금액순 정렬");
            System.out.println("5. 뒤로가기");
            System.out.println("++++++++++++++++++++++++++++++++");
            System.out.print(">>");

            String input = scanner.next();
            int inputNum = 0;
            if(input.charAt(0) >= 48 && input.charAt(0) <= 57)
                inputNum = Integer.parseInt(input);
            else{
                System.out.println("『");
                System.out.println("    잘못된 입력입니다. 숫자만 입력해주세요.");
                System.out.println("                                        』");
                continue;
            }

            switch (inputNum){
                case 1:
                    System.out.println("그냥 분류");
                    break;
                case 2:
                    System.out.println("그냥 분류 + 이름순 정렬");
                    break;
                case 3:
                    System.out.println("그냥 분류 + 이용시간순 정렬");
                    break;
                case 4:
                    System.out.println("그냥 분류 + 결제금액순 정렬");
                    break;
                case 5:
                    System.out.println("『");
                    System.out.println("    메인화면으로 돌아갑니다.");
                    System.out.println("                           』");
                    break;
                default:
                    System.out.println("『");
                    System.out.println("    잘못된 입력입니다. 메뉴에 있는 숫자만 입력해주세요.");
                    System.out.println("                                                  』");
            }
            if(inputNum == 5) break;
        }
    }
}
