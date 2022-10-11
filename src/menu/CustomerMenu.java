package menu;

import customer.Customers;

import java.util.Scanner;

/**
 * @author 홍수희
 * 고객 정보 입출력 받는 메뉴
 */
public class CustomerMenu {
    public void printCustomerMenu(){
        Scanner scanner = new Scanner(System.in);
        Customers customers = new Customers();
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
                    customers.insertCustomer();
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
