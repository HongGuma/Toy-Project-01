package grade;

/**
 * @author 홍수희
 * 등급 객체
 */
public class Group {
    private Grade groupType; //등급 타입 general, vip, vvip
    private int spentTime; //스토어 이용시간 기준
    private int totalPay; //스토어 사용 금액 기준

    /**
     * Group 생성자
     * @param groupType : 등급 타입 (general 인지 vip 인지 등등)
     * @param spentTime : 해당 등급의 스토어 이용 시간 기준
     * @param totalPay : 해당 등급의 스토어에서 사용한 금액
     */
    public Group(Grade groupType, int spentTime, int totalPay){
        this.groupType = groupType;
        this.spentTime = spentTime;
        this.totalPay = totalPay;
    }

    public Grade getGroupType() {
        return groupType;
    }

    public void setGroupType(Grade groupType) {
        this.groupType = groupType;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    public int getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupType=" + groupType +
                ", spentTime=" + spentTime +
                ", totalPay=" + totalPay +
                '}';
    }
}
