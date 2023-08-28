/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.VenueBean1;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alvin
 */
public class VenueDB1 {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public VenueDB1(String dburl, String dbUser, String dbPassword) {
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

    public ArrayList<VenueBean1> queryVenueList() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<VenueBean1> Venues = new ArrayList<VenueBean1>();
        try {
            cnnct = getConnection();

            String preQueryStatement = "SELECT v.id, v.name , v.type, v.capacity , v.location , v.description , v.description , v.person_in_charge  , v.booking_fee , v.status , i.path as path "
                    + "FROM  venue v , image_path i "
                    + "Where i.id = v.id_image ";

            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                VenueBean1 venue = new VenueBean1();
                venue.setId(rs.getInt("id"));
                venue.setName(rs.getString("name"));
                venue.setType(rs.getString("type"));
                venue.setCapacity(rs.getInt("capacity"));
                venue.setLocation(rs.getString("location"));
                venue.setDesciption(rs.getString("description"));
                venue.setPerson_in_charge(rs.getString("person_in_charge"));
                venue.setBooking_fee(rs.getInt("booking_fee"));
                venue.setStatus(rs.getString("status"));
                venue.setImage_path(rs.getString("path"));

                Venues.add(venue);
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

        return Venues;
    }

    public void InsertVenue(
            String DBuploadPath,
            String VenueName,
            String VenueType,
            String VenueCapacity,
            String VenueLocation,
            String VenueDescription,
            String VenuePerson_in_charge,
            String VenueBooking_Fee) {
        String Error = "";
        boolean flag = true;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<VenueBean1> Venues = new ArrayList<VenueBean1>();
        try {
            cnnct = getConnection();

            String preQueryStatement = "SELECT name "
                    + "FROM  venue v "
                    + "Where v.name ='" + VenueName + "' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                flag = false;
            }
            System.out.println("Insert");
            preQueryStatement = "SELECT description "
                    + "FROM  venue v "
                    + "Where v.description ='" + VenueDescription + "' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = null;
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                flag = false;
            }
            System.out.println(flag);
            if (flag) {
                System.out.println("Inser t2");
                preQueryStatement = "INSERT INTO image_path(path) "
                        + "VALUES (?);";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, DBuploadPath);
                pStmnt.executeUpdate();

                preQueryStatement = "SELECT id "
                        + "FROM  image_path i "
                        + "Where i.path ='" + DBuploadPath + "' ";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs = null;
                rs = pStmnt.executeQuery();
                int id_image = 0;
                if (rs.next()) {
                    id_image = rs.getInt("id");
                }

                preQueryStatement = "INSERT INTO venue(name, type, capacity,location,description,person_in_charge,booking_fee,status,id_image) "
                        + "VALUES (?,?,?,?,?,?,?,?,?);";

                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, VenueName);
                pStmnt.setString(2, VenueType);
                pStmnt.setInt(3, Integer.parseInt(VenueCapacity));
                pStmnt.setString(4, VenueLocation);
                pStmnt.setString(5, VenueDescription);
                pStmnt.setString(6, VenuePerson_in_charge);
                pStmnt.setInt(7, Integer.parseInt(VenueBooking_Fee));
                pStmnt.setString(8, "enable");
                pStmnt.setInt(9, id_image);
                pStmnt.executeUpdate();

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

    }

    public String FindVenueName(int VenueId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int id = 0;
        System.out.println(VenueId);
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT id_image FROM venue Where id =? ;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, VenueId);
            ResultSet rs = null;

            rs = pStmnt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_image");
                System.out.println(id);
                preQueryStatement = "SELECT path FROM image_path Where id =? ;";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setInt(1, id);
                rs = null;

                rs = pStmnt.executeQuery();

                String path = "";
                if (rs.next()) {
                    path = rs.getString("path");
                    System.out.println(id);
                    return path;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(VenueDB1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VenueDB1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void EditVenue(
            String DBuploadPath,
            String VenueId,
            String VenueName,
            String VenueType,
            String VenueCapacity,
            String VenueLocation,
            String VenueDescription,
            String VenuePerson_in_charge,
            String VenueBooking_Fee) {
        String Error = "";
        boolean flag = true;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<VenueBean1> Venues = new ArrayList<VenueBean1>();
        try {
            cnnct = getConnection();

            String preQueryStatement = "SELECT id_image "
                    + "FROM  venue "
                    + "Where id ='" + VenueId + "' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            int id_image = 0;
            if (!rs.next()) {
                flag = false;
            } else {
                id_image = rs.getInt("id_image");
            }

        

            preQueryStatement = "SELECT description "
                    + "FROM  venue v "
                    + "Where v.description ='" + VenueDescription + "' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            rs = null;
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                flag = false;
            }
            System.out.println("Edit");

            System.out.println(flag);
            if (flag) {

                preQueryStatement = "UPDATE image_path "
                        + "SET path = '" + DBuploadPath + "'"
                        + "where id = '" + id_image + "'";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.executeUpdate();

                preQueryStatement = "UPDATE venue "
                        + "SET  name=? , type=? , capacity=?, location=? ,description=? ,person_in_charge=? ,booking_fee=? "
                        + "where id=? ;";

                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, VenueName);
                pStmnt.setString(2, VenueType);
                pStmnt.setInt(3, Integer.parseInt(VenueCapacity));
                pStmnt.setString(4, VenueLocation);
                pStmnt.setString(5, VenueDescription);
                pStmnt.setString(6, VenuePerson_in_charge);
                pStmnt.setInt(7, Integer.parseInt(VenueBooking_Fee));
                pStmnt.setInt(8, Integer.parseInt(VenueId));

                pStmnt.executeUpdate();

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
    }

    public void DeleteVenue(String VenueId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<VenueBean1> Venues = new ArrayList<VenueBean1>();
        int id = 0;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT id_image FROM venue Where id =? ;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, Integer.parseInt(VenueId));
            ResultSet rs = null;

            rs = pStmnt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_image");
                System.out.println(id);
                preQueryStatement = "delete  FROM image_path Where id = ? ;";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setInt(1, id);
                pStmnt.executeUpdate();
                preQueryStatement = "delete  FROM venue Where id = ? ;";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setInt(1, Integer.parseInt(VenueId));
                pStmnt.executeUpdate();

            }

        } catch (SQLException ex) {
            Logger.getLogger(VenueDB1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VenueDB1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EnableOrDisable(int VenueId, String VenueStatus) throws IOException {
        System.out.print("aaa^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        int id = 0;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE venue "
                    + "SET  status=? "
                    + "where id=? ;";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, VenueStatus);
            pStmnt.setInt(2, VenueId);

            pStmnt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VenueDB1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
