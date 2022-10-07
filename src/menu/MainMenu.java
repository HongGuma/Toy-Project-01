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
        CustomerMenu customerMenu = new CustomerMenu();
        ClassificationMenu classificationMenu = new ClassificationMenu();
        while(true){
            System.out.println("=========== 메인 메뉴 ===========");
            System.out.println("1. 등급 분류 설정");
            System.out.println("2. 고객 정보");
            System.out.println("3. 고객 등급 분류");
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
                    gradeMenu.printGradeMenu();
                    break;
                case 2:
                    customerMenu.printCustomerMenu();
                    break;
                case 3:
                    classificationMenu.printClassificationMenu();
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
