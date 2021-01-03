
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPcNum() {
		return pcNum;
	}
	public void setPcNum(int pcNum) {
		this.pcNum = pcNum;
	}
	Member(String name, String password, int pcNum){
		this.name = name;
		this.password = password;
		this.pcNum = pcNum;
	}
	
}//end Member