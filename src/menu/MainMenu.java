package menu;

import customer.ClassifiedCustomers;
import customer.Customers;
import exception.InputNumberException;
import grade.Groups;

import java.util.Scanner;

/***
 * @author 홍수희
 * 메인 메뉴 출력하는 클래스
 */
public class MainMenu {
    static Scanner scanner = new Scanner(System.in); //scanner
    static InputNumberException numberException = new InputNumberException(); //숫자만 입력 받게

    /**
     * 메인 메뉴 출력하는 함수
     */
    public void printMainMenu(){
        Customers customers = new Customers(); //customer 객체
        Groups groups = new Groups(); //groups 객체
        ClassifiedCustomers classifiedCustomer = new ClassifiedCustomers(customers,groups); //classification 객체

        GradeMenu gradeMenu = new GradeMenu(); //등급 설정 메뉴
        CustomerMenu customerMenu = new CustomerMenu(); //고객 설정 메뉴
        ClassificationMenu classificationMenu = new ClassificationMenu(); //고객 분류 메뉴

        while(true){
            System.out.println("=========== 메인 메뉴 ===========");
            System.out.println("1. 등급 분류 설정");
            System.out.println("2. 고객 정보");
            System.out.println("3. 고객 등급 분류");
            System.out.println("4. 종료");
            System.out.println("================================");
            System.out.print(">> ");

            String input = scanner.next();
            int inputNum = numberException.exception(input); //숫자가 아니면 -1 리턴
            if(inputNum == -1) continue; //다시 입력 받기

            switch (inputNum){
                case 1:
                    gradeMenu.showGradeMenu(groups);
                    break;
                case 2:
                    customerMenu.printCustomerMenu(customers);
                    break;
                case 3:
                    classificationMenu.printClassificationMenu(classifiedCustomer);
                    break;
                case 4:
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("『");
                    System.out.println("    잘못된 입력입니다. 메뉴에 있는 숫자만 입력해주세요.");
                    System.out.println("                                                  』");
                    break;
            }

        }

    }
}
