//
//* To change this license header, choose License Headers in Project Properties.
//* To change this template file, choose Tools | Templates
//* and open the template in the editor.
//
package com.mycompany.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JButton;

/**
 *
 * @author 25067
 */
//接收消息
public class UClientThread extends Thread {

    JTextArea jta;
    DatagramSocket socket;
    DatagramPacket packet;
    InetAddress address;
    int port;
    boolean flag = true;
    JButton jButton1;
    boolean key = true;//键盘状态

    int client;//总用户数
    String[] name;//用户名

    public UClientThread(DatagramSocket socket, int port, JTextArea jta) {
        this.socket = socket;
        this.jta = jta;
        this.port = port;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, 256);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getData().length).trim();
                //登陆成功判断
                if (received.equals("Login successful!")) {
                    key = false;
                }
                if (received.substring(0, 4).equals("name")) {
                    parseString(received);
                } else {
                    jta.append(received + "\r\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(UClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * 解析服务器发的更新包字符串
     *
     * @param str
     */
    public void parseString(String str) {
        int index = str.indexOf("client") + 6;
        client = Integer.parseInt(str.substring(index).trim());
        name = new String[client];
        str = str.substring(4, index - 6);
        for (int i = 0; i < client; i++) {
            index = str.indexOf(",");
            name[i] = str.substring(0, index).trim();
            if (i == client - 1) {
                break;
            }
            str = str.substring(index + 1);
        }
    }

}
