package customer;

import exception.InputNumberException;
import grade.*;

import java.util.Scanner;

/**
 * @author 홍수희
 * 분류된 고객의 데이터를 확인
 */
public class ClassifiedCustomers {
    private Customers[] classifiedCustomers; //분류된 고객 데이터
    private Customer[] customers; //분류되기전 고객 데이터
    private Group[] groups; //등급

    static Scanner scanner = new Scanner(System.in); //scanner
    static InputNumberException numberException = new InputNumberException(); //숫자만 입력받기

    /**
     * classfiedCustomers 생성자
     * @param customers : 분류되기전 기존의 고객 데이터
     * @param groups : 설정된 등급 데이터
     */
    public ClassifiedCustomers(Customers customers, Groups groups){
        this.customers = customers.getCustomers();
        this.groups = groups.getGroups();
        this.classifiedCustomers = new Customers[Grade.values().length];
    }

    /**
     * 분류된 객체들을 출력하는 함수
     */
    public void show(){
        int i=0;
        for(Customers customers1 : classifiedCustomers){
            System.out.println("[["+Grade.values()[i]+"]]");
            if(customers1 != null && customers1.getCustomers().length > 0){ //null 이 아니라면
                for(Customer customer : customers1.getCustomers()){
                    System.out.println(customer.toString());
                }
            }else{ //null 이라면
                System.out.println("null");
            }
            i++;
        }
    }

    /**
     * 제일 기본 분류 (등급만으로만 분류되어 있음 정렬 X)
     */
    public void setDefault(){
        //설정된 기준이 있는지 확인
        for(Group  group : this.groups){
            if(group == null){
                System.out.println("등급을 먼저 설정해주세요.");
                return;
            }
        }

        //customer 등급 설정하기
        for(Customer customer : this.customers){
            if(customer != null){
                switch (compareRange(customer)){
                    case 0: //general
                        customer.setGrade(Grade.GENERAL);
                        break;
                    case 1: //vip
                        customer.setGrade(Grade.VIP);
                        break;
                    case 2: //vvip
                        customer.setGrade(Grade.VVIP);
                        break;
                    case -1: //none
                        customer.setGrade(Grade.NONE);
                        break;
                    default: //error
                        System.out.println("error");
                }
            }
        }

        //등급 개수 만큼 반복
        for(int num = 0; num<Grade.values().length; num++){
            int cnt = 0;

            for(Customer customer : this.customers){
                if(customer != null ) {
                    if (customer.getGrade().equals(Grade.values()[num]))
                        cnt++;
                }
            }

            Customer[] tmp = new Customer[cnt];
            int i=0;
            for(Customer customer : this.customers){
                if(customer != null){
                    if(customer.getGrade().equals(Grade.values()[num])){
                        tmp[i] = customer;
                        i++;
                    }
                }
            }
            classifiedCustomers[num] = new Customers(tmp);

        }
    }

    /**
     * 고객의 spent time 이랑 total pay 가 등급안에 해당되는 값인지 판단하는 함수
     * @param customer : 고객 객체
     * @return -1: none (어떤 범위에도 해당하지 않음), 0 : general, 1: vip, 2: vvip
     */
    public int compareRange(Customer customer){
        int spentTime = customer.getSpentTime(); //고객의 스토어 이용 시간
        int totalPay = customer.getTotalPay(); //고객이 스토어에서 사용한 돈
        Group general = this.groups[0]; // general group 객체
        Group vip = this.groups[1]; // vip group 객체
        Group vvip = this.groups[2]; // vvip group 객체

        //고객의 데이터가 general 이상인가?
        if(spentTime >= general.getSpentTime() && totalPay >= general.getTotalPay()){
            //고객의 데이터가 vip 이상인가?
            if(spentTime >= vip.getSpentTime() && totalPay >= vip.getTotalPay()){
                //고객의 데이터가 vvip 이상인가?
                if(spentTime >= vvip.getSpentTime() && totalPay >= vvip.getTotalPay())
                    return 2; //vvip
                //vvip 보다 작고 vip 보다 크다는 뜻임으로
                else
                    return 1; //vip
            //general 보다 크고 vip 보다 작다는 뜻임으로
            }else
                return 0; //general
        //general 보다 작으면
        }else
            return -1; //none
    }


