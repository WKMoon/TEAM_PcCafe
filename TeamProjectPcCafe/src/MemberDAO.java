import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String userid="PCUSER";//만든 맴버 클래스의 아이디랑 비밀번호 
		String passwd="12345";

		
		public MemberDAO() {
			try {			
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}//end catch

		}// end memberDAO CONS
		
		//로그인 되어있는지 check
		public boolean loginCheck(String id) {
			boolean result = false;
			Connection con= null;
	    	PreparedStatement pstmt=null;
	    	ResultSet rs=null;
	    	
	    	try {
				con=DriverManager.getConnection(url,userid,passwd);
				String sql="SELECT * FROM pc WHERE id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
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
		}//end loginCheck
		
		//pc가 비어있는지 check
		public boolean pcCheck(int seat) {
			boolean result = false;
			Connection con= null;
	    	PreparedStatement pstmt=null;
	    	ResultSet rs=null;
	    	
	    	try {
				con=DriverManager.getConnection(url,userid,passwd);
				String sql="SELECT * FROM pc WHERE pcNum=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, seat);
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
		}//end pcCheck
		//member id 체크 메서드
	    public boolean isExist(String id, String password) {
	    	boolean result=false;
	    	Connection con= null;
	    	PreparedStatement pstmt=null;
	    	ResultSet rs=null;
	    	
	    	try {
				con=DriverManager.getConnection(url,userid,passwd);
				String sql="SELECT * FROM member WHERE id=? AND password=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
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
	    
	    //pc에 insert
	    public void insert(int pcNum, String id, String password) {
			Connection con = null;
			PreparedStatement pstmt=null;
			
			try {
				con=DriverManager.getConnection(url,userid,passwd);
				String sql="INSERT INTO pc "
						+ "VALUES(?,?,?)";
				pstmt=con.prepareStatement(sql);
				//?안에 실제 데이터 넣기 
				pstmt.setInt(1, pcNum);
				pstmt.setString(2,id.trim());
				pstmt.setString(3,password.trim());
				
				
				int n=pstmt.executeUpdate();//excuteupdate->sql저장한 것을 실행 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null)pstmt.close();
					if(con != null) con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}//end insert
	    
}//end memberDAO
