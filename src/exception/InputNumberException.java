package exception;

public class InputNumberException {
    public int exception(String input){
        String number = "";
        //들어온 input을 처음부터 끝까지 검사한다.
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) >= 48 && input.charAt(i) <= 57) { //숫자가 아닐때 예외 처리
                number += input.charAt(i); //한글자씩 number에 합친다.
            }else{
                System.out.println("『");
                System.out.println("    잘못된 입력입니다. 숫자만 입력해주세요.");
                System.out.println("                                        』");
                return -1; //숫자가 아닌게 하나라도 들어오면 종료
            }
        }

        return Integer.parseInt(number); //string을 int로 변환해서 반환
    }
}
