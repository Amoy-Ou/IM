/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author 25067
 */
public class USeverThread extends Thread {

    JTextArea jta;
    DatagramSocket socket;
    DatagramPacket packet;

    int num = 1000;
    int port[] = new int[num];
    InetAddress address[] = new InetAddress[num];
    int client = 0;
    boolean flag = true;
    String[] name = new String[num];
    String index;//消息识别位
    int passindex;//密码起始位
    long[] dietime = new long[num]; //计时器，客户机上一次发心跳包的时间
    int[] commu = new int[num]; //存放每个客户端想要联系的对象
    InetAddress commua[] = new InetAddress[num];//联系地址
    String[] commun=new String[num]; //联系人
    public USeverThread(DatagramSocket socket, JTextArea jta) throws SocketException {
        this.jta = jta;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, 256);
                socket.receive(packet);
                String text = new String(packet.getData(), 0, packet.getData().length);
                if (text.length() >= 5) {
                    index = text.substring(0, 5);
                    if (index.equals("name0")) {
                        passindex = text.indexOf("pass0") + 5;
                    } else if (index.equals("name1")) {
                        passindex = text.indexOf("pass1") + 5;
                    } else if (index.equals("SoSoS")) {
                        Date date = new Date();
                        for (int i = 0; i < client; i++) {
                            if (port[i] == packet.getPort()) {
                                dietime[i] = date.getTime();
                                break;
                            }
                        }
                    } else if (index.equals("commu")) {
                        for (int i = 0; i < client; i++) {
                            if (port[i] == packet.getPort()) {
                                commu[i] = port[Integer.parseInt(text.substring(5).trim())];
                                for(int j=0;j<client;j++){
                                    if(port[j]==commu[i]){
                                        commua[i]=address[j];
                                        commun[i]=name[j];
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }else if (index.equals("recor")){//发送聊天记录
                        for (int i = 0; i < client; i++) {
                            if (port[i] == packet.getPort()) {
                                this.deliverRecord(i);
                            }
                        }
                    }

                }
//                jta.append("connecting:"+new String(packet.getData(), 0,packet.getData().length)+packet.getPort()+"\r\n");
                if (this.isFirst(packet)) {
                    String username = text.substring(5, passindex - 5);
                    //必须要加trim，避免后面有空格
                    String password = text.substring(passindex).trim();
                    //注册信息
                    DataBase db = new DataBase();
                    if (index.equals("name0")) {
                        //查询是否已注册
                        if (db.isExist(username)) {
                            String mess = "The account has been registered!".trim();
                            byte[] data = mess.getBytes();
                            DatagramPacket dp = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
                            socket.send(dp);
                        } else {
                            db.regist(username, password);
                            String mess = "Registration is successful, please log in!".trim();
                            byte[] data = mess.getBytes();
                            DatagramPacket dp = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
                            socket.send(dp);
                        }
//                        jta.append("name and password  "+uername+password);
                    } //登陆信息
                    else if (index.equals("name1")) {
                        if (db.isMatch(username, password)) {
                            String mess = "Login successful!".trim();
                            byte[] data = mess.getBytes();
                            DatagramPacket dp = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
                            socket.send(dp);
                            jta.append("Client(" + client + "): " + username + " Online " + " port:" + packet.getPort() + "\r\n");
                            port[client] = packet.getPort();
                            address[client] = packet.getAddress();
                            name[client++] = username;
                        } else {
                            String mess = "Login failed! please check you username or password".trim();
                            byte[] data = mess.getBytes();
                            DatagramPacket dp = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
                            socket.send(dp);
                        }
                    }
                } else if ((!index.equals("SoSoS")) && (!index.equals("commu"))&& (!index.equals("recor"))) {
                    this.deliverMessa(packet);//普通消息
                }
            } catch (IOException | ClassNotFoundException | SQLException ex) {
                Logger.getLogger(USeverThread.class.getName()).log(Level.SEVERE, null, ex);
//                flag=false;
            }

            try {
                //向每一个客户端发送最新的上线情况
                this.deliverClient();
            } catch (IOException ex) {
                Logger.getLogger(USeverThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            //判断是否有客户下线
            this.clientSort();
        }
        jta.append("The server offline...\r\n");

    }

    /**
     * 判断是否登陆
     *
     * @param dp
     * @return
     */
    private boolean isFirst(DatagramPacket dp) {
        for (int i = 0; i < client; i++) {
            if (dp.getPort() == port[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 用于发送消息
     *
     * @param dp
     * @throws SocketException
     * @throws IOException
     */
    private void deliverMessa(DatagramPacket dp) throws SocketException, IOException, ClassNotFoundException, SQLException {
        int i = -1;
        for (i = 0; i < client; i++) {
            if (port[i] == packet.getPort()) {
                break;
            }
        }
        if (client <= 1) {
            String text = "You are the only one online~".trim();
            byte[] data = text.getBytes();
            packet = new DatagramPacket(data, data.length, dp.getAddress(), dp.getPort());
            socket.send(packet);
        } else if (i == -1 || commu[i] == 0) {
            String text = "You did not select a contact~".trim();
            byte[] data = text.getBytes();
            packet = new DatagramPacket(data, data.length, dp.getAddress(), dp.getPort());
            socket.send(packet);
        } else {
            DataBase db = new DataBase();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String text=name[i]+"(" + df.format(new Date()) + "):";
            String mess = new String(packet.getData(), 0, packet.getData().length).trim();
            text=(text+mess).trim();
            db.saveText(name[i], commun[i], text);            
            byte[] data = text.getBytes();
            packet = new DatagramPacket(data, data.length, commua[i], commu[i]);
            socket.send(packet);
        }
    }
    
    /**
     * 发送聊天记录
     * @param i 
     */
    private void deliverRecord(int i) throws ClassNotFoundException, SQLException, IOException{
        DataBase db = new DataBase();
        ResultSet rs=db.sendText(name[i]);
        while(rs.next()){
            String text=rs.getString("mess");
            byte[] data = text.getBytes();
            packet = new DatagramPacket(data, data.length, address[i], port[i]);
            socket.send(packet);
        }
    }


/**
 * 给每个客户端发客户信息
 *
 * @throws IOException
 */
private void deliverClient() throws IOException {
        String text = "name";
        for (int i = 0; i < client; i++) {
            text = text + name[i] + ",";
        }
        text = text + "client" + client;
        byte[] data = text.getBytes();
        for (int i = 0; i < client; i++) {
            packet = new DatagramPacket(data, data.length, address[i], port[i]);
            socket.send(packet);
        }
    }

    /**
     * 清除下线客户
     */
    private void clientSort() {
        for (int i = 0; i < client; i++) {
            Date date = new Date();
            if (dietime[i] - date.getTime() > 20 * 1000) {

                jta.append("Client(" + client + ")" + name[i] + "offline\r\n ");
                client--;
                for (int j = i; j < client; j++) {
                    port[j] = port[j + 1];
                    address[j] = address[j + 1];
                    name[j] = name[j + 1];
                    dietime[j] = dietime[j + 1];
                }
            }
        }
    }

}
