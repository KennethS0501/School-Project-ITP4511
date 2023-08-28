/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.VenueBean;
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
public class VenueDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public VenueDB(String dburl, String dbUser, String dbPassword) {
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

    public VenueBean getVenueInformation(int id) {
        VenueBean venue = new VenueBean();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM venue WHERE id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                venue.setId(rs.getInt("id"));
                venue.setName(rs.getString("name"));
                venue.setType(rs.getString("type"));
                venue.setCapacity(rs.getInt("capacity"));
                venue.setLocation(rs.getString("location"));
                venue.setDescription(rs.getString("description"));
                venue.setPerson_in_charge(rs.getString("person_in_charge"));
                venue.setBooking_fee(rs.getInt("booking_fee"));
                venue.setStatus(rs.getString("status"));
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

        return venue;
    }

    public ArrayList<VenueBean> queryVenue() {
        ArrayList<VenueBean> venues = new ArrayList<VenueBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM venue";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                VenueBean venue = new VenueBean();
                venue.setId(rs.getInt("id"));
                venue.setName(rs.getString("name"));
                venue.setType(rs.getString("type"));
                venue.setCapacity(rs.getInt("capacity"));
                venue.setLocation(rs.getString("location"));
                venue.setDescription(rs.getString("description"));
                venue.setPerson_in_charge(rs.getString("person_in_charge"));
                venue.setBooking_fee(rs.getInt("booking_fee"));
                venue.setStatus(rs.getString("status"));
                venues.add(venue);
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

        return venues;
    }
}
