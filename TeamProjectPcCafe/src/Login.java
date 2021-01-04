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
	static JFrame f = new JFrame("�α���");

	public static void main(String[] args) {
		JLabel l1 = new JLabel("���̵�:");
		l1.setBounds(20,20,80,30);
		JTextField text = new JTextField();
		text.setBounds(100,20,100,30);
		JLabel l2 = new JLabel("��й�ȣ:");
		l2.setBounds(20,75,80,30);
		JPasswordField value = new JPasswordField();
		value.setBounds(100,75,100,30);
		
		JLabel l3 = new JLabel("PC:");
		JTextField pc = new JTextField();
		l3.setBounds(20,125,120,30);
		pc.setBounds(100,130,100,30);
		
		JButton b = new JButton("�α���");
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
					JOptionPane.showMessageDialog(null,"PC ��ȣ�� �Է��ϼ���.");
				}//end if
				else if(text.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"���̵� �Է��ϼ���.");
				}//end if
				else if(value.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"��й�ȣ�� �Է��ϼ���.");
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
	
	//�ڸ� ����
	public static void setSeat(String id, String password, int seat) {
		HashMap<Integer, String> seatMap = new HashMap();
		if(seatMap.containsKey(seat)) {
			JOptionPane.showMessageDialog(null,"�ش� �¼��� ��� ���� �ʽ��ϴ�.");
		}//end if
		else {
			seatMap.put(seat, id);
			MemberDAO md = new MemberDAO();
			md.insert(seat, id, password);
			
			MainPanel m = new MainPanel();
			JOptionPane.showMessageDialog(null,"�¼� ������.");

			//�¼�����
			//TRUNCATE TABLE tableName; -- ���̺��� ������ �����ϸ鼭 ��� �����͵鸸 ����
			f.dispose();//�¼� ������ â �ݱ�
		}//end else
	}//end setSeat

}//end Login
