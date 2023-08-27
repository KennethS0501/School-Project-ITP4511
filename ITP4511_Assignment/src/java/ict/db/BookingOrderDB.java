/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.BookingOrderBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alvin
 */
public class BookingOrderDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public BookingOrderDB(String dburl, String dbUser, String dbPassword) {
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

    public ArrayList<BookingOrderBean> queryBookingOrderListWP() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<BookingOrderBean> orders = new ArrayList<BookingOrderBean>();
        try {
            cnnct = getConnection();

            String preQueryStatement = "SELECT b.id, m.name as MName , v.name as VName , b.booking_date , b.start_time , b.end_time , b.status , i.path as path "
                    + "FROM  booking_record b ,member m,venue v , image_path i "
                    + "Where v.id = b.id_venue and m.id = b.id_member and i.id = b.id_image and ( b.status = 'waitPayment' or b.status = 'process')";

            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingOrderBean order = new BookingOrderBean();
                order.setImagePath(rs.getString("path"));
                order.setMember_name(rs.getString("MName"));
                order.setId(rs.getInt("id"));
                order.setVenue_name(rs.getString("VName"));
                order.setBooking_date(rs.getString("booking_date"));
                order.setStart_time(rs.getString("start_time"));
                order.setEnd_time(rs.getString("end_time"));
                order.setStatus(rs.getString("status"));

                orders.add(order);
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

        return orders;
    }

    public ArrayList<BookingOrderBean> queryBookingOrderListC() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<BookingOrderBean> orders = new ArrayList<BookingOrderBean>();
        try {
            cnnct = getConnection();

            String preQueryStatement = "SELECT b.id, m.name as MName , v.name as VName , b.booking_date , b.start_time , b.end_time , b.status , i.path as path "
                    + "FROM  booking_record b ,member m,venue v , image_path i "
                    + "Where v.id = b.id_venue and m.id = b.id_member and i.id = b.id_image and ( b.status = 'confirmed')";

            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingOrderBean order = new BookingOrderBean();
                order.setImagePath(rs.getString("path"));
                order.setMember_name(rs.getString("MName"));
                order.setId(rs.getInt("id"));
                order.setVenue_name(rs.getString("VName"));
                order.setBooking_date(rs.getString("booking_date"));
                order.setStart_time(rs.getString("start_time"));
                order.setEnd_time(rs.getString("end_time"));
                order.setStatus(rs.getString("status"));

                orders.add(order);
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

        return orders;
    }

    public String BlockMember(String id, String  Block) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            System.out.println( "db:"+Block);
            String preQueryStatement = "UPDATE member SET block =? WHERE id =? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, Integer.parseInt(Block));
            pStmnt.setInt(2, Integer.parseInt(id));

