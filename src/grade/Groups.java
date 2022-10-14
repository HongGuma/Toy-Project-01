package grade;

import customer.Customer;
import exception.InputNumberException;

import java.util.Scanner;

public class Groups {
    private Group[] groups;

    public Groups(){
        this.groups = new Group[3];
    }

    static Scanner scanner = new Scanner(System.in);
    static InputNumberException numberException = new InputNumberException();

    /**
     * 설정된 기준값을 출력하는 함수
     */
    public void showGrade(){
        int cnt = 0; //출력 문구 띄우고 싶어서 사용함
        for(Group group : this.groups){
            if(group != null){
                System.out.println(group.toString());
                cnt++;
            }
        }
        //아무 데이터가 없으면 출력
        if(cnt == 0 ) {
            System.out.println("『");
            System.out.println("    설정한 기준값이 없습니다.");
            System.out.println("                            』");
        }
    }

    /**
     * 등급 분류 기준을 설정하는 함수
     */
    public void settingGrade(){
        while(true){
            System.out.println("--------- 등급 기준 설정 ----------");
            System.out.println("1. General");
            System.out.println("2. VIP");
            System.out.println("3. VVIP");
            System.out.println("4. 뒤로가기");
            System.out.println("--------------------------------");
            System.out.print(">> ");

            String input = scanner.next();
            int inputNum = numberException.exception(input);
            if(inputNum == -1) continue;

            if(inputNum >= 1 && inputNum <= 4){
                if(inputNum == 4 ) return;
                if(this.groups[inputNum-1] == null){
                    Group newGroup = inputGrade(inputNum-1);
                    this.groups[inputNum-1] = newGroup;
                }else{
                    System.out.println("『");
                    System.out.println("    이미 설정된 등급입니다.");
                    System.out.println("                          』");
                }
            }else{
                System.out.println("『");
                System.out.println("    메뉴에 있는 번호만 선택해주세요.");
                System.out.println("                                  』");
            }

        }
    }

    /**
     * 등급 분류 기준을 입력받는 함수
     * @param gradeType 등급 타입
     * @return 새로 생성한 Group 객체
     */
    public Group inputGrade(int gradeType){
        String input = ""; // 입력값
        int spentTime = 0; //스토어 이용 시간
        int totalPay = 0; //스토어 이용 금액

        System.out.println("스토어 이용 시간 기준을 입력해주세요.");
        while(true){
            System.out.print(">> ");
            input = scanner.next();
            spentTime = numberException.exception(input); //입력값이 숫자인지 판단
            if(spentTime != -1){ // -1
                if(exceedGradeException(1,spentTime,gradeType)) break;
                else{
                    switch (gradeType){
                        case 0:
                            System.out.println("VIP등급보다 낮아야합니다. 다시 설정해주세요.");
                            System.out.println("VIP : "+this.groups[gradeType+1].getSpentTime());
                            break;
                        case 1:
                            System.out.println(exceedGradeException(1,spentTime,gradeType));
                            System.out.println("GENERAL 보다 높고 VVIP 보다 낮아야합니다. 다시 설정해주세요.");
                            if(this.groups[gradeType-1] !=null)
                                System.out.println("GENERAL : "+this.groups[gradeType-1].getSpentTime());
                            if(this.groups[gradeType+1] != null)
                                System.out.println("VVIP : "+this.groups[gradeType+1].getSpentTime());
                            break;
                        case 2:
                            System.out.println("VIP 보다 높아야 합니다. 다시 설정해주세요.");
                            System.out.println("VIP : "+this.groups[gradeType-1].getSpentTime());
                    }
                }
            }
        }

        System.out.println("스토어에서 사용한 금액의 기준값을 입력해주세요.");
        while(true){
            System.out.print(">> ");
            input = scanner.next();
            totalPay = numberException.exception(input);
            if(totalPay != -1){
                if(exceedGradeException(2,totalPay,gradeType)) break;
                else{
                    switch (gradeType){
                        case 0:
                            System.out.println("VIP등급보다 낮아야합니다. 다시 설정해주세요.");
                            System.out.println("VIP : "+this.groups[gradeType+1].getTotalPay());
                            break;
                        case 1:
                            System.out.println("GENERAL 보다 높고 VVIP 보다 낮아야합니다. 다시 설정해주세요.");
                            if(this.groups[gradeType-1] !=null)
                                System.out.println("GENERAL : "+this.groups[gradeType-1].getTotalPay());
                            if(this.groups[gradeType+1] != null)
                                System.out.println("VVIP : "+this.groups[gradeType+1].getTotalPay());
                            break;
                        case 2:
                            System.out.println("VIP 보다 높아야 합니다. 다시 설정해주세요.");
                            System.out.println("VIP : "+this.groups[gradeType-1].getTotalPay());
                    }
                }
            }
        }

        return new Group(Grade.values()[gradeType],spentTime,totalPay);

    }

