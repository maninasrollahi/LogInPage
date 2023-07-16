package com.example.loginpage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionToDB {

    public static Connection Connection(){
        Connection con=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "mani", "123");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static Integer Check(String name, String pass){
        int a = 0;
        try{
            PreparedStatement ps = Connection().prepareCall("select *from users where username = ? and password = ?");
            ps.setString(1,name);
            ps.setString(2,pass);
            a = ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return a;
    }

    public static Integer Add_User(String email,String username,String password){
        int a=-1;
        try {
            PreparedStatement ps1 = Connection().prepareStatement("select *from users where email = ?");
            ps1.setString(1,email);
            PreparedStatement ps2 = Connection().prepareStatement("select *from users where username = ?");
            ps2.setString(1,username);
            if (ps1.executeUpdate()+ps2.executeUpdate()>0){
                Connection().close();
            }
            else {
                PreparedStatement ps = Connection().prepareStatement("insert into users values (?,?,?)");
                ps.setString(1,email);
                ps.setString(2,username);
                ps.setString(3,password);
                a = ps.executeUpdate();
                Connection().close();}
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return a;
    }

    public static ResultSet Find_User(String email, String username){
        ResultSet rs = null;
        try{
            PreparedStatement ps = Connection().prepareStatement("select *from users where email = ? and username = ?");
            ps.setString(1,email);
            ps.setString(2,username);
            rs = ps.executeQuery();
            Connection().close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public static Integer ChangePass(String email, String username, String password){
        int a = 0;
        try {
            PreparedStatement ps = Connection().prepareStatement("update users set password = ? where email = ? and username = ?");
            ps.setString(1,password);
            ps.setString(2,email);
            ps.setString(3,username);
            a = ps.executeUpdate();
            Connection().close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return a;
    }
}
