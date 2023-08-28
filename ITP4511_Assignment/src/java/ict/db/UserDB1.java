/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.MemberBean1;
import ict.bean.StaffBean1;
import ict.bean.User1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kikit
 */
public class UserDB1 {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public UserDB1(String dburl, String dbUser, String dbPassword) {
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

    public boolean isValidUser(String email, String pwd, String type) {
        boolean isValid = false;

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM " + type + " WHERE email=? and password=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //pStmnt.setString(1, type);
            pStmnt.setString(1, email);
            pStmnt.setString(2, pwd);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                isValid = true;
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

        return isValid;
    }

    public User1 getMemberDetail(String email) {
        MemberBean1 member = new MemberBean1();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM member WHERE email = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, email);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                member.setEmail(rs.getString("email"));
                member.setName(rs.getString("name"));
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

        return member;
    }

    public StaffBean1 getStaffDetail(String email) {
        StaffBean1 staff = new StaffBean1();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            //String preQueryStatement = "SELECT * FROM staff WHERE email = ?";
            String preQueryStatement = "SELECT email, staff.name, role.name as role "
                    + "FROM staff "
                    + "INNER JOIN role ON role.id = staff.id_role "
                    + "WHERE email = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, email);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                staff.setEmail(rs.getString("email"));
                staff.setName(rs.getString("name"));
                staff.setRole(rs.getString("role"));
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

        return staff;
    }

    public String getUserType(String email) {
        String type = "";
        ArrayList<StaffBean1> staffs = queryStaffList();
        ArrayList<MemberBean1> members = queryMemberList();

        for (StaffBean1 staff : staffs) {

            if (email.equals(staff.getEmail())) {
                type = "staff";

                break;
            }
        }

        for (MemberBean1 member : members) {
            if (email.equals(member.getEmail())) {
                type = "member";
                break;
            }
        }

        return type;
    }

    public ArrayList<StaffBean1> queryStaffList() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<StaffBean1> staffs = new ArrayList<StaffBean1>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT email, staff.name, role.name as role "
                    + "FROM staff "
                    + "INNER JOIN role ON role.id = staff.id_role";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                StaffBean1 staff = new StaffBean1();
                staff.setEmail(rs.getString("email"));
                staff.setName(rs.getString("name"));
                staff.setRole(rs.getString("role"));

                staffs.add(staff);
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

        return staffs;
    }

    public ArrayList<MemberBean1> queryMemberList() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<MemberBean1> members = new ArrayList<MemberBean1>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM member";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                MemberBean1 member = new MemberBean1();

                //pan
                member.setId(rs.getInt("id"));
                member.setBlock(rs.getInt("block"));
                //pan

                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));

                members.add(member);
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

        return members;
    }

    public ArrayList<User1> queryUserList() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<User1> users = new ArrayList<User1>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT email, m.name, r.name as role "
                    + "FROM member m "
                    + "INNER JOIN role r on r.id = m.id_role";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                User1 user = new User1();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }

            preQueryStatement = "SELECT email, s.name, r.name as role "
                    + "FROM staff s "
                    + "INNER JOIN role r on r.id = s.id_role";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                User1 user = new User1();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                users.add(user);
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

        return users;
    }

    public void Register(String name, String email, String password, String Opassword) throws SQLException {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT email "
                    + "FROM member "
                    + "where email =?";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, email);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            boolean flag = true;
            if (rs.next()) {
                flag = false;
            }
            if (flag) {

                preQueryStatement = "Insert into member ( name,email,password,id_role,block)"
                        + "values (?,?,?,?,?) ";

                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, name);
                pStmnt.setString(2, email);
                pStmnt.setString(3, password);
                pStmnt.setString(4, "1");
                pStmnt.setString(5, "0");
                pStmnt.executeUpdate();

                pStmnt.close();
                cnnct.close();

            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            Logger.getLogger(UserDB1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
