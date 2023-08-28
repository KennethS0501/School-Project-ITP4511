/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.GuestListBean;
import ict.bean.GuestListDetailBean;
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
public class GuestListDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public GuestListDB(String dburl, String dbUser, String dbPassword) {
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

    public ArrayList<GuestListBean> queryGuestList(int memberId) {
        ArrayList<GuestListBean> guestLists = new ArrayList<GuestListBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM guest_list "
                    + "WHERE id_member=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);
            ResultSet rs1 = null;
            rs1 = pStmnt.executeQuery();

            while (rs1.next()) {
                ArrayList<GuestListDetailBean> guestListDetails = new ArrayList<GuestListDetailBean>();
                GuestListBean guestList = new GuestListBean();
                guestList.setId(rs1.getInt("id"));
                guestList.setName(rs1.getString("name"));

                preQueryStatement = "SELECT * "
                        + "FROM guest_list_detail "
                        + "WHERE id_guest_list=?";

                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setInt(1, rs1.getInt("id"));
                ResultSet rs2 = null;
                rs2 = pStmnt.executeQuery();
                while (rs2.next()) {
                    GuestListDetailBean guestListDetail = new GuestListDetailBean();
                    guestListDetail.setId(rs2.getInt("id"));
                    guestListDetail.setId_guest_list(rs2.getInt("id_guest_list"));
                    guestListDetail.setGuest_name(rs2.getString("guest_name"));
                    guestListDetail.setGuest_email(rs2.getString("guest_email"));
                    guestListDetails.add(guestListDetail);
                    guestList.setGuestListDetails(guestListDetails);
                }
                guestLists.add(guestList);
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

        return guestLists;
    }

    public boolean insertGuestList(String name, int memberId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement;
            preQueryStatement = "INSERT INTO guest_list (name, id_member) VALUES (?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setInt(2, memberId);

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

    public boolean deleteGuestList(int id, int memberId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement;

            int rowCount;

            preQueryStatement = "DELETE FROM guest_list WHERE id=? AND id_member=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            pStmnt.setInt(2, memberId);
            rowCount = pStmnt.executeUpdate();

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

    public boolean editGuestList(int id, String name, int memberId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement;

            int rowCount;

            preQueryStatement = "UPDATE guest_list SET name=? WHERE id=? AND id_member=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setInt(2, id);
            pStmnt.setInt(3, memberId);

            rowCount = pStmnt.executeUpdate();

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

    public boolean insertGuest(String name, String email, int memberId, int guestListId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement;
            preQueryStatement = "INSERT INTO guest_list_detail (id_guest_list, guest_name, guest_email) VALUES (?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, guestListId);
            pStmnt.setString(2, name);
            pStmnt.setString(3, email);

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

    public boolean deleteGuest(int id, int memberId, int guestListId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement;

            int rowCount;

            preQueryStatement = "DELETE FROM guest_list_detail WHERE id=? AND id_guest_list=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            pStmnt.setInt(2, guestListId);
            rowCount = pStmnt.executeUpdate();

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

    public String getGuestListName(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT name "
                    + "FROM guest_list "
                    + "WHERE id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs;
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
