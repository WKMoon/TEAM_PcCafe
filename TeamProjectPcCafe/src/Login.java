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

	public static void main(String[] args) {
		JLabel l1 = new JLabel("아이디:");
		l1.setBounds(20,20,80,30);
		JTextField text = new JTextField();
		text.setBounds(100,20,100,30);
		JLabel l2 = new JLabel("비밀번호:");
		l2.setBounds(20,75,80,30);
		JPasswordField value = new JPasswordField();
		value.setBounds(100,75,100,30);
		
		JLabel l3 = new JLabel("PC:");
		JTextField pc = new JTextField();
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
				int pcNum = Integer.parseInt(pc.getText());
				MemberDAO md = new MemberDAO();
				boolean check = md.isExist(id, pw);
				
				if(pc.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"PC 번호를 입력하세요.");
				}//end if
				else if(text.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"아이디를 입력하세요.");
				}//end if
				else if(value.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"비밀번호를 입력하세요.");
				}//end if
				
				
				if(check && !pc.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"correct Account");

					setSeat(id, pw, pcNum);
				}//end if
				else {
					JOptionPane.showMessageDialog(null,"Incorrect Account");
				}//end else
				
			}//end actionPerformed
		});
	}//end main
	
	//자리 배정
	public static void setSeat(String id, String password, int seat) {
		HashMap<Integer, String> seatMap = new HashMap();
		if(seatMap.containsKey(seat)) {
			JOptionPane.showMessageDialog(null,"해당 좌석은 비어 있지 않습니다.");
		}//end if
		else {
			seatMap.put(seat, id);
			MemberDAO md = new MemberDAO();
			md.insert(seat, id, password);
			
			MainPanel m = new MainPanel();
			JOptionPane.showMessageDialog(null,"좌석 배정됨.");

			//좌석배정
			//TRUNCATE TABLE tableName; -- 테이블의 구조는 유지하면서 모든 데이터들만 삭제
			f.dispose();//좌석 배정시 창 닫기
		}//end else
	}//end setSeat

}//end Login
