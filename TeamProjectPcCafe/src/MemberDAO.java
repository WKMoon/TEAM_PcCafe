
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
			}

		}// end memberDAO CONS
}//end memberDAO
