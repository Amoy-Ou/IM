/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 25067
 */
public class DataBase {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    static Connection ct = null;

    public DataBase() throws ClassNotFoundException, SQLException {
        Class.forName("com.hxtt.sql.access.AccessDriver");
        String conUrl = "jdbc:Access:///F:\\\\DataBase\\\\Database.accdb";
        ct = DriverManager.getConnection(conUrl, "OuY", "1401");
    }

    /**
     * 查询是否注册
     *
     * @param username
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean isExist(String username) throws ClassNotFoundException, SQLException {
//        DataBase dataBase = new DataBase();
        Statement st = ct.createStatement();
        String query = "select username from UserMess where username=" + "'" + username + "'";
        ResultSet rs = st.executeQuery(query.trim());
        return rs.next();
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public void regist(String username, String password) throws ClassNotFoundException, SQLException {
//        DataBase dataBase = new DataBase();

        Statement st = ct.createStatement();
        String query = "select count(*) as uc from UserMess";
        ResultSet rs = st.executeQuery(query.trim());

        int usercount = 0;
        if (rs.next()) {
            usercount = rs.getInt("uc");//查出当前注册用户数
        }
        query = "insert into UserMess values(" + usercount + ",'" + username + "','" + password + "')";
        st.executeUpdate(query.trim());
    }

    /**
     * 登陆是否匹配
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean isMatch(String username, String password) throws SQLException {
        Statement st = ct.createStatement();
        String query = "select username from UserMess where username=" + "'" + username + "' and password='" + password + "'";
        ResultSet rs = st.executeQuery(query.trim());
        return rs.next();
    }

    /**
     * 储存聊天信息
     *
     * @param from
     * @param to
     * @param mess
     * @throws java.sql.SQLException
     */
    public void saveText(String from, String to, String mess) throws SQLException {
        Statement st = ct.createStatement();
        String query = "select count(*) as uc from ChatMess";
        ResultSet rs = st.executeQuery(query.trim());
        int messcount = 0;
        if (rs.next()) {
            messcount = rs.getInt("uc");//查出当前注册用户数
        }
        query = "insert into ChatMess values(" + messcount + ",'" + from + "','" + to + "','" + mess + "')";
        st.executeUpdate(query.trim());
    }

    public ResultSet sendText(String name) throws SQLException {
        Statement st = ct.createStatement();
        String query = "select mess from ChatMess where fuser=" + "'" + name + "' or tuser='" + name + "'";
        ResultSet rs = st.executeQuery(query.trim());
        return rs;

    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        // TODO code application logic here
//        DataBase dataBase = new DataBase();
//        Statement st = ct.createStatement();  //建立Statement类对象
//        //设定预改参数
//        String name = "amoy".trim();
//        String query = "select mess from ChatMess where fuser=" + "'" + name + "' or tuser='" + name + "'";
//        ResultSet rs = st.executeQuery(query.trim());
//        while (rs.next()) {
////            String id = rs.getString("ID");
//            String mess = rs.getString("mess");
//            System.out.println("   mess=" + mess);
//        }
//
//    }

}