    /**
     * 설정된 기준을 수정하는 함수
     */
    public void editGrade(){
        while(true){
            System.out.println("--------- 등급 기준 설정 ----------");
            System.out.println("1. General");
            System.out.println("2. VIP");
            System.out.println("3. VVIP");
            System.out.println("4. 뒤로가기");
            System.out.println("--------------------------------");
            System.out.print(">> ");

            String input = scanner.next();
            int gradeType = numberException.exception(input);
            if(gradeType == -1) continue;


            if(gradeType >= 1 && gradeType <= 4){
                if(gradeType == 4 ) return;
                if(this.groups[gradeType-1] == null){
                    System.out.println("수정할 기준이 없습니다.");
                }else{
                    System.out.println("설정된 스토어 이용 시간 : "+this.groups[gradeType-1].getSpentTime());
                    System.out.println("설정된 사용 금액 : "+this.groups[gradeType-1].getTotalPay());
                    /*int newSpentTime = 0;
                    int newTotalPay = 0;

                    while(true){
                        System.out.printf("수정할 스토어 이용 시간 : ");
                        input = scanner.next();
                        newSpentTime = numberException.exception(input);
                        if(newSpentTime != -1){
                            if(exceedGradeException(1,newSpentTime,gradeType-1)) break;
                            else{
                                switch (gradeType-1){
                                    case 0:
                                        System.out.println("VIP등급보다 낮아야합니다. 다시 설정해주세요.");
                                        break;
                                    case 1:
                                        System.out.println("GENERAL 보다 높고 VVIP 보다 낮아야합니다. 다시 설정해주세요.");
                                        break;
                                    case 2:
                                        System.out.println("VIP 보다 높아야 합니다. 다시 설정해주세요.");
                                }
                            }
                        }
                    }
                    this.groups[gradeType-1].setSpentTime(newSpentTime);


                    while (true){
                        System.out.print("수정할 사용 금액 : ");
                        input = scanner.next();
                        newTotalPay = numberException.exception(input);
                        if(newTotalPay != -1){
                            if(exceedGradeException(2,newTotalPay,gradeType)) break;
                            else{
                                switch (gradeType){
                                    case 0:
                                        System.out.println("VIP등급보다 낮아야합니다. 다시 설정해주세요.");
                                        break;
                                    case 1:
                                        System.out.println("GENERAL 보다 높고 VVIP 보다 낮아야합니다. 다시 설정해주세요.");
                                        break;
                                    case 2:
                                        System.out.println("VIP 보다 높아야 합니다. 다시 설정해주세요.");
                                }
                            }
                        }
                    }
                    this.groups[gradeType-1].setTotalPay(newTotalPay);*/
                    Group newGroup = inputGrade(gradeType-1);
                    this.groups[gradeType-1] = newGroup;

                    System.out.println("『");
                    System.out.println("    수정이 완료되었습니다.");
                    System.out.println("                          』");
                }

            }else{
                System.out.println("『");
                System.out.println("    메뉴에 있는 번호만 선택해주세요.");
                System.out.println("                                  』");
            }

        }
    }

    /**
     * 기준값 설정시 하위 등급이 상위 등급 보다 큰 값을 가지는지, 상위 등급이 하위 등급보다 작은 값을 가지는지 판별하는 함수
     * @param inputType 입력값이 spent time 인지 total pay 인지 (1이면 spent time, 2면 total pay)
     * @param input 입력받은 값
     * @param gradeType 등급이 general, vip, vvip 인지
     * @return 기준값을 벗어나면 false 아니면 true
     */
    public boolean exceedGradeException(int inputType, int input, int gradeType){
        switch (gradeType){
            case 0: //general : vip값보다 작아야 한다.
                if(this.groups[gradeType+1] == null) return true; //vip 가 null 이면 아무값이나 설정 가능
                if(inputType == 1) { //spent time
                    return input < this.groups[gradeType + 1].getSpentTime();
                }else //total pay
                    return input < this.groups[gradeType+1].getTotalPay();

            case 1: //vip : general 보다 커야하고 vvip 보다 작아야한다.
                //vvip, general 둘다 null 일 때 (아무 값이나 입력하면 된다.)
                if(this.groups[gradeType+1] == null && this.groups[gradeType-1] == null) {
                    return true;

                //vvip만 null 일 때 (general 보다 크면 된다.)
                }else if(this.groups[gradeType-1] != null && this.groups[gradeType+1] == null){
                    if(input == 1)
                        return input > this.groups[gradeType-1].getSpentTime();
                    else
                        return input > this.groups[gradeType-1].getTotalPay();

                //general 만 null 일 때 (vvip 보다 작으면 된다.)
                }else if(this.groups[gradeType-1] == null && this.groups[gradeType+1] != null) {
                    if(input == 1)
                        return input < this.groups[gradeType+1].getSpentTime();
                    else
                        return input < this.groups[gradeType+1].getTotalPay();

                //둘다 null이 아닐때 (general 보다 크고 vvip 보다 작아야한다.)
                }else {
                    if(inputType == 1)
                        return input < this.groups[gradeType+1].getSpentTime() && input > this.groups[gradeType-1].getSpentTime();
                    else
                        return input < this.groups[gradeType+1].getTotalPay() && input > this.groups[gradeType-1].getTotalPay();
                }

            case 2: //vvip : vip 보다 크기만 하면 된다.
                if(this.groups[gradeType-1] == null) return  true; //vip 가 null 이면 아무값이나 입력 가능
                if(inputType == 1) //spent time
                    return input > this.groups[gradeType-1].getSpentTime();
                else //total pay
                    return input > this.groups[gradeType-1].getTotalPay();

            default:
                return false;
        }
    }


}
