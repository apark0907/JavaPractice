package view;

import java.util.Scanner;
import dao.UserDAO;
import dto.UserDTO;

public class RegisterView {
	public RegisterView() {
		Scanner sc = new Scanner(System.in);
		// ȸ�������� �����ϱ� ���� UserDAO ��ü�� ������ش�.
		UserDAO udao = new UserDAO();
		System.out.print("���̵� : ");
		String userid = sc.next();
		String userpw = "";
		
		if(udao.checkId(userid)) {				// ���̵� �ߺ�üũ�� Ȯ�εǾ��ٸ� 
			while(true) {						// ��й�ȣ�� �ι� �Է¹޾� ��й�ȣ Ȯ���� �Ѵ�.
				System.out.print("��й�ȣ : ");		
				userpw = sc.next();
				System.out.print("��й�ȣ Ȯ�� : ");
				String userpw_re = sc.next();
				if(userpw.equals(userpw_re)) {	
					break;						
				} else {
					System.out.println("��й�ȣ�� �ٽ� Ȯ�����ּ���.");
					continue;					// ��й�ȣ�� ��ġ���� ������ �ٽ� ��й�ȣ�� �Է¹޴´�.
				}
			}
			
			System.out.print("�̸� : ");
			String username = sc.next();
			System.out.print("���� : ");
			int userage = sc.nextInt();
			System.out.print("�ڵ��� ��ȣ : ");
			String userphone = sc.next();
			System.out.print("�ּ� : ");
			sc = new Scanner(System.in);
			String useraddr = sc.nextLine();
			
			// ȸ�����Կ� �ʿ��� ��� ���� �Է� �Ϸ�.
			// ���� �����͸� �Ű������� �Ѱܾ� �ϴµ�, ���� �����͸� �ޱ� ���� �Ű������� ���� �ʿ������Ƿ�,
			// �ϳ��� ��ü(UserDTO)�� �����ؼ� �Ѱ��ش�.
			UserDTO newUser = new UserDTO(userid, userpw, username, userage, userphone, useraddr);
			// ��й�ȣ�� �� ��ȣȭ �ϰ�, �����ǵ� toString�������� �޸��� ���Ͽ� �� insert �ߴٸ�
			if(udao.join(newUser)) {		
				System.out.println(username + "�� ������ ȯ���մϴ�!");
			// �׷��� ������
			} else {
				System.out.println("ȸ������ ���� / �ٽ� �õ��� �ּ���.");
			}
		} else {
			System.out.println("�ߺ��� ���̵� �ֽ��ϴ�.");
		}
	}
}
