package customer;

import grade.*;

import java.util.Arrays;

public class ClassifiedCustomer {
    private Customers[] classifiedCustomers;
    private Customer[] customers;
    private Group[] groups;

    public ClassifiedCustomer(){
        this.customers = new Customers().getCustomers();
        this.groups = new Groups().getGroups();
    }

    public void show(){
        for(Customers customers1 : classifiedCustomers){
            if(customers1 != null){
                System.out.println(customers1.getCustomers());
            }
        }
    }


    public void setDefault(){
        for(Group group : this.groups){
            if(group == null){
                System.out.println("등급을 먼저 설정해주세요.");
                return;
            }
        }
        //customer 등급 설정하기
        for(Customer customer : this.customers){
            if(customer != null){
                switch (compareRange(customer)){
                    case 0:
                        customer.setGrade(Grade.GENERAL);
                        break;
                    case 1:
                        customer.setGrade(Grade.VIP);
                        break;
                    case 2:
                        customer.setGrade(Grade.VVIP);
                        break;
                    case -1:
                        customer.setGrade(Grade.NONE);
                        break;
                    default: //TODO: default 값 생각해보기
                        System.out.println("에러...?");
                }
            }
        }

        int num = 0;
        for(Group group : this.groups){
            int cnt = 0;

            for(Customer customer : this.customers){
                if(customer.getGrade() == Grade.GENERAL) cnt++;
            }

            Customer[] tmp = new Customer[cnt];
            int i=0;
            for(Customer customer : this.customers){
                if(customer.getGrade() == Grade.GENERAL){
                    tmp[i] = customer;
                    i++;
                }
            }

            classifiedCustomers[num] = new Customers(tmp);
            num++;

        }

        show();

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
        if(spentTime >= general.getSpentTime() && totalPay >= general.getTotalPay()){ //고객의 데이터가 general 이상인가?
            if(spentTime >= vip.getSpentTime() && totalPay >= vip.getTotalPay()){ //고객의 데이터가 vip 이상인가?
                if(spentTime >= vvip.getSpentTime() && totalPay >= vvip.getTotalPay()) //고객의 데이터가 vvip 이상인가?
                    return 2; //vvip
                else //vvip 보다 작고 vip 보다 크다는 뜻임으로
                    return 1; //vip
            }else //general 보다 크고 vip 보다 작다는 뜻임으로
                return 0; //general
        }else //general 보다 작으면
            return -1; //none
    }

    public void setByName(){

    }

    public void setBySpentTime(){

    }

    public void setByTotalPay(){

    }

}
