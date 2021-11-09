package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Index {
	public static void main(String[] args) {
		while(true) {
			System.out.println("----------환영합니다-----------");
			System.out.println("-------번호를 선택해 주세요-------");
			System.out.println("         1. 로그인");
			System.out.println("         2. 회원가입");
			System.out.println("         3. 나가기");
			System.out.println("-----------------------------");
			
			try {
				Scanner sc = new Scanner(System.in);
				int choice = sc.nextInt();
				
				if(choice == 1){
					// 로그인
				} else if(choice == 2){
					// 회원가입
					new RegisterView();
				} else if(choice == 3) {
					// 나가기
					System.out.println("안녕히가세요!");
					break;
				} else {
					// 1,2,3 숫자 외의 값 예외처리
					System.out.println("1,2,3 중 하나를 선택해주세요.");
					continue;
				}
			} catch (InputMismatchException e) {		// 숫자 예외의 값을 입력했을 때 예외처리
				System.out.println("1,2,3 중 하나를 선택해주세요.");
				continue;
			}
		}
	}
}
