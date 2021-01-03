import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	//1.����Ŭ �����ͺ��̽� ������ ���� 4���� ������ ���ڿ��� ����
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String userid="system";//���� �ɹ� Ŭ������ ���̵�� ��й�ȣ 
		String passwd="1234";

		
		public MemberDAO() {
			try {			
		//2.����̹� �ε�  OracleDriver Ŭ������ �ڹٿ��� �̿��ϱ� ���ؼ� JVM�� �÷���
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}//end catch

		}// end memberDAO CONS
		
		
		//name�� �����ϴ��� üũ�ϴ� �޼ҵ�
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
