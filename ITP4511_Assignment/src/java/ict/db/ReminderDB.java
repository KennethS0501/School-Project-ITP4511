/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.ReminderBean;
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
public class ReminderDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public ReminderDB(String dburl, String dbUser, String dbPassword) {
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

    public ArrayList<ReminderBean> queryReminderById(int memberId) {
        ArrayList<ReminderBean> reminders = new ArrayList<ReminderBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM reminder "
                    + "WHERE id_member=? "
                    + "ORDER BY datetime DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);

            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                ReminderBean reminder = new ReminderBean();
                reminder.setId(rs.getInt("id"));
                reminder.setId_member(rs.getInt("id_member"));
                reminder.setId_staff(rs.getInt("id_staff"));
                reminder.setType(rs.getString("type"));
                reminder.setId_booking_record(rs.getInt("id_booking_record"));
                reminder.setDatetime(rs.getString("datetime"));
                reminders.add(reminder);
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

        return reminders;
    }
}
