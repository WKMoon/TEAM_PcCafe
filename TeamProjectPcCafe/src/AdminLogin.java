import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminLogin extends JFrame{
	final String ADMINID = "admin";
	final String ADMINPASS = "1234";
	
	BufferedImage img = null;
	JTextField loginTextField;
	JPasswordField passwordField;
	JButton btn;
	
	public static void main(String[] args) {
		new AdminLogin();	

	}
	
	public AdminLogin() {
		setTitle("Admin Login");
		setSize(600,300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);		
		
		
		try {
			img = ImageIO.read(new File("C:\\Users\\wkmoo\\Java\\Korea-Java\\TEAM_PcCafe\\TeamProjectPcCafe\\src\\img/login.jpg"));
		}catch(IOException e) {
			System.out.println("Failed to load image");
			System.exit(0);
		}//end catch
		
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,600,300);
		layeredPane.setLayout(null);
		
		
		LoginPanel panel = new LoginPanel();
		panel.setBounds(0,0,600,300);

		
		loginTextField = new JTextField(15);
		loginTextField.setBounds(230,90,200,20);
		loginTextField.setOpaque(false);
		loginTextField.setForeground(Color.GREEN);
		loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		layeredPane.add(loginTextField);
		
		passwordField = new JPasswordField(4);
		passwordField.setBounds(230,135,200,20);
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.GREEN);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		layeredPane.add(passwordField);

		btn = new JButton(new ImageIcon("C:\\Users\\wkmoo\\Java\\Korea-Java\\TEAM_PcCafe\\TeamProjectPcCafe\\src\\img/loginBtn.png"));
		btn.setBounds(176,185,235,40);

		layeredPane.add(btn);
		
		layeredPane.add(panel);
		
		add(layeredPane);
		setLocationRelativeTo(null);

		setVisible(true);
		
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = loginTextField.getText().toString();
				String pass = passwordField.getText().toString();
				
				if(id.equals(ADMINID) && pass.equals(ADMINPASS)) {
		            MainPanel mp = new MainPanel();
		            
				}
			}
		});
		
	}//end adminLogin cons
	
	class LoginPanel extends JPanel{
		@Override
		public void paint(Graphics g) {
			super.paint(g);

			g.drawImage(img,0,0, this.getWidth(), this.getHeight(), null);
		}
	}

}//end AdminLogin