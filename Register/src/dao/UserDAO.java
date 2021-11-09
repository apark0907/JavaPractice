package dao;

import java.util.HashSet;
import dto.UserDTO;

public class UserDAO {
	DBConnection conn = null;
	public UserDAO() {
		conn = new DBConnection("UserTable.txt");	// ������ ���� UserTable.txt
	}
	
	public boolean join(UserDTO newUser) {
		// �޾ƿ� ��ü�� ��й�ȣ�� ��ȣȭ�ؼ� �ٲ���
		newUser.userpw = encrypt(newUser.userpw);
		// DTOŬ������ toString()�� ������ �س��� ȣ���ؼ� ���� �ξ� �ڵ尡 ��������
		return conn.insert(newUser.toString());
	}
	
	// ���̵� �ߺ�üũ
	public boolean checkId(String userid) {
		// select�� �̿��ؼ� �����͸� �˻�
		// 0��° �濡 �Է¹��� userid�� �ִ� ��� ����� �˻�
		HashSet<String> rs = conn.select(0,userid);
		// re.size == 0 �̸� �˻��� ���� ���� ������ �ߺ��� ���̵� ���ٴ� ���̴� --> true
		// re.size != 0 �̸� �˻��� ���� �ִ� ������ �ߺ��� ���̵� �ִٴ� ���̴� --> false
		return rs.size() == 0;
	}
	
	
	// ��й�ȣ ��ȣȭ
	public String encrypt(String userpw) {
		String result = "";
		// ���ڿ��� ���ڴ����� �ɰ��� �ƽ�Ű�ڵ尪 + 4 �� �� �����Ѵ�
		// ���� ��й�ȣ�� 1234 �̸� 1�� �ƽ�Ű�ڵ��� 49���� 4�� ���� 53��, 2�� 54��, 3�� 55��, 4�� 56 ������
		// �ٽ� char Ÿ������ ����ȯ�� ���ش�. ���� 5678�� �Ǵ� ���̴�.
		for (int i = 0; i < userpw.length(); i++) {
			result += (char)(userpw.charAt(i) + 4);
		}
		return result;
	}

}
