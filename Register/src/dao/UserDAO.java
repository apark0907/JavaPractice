package dao;

import java.util.HashSet;
import dto.UserDTO;

public class UserDAO {
	DBConnection conn = null;
	public UserDAO() {
		conn = new DBConnection("UserTable.txt");	// 접근할 파일 UserTable.txt
	}
	
	public boolean join(UserDTO newUser) {
		// 받아온 객체의 비밀번호를 암호화해서 바꿔줌
		newUser.userpw = encrypt(newUser.userpw);
		// DTO클래스에 toString()을 재정의 해놓고 호출해서 쓰면 훨씬 코드가 간결해짐
		return conn.insert(newUser.toString());
	}
	
	// 아이디 중복체크
	public boolean checkId(String userid) {
		// select를 이용해서 데이터를 검색
		// 0번째 방에 입력받은 userid가 있는 모든 행들을 검색
		HashSet<String> rs = conn.select(0,userid);
		// re.size == 0 이면 검색된 행이 없는 것으로 중복된 아이디가 없다는 뜻이다 --> true
		// re.size != 0 이면 검색된 행이 있는 것으로 중복된 아이디가 있다는 뜻이다 --> false
		return rs.size() == 0;
	}
	
	
	// 비밀번호 암호화
	public String encrypt(String userpw) {
		String result = "";
		// 문자열을 문자단위로 쪼개서 아스키코드값 + 4 한 후 저장한다
		// 따라서 비밀번호가 1234 이면 1의 아스키코드인 49에서 4를 더한 53을, 2는 54를, 3은 55를, 4는 56 저장해
		// 다시 char 타입으로 형변환을 해준다. 따라서 5678이 되는 것이다.
		for (int i = 0; i < userpw.length(); i++) {
			result += (char)(userpw.charAt(i) + 4);
		}
		return result;
	}

}
