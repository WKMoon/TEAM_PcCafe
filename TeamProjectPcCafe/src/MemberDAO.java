import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String userid="PCUSER";//���� �ɹ� Ŭ������ ���̵�� ��й�ȣ 
		String passwd="12345";

		
		public MemberDAO() {
			try {			
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}//end catch

		}// end memberDAO CONS
		
		
		//name�� �����ϴ��� üũ�ϴ� �޼ҵ�
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
	    
	    public void insert(int pcNum, String id, String password) {
			Connection con = null;
			PreparedStatement pstmt=null;
			
			try {
				con=DriverManager.getConnection(url,userid,passwd);
				String sql="INSERT INTO pc "
						+ "VALUES(?,?,?)";
				pstmt=con.prepareStatement(sql);
				//?�ȿ� ���� ������ �ֱ� 
				pstmt.setInt(1, pcNum);
				pstmt.setString(2,id.trim());
				pstmt.setString(3,password.trim());
				
				
				int n=pstmt.executeUpdate();//excuteupdate->sql������ ���� ���� 
				
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
