package grade;

public class Group {
    private Grade groupType;
    private int spentTime;
    private int totalPay;

    public Group(Grade groupType,int spentTime, int totalPay){
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
