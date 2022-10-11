package customer;


public class Customer {
    public static int serial = 0;
    protected int customerSerialNum;
    protected String customerName;
    protected String customerId;
    protected int spentTime;
    protected int totalPay;



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

    @Override
    public String toString() {
        return "Customer{" +
                "customerSerialNum=" + customerSerialNum +
                ", customerName='" + customerName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", spentTime=" + spentTime +
                ", totalPay=" + totalPay +
                '}';
    }
}
