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
	
	// insert : 받아온 데이터를 file에 그대로 입력해주는 메서드
	boolean insert(String data) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
			bw.write(data + "\r\n");
			bw.close();
			return true;
		} catch (IOException e) {
			System.out.println("=======오류 발생 : DB 연결 실패=======");
			System.out.println(e);
			System.out.println("=================================");
		}
		return false;
	}
	
	// update : 받아온 PK값에 해당하는 줄의 col번째 열을 newData로 수정하는 메서드
	//			tab 단위로 구별되있는 데이터 쪼개 빈 문자열에 계속 연결 반복.
	//			단, 수정해야할 문자열은 기존 데이터 말고 매개변수로 받아온 newData를 연결.
	boolean update(String key, int col, String newData) {
		String result = "";
		boolean check = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(true) {
				String line = br.readLine();		// 파일을 한 줄씩 읽어 line에 저장
				if(line == null) {
					break;							// 계속 읽어내려가다 라인이 비어있을때 반복문 중지
				}
				String[] datas = line.split("\t");		// 읽어온 한 줄을 tab 단위로 쪼개서 배열에 저장
				if(datas[0].equals(key)) {				// 배열의 0번째 값이 매개변수로 받아온 key와 같다면
					result += datas[0];					// result에 그 값 추가
					for (int i = 1; i < datas.length; i++) {
						if(i==col) {
							result += "\t" + newData;		// 매개변수로 받아온 col값이 i와 같다면
							check = true;					// 새로운 데이터 연결
						} else {
							result += "\t" + datas[i];		// 매개변수로 받아온 col값과 i와 다르면
						}									// 기존의 데이터 연결
					}
					result += "\r\n";						// 한줄 완성했으니 줄바꿈
				}else {
					result += line + "\r\n";				// 배열의 0번째 값이 매개변수로 넘어온 key와 다르면
				}											// 읽어온 line을 result에 저장후 줄바꿈
			}
		} catch (FileNotFoundException e) {					// 파일 위치를 읽어올 수 없거나 이름을 잘못 입력한 경우의 예외처리
			System.out.println("=======오류발생 : DB 파일 오류=======");
			System.out.println(e);
			System.out.println("================================");
		} catch (IOException e) {							// 그 외의 모든 예외처리
			System.out.println("=======오류발생 : DB 연결 실패=======");
			System.out.println(e);
			System.out.println("================================");
		}
		
		if(check) {
			try {										// check가 true일 때, file에 result 작성
				BufferedWriter bw =  new BufferedWriter(new FileWriter(file));	
				bw.write(result);
				bw.close();
			} catch (IOException e) {
				System.out.println("=======오류발생 : DB 연결 실패=======");
				System.out.println(e);
				System.out.println("================================");
			}
		}
		return check;
	}
	
	// delete : 각줄의 PK값 하나를 받아와서 그 PK에 해당하는 줄을 통째로 삭제
	//			tab 단위로 구별되있는 데이터 쪼개 PK를 매개변수 key와 비교
	//			비교한 값이 같을 시 연결하지 않고 그렇지 않은 줄을 result에 연결 
	boolean delete(String key) {
		String result = "";
		boolean check = false;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(true) {
				String line = br.readLine();			// 파일을 한 줄씩 읽어 line에 저장
				if(line == null) {						// 계속 읽어내려가다 라인이 비어있을때 반복문 중지
					break;
				}
				String[] datas = line.split("\t");		// 읽어온 한 줄을 tab 단위로 쪼개서 배열에 저장
				if(datas[0].equals(key)) {				// 배열의 0번째 값이 매개변수로 받아온 key와 같다면
					check = true;						// 아무것도 하지 않고 check에 true 할당
				} else {
					result += line + "\r\n";			// 배열의 0번째 값이 매개변수로 넘어온 key와 다르면
				}										// 읽어온 line을 result에 저장후 줄바꿈
			}
		} catch (FileNotFoundException e) {
			System.out.println("=======오류발생 : DB 파일 오류=======");
			System.out.println(e);
			System.out.println("================================");
		} catch (IOException e) {
			System.out.println("=======오류발생 : DB 연결 실패=======");
			System.out.println(e);
			System.out.println("================================");
		}
		if(check) {
			try {										// check가 true일 때, file에 result 작성
				BufferedWriter bw =  new BufferedWriter(new FileWriter(file));	
				bw.write(result);
				bw.close();
			} catch (IOException e) {
				System.out.println("=======오류발생 : DB 연결 실패=======");
				System.out.println(e);
				System.out.println("================================");
			}
		}
		return check;
	}
	
	// select : "file" 안에 있는 모든 정보 검색
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
			System.out.println("=======오류발생 : DB 파일 오류=======");
			System.out.println(e);
			System.out.println("================================");
		} catch (IOException e) {
			System.out.println("=======오류발생 : DB 연결 실패=======");
			System.out.println(e);
			System.out.println("================================");
		}
		return resultSet;
	}
	
	// col번째 열에 data를 검색해 그 data가 포함된 한줄을 반환
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
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return resultSet;
	}
	
}
