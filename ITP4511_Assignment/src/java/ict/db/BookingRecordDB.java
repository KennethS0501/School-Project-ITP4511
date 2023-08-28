/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.BookingRecordBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author kikit
 */
public class BookingRecordDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public BookingRecordDB(String dburl, String dbUser, String dbPassword) {
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

    public boolean insertBookingRecord(int id_member, int id_venue, String booking_date, String start_time, String end_time, int id_guest_list, int id_notifi_template) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cnnct = getConnection();
            String preQueryStatement;
            preQueryStatement = "INSERT INTO booking_record (id_member, id_venue, booking_date, start_time, end_time, order_date, status, id_guest_list, id_notifi_template) VALUES (?,?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id_member);
            pStmnt.setInt(2, id_venue);
            pStmnt.setString(3, booking_date);
            pStmnt.setString(4, start_time);
            pStmnt.setString(5, end_time);
            pStmnt.setString(6, ft.format(date));
            pStmnt.setString(7, "process");
            pStmnt.setInt(8, id_guest_list);
            pStmnt.setInt(9, id_notifi_template);

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

    public ArrayList<BookingRecordBean> queryBookingRecordByMemberId(int member_id) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_member=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, member_id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));
                bookingRecord.setPrice(rs.getInt("price"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public boolean updateGusetList(int orderId, int guest_list_id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement;
            preQueryStatement = "UPDATE booking_record SET id_guest_list=? WHERE id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, guest_list_id);
            pStmnt.setInt(2, orderId);

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

    public boolean updateNotiTemplate(int orderId, int noti_Template_id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement;
            preQueryStatement = "UPDATE booking_record SET id_notifi_template=? WHERE id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, noti_Template_id);
            pStmnt.setInt(2, orderId);

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

    public ArrayList<BookingRecordBean> queryConfirmBookingRecord(int memberId) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_member=? "
                    + "AND status=? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);
            pStmnt.setString(2, "complete");
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryBookingRecordByVenue(int venueId) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_venue=? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, venueId);
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryBookingRecord() {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryBookingRecordByVenueAndYear(int venueId, String year) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_venue=? "
                    + "AND order_date LIKE '" + year + "%'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, venueId);

            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryBookingRecordByVenueAndMonth(int venueId, String month) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            System.out.println(month);
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_venue=? "
                    + "AND order_date LIKE '" + month + "%' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, venueId);

            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryApproveBookingRecordByYear(int memberId, String year) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_member=? "
                    + "AND status=? "
                    + "OR status=? "
                    + "OR status=? "
                    + "AND order_date LIKE '" + year + "%' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);
            pStmnt.setString(2, "complete");
            pStmnt.setString(3, "confirmed");
            pStmnt.setString(4, "check-in");
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryApproveBookingRecordByMonth(int memberId, String month) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_member=? "
                    + "AND status=? "
                    + "OR status=? "
                    + "OR status=? "
                    + "AND order_date LIKE '" + month + "%' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);
            pStmnt.setString(2, "complete");
            pStmnt.setString(3, "confirmed");
            pStmnt.setString(4, "check-in");
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryAttendanceNeededBookingByYear(int memberId, String year) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_member=? "
                    + "AND order_date LIKE '" + year + "%' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryAttendanceNeededBookingByMonth(int memberId, String month) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_member=? "
                    + "AND order_date LIKE '" + month + "%' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryPresendBookingRecordByYear(int memberId, String year) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_member=? "
                    + "AND status=? "
                    + "AND order_date LIKE '" + year + "%' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);
            pStmnt.setString(2, "complete");
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryPresendBookingRecordByMonth(int memberId, String month) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_member=? "
                    + "AND status=? "
                    + "AND order_date LIKE '" + month + "%' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);
            pStmnt.setString(2, "complete");
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryAbsentBookingRecordByYear(int memberId, String year) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_member=? "
                    + "AND status=? "
                    + "AND order_date LIKE '" + year + "%' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);
            pStmnt.setString(2, "absent");
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryAbsentBookingRecordByMonth(int memberId, String month) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * "
                    + "FROM booking_record "
                    + "WHERE id_member=? "
                    + "AND status=? "
                    + "AND order_date LIKE '" + month + "%' ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);
            pStmnt.setString(2, "absent");
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();
                bookingRecord.setId(rs.getInt("id"));
                bookingRecord.setId_member(rs.getInt("id_member"));
                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setBooking_date(rs.getString("booking_date"));
                bookingRecord.setStart_time(rs.getString("start_time"));
                bookingRecord.setEnd_time(rs.getString("end_time"));
                bookingRecord.setOrder_date(rs.getString("order_date"));
                bookingRecord.setStatus(rs.getString("status"));
                bookingRecord.setId_guest_list(rs.getInt("id_guest_list"));
                bookingRecord.setId_notifi_template(rs.getInt("id_notifi_template"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryVenueIncomeByYear(String year) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT id_venue, SUM(price) as price FROM booking_record WHERE order_date LIKE '" + year + "%' GROUP BY id_venue";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();

                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setPrice(rs.getInt("price"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }

    public ArrayList<BookingRecordBean> queryVenueIncomeByMonth(String month) {
        ArrayList<BookingRecordBean> bookingRecords = new ArrayList<BookingRecordBean>();

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT id_venue, SUM(price) as price FROM booking_record WHERE order_date LIKE '" + month + "%' GROUP BY id_venue";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingRecordBean bookingRecord = new BookingRecordBean();

                bookingRecord.setId_venue(rs.getInt("id_venue"));
                bookingRecord.setPrice(rs.getInt("price"));

                bookingRecords.add(bookingRecord);
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

        return bookingRecords;
    }
}
