package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Index {
	public static void main(String[] args) {
		while(true) {
			System.out.println("----------ȯ���մϴ�-----------");
			System.out.println("-------��ȣ�� ������ �ּ���-------");
			System.out.println("         1. �α���");
			System.out.println("         2. ȸ������");
			System.out.println("         3. ������");
			System.out.println("-----------------------------");
			
			try {
				Scanner sc = new Scanner(System.in);
				int choice = sc.nextInt();
				
				if(choice == 1){
					// �α���
				} else if(choice == 2){
					// ȸ������
					new RegisterView();
				} else if(choice == 3) {
					// ������
					System.out.println("�ȳ���������!");
					break;
				} else {
					// 1,2,3 ���� ���� �� ����ó��
					System.out.println("1,2,3 �� �ϳ��� �������ּ���.");
					continue;
				}
			} catch (InputMismatchException e) {		// ���� ������ ���� �Է����� �� ����ó��
				System.out.println("1,2,3 �� �ϳ��� �������ּ���.");
				continue;
			}
		}
	}
}
