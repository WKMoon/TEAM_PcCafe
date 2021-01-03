import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			}//end catch

		}// end memberDAO CONS
		
		
		//name이 존재하는지 체크하는 메소드
	    public boolean isExist(String name, String password) {
	    	boolean result=false;
	    	Connection con= null;
	    	PreparedStatement pstmt=null;
	    	ResultSet rs=null;
	    	
	    	try {
				con=DriverManager.getConnection(url,userid,passwd);
				String sql="SELECT*FROM member WHERE name=? AND password=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, password);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					result=true;
				}
				
				
	    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(rs !=null) rs.close();
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	  	  return result;
	    }//end isExist
	    
}//end memberDAO
