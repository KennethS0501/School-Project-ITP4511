/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kikit
 */
public class AccountDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public AccountDB(String dburl, String dbUser, String dbPassword) {
        this.dburl = dburl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return DriverManager.getConnection(dburl, dbUser, dbPassword);
    }

    public boolean insertAccount(String name, String email, String pwd, int role) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement;
            if (role == 1) {
                preQueryStatement = "INSERT INTO member (name, email, password, id_role) VALUES (?,?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, name);
                pStmnt.setString(2, email);
                pStmnt.setString(3, pwd);
                pStmnt.setInt(4, role);
            } else {
                preQueryStatement = "INSERT INTO staff (name, email, id_role, password) VALUES (?,?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, name);
                pStmnt.setString(2, email);
                pStmnt.setInt(3, role);
                pStmnt.setString(4, pwd);
            }

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean editAccount(int id, String name, String email, int role) {

        UserDB userDB = new UserDB(dburl, dbUser, dbPassword);
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        ArrayList<User> users = userDB.queryUserList();
        try {
            cnnct = getConnection();
            String preQueryStatement;

            String old_name = users.get(id - 1).getName();
            String old_email = users.get(id - 1).getEmail();
            String old_role = users.get(id - 1).getRole();
            if (old_role.equals("Member")) {
                preQueryStatement = "UPDATE member SET name=?, email=?, id_role=? WHERE name=? AND email=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, name);
                pStmnt.setString(2, email);
                pStmnt.setInt(3, role);
                pStmnt.setString(4, old_name);
                pStmnt.setString(5, old_email);
            } else {
                preQueryStatement = "UPDATE staff SET name=?, email=?, id_role=? WHERE name=? AND email=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, name);
                pStmnt.setString(2, email);
                pStmnt.setInt(3, role);
                pStmnt.setString(4, old_name);
                pStmnt.setString(5, old_email);
            }
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean deleteAccount(int id) {

        UserDB userDB = new UserDB(dburl, dbUser, dbPassword);
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        ArrayList<User> users = userDB.queryUserList();
        try {
            cnnct = getConnection();
            String preQueryStatement;

            String old_name = users.get(id - 1).getName();
            String old_email = users.get(id - 1).getEmail();
            String old_role = users.get(id - 1).getRole();
            if (old_role.equals("Member")) {
                preQueryStatement = "DELETE FROM member WHERE name=? AND email=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, old_name);
                pStmnt.setString(2, old_email);
            } else {
                preQueryStatement = "DELETE FROM staff WHERE name=? AND email=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, old_name);
                pStmnt.setString(2, old_email);
            }
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

}
