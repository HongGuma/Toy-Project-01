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
     * 고객을 등급별로 분류하는 메뉴를 출력하는 함수
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
            int inputNum = numberException.exception(input);
            if(inputNum == -1) continue;

            switch (inputNum){
                case 1:
                    classifiedCustomer.setDefault();
                    break;
                case 2:
                    classifiedCustomer.setByName();
                    break;
                case 3:
                    classifiedCustomer.setBySpentTime();
                    break;
                case 4:
                    classifiedCustomer.setByTotalPay();
                    break;
                case 5:
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
