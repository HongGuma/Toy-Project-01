package exception;

public class InputNumberException {
    //TODO: 첫 글자는 숫자고 뒷부분이 숫자외의 다른 숫자일 때 상황을 못걸러냄 수정할것
    public int exception(String input){
        int number = -1;
        if(input.charAt(0) >= 48 && input.charAt(0) <= 57) //숫자가 아닐때 예외 처리
            number = Integer.parseInt(input);
        else{
            System.out.println("『");
            System.out.println("    잘못된 입력입니다. 숫자만 입력해주세요.");
            System.out.println("                                        』");
        }
        return number;
    }
}
