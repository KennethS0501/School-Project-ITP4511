/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.MemberBean;
import ict.bean.StaffBean;
import ict.bean.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kikit
 */
public class UserDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public UserDB(String dburl, String dbUser, String dbPassword) {
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

    public User getMemberDetail(String email) {
        MemberBean member = new MemberBean();
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
                member.setId(rs.getInt("id"));
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

    public StaffBean getStaffDetail(String email) {
        StaffBean staff = new StaffBean();
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
        ArrayList<StaffBean> staffs = queryStaffList();
        ArrayList<User> members = queryMemberList();

        for (StaffBean staff : staffs) {
            if (email.equals(staff.getEmail())) {
                type = "staff";
                break;
            }
        }

        for (User member : members) {
            if (email.equals(member.getEmail())) {
                type = "member";
                break;
            }
        }

        return type;
    }

    public ArrayList<StaffBean> queryStaffList() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<StaffBean> staffs = new ArrayList<StaffBean>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT email, staff.name, role.name as role "
                    + "FROM staff "
                    + "INNER JOIN role ON role.id = staff.id_role";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                StaffBean staff = new StaffBean();
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

    public ArrayList<User> queryMemberList() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<User> members = new ArrayList<User>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM member";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                MemberBean member = new MemberBean();
                member.setId(rs.getInt("id"));
                member.setEmail(rs.getString("email"));
                member.setName(rs.getString("name"));
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

    public ArrayList<User> queryUserList() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<User> users = new ArrayList<User>();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT email, m.name, r.name as role, id_role "
                    + "FROM member m "
                    + "INNER JOIN role r on r.id = m.id_role";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                user.setRoleId(rs.getInt("id_role"));
                users.add(user);
            }

            preQueryStatement = "SELECT email, s.name, r.name as role, id_role "
                    + "FROM staff s "
                    + "INNER JOIN role r on r.id = s.id_role";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                user.setRoleId(rs.getInt("id_role"));
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

    public String queryStaffNameById(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT name "
                                    + "FROM staff "
                                    + "WHERE id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                return rs.getString("name");
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

        return "null";
    }
}
