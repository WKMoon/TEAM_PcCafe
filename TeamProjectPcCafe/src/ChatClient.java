import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame {

   JTextArea textArea; // ��� ��������
   JTextField tfMsg;
   JButton btnSend;
   Socket socket;
   DataInputStream dis;
   DataOutputStream dos;

   static String id;

   public ChatClient() {
      setTitle("1:1 ä�� Ŭ���̾�Ʈ");
      setBounds(450, 400, 500, 350);
      textArea = new JTextArea();
      textArea.setEditable(false); // ���� ����
      JScrollPane scrollPane = new JScrollPane(textArea);
      add(scrollPane, BorderLayout.CENTER);
      JPanel msgPanel = new JPanel();
      msgPanel.setLayout(new BorderLayout());
      tfMsg = new JTextField();
      btnSend = new JButton("�Է�");
      msgPanel.add(tfMsg, BorderLayout.CENTER);
      msgPanel.add(btnSend, BorderLayout.EAST);
      add(msgPanel, BorderLayout.SOUTH);
      // �Է� ��ư Ŭ���� �����ϴ� ������ �߰�
      btnSend.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            sendMessage();
         }

      });

      // ����Ű ������ �� �����ϱ�
      tfMsg.addKeyListener(new KeyAdapter() {
         // Ű���忡�� Ű �ϳ��� �������� �ڵ����� ����Ǵ� �޼ҵ�..: �ݹ� �޼ҵ�
         @Override
         public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            // �Է¹��� Ű�� �������� �˾Ƴ���, KeyEvent ��ü�� Ű������ ���� ��������
            int keyCode = e.getKeyCode();
            switch (keyCode) {
            case KeyEvent.VK_ENTER:

               sendMessage();
               break;
            }
         }
      });

      setVisible(true);
      tfMsg.requestFocus();

      // ������ �����ϴ� ��Ʈ��ũ �۾� : ������ ��ü ���� �� ����
      ClientThread clientThread = new ClientThread();
      clientThread.setDaemon(true);
      clientThread.start();

      addWindowListener(new WindowAdapter() {
         @Override // Ŭ���̾�Ʈ �����ӿ� window(â) ���� ������ �߰�
         public void windowClosing(WindowEvent e) {
            super.windowClosing(e);

            try {
               if (dos != null)
                  dos.close();
               if (dis != null)
                  dis.close();
               if (socket != null)
                  socket.close();
               if (id != null) {
                  id = null;
               }
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }
      });

   }// ������

   // �̳�Ŭ���� : ������ �����ϴ� ��Ʈ��ũ �۾� ������
   class ClientThread extends Thread {

      @Override
      public void run() {
         if (id != null&&id!="") {
            try {
               socket = new Socket("localhost", 1001);
               textArea.append("�����ڿ� ä��\n");

               // ������ ������ ���� ��Ʈ�� ����(���߷� ���)
               InputStream is = socket.getInputStream();
               OutputStream os = socket.getOutputStream();

               // ������Ʈ������ ���� ���������� �۾��� ���ϰ� �شٸ� ������Ʈ�� ���
               dis = new DataInputStream(is);
               dos = new DataOutputStream(os);

               while (true) {// ���� �޽��� �ޱ�
                  String msg = dis.readUTF();
                  textArea.append(" [������] : " + msg + "\n");
                  textArea.setCaretPosition(textArea.getText().length());
               }
            } catch (UnknownHostException e) {
               textArea.append("������ �ּҰ� �̻��մϴ�.\n");
            } catch (IOException e) {
               textArea.append("�����ڿ� ������ ������ϴ�.\n");
            }
         }
      }
   }

   // �޽��� �����ϴ� ��� �޼ҵ�

   void sendMessage() {

      String msg = tfMsg.getText(); // TextField�� ���ִ� �۾��� ������
      tfMsg.setText(""); // �Է� �� ��ĭ����

      if (id != null && id!="") 
      if (!msg.equals(""))
         textArea.append(" [" + id + "] : " + msg + "\n");// 1.TextArea(ä��â)�� ǥ��

      textArea.setCaretPosition(textArea.getText().length());

      // 2.����(Server)���� �޽��� �����ϱ�
      // �ƿ�ǲ ��Ʈ���� ���� ���濡 ������ ����
      // ��Ʈ��ũ �۾��� ������ Thread�� �ϴ� ���� ����
      Thread t = new Thread() {
         @Override
         public void run() {
            try { // UTF = �����ڵ��� �Ծ�(����), �ѱ� ������ �ʰ� ����
               if (!msg.equals(""))
                  dos.writeUTF(msg);

               dos.flush(); // ��� ä�� ���� close()�ϸ� �ȵ�
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      };
      t.start();

   }

}