    public void sort(int standard){
        if(classifiedCustomers[0] == null)
            setDefault();
        while(true){
            System.out.println("+++++++++++");
            System.out.println("1.오름 차순");
            System.out.println("2.내림 차순");
            System.out.println("3.뒤로 가기");
            System.out.print(">> ");
            String input = scanner.next();
            int menu = numberException.exception(input);

            if(menu == 3) return;
            if(menu >= 1 && menu < 3){
                switch (standard){
                    case 2: //이름순
                        System.out.println("+++++ ( 이름순 정렬 ) +++++");
                        for(Customers classifiedCustomer : classifiedCustomers){
                            Customer[] tmp = sortByName(menu, classifiedCustomer.getCustomers());
                            classifiedCustomer.setCustomers(tmp);
                        }
                        show();
                        break;
                    case 3: //스토어 이용 시간 순
                        System.out.println("+++++ ( 스토어 이용시간순 정렬 ) +++++");
                        for(Customers classifiedCustomer : classifiedCustomers){
                            Customer[] tmp = sortBySpentTime(menu, classifiedCustomer.getCustomers());
                            classifiedCustomer.setCustomers(tmp);
                        }
                        show();
                        break;
                    case 4: //스토어 사용 금액 순
                        System.out.println("+++++ ( 스토어 사용금액순 정렬 ) +++++");
                        for(Customers classifiedCustomer : classifiedCustomers){
                            Customer[] tmp = sortByTotalPay(menu, classifiedCustomer.getCustomers());
                            classifiedCustomer.setCustomers(tmp);
                        }
                        show();
                        break;
                }
            }else{
                System.out.println("메뉴에 있는 번호만 입력해주세요.");
            }

        }
    }

    /**
     * 이름순으로 정렬
     * @param sortType 1: 오름차순, 2: 내림차순
     * @param customers 정렬되기전 분류된 데이터
     * @return 정렬된 데이터
     */
    public Customer[] sortByName(int sortType, Customer[] customers){
        if(sortType == 1){ //오름차순
            for(int i = 0; i< customers.length; i++){
                for(int j = i; j< customers.length; j++){
                    if(customers[i].getCustomerName().compareTo(customers[j].getCustomerName()) > 0){
                        Customer tmp = customers[i];
                        customers[i] = customers[j];
                        customers[j] = tmp;
                    }
                }
            }
        }else{ //내림차순
            for(int i = 0; i< customers.length; i++){
                for(int j = i; j< customers.length; j++){
                    if(customers[i].getCustomerName().compareTo(customers[j].getCustomerName()) < 0){
                        Customer tmp = customers[i];
                        customers[i] = customers[j];
                        customers[j] = tmp;
                    }
                }
            }
        }

        return customers;
    }

    /**
     * 스토어 이용시간순 정렬
     * @param sortType 1: 오름차순, 2: 내림차순
     * @param customers 정렬되기전 분류된 고객 데이터
     * @return 정렬된 고객 데이터
     */
    public Customer[] sortBySpentTime(int sortType, Customer[] customers){
        if(sortType == 1){ //오름차순
            for(int i = 0; i< customers.length; i++){
                for(int j = i; j< customers.length; j++){
                    if(customers[i].getSpentTime() > customers[j].getSpentTime()){
                        Customer tmp = customers[i];
                        customers[i] = customers[j];
                        customers[j] = tmp;
                    }
                }
            }
        }else{ //내림차순
            for(int i = 0; i< customers.length; i++){
                for(int j = i; j< customers.length; j++){
                    if(customers[i].getSpentTime() < customers[j].getSpentTime()){
                        Customer tmp = customers[i];
                        customers[i] = customers[j];
                        customers[j] = tmp;
                    }
                }
            }
        }

        return customers;
    }

    /**
     * 스토어에서 사용한 금액순으로 정렬
     * @param sortType 1: 오름차순, 2:내림차순
     * @param customers 정렬되기전 분류된 데이터
     * @return 정렬된 데이터
     */
    public Customer[] sortByTotalPay(int sortType, Customer[] customers){
        if(sortType == 1){ //오름차순
            for(int i = 0; i< customers.length; i++){
                for(int j = i; j< customers.length; j++){
                    if(customers[i].getTotalPay() > customers[j].getTotalPay()){
                        Customer tmp = customers[i];
                        customers[i] = customers[j];
                        customers[j] = tmp;
                    }
                }
            }
        }else{ //내림차순
            for(int i = 0; i< customers.length; i++){
                for(int j = i; j< customers.length; j++){
                    if(customers[i].getTotalPay() < customers[j].getTotalPay()){
                        Customer tmp = customers[i];
                        customers[i] = customers[j];
                        customers[j] = tmp;
                    }
                }
            }
        }

        return customers;
    }


}
