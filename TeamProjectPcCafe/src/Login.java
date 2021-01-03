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

	public static void main(String[] args) {
		JFrame f = new JFrame("로그인");
		JLabel l1 = new JLabel("이름:");
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
				String name = l1.toString();
				String pw = l2.toString();
				MemberDAO md = new MemberDAO();
				boolean check = md.isExist(name, pw);
				
				if(pc.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"PC 번호를 입력하세요.");
				}//end if
				else if(text.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"이름을 입력하세요.");
				}//end if
				else if(value.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"비밀번호를 입력하세요.");
				}//end if
				
				
				if(check && !pc.getText().isEmpty()) {
					setSeat(text.toString(), pc.toString());
				}//end if
				else {
					JOptionPane.showMessageDialog(null,"Incorrect Account");
				}//end else
				
			}//end actionPerformed
		});
	}//end main
	
	//자리 배정
	public static void setSeat(String name,String seat) {
		HashMap<String, String> seatMap = new HashMap();
		
		
	}//end setSeat

}//end Login
