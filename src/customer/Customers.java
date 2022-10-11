package customer;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Customers {

    private Customer[] customers;

    public Customers(){
        this.customers = new Customer[10];
    }

   /* public Customers(int size){
        Customer[] newCustomers = new Customer[size+10];
        for(int i=0; i<this.customers.length; i++){
            newCustomers[i] = this.customers[i];
        }
    }*/

    static Scanner scanner = new Scanner(System.in);

    /**
     * 고객의 데이터를 추가하는 함수
     */
    public void insertCustomer(){
        String ID_REGEX = "^[a-zA-Z]*${4,}"; //아이디 정규표현식 (영문자 4자 이상만 가능)
        int customerCnt = 0; //추가할 고객의 수
        countNullObject();
        System.out.println("추가할 고객의 인원을 입력해주세요.");
        System.out.print(">>");
        String input = scanner.next();

        //TODO : 숫자만 입력받는거 예외처리 코드 너무 중복되는데 함수로 따로 만들어보기
        if(input.charAt(0) >= 48 && input.charAt(0) <= 57) //숫자가 아닐때 예외 처리
            customerCnt = Integer.parseInt(input);
        else{
            System.out.println("『");
            System.out.println("    잘못된 입력입니다. 숫자만 입력해주세요.");
            System.out.println("                                        』");
        }

        //TODO : 새로 추가한 데이터를 기존 배열에 넣는 함수 만들기 (배열 확장해야댐)
        Customer[] newCustomers = inputCustomerInfo(customerCnt,ID_REGEX);
        extendCustomersArray(newCustomers);

    }

    /**
     * 사용자에게 데이터 입력받는 함수
     * @param num 추가할 고객의 수
     * @param REGEX 아이디 정규표현식
     * @return 새로만든 Customer 객체 배열
     */
    public Customer[] inputCustomerInfo(int num, String REGEX){
        Customer[] insertCustomers = new Customer[num]; //입력받은 수 만큼 Customer 객체 배열 생성
        String name = ""; //입력받을 사용자 이름
        String id = ""; //입력받을 사용자 아이디
        int spentTime = 0; //입력받을 스토어 이용 시간
        int totalPay = 0; //입력받을 사용한 금액
        int i=0;

        while(i<num){
            // 이름 입력받기
            System.out.println("고객 이름 입력 (3글자 이상)");
            System.out.print(">>");
            name = scanner.next();
            if(!Pattern.matches("^{3,}$",name)){ //3글자 이하 입력 예외 처리
                System.out.println("【 3글자 이상 입력해주세요. 】");
                continue;
            }
            // 아이디 입력 받기
            System.out.println("고객 아이디 입력 (영문자, 4글자 이상)");
            System.out.print(">>");
            id = scanner.next();
            if(!Pattern.matches(REGEX,id)){ //정규표현식이랑 맞지 않을때 예외 처리
                System.out.println("【 아이디는 영문자만 4글자 이상 입력해주세요. 처음으로 돌아갑니다. 】");
                continue;
            }
            //스토어 이용시간 입력 받기
            System.out.println("스토어 이용시간 입력");
            String input1 = scanner.next();
            if(input1.charAt(0) >= 48 && input1.charAt(0) <= 57){ //숫자가 아닐때 예외 처리
                spentTime = Integer.parseInt(input1);
            }else{
                System.out.println("【 숫자만 입력해주세요. 처음으로 돌아갑니다. 】");
            }
            //스토어 사용 금액 입력 받기
            System.out.println("스토어에서 사용한 금액 입력");
            String input2 = scanner.next();
            if(input2.charAt(0) >= 48 && input2.charAt(0) <= 57){ //숫자가 아닐때 예외 처리
                totalPay = Integer.parseInt(input1);
            }else{
                System.out.println("【 숫자만 입력해주세요. 처음으로 돌아갑니다. 】");
            }
            //입력받은 데이터로 새 customer 객체 생성해서 customers 배열에 넣기
            insertCustomers[i] = new Customer(name,id,spentTime,totalPay);
            i++;
        }

        return insertCustomers;

    }

    public void extendCustomersArray(Customer[] newCustomers){
        int originArrayNum = countNullObject();
        if(newCustomers.length > originArrayNum){
            int size = this.customers.length + newCustomers.length;
            Customer[] tmp = new Customer[size];
            int i=0;
            for(Customer origin : this.customers){
                if(origin != null){
                    tmp[i] = origin;
                }
                i++;
            }
            for(Customer newCustomer : newCustomers){
                tmp[i] = newCustomer;
            }
        }
    }

    public int countNullObject(){
        int cnt = 0;
        for(Customer c : this.customers){
            if(c == null) cnt++;
        }
        return cnt;
    }

    public void removeCustomer(){
        System.out.print("삭제할 고객의 아이디를 입력해주세요 >> ");
        String inputId = scanner.next();
    }

    public void printCustomer(){
        System.out.println("『");
        System.out.println("    고객 데이터 출력");
        System.out.println("                     』");
    }

    public void editCustomer(){
        System.out.print("수정할 고객의 아이디를 입력해주세요 >> ");
        String inputId = scanner.next();
    }
}
