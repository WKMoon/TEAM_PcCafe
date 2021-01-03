
public class Member {
	//회원가입
	String name;
	String password;
	int age;
	String phone;
	
	//로그인
	int pcNum;

	Member(String name, String password, int age, String phone){
		this.name = name;
		this.password = password;
		this.age = age;
		this.phone = phone;
	}
	Member(String name, String password, int pcNum){
		this.name = name;
		this.password = password;
		this.pcNum = pcNum;
	}
	
}//end Member