package dto;

public class UserDTO {
	public String userid;
	public String userpw;
	public String username;
	public int userage;
	public String userphone;
	public String useraddr;
	
	public UserDTO() {		// 기본 생성자
	}
		
	// 위의 변수들을 매개변수로 받아오는 생성자
	public UserDTO(String userid, String userpw, String username, int userage, String userphone, String useraddr) {
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.userage = userage;
		this.userphone = userphone;
		this.useraddr = useraddr;
	}
	

	@Override
	public String toString() {				// toString을 재정의
		return userid + "\t" + userpw + "\t" + username 
				+ "\t" + userage + "\t" + userphone + "\t" + useraddr; 
	}
	
	
	
	
    
}   
    