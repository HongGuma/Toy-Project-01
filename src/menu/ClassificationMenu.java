package menu;

import customer.ClassifiedCustomers;
import exception.InputNumberException;

import java.util.Scanner;

/**
 * @author 홍수희
 * 고객 분류 메뉴
 */
public class ClassificationMenu {
    static Scanner scanner = new Scanner(System.in);
    static InputNumberException numberException = new InputNumberException();

    /**
     * 분류된 고객의 데이터를 확인하는 함수
     * @param classifiedCustomer :MainMenu에서 생성한 객체
     */
    public void printClassificationMenu(ClassifiedCustomers classifiedCustomer){
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
            int inputNum = numberException.exception(input); //숫자가 아니면 -1 리턴
            if(inputNum == -1) continue;

            switch (inputNum){
                case 1:
                    classifiedCustomer.setDefault(); //가장 기본 분류
                    classifiedCustomer.show(); //분류된 데이터 출력하는 함수
                    break;
                case 2:
                case 3:
                case 4:
                    classifiedCustomer.sort(inputNum);
                    break;
                case 5:
                    System.out.println("『");
                    System.out.println("    메인화면으로 돌아갑니다.");
                    System.out.println("                           』");
                    return;
                default:

            }
        }
    }
}