            pStmnt.executeUpdate();

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
        return "";
    }

    public ArrayList<BookingOrderBean> queryBookingOrderListO() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<BookingOrderBean> orders = new ArrayList<BookingOrderBean>();
        try {
            cnnct = getConnection();

            String preQueryStatement = "SELECT b.id, m.name as MName , v.name as VName , b.booking_date , b.start_time , b.end_time , b.status , i.path as path "
                    + "FROM  booking_record b ,member m,venue v , image_path i "
                    + "Where v.id = b.id_venue and m.id = b.id_member and i.id = b.id_image and ( b.status = 'check-in')";

            pStmnt = cnnct.prepareStatement(preQueryStatement);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingOrderBean order = new BookingOrderBean();
                order.setImagePath(rs.getString("path"));
                order.setMember_name(rs.getString("MName"));
                order.setId(rs.getInt("id"));
                order.setVenue_name(rs.getString("VName"));
                order.setBooking_date(rs.getString("booking_date"));
                order.setStart_time(rs.getString("start_time"));
                order.setEnd_time(rs.getString("end_time"));
                order.setStatus(rs.getString("status"));

                orders.add(order);
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

        return orders;
    }

    public String ConfirmOrDecline(String id, String status, String StaffName) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<BookingOrderBean> orders = new ArrayList<BookingOrderBean>();
        String Error = "";
        try {
            cnnct = getConnection();

            String preQueryStatement = "SELECT id From booking_record where id='" + id + "' ;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            String Mid = "";
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs != null) {
                preQueryStatement = "SELECT id_member From booking_record where id=" + id + " ;";
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    Mid = Integer.toString(rs.getInt("id_member"));
                }
                System.out.println("MM   " + Mid);
                preQueryStatement = "SELECT id From staff where name='" + StaffName + "' ;";
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                String Sid = "";

                rs = null;
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    Sid = Integer.toString(rs.getInt("id"));
                }

                System.out.println("ss  " + Sid);
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                preQueryStatement = "INSERT INTO reminder(id_member,id_staff,type,id_booking_record,datetime) "
                        + "VALUES (?, ?, ?, ?,?);";
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                pStmnt.setString(1, Mid);
                pStmnt.setString(2, Sid);
                pStmnt.setString(3, status);
                pStmnt.setInt(4, Integer.parseInt(id));
                pStmnt.setString(5, formatter.format(date));
                pStmnt.executeUpdate();
            }
            preQueryStatement = "UPDATE booking_record  SET status =?"
                    + " WHERE id =? ";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, status);
            pStmnt.setInt(2, Integer.parseInt(id));

            pStmnt.executeUpdate();

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

        return Error;
    }

    public String CheckIn(String id, String status, String StaffName) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<BookingOrderBean> orders = new ArrayList<BookingOrderBean>();
        String Error = "";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            cnnct = getConnection();

            String preQueryStatement = "SELECT id From booking_record where id='" + id + "' ;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            String Mid = "";
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs != null) {
                preQueryStatement = "SELECT id_member From booking_record where id=" + id + " ;";
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    Mid = Integer.toString(rs.getInt("id_member"));
                }
                System.out.println("MM   " + Mid);
                preQueryStatement = "SELECT id From staff where name='" + StaffName + "' ;";
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                String Sid = "";

                rs = null;
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    Sid = Integer.toString(rs.getInt("id"));
                }

                System.out.println("ss  " + Sid);

                preQueryStatement = "INSERT INTO reminder(id_member,id_staff,type,id_booking_record,datetime) "
                        + "VALUES (?, ?, ?, ?,?);";
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                pStmnt.setString(1, Mid);
                pStmnt.setString(2, Sid);
                pStmnt.setString(3, status);
                pStmnt.setInt(4, Integer.parseInt(id));
                pStmnt.setString(5, formatter.format(date));
                pStmnt.executeUpdate();
            }
            preQueryStatement = "UPDATE booking_record  SET status =?"
                    + " WHERE id =? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, status);
            pStmnt.setInt(2, Integer.parseInt(id));
            pStmnt.executeUpdate();

            if (status.equals("check-in")) {
                preQueryStatement = "INSERT INTO check_in_out(id_booking_record,check_in_datetime,check_out_datetime,remark) "
                        + "VALUES (? , ? , ? , ?);";
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                pStmnt.setInt(1, Integer.parseInt(id));
                pStmnt.setString(2, formatter.format(date));
                pStmnt.setString(3, null);
                pStmnt.setString(4, null);

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

        return Error;
    }

    public String CheckOut(String id, String StaffName, String Remark) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<BookingOrderBean> orders = new ArrayList<BookingOrderBean>();
        String Error = "";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            cnnct = getConnection();

            String preQueryStatement = "SELECT id From booking_record where id='" + id + "' where id='" + id + "';";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            int Mid = 0;
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs != null) {
                preQueryStatement = "SELECT id_member From booking_record where id=" + id + " ;";
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    Mid = rs.getInt("id_member");
                }
                System.out.println("MM   " + Mid);
                preQueryStatement = "SELECT id From staff where name='" + StaffName + "' ;";
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                int Sid = 0;

                rs = null;
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    Sid = rs.getInt("id");
                }

                System.out.println("ss  " + Sid);

                preQueryStatement = "INSERT INTO reminder(id_member,id_staff,type,id_booking_record,datetime) "
                        + "VALUES (?, ?, ?, ?,?);";
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                pStmnt.setInt(1, Mid);
                pStmnt.setInt(2, Sid);
                pStmnt.setString(3, "check-out");
                pStmnt.setString(4, id);
                pStmnt.setString(5, formatter.format(date));
                pStmnt.executeUpdate();
                preQueryStatement = "UPDATE  check_in_out  SET check_out_datetime = ?, remark= ? WHERE id_booking_record = ? ";

                pStmnt = cnnct.prepareStatement(preQueryStatement);

                pStmnt.setString(1, formatter.format(date));
                pStmnt.setString(2, Remark);
                pStmnt.setString(3, id);

                pStmnt.executeUpdate();

            }
            preQueryStatement = "UPDATE booking_record  SET status =?"
                    + " WHERE id =? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "check-out");
            pStmnt.setInt(2, Integer.parseInt(id));
            pStmnt.executeUpdate();

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

        return Error;
    }

}
