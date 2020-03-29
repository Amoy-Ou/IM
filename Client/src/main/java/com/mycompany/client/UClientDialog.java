/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author 25067
 */
public class UClientDialog extends javax.swing.JFrame {

    /**
     * Creates new form UClientDialog
     */
    static InetAddress address;
    static int port = 12306;
    DatagramSocket socket;
    DatagramPacket packet;
    UClientThread clientthread = null;
    boolean heart = true;

    public UClientDialog() {
        try {
            initComponents();
            setTitle("客户机");
            try {
                address = InetAddress.getByName("127.0.0.1");
            } catch (UnknownHostException ex) {
                Logger.getLogger(UClientDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            socket = new DatagramSocket();
            clientthread = new UClientThread(socket, port, jTextArea1);
            //初始化并显示列表
            String[] list = new String[1];
            list[0] = "未登录无法查看在线列表";
            //        list[0] = "lalala";
//        list[1] = "bababa";
//        list[2] = "fafafa";
//        list[3] = "gagaga";
            jList1.setListData(list);
            jScrollPane2.setViewportView(jList1);
//设置监听，等价于下面的写法
/*
jList1.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    try {
                        jList1valueChanged(e);
                    } catch (IOException ex) {
                        Logger.getLogger(UClientDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });*/

            jList1.addListSelectionListener((ListSelectionEvent e) -> {
                try {
                    jList1valueChanged(e);
                } catch (IOException ex) {
                    Logger.getLogger(UClientDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SocketException ex) {
            Logger.getLogger(UClientDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("登陆");
        jButton1.setActionCommand("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton2.setText("Send");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        /*
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        */
        /*
        jScrollPane2.setViewportView(jList1);
        */

        jLabel1.setText("Online user list");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton3.setText("注册");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("用户名");

        jLabel3.setText("密码");

        jButton4.setForeground(new java.awt.Color(255, 0, 51));
        jButton4.setText("下线");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("聊天记录");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                            .addComponent(jTextField2))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField3)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton1)
                            .addComponent(jButton4)
                            .addComponent(jButton5))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //登陆
        try {
            if (!clientthread.isAlive()) {
                clientthread.start();
            }
            jTextArea1.append("Client Connecting.....\r\n");
//            String text = "SoS".trim();
//            byte[] data = text.getBytes();

            String name = "name1" + jTextField2.getText().trim();
            String pass = "pass1" + jTextField3.getText().trim();
            //发送用户名和密码
            byte[] data = (name + pass).getBytes();
            packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
            //接受消息            
        } catch (SocketException ex) {
            Logger.getLogger(UClientDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UClientDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        //登陆成功后登录注册按键失效
        new Thread() {
            public void run() {
                boolean flag = true;
                while (flag) {
                    if (!clientthread.key) {
                        jButton1.setEnabled(false);
                        jButton3.setEnabled(false);
//                        jTextField2.setText(null);
                        jTextField2.setEditable(false);
                        jTextField3.setText(null);
                        jTextField3.setEditable(false);
                        flag = false;
                    }
                }
            }
        }.start();

        //启动之后与服务器之间的心跳包,5s一个
        new Thread() {
            public void run() {
                while (heart) {
                    String text = "SoSoS".trim();
                    byte[] data = text.getBytes();
                    packet = new DatagramPacket(data, data.length, address, port);
                    //                        socket.send(packet);
                    String list[] = clientthread.name;
                    jList1.removeAll();
                    jList1.setListData(list);
                    jList1.updateUI();
                    //                        jScrollPane2.setViewportView(jList1);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UClientDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
        //实时更新在线列表

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //发送
        String text = jTextField1.getText().trim();
        byte[] data = text.getBytes();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        jTextArea1.append("Me(" + df.format(new Date()) + "):" + text + "\r\n");
        packet = new DatagramPacket(data, data.length, address, port);
        try {
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(UClientDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextField1.setText(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //注册
        if (!clientthread.isAlive()) {
            clientthread.start();
        }
        try {
            // TODO add your handling code here:
            String name = "name0" + jTextField2.getText().trim();
            String pass = "pass0" + jTextField3.getText().trim();
            //发送用户名和密码
            byte[] data = (name + pass).getBytes();
            packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(UClientDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //下线
        clientthread.flag = false;
        clientthread.key = true;
        heart = false;
        jButton1.setEnabled(true);
        jButton3.setEnabled(true);
        jTextField2.setText(null);
        jTextField2.setEditable(true);
        jTextField3.setText(null);
        jTextField3.setEditable(true);
        jList1.removeAll();
        jList1.updateUI();
        jTextArea1.append("系统：下线...\r\n");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            // TODO add your handling code here:
            //聊天记录；
            jTextArea1.append("系统：...历史聊天记录...\r\n");
            String mess = "recor";
            byte[] data = mess.trim().getBytes();
            DatagramPacket dp = new DatagramPacket(data, data.length, address, port);
            socket.send(dp);
        } catch (IOException ex) {
            Logger.getLogger(UClientDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton5ActionPerformed
    String text = "Null";

    private void jList1valueChanged(ListSelectionEvent e) throws IOException {
        //监听列表点击事件

        if (text.equals(jList1.getSelectedValue().trim())) {
            return;
        }
        text = jList1.getSelectedValue().trim();
        if (text.equals("Null")) {
            jTextArea1.append("系统：没有选中！\r\n");
        } else {
            jTextArea1.append("您请求与" + jList1.getSelectedValue() + "通讯..." + "\r\n");
            int i = jList1.getSelectedIndex();
            String mess = "commu" + i;
            byte[] data = mess.trim().getBytes();
            DatagramPacket dp = new DatagramPacket(data, data.length, address, port);
            socket.send(dp);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UClientDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UClientDialog().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
