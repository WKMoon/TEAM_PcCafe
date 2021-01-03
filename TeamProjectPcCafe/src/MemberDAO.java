
public class MemberDAO {
	//1.오라클 데이터베이스 연동을 위한 4가지 정보를 문자열에 저장
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String userid="system";//만든 맴버 클래스의 아이디랑 비밀번호 
		String passwd="1234";

		
		public MemberDAO() {
			try {			
		//2.드라이버 로딩  OracleDriver 클래스를 자바에서 이용하기 위해서 JVM에 올려줌
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}// end memberDAO CONS
}//end memberDAO
