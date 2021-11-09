package view;

import java.util.Scanner;
import dao.UserDAO;
import dto.UserDTO;

public class RegisterView {
	public RegisterView() {
		Scanner sc = new Scanner(System.in);
		// 회원정보에 접근하기 위해 UserDAO 객체를 만들어준다.
		UserDAO udao = new UserDAO();
		System.out.print("아이디 : ");
		String userid = sc.next();
		String userpw = "";
		
		if(udao.checkId(userid)) {				// 아이디 중복체크가 확인되었다면 
			while(true) {						// 비밀번호를 두번 입력받아 비밀번호 확인을 한다.
				System.out.print("비밀번호 : ");		
				userpw = sc.next();
				System.out.print("비밀번호 확인 : ");
				String userpw_re = sc.next();
				if(userpw.equals(userpw_re)) {	
					break;						
				} else {
					System.out.println("비밀번호를 다시 확인해주세요.");
					continue;					// 비밀번호가 일치하지 않으면 다시 비밀번호를 입력받는다.
				}
			}
			
			System.out.print("이름 : ");
			String username = sc.next();
			System.out.print("나이 : ");
			int userage = sc.nextInt();
			System.out.print("핸드폰 번호 : ");
			String userphone = sc.next();
			System.out.print("주소 : ");
			sc = new Scanner(System.in);
			String useraddr = sc.nextLine();
			
			// 회원가입에 필요한 모든 정보 입력 완료.
			// 따라서 데이터를 매개변수로 넘겨야 하는데, 많은 데이터를 받기 위해 매개변수도 많이 필요해지므로,
			// 하나의 객체(UserDTO)로 포장해서 넘겨준다.
			UserDTO newUser = new UserDTO(userid, userpw, username, userage, userphone, useraddr);
			// 비밀번호를 잘 암호화 하고, 재정의된 toString형식으로 메모장 파일에 잘 insert 했다면
			if(udao.join(newUser)) {		
				System.out.println(username + "님 가입을 환영합니다!");
			// 그렇지 않으면
			} else {
				System.out.println("회원가입 실패 / 다시 시도해 주세요.");
			}
		} else {
			System.out.println("중복된 아이디가 있습니다.");
		}
	}
}
