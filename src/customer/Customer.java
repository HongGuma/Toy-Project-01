package customer;


import grade.Grade;

/**
 * @author 홍수희
 * 고객 객체
 */
public class Customer {
    public static int serial = 0; //static, 객체가 하나 생성될 때 마다 1씩 증가, 모든 객체가 공유
    protected int customerSerialNum; //객체 고유의 값
    protected String customerName; //고객 이름
    protected String customerId; //고객 아이디
    protected int spentTime; //고객이 스토어를 이용한 시간
    protected int totalPay; //고객이 스토어에서 이용한 금액
    protected Grade grade; //해당 고객의 등급



    /**
     * Customer 객체 생성자
     * @param name : 고객 성함 (3자 이상)
     * @param id : 고객 아이디 (only 영문자, 4자 이상)
     * @param spentTime : 고객이 스토어 이용한 시간
     * @param totalPay : 고객이 스토어에서 사용한 총 금액
     */
    public Customer(String name, String id, int spentTime, int totalPay){
        serial++;
        this.customerSerialNum = serial;
        this.customerName = name;
        this.customerId = id;
        this.spentTime = spentTime;
        this.totalPay = totalPay;
    }

    //SerialNum getter setter
    public int getCustomerSerialNum() {
        return customerSerialNum;
    }
    public void setCustomerSerialNum(int customerSerialNum) {
        this.customerSerialNum = customerSerialNum;
    }

    //customerName getter setter
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    //customerId getter setter
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    //spentTime getter setter
    public int getSpentTime() {
        return spentTime;
    }
    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    //totalPay getter setter
    public int getTotalPay() {
        return totalPay;
    }
    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
    }

    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerSerialNum=" + customerSerialNum +
                ", customerName='" + customerName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", spentTime=" + spentTime +
                ", totalPay=" + totalPay +
                ", grade=" +grade +
                '}';
    }
}
