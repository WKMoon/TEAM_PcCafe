import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import oracle.sql.ARRAY;

public class UserList extends JFrame {

   // 콤보박스에 나타낼 데이터를 배열에 저장합니다.
//   String users[] = {};
   ArrayList<String> users = new ArrayList<String>();

//   JComboBox<String> combo;
   JButton btnServer = new JButton("채팅하기");

   UserList() {
      boolean flag=true;
      if (!MainPanel.tfName1.getText().equals("")) {
         users.add(MainPanel.tfName1.getText());
      }
      if (!MainPanel.tfName2.getText().equals("")) {
         users.add(MainPanel.tfName2.getText());
      }
      if (!MainPanel.tfName3.getText().equals("")) {
         users.add(MainPanel.tfName3.getText());
      }
      if (!MainPanel.tfName4.getText().equals("")) {
         users.add(MainPanel.tfName4.getText());
      }
      if (!MainPanel.tfName5.getText().equals("")) {
         users.add(MainPanel.tfName5.getText());
      }
      if (!MainPanel.tfName6.getText().equals("")) {
         users.add(MainPanel.tfName6.getText());
      }
      if (!MainPanel.tfName7.getText().equals("")) {
         users.add(MainPanel.tfName7.getText());
      }
      if (!MainPanel.tfName8.getText().equals("")) {
         users.add(MainPanel.tfName8.getText());
      }
      if (!MainPanel.tfName9.getText().equals("")) {
         users.add(MainPanel.tfName9.getText());
      }
      if (!MainPanel.tfName10.getText().equals("")) {
         users.add(MainPanel.tfName10.getText());
      }
      if (!MainPanel.tfName11.getText().equals("")) {
         users.add(MainPanel.tfName11.getText());
      }
      if (!MainPanel.tfName12.getText().equals("")) {
         users.add(MainPanel.tfName12.getText());
      }
      if (!MainPanel.tfName13.getText().equals("")) {
         users.add(MainPanel.tfName13.getText());
      }
      if (!MainPanel.tfName14.getText().equals("")) {
         users.add(MainPanel.tfName14.getText());
      }
      if (!MainPanel.tfName15.getText().equals("")) {
         users.add(MainPanel.tfName15.getText());
      }
      if (!MainPanel.tfName16.getText().equals("")) {
         users.add(MainPanel.tfName16.getText());
      }
      if (!MainPanel.tfName17.getText().equals("")) {
         users.add(MainPanel.tfName17.getText());
      }
      if (!MainPanel.tfName18.getText().equals("")) {
         users.add(MainPanel.tfName18.getText());
      }
      if (!MainPanel.tfName19.getText().equals("")) {
         users.add(MainPanel.tfName19.getText());
      }
      if (!MainPanel.tfName20.getText().equals("")) {
         users.add(MainPanel.tfName20.getText());
      }
      if(users.isEmpty()) {
         JOptionPane.showMessageDialog(null, "사용중인 손님이 없습니다.", "에러 메시지", JOptionPane.ERROR_MESSAGE);
         flag=false;
      }

      setLayout(new BorderLayout());

//      combo = new JComboBox<String>(users);
      JComboBox<?> combo = new JComboBox(users.toArray(new String[users.size()]));

      add(combo, BorderLayout.NORTH);
      add(btnServer, BorderLayout.SOUTH);

      setSize(200, 300);
      setVisible(flag);

      // 콤보박스에 addActionListener 이벤트 처리를 합니다.
      combo.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            ChatServer.id = combo.getSelectedItem().toString();
            ChatClient.id = combo.getSelectedItem().toString();
         }
      });

      btnServer.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            ChatServer cs = new ChatServer();
            ChatClient cc = new ChatClient();

         }
      });
   }

   public static void main(String[] args) {
      new UserList();
   }
}