/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.NotiTemplateBean;
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
public class NotiTemplateDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public NotiTemplateDB(String dburl, String dbUser, String dbPassword) {
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

    public ArrayList<NotiTemplateBean> queryNotiTamplate() {
        ArrayList<NotiTemplateBean> notiTemplates = new ArrayList<NotiTemplateBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM notification_template ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                NotiTemplateBean notiTemplate = new NotiTemplateBean();
                notiTemplate.setId(rs.getInt("id"));
                notiTemplate.setName(rs.getString("name"));
                notiTemplates.add(notiTemplate);
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

        return notiTemplates;
    }

    public String getTemplateName(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT name "
                    + "FROM notification_template "
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
