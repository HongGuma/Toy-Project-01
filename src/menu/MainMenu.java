package menu;

import java.util.Scanner;

/***
 * @author 홍수희
 * 메인 메뉴 출력하는 클래스
 */
public class MainMenu {
    public void printMainMenu(){
        Scanner scanner = new Scanner(System.in);
        GradeMenu gradeMenu = new GradeMenu();
        while(true){
            System.out.println("================================");
            System.out.println("1. 등급 분류 설정");
            System.out.println("2. 고객 정보");
            System.out.println("3. 고객 정보 확인");
            System.out.println("4. 종료");
            System.out.println("================================");
            System.out.print(">> ");

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
                    //TODO : 등급 분류 기능 연결하기
                    System.out.println("1번 선택");
                    gradeMenu.printGradeMenu();
                    break;
                case 2:
                    //TODO : 고객 정보 입력 기능 연결하기
                    System.out.println("2번 선택");
                    break;
                case 3:
                    //TODO : 고객 분류 출력 기능 연결하기
                    System.out.println("3번 선택");
                    break;
                case 4:
                    System.out.println("종료합니다.");
                    break;
                default:
                    System.out.println("『");
                    System.out.println("    잘못된 입력입니다. 메뉴에 있는 숫자만 입력해주세요.");
                    System.out.println("                                                  』");
                    break;
            }
            if(inputNum == 4) break;
        }

    }
}
