package menu;

import customer.Customers;
import exception.InputNumberException;

import java.util.Scanner;

/**
 * @author 홍수희
 * 고객 정보 입출력 받는 메뉴
 */
public class CustomerMenu {
    static Scanner scanner = new Scanner(System.in);
    static InputNumberException numberException = new InputNumberException();

    /**
     * 고객 정보를 입력, 수정, 확인 하는 메뉴
     * @param customers :MainMenu에서 생성한 customer 객체
     */
    public void printCustomerMenu(Customers customers){
        while (true){
            System.out.println("########### 고객 정보 ###########");
            System.out.println("1. 고객 정보 추가하기");
            System.out.println("2. 고객 정보 확인하기");
            System.out.println("3. 고객 정보 수정하기");
            System.out.println("4. 고객 정보 삭제하기");
            System.out.println("5. 뒤로가기");
            System.out.println("###############################");
            System.out.print(">>");

            String input = scanner.next();
            int inputNum = numberException.exception(input); //숫자가 아니면 -1 리턴
            if(inputNum == -1) continue;

            switch (inputNum){
                case 1:
                    customers.addCustomer();
                    break;
                case 2:
                    customers.printCustomer();
                    break;
                case 3:
                    customers.editCustomer();
                    break;
                case 4:
                    customers.removeCustomer();
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
