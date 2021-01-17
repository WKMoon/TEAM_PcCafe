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
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatServer extends JFrame {
   JTextArea textArea; // ��� ��������
   JTextField tfMsg;
   JButton btnSend;

   ServerSocket serverSocket;
   Socket socket;
   DataInputStream dis;
   DataOutputStream dos;

   static String id;

   public ChatServer() {
      setTitle("1:1 ä�� ����");
      setBounds(450, 50, 500, 350);

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

      // ������ ������ �� �ֵ��� ���������� ����� ����� �� �ִ� �غ� �۾�!
      // ��Ʈ��ũ �۾��� Main Thread�� �ϰ��ϸ� �ٸ� �۾�(Ű���� �Է�, Ŭ�� ��..)����
      // ���� �� �� ����, ���α׷��� ����, �׷��� Main�� UI�۾��� �����ϵ��� �ϰ�,
      // �ٸ� �۾���(���� �ɸ���)�� ������ Thread���� �����ϴ� ���� ������.
      ServerThread serverThread = new ServerThread();
      serverThread.setDaemon(true); // ���� ������ ���� ����
      serverThread.start();

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
               if (serverSocket != null)
                  serverSocket.close();
               if (id != null) {
                  id = null;
               }
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }
      });

   }// ������ �޼ҵ�

   // �̳�Ŭ���� : ���������� �����ϰ� Ŭ���̾�Ʈ�� ������ ����ϰ�,
   // ����Ǹ� �޽����� ���������� �޴� ���� ����
   class ServerThread extends Thread {

      @Override
      public void run() {
         if (id != null && id!=""&&id!=" ") {
            try { // ���� ���� ���� �۾�
               serverSocket = new ServerSocket(1001);
               socket = serverSocket.accept();// Ŭ���̾�Ʈ�� �����Ҷ����� Ŀ��(������)�� ���
               textArea.append(id + "�԰��� ä��\n");

               // ����� ���� ��Ʈ�� ����
               dis = new DataInputStream(socket.getInputStream());
               dos = new DataOutputStream(socket.getOutputStream());

               while (true) {
                  // ������ ������ �����͸� �б�
                  String msg = dis.readUTF();// ������ ���������� ���
                  textArea.append(" ["+id+"] : " + msg + "\n");
                  textArea.setCaretPosition(textArea.getText().length());
               }
            } catch (IOException e) {
               textArea.append(id + "���� �������ϴ�.\n");
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
         textArea.append(" [������] : " + msg + "\n");// 1.TextArea(ä��â)�� ǥ��

      textArea.setCaretPosition(textArea.getText().length()); // ��ũ�� ���󰡰�

      // 2.����(Client)���� �޽��� �����ϱ�
      Thread t = new Thread() {

         @Override
         public void run() {
            try {
               if (!msg.equals(""))
                  dos.writeUTF(msg);

               dos.flush();
            } catch (IOException e) {
               e.printStackTrace();

            }

         }

      };

      t.start();

   }

}