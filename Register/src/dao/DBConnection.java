package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class DBConnection {
	String file;
	public DBConnection(String file) {
		this.file = file;
	}
	
	// insert : �޾ƿ� �����͸� file�� �״�� �Է����ִ� �޼���
	boolean insert(String data) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
			bw.write(data + "\r\n");
			bw.close();
			return true;
		} catch (IOException e) {
			System.out.println("=======���� �߻� : DB ���� ����=======");
			System.out.println(e);
			System.out.println("=================================");
		}
		return false;
	}
	
	// update : �޾ƿ� PK���� �ش��ϴ� ���� col��° ���� newData�� �����ϴ� �޼���
	//			tab ������ �������ִ� ������ �ɰ� �� ���ڿ��� ��� ���� �ݺ�.
	//			��, �����ؾ��� ���ڿ��� ���� ������ ���� �Ű������� �޾ƿ� newData�� ����.
	boolean update(String key, int col, String newData) {
		String result = "";
		boolean check = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(true) {
				String line = br.readLine();		// ������ �� �پ� �о� line�� ����
				if(line == null) {
					break;							// ��� �о������ ������ ��������� �ݺ��� ����
				}
				String[] datas = line.split("\t");		// �о�� �� ���� tab ������ �ɰ��� �迭�� ����
				if(datas[0].equals(key)) {				// �迭�� 0��° ���� �Ű������� �޾ƿ� key�� ���ٸ�
					result += datas[0];					// result�� �� �� �߰�
					for (int i = 1; i < datas.length; i++) {
						if(i==col) {
							result += "\t" + newData;		// �Ű������� �޾ƿ� col���� i�� ���ٸ�
							check = true;					// ���ο� ������ ����
						} else {
							result += "\t" + datas[i];		// �Ű������� �޾ƿ� col���� i�� �ٸ���
						}									// ������ ������ ����
					}
					result += "\r\n";						// ���� �ϼ������� �ٹٲ�
				}else {
					result += line + "\r\n";				// �迭�� 0��° ���� �Ű������� �Ѿ�� key�� �ٸ���
				}											// �о�� line�� result�� ������ �ٹٲ�
			}
		} catch (FileNotFoundException e) {					// ���� ��ġ�� �о�� �� ���ų� �̸��� �߸� �Է��� ����� ����ó��
			System.out.println("=======�����߻� : DB ���� ����=======");
			System.out.println(e);
			System.out.println("================================");
		} catch (IOException e) {							// �� ���� ��� ����ó��
			System.out.println("=======�����߻� : DB ���� ����=======");
			System.out.println(e);
			System.out.println("================================");
		}
		
		if(check) {
			try {										// check�� true�� ��, file�� result �ۼ�
				BufferedWriter bw =  new BufferedWriter(new FileWriter(file));	
				bw.write(result);
				bw.close();
			} catch (IOException e) {
				System.out.println("=======�����߻� : DB ���� ����=======");
				System.out.println(e);
				System.out.println("================================");
			}
		}
		return check;
	}
	
	// delete : ������ PK�� �ϳ��� �޾ƿͼ� �� PK�� �ش��ϴ� ���� ��°�� ����
	//			tab ������ �������ִ� ������ �ɰ� PK�� �Ű����� key�� ��
	//			���� ���� ���� �� �������� �ʰ� �׷��� ���� ���� result�� ���� 
	boolean delete(String key) {
		String result = "";
		boolean check = false;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(true) {
				String line = br.readLine();			// ������ �� �پ� �о� line�� ����
				if(line == null) {						// ��� �о������ ������ ��������� �ݺ��� ����
					break;
				}
				String[] datas = line.split("\t");		// �о�� �� ���� tab ������ �ɰ��� �迭�� ����
				if(datas[0].equals(key)) {				// �迭�� 0��° ���� �Ű������� �޾ƿ� key�� ���ٸ�
					check = true;						// �ƹ��͵� ���� �ʰ� check�� true �Ҵ�
				} else {
					result += line + "\r\n";			// �迭�� 0��° ���� �Ű������� �Ѿ�� key�� �ٸ���
				}										// �о�� line�� result�� ������ �ٹٲ�
			}
		} catch (FileNotFoundException e) {
			System.out.println("=======�����߻� : DB ���� ����=======");
			System.out.println(e);
			System.out.println("================================");
		} catch (IOException e) {
			System.out.println("=======�����߻� : DB ���� ����=======");
			System.out.println(e);
			System.out.println("================================");
		}
		if(check) {
			try {										// check�� true�� ��, file�� result �ۼ�
				BufferedWriter bw =  new BufferedWriter(new FileWriter(file));	
				bw.write(result);
				bw.close();
			} catch (IOException e) {
				System.out.println("=======�����߻� : DB ���� ����=======");
				System.out.println(e);
				System.out.println("================================");
			}
		}
		return check;
	}
	
	// select : "file" �ȿ� �ִ� ��� ���� �˻�
	HashSet<String> select(){
		HashSet<String> resultSet = new HashSet<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				resultSet.add(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("=======�����߻� : DB ���� ����=======");
			System.out.println(e);
			System.out.println("================================");
		} catch (IOException e) {
			System.out.println("=======�����߻� : DB ���� ����=======");
			System.out.println(e);
			System.out.println("================================");
		}
		return resultSet;
	}
	
	// col��° ���� data�� �˻��� �� data�� ���Ե� ������ ��ȯ
	HashSet<String> select(int col, String data){
		HashSet<String> resultSet = new HashSet<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(line);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======���� �߻� : DB ���� ����======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======���� �߻� : DB ���� ����======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return resultSet;
	}
	
}
