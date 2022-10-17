package customer;

import grade.*;

public class ClassifiedCustomers {
    private Customers[] classifiedCustomers;
    private Customer[] customers;
    private Group[] groups;

    public ClassifiedCustomers(Customers customers, Groups groups){
        this.customers = customers.getCustomers();
        this.groups = groups.getGroups();
        this.classifiedCustomers = new Customers[Grade.values().length];
    }

    public void show(){
        int i=0;
        for(Customers customers1 : classifiedCustomers){
            System.out.println("[["+Grade.values()[i]+"]]");
            if(customers1 != null && customers1.getCustomers().length > 0){
                for(Customer customer : customers1.getCustomers()){
                    System.out.println(customer.toString());
                }
            }else{
                System.out.println("null");
            }
            i++;
        }
    }


    public void setDefault(){
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
                    default:
                        System.out.println("error");
                }
            }
        }

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

    public void setByName(){

    }

    public void setBySpentTime(){

    }

    public void setByTotalPay(){

    }

}
