package customer;

import exception.InputNumberException;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Customers {

    private Customer[] customers;
    private final String ID_REGEX = "^[a-zA-Z0-9_-]{4,}$"; //아이디 정규표현식 (영문자 4자 이상만 가능)

    public Customers(){
        this.customers = new Customer[10];
        customers[0] = new Customer("김철수","kim123",32,120000);
        customers[1] = new Customer("이영희","lee123",20,300000);
        customers[2] = new Customer("박문대","park1234",3,5000);
    }

   /* public Customers(int size){
        Customer[] newCustomers = new Customer[size+10];
        for(int i=0; i<this.customers.length; i++){
            newCustomers[i] = this.customers[i];
        }
    }*/

    static Scanner scanner = new Scanner(System.in);
    static InputNumberException numberException = new InputNumberException();

    /**
     * 고객의 데이터를 추가하는 함수
     */
    public void insertCustomer(){
        int customerCnt = 0; //추가할 고객의 수
        System.out.println("추가할 고객의 인원을 입력해주세요."); //입력 유도 메시지
        System.out.print(">>");
        String input = scanner.next();

        customerCnt = numberException.exception(input);
        if(customerCnt == -1) return;

        Customer[] newCustomers = inputCustomerInfo(customerCnt); //newCustomers 라는 새로운 객체에 입력 받았던 데이터 넣기
        int originArrayNum = countNullObject(); //기존 객체 배열에 실제로 담긴 데이터 개수

        if(newCustomers.length > this.customers.length){ //새로 입력받은 객체배열의 크기가 기존 객체 배열의 크기보다 크다면
            extendCustomersArray(newCustomers); //기존 객체 확장
        }else if(this.customers.length - originArrayNum < newCustomers.length ){ //기존 객체 배열의 빈 데이터 크기보다 새로 입력받은 객체배열의 크기가 크다면
            extendCustomersArray(newCustomers); //기존 객체 확장
        }else{ //기존 객체 배열의 빈 데이터 크기보다 새로 입력 받은 객체배열의 크기가 작다면
            int n = 0;
            for(int i=0; i<customers.length; i++){ //기존 객체 배열의 빈 값에 새로 입력 받은 객체 배열 값 넣기
                if(customers[i] == null){ //빈 데이터 자리에만 넣으려고
                    customers[i] = newCustomers[n];
                    n++;
                    if(n > newCustomers.length-1) return; // n 이 newCustomers 의 크기만큼 커지면 입력이 끝난다. 입력 종료
                }
            }
        }

    }

    /**
     * 사용자에게 데이터 입력받는 함수
     * @param num 추가할 고객의 수
     * @return 새로만든 Customer 객체 배열
     */
    public Customer[] inputCustomerInfo(int num){
        //TODO : 아이디 입력시 중복 체크? 기능 추가하기?
        //TODO : 입력받을때 예외 상황이 발생하면 처음부터 돌아가는거 수정하기
        Customer[] insertCustomers = new Customer[num]; //입력받은 수 만큼 Customer 객체 배열 생성
        String name = ""; //입력받을 사용자 이름
        String id = ""; //입력받을 사용자 아이디
        int spentTime = 0; //입력받을 스토어 이용 시간
        int totalPay = 0; //입력받을 사용한 금액
        int i=0;

        while(i<num){
            // 이름 입력받기
            System.out.println("~** "+(i+1)+"번 입력 중 **~");
            System.out.println("고객 이름 입력 (3글자 이상)");
            System.out.print(">>");
            name = scanner.next();
            if(name.length() < 3){ //3글자 이하 입력 예외 처리
                System.out.println("【 3글자 이상 입력해주세요. 】");
                continue;
            }
            // 아이디 입력 받기
            System.out.println("고객 아이디 입력 (영문자, 4글자 이상)");
            System.out.print(">>");
            id = scanner.next();
            if(!Pattern.matches(ID_REGEX,id)){ //정규표현식이랑 맞지 않을때 예외 처리
                System.out.println("【 아이디는 영문자만 4글자 이상 입력해주세요. 처음으로 돌아갑니다. 】");
                continue;
            }
            //스토어 이용시간 입력 받기
            System.out.println("스토어 이용시간 입력");
            System.out.print(">>");
            String input1 = scanner.next();
            if(input1.charAt(0) >= 48 && input1.charAt(0) <= 57){ //숫자가 아닐때 예외 처리
                spentTime = Integer.parseInt(input1);
            }else{
                System.out.println("【 숫자만 입력해주세요. 처음으로 돌아갑니다. 】");
            }
            //스토어 사용 금액 입력 받기
            System.out.println("스토어에서 사용한 금액 입력");
            System.out.print(">>");
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

    /**
     * 기존의 객체 배열을 확장하는 함수
     * @param newCustomers : 새로 입력받은 객체 배열
     */
    public void extendCustomersArray(Customer[] newCustomers){
        int size = this.customers.length + newCustomers.length; //기존 객체 배열 크기 + 새로운 객체 배열 크기 ( 새로운 객체 배열 크기만큼 확장하려고 )
        Customer[] tmp = new Customer[size]; // 임시 객체 배열 생성
        int i=0;
        for(Customer origin : this.customers) { // 임시 객체 배열에 기존 객체 배열 데이터 부터 넣기
            if (origin != null) tmp[i] = origin; //null 값말고 실제 데이터만 넣기
            i++;
        }
        for(Customer newCustomer : newCustomers){ // 임시 객체 배열에 새로 입력 받은 객체 배열 데이터 넣기
            tmp[i] = newCustomer;
            i++;
        }
        this.customers = tmp; // 기존 객체 배열 -> 확장된 객체 배열로 변경
    }

    /**
     * 기존 객체배열에 담긴 데이터의 개수를 세는 함수
     * @return null이 아닌 데이터의 개수
     */
    public int countNullObject(){
        int cnt = 0;
        for(Customer c : this.customers){ //null 이 아닌 데이터만 세기
            if(c != null) cnt++;
        }
        return cnt;
    }

    /**
     * 고객 데이터 출력하는 함수
     */
    public void printCustomer(){
        int cnt = 0;
        for(Customer customer : this.customers){
            if(customer != null){
                System.out.println(customer.toString());
                cnt++;
            }
        }
        //아무 데이터가 없으면 출력
        if(cnt == 0 ) {
            System.out.println("『");
            System.out.println("    고객 데이터가 없습니다.");
            System.out.println("                          』");
        }
    }

    /**
     * 고객정보 삭제하는 함수
     */
    public void removeCustomer(){
        System.out.print("삭제할 고객의 아이디를 입력해주세요 >> ");
        String inputId = scanner.next();
        Customer[] tmp = new Customer[this.customers.length-1]; //한개 지울꺼라서 기존 객체 배열 크기 -1
        int i=0;
        int cnt=0; // 입력받은 아이디가 해당 객체 배열에 있는지 확인하는용
        for(Customer customer : this.customers){
            if(customer != null){
                if(customer.getCustomerId().equals(inputId)){
                    cnt++;
                    continue;
                }
                tmp[i] = customer;
                i++;
            }
        }

        if(cnt == 0){
            System.out.println("『");
            System.out.println("    없는 아이디 입니다.");
            System.out.println("                       』");
        }else{
            this.customers = tmp;
            System.out.println("『");
            System.out.println("    삭제가 완료 되었습니다.");
            System.out.println("                       』");
        }
    }

    /**
     * 고객정보 수정하는 함수
     */
    public void editCustomer(){
        System.out.print("수정할 고객의 아이디를 입력해주세요 >> ");
        String inputId = scanner.next();
        for(Customer customer : this.customers){
            if(customer != null && customer.getCustomerId().equals(inputId)){
                while(true){
                    System.out.println("###############################");
                    System.out.println("1. 이름 수정");
                    System.out.println("2. 아이디 수정");
                    System.out.println("3. 스토어 이용 시간 수정");
                    System.out.println("4. 스토어 사용 금액 수정");
                    System.out.println("5. 종료");
                    System.out.println("###############################");
                    System.out.print(">>");
                    String input = scanner.next();
                    int selectNum = numberException.exception(input);
                    if(selectNum == -1) continue;

                    switch (selectNum){
                        case 1:
                            System.out.println("현재 이름 : "+customer.getCustomerName());
                            while(true){
                                System.out.print("수정할 이름 : ");
                                String newName = scanner.next();
                                if(newName.length() < 3){
                                    System.out.println("3글자 이상 입력해주세요.");
                                }else{
                                    customer.setCustomerName(newName);
                                    break;
                                }
                            }
                            break;
                        case 2:
                            System.out.println("현재 아이디 : "+customer.getCustomerId());
                            while(true){
                                System.out.print("수정할 아이디 : ");
                                String newId = scanner.next();
                                if(!Pattern.matches(ID_REGEX,newId)){
                                    System.out.println("영문자 및 숫자 조합으로 4글자 이상 입력해주세요.");
                                }else{
                                    customer.setCustomerId(newId);
                                    break;
                                }
                            }
                            break;
                        case 3:
                            System.out.println("현재 스토어 이용 시간 : "+customer.getSpentTime());
                            while(true){
                                System.out.print("수정할 시간 : ");
                                String newTime = scanner.next();
                                if(newTime.charAt(0) >= 48 && newTime.charAt(0) <= 57){
                                    customer.setSpentTime(Integer.parseInt(newTime));
                                    break;
                                }else{
                                    System.out.println("숫자만 입력해주세요.");
                                }
                            }
                            break;
                        case 4:
                            System.out.println("현재 스토어 이용 금액 : "+customer.getTotalPay());
                            while(true){
                                System.out.print("수정할 금액 : ");
                                String newPay = scanner.next();
                                if(newPay.charAt(0) >= 48 && newPay.charAt(0) <= 57){
                                    customer.setTotalPay(Integer.parseInt(newPay));
                                    break;
                                }else{
                                    System.out.println("숫자만 입력해주세요.");
                                }
                            }
                            break;
                        case 5:
                            System.out.println("수정이 완료되었습니다.");
                            return;
                        default:
                            System.out.println("『");
                            System.out.println("    메뉴에 있는 번호만 입력해주세요.");
                            System.out.println("                                 』");
                    }
                }
            }
        }
    }
}
