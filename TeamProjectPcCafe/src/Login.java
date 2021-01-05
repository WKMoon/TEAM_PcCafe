import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login {
	static JFrame f = new JFrame("로그인");
	static JTextField text = new JTextField();
	static JPasswordField value = new JPasswordField();
	static JTextField pc = new JTextField();

	public static void main(String[] args) {
		JLabel l1 = new JLabel("아이디:");
		l1.setBounds(20,20,80,30);
		text.setBounds(100,20,100,30);
		JLabel l2 = new JLabel("비밀번호:");
		l2.setBounds(20,75,80,30);
		value.setBounds(100,75,100,30);
		
		JLabel l3 = new JLabel("PC:");
		l3.setBounds(20,125,120,30);
		pc.setBounds(100,130,100,30);
		
		JButton b = new JButton("로그인");
		b.setBounds(100,170,80,30);
		
		f.add(l1); f.add(text);
		f.add(l2); f.add(value); 
		f.add(l3); f.add(pc);
		f.add(b);
		f.setSize(300,250);
		f.setLayout(null);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		b.addActionListener(new ActionListener() {
			
			@Override 
			public void actionPerformed(ActionEvent e) {
				String id = text.getText().toString();
				String pw = value.getText().toString();
				MemberDAO md = new MemberDAO();
				boolean check = md.isExist(id, pw);
				
				if(text.getText().isEmpty() || value.getText().isEmpty() || pc.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"정보를 모두 입력하세요.");
				}//end if
				
				
				if(check && !pc.getText().isEmpty()) {
					if(md.loginCheck(id)) {
						JOptionPane.showMessageDialog(null,"이미 로그인 되어있습니다.");
						text.setText("");
						value.setText("");
						pc.setText("");
					}//end if
					else {
						setSeat(id, pw, Integer.parseInt(pc.getText()));
					}//end else
				}//end if
				else if(!check){
					JOptionPane.showMessageDialog(null,"Incorrect Account");
				}//end else
				
			}//end actionPerformed
		});
	}//end main
	
	//자리 배정
	public static void setSeat(String id, String password, int seat) {
		MemberDAO md = new MemberDAO();
		if(md.pcCheck(seat)) {
			JOptionPane.showMessageDialog(null,"해당 좌석은 비어 있지 않습니다.");
			text.setText("");
			value.setText("");
			pc.setText("");
		}//end if
		else {
			md.insert(seat, id, password);
			JOptionPane.showMessageDialog(null,"좌석 배정됨.");
			//좌석배정
			//TRUNCATE TABLE tableName; -- 테이블의 구조는 유지하면서 모든 데이터들만 삭제
			setTime(seat, id);
			f.dispose();//좌석 배정시 창 닫기
		}//end else
	}//end setSeat
	
	public static void setTime(int seat, String id) {
		MainPanel mp = new MainPanel();
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		long elapsedTime = end - start;
		String strTime = String.valueOf(elapsedTime);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		switch(seat) {
		case 1:
			mp.tfName1.setText(id);
			mp.tfTime1.setText(strTime);
			break;
		case 2:
			mp.tfName2.setText(id);
			mp.tfTime2.setText(strTime);
			break;
		case 3:
			mp.tfName3.setText(id);
			mp.tfTime3.setText(strTime);
			break;
		case 4:
			mp.tfName4.setText(id);
			mp.tfTime4.setText(strTime);
			break;
		case 5:
			mp.tfName5.setText(id);
			mp.tfTime5.setText(strTime);
			break;
		case 6:
			mp.tfName6.setText(id);
			mp.tfTime6.setText(strTime);
			break;
		case 7:
			mp.tfName7.setText(id);
			mp.tfTime7.setText(strTime);
			break;
		case 8:
			mp.tfName8.setText(id);
			mp.tfTime8.setText(strTime);
			break;
		case 9:
			mp.tfName9.setText(id);
			mp.tfTime9.setText(strTime);
			break;
		case 10:
			mp.tfName10.setText(id);
			mp.tfTime10.setText(strTime);
			break;
		case 11:
			mp.tfName11.setText(id);
			mp.tfTime11.setText(strTime);
			break;
		case 12:
			mp.tfName12.setText(id);
			mp.tfTime12.setText(strTime);
			break;
		case 13:
			mp.tfName13.setText(id);
			mp.tfTime13.setText(strTime);
			break;
		case 14:
			mp.tfName14.setText(id);
			mp.tfTime14.setText(strTime);
			break;
		case 15:
			mp.tfName15.setText(id);
			mp.tfTime15.setText(strTime);
			break;
		case 16:
			mp.tfName16.setText(id);
			mp.tfTime16.setText(strTime);
			break;
		case 17:
			mp.tfName17.setText(id);
			mp.tfTime17.setText(strTime);
			break;
		case 18:
			mp.tfName18.setText(id);
			mp.tfTime18.setText(strTime);
			break;
		case 19:
			mp.tfName19.setText(id);
			mp.tfTime19.setText(strTime);
			break;
		case 20:
			mp.tfName20.setText(id);
			mp.tfTime20.setText(strTime);
			break;
		}//end switch
	}//end setTime

}//end Login
