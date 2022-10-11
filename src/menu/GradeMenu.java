package menu;

import exception.InputNumberException;

import java.util.Scanner;

/**
 * @author 홍수희
 * 등급 분류 기준 설정하는 메뉴
 */
public class GradeMenu {
    static Scanner scanner = new Scanner(System.in);
    static InputNumberException numberException = new InputNumberException();
    /**
     * 등급 분류 기준을 설정하는 메뉴를 출력하는 함수
     */
    public void printGradeMenu(){
        while (true){
            System.out.println("********* 분류 기준 설정 **********");
            System.out.println("1. 분류 기준 설정하기");
            System.out.println("2. 분류 기준 확인하기");
            System.out.println("3. 분류 기준 수정하기");
            System.out.println("4. 뒤로가기");
            System.out.println("********************************");
            System.out.print(">>");

            String input = scanner.next();
            int inputNum = numberException.exception(input);
            if(inputNum == -1) continue;

            switch (inputNum){
                case 1:
                    System.out.println("분류 기준을 설정합니다.");
                    break;
                case 2:
                    System.out.println("분류 기준을 확인합니다.");
                    break;
                case 3:
                    System.out.println("분류 기준을 수정합니다.");
                    break;
                case 4:
                    System.out.println("『");
                    System.out.println("    메인화면으로 돌아갑니다.");
                    System.out.println("                           』");
                    return;
                default:
                    System.out.println("『");
                    System.out.println("    잘못된 입력입니다. 메뉴에 있는 숫자만 입력해주세요.");
                    System.out.println("                                                  』");
            }
        }
    }
}
