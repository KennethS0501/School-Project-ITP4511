/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.BookingRecordBean;
import ict.db.BookingRecordDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kikit
 */
public class BookingRecordTag extends SimpleTagSupport {

    private String tagType;
    private int memberId;
    private int venueId;
    private BookingRecordDB bookingRecordDB;
    private String radio;
    private String year;
    private String month;

    public void setTagType(String type) {
        this.tagType = type;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BookingRecordTag() {
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
        bookingRecordDB = new BookingRecordDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            if ("ShowByTable".equalsIgnoreCase(tagType)) {
                showBookingRecordByTable();
            } else if ("ShowUpdateBookingRecordByTable".equalsIgnoreCase(tagType)) {
                showUpdateBookingRecordByTable();
            } else if ("ShowConfirmByTable".equalsIgnoreCase(tagType)) {
                showWaitPayBookingRecordByTable();
            } else if ("ShowCompleteBookingRecordByTable".equalsIgnoreCase(tagType)) {
                showCompleteBookingRecordByTable();
            } else if ("showBookingRecordByTableByVenue".equalsIgnoreCase(tagType)) {
                showBookingRecordByTableByVenue();
            } else if ("showVenueBookingNumber".equalsIgnoreCase(tagType)) {
                showVenueBookingNumber();
            } else if ("showBookingNumberByTimeRange".equalsIgnoreCase(tagType)) {
                showBookingNumberByTimeRange();
            } else if ("showBookingRecordByTimeRange".equalsIgnoreCase(tagType)) {
                showBookingRecordByTimeRange();
            } else if ("showBookingRate".equalsIgnoreCase(tagType)) {
                showBookingRate();
            } else if ("showTotalApproveBookingNumber".equalsIgnoreCase(tagType)) {
                showTotalApproveBooking();
            } else if ("showPresendBookingNumber".equalsIgnoreCase(tagType)) {
                showPresendBookingNumber();
            } else if ("showAbsentBookingNumber".equalsIgnoreCase(tagType)) {
                showAbsentBookingNumber();
            } else if ("showBookingAttendanceRate".equalsIgnoreCase(tagType)) {
                showBookingAttendanceRate();
            } else if ("getIncomeByEachVenue".equalsIgnoreCase(tagType)) {
                getIncomeByEachVenue();
            } else {
                out.println("No such type");
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showBookingRecordByTable() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords = bookingRecordDB.queryBookingRecordByMemberId(memberId);

            int i = 1;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                out.print("<tr>");
                out.print("<th>" + i + "</th>");
                out.print("<td>" + bookingRecord.getVenueName() + "</td>");
                out.print("<td>" + bookingRecord.getBooking_date() + "</td>");
                out.print("<td>" + bookingRecord.getStart_time() + " - " + bookingRecord.getEnd_time() + "</td>");
                out.print("<td>" + bookingRecord.getStatus() + "</td>");
                out.print("</tr>");
                i++;
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showUpdateBookingRecordByTable() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords = bookingRecordDB.queryBookingRecordByMemberId(memberId);

            int i = 1;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                if (bookingRecord.getStatus().equals("process")) {
                    out.print("<tr>");
                    out.print("<th>" + i + "</th>");
                    out.print("<td>" + bookingRecord.getVenueName() + "</td>");
                    out.print("<td>" + bookingRecord.getGuestListName() + "</td>");
                    out.print("<td>" + bookingRecord.getNotiTemplateName() + "</td>");
                    out.print("<td>" + bookingRecord.getPrice() + "</td>");
                    out.print("<td>" + bookingRecord.getStatus() + "</td>");
                    out.print("</tr>");
                    i++;
                }
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showWaitPayBookingRecordByTable() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords = bookingRecordDB.queryBookingRecordByMemberId(memberId);

            int i = 1;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                if (bookingRecord.getStatus().equals("waitPayment")) {
                    out.print("<tr>");
                    out.print("<th>" + i + "</th>");
                    out.print("<td>" + bookingRecord.getVenueName() + "</td>");
                    out.print("<td>" + bookingRecord.getGuestListName() + "</td>");
                    out.print("<td>" + bookingRecord.getNotiTemplateName() + "</td>");
                    out.print("<td>" + bookingRecord.getPrice() + "</td>");
                    out.print("<td>" + bookingRecord.getStatus() + "</td>");
                    out.print("</tr>");
                    i++;
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showCompleteBookingRecordByTable() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords = bookingRecordDB.queryConfirmBookingRecord(memberId);

            int i = 1;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                if (bookingRecord.getStatus().equals("complete")) {
                    out.print("<tr style=\"cursor: pointer;\"onclick=\"location.href='Comment.jsp?index=" + i + "';\">");
                    out.print("<th>" + i + "</th>");
                    out.print("<td>" + bookingRecord.getVenueName() + "</td>");
                    out.print("<td>" + bookingRecord.getGuestListName() + "</td>");
                    out.print("<td>" + bookingRecord.getNotiTemplateName() + "</td>");
                    out.print("<td>" + bookingRecord.getStatus() + "</td>");
                    out.print("</tr>");
                    i++;
                }
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showBookingRecordByTableByVenue() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords = bookingRecordDB.queryBookingRecordByVenue(venueId);

            int i = 1;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                out.print("<tr>");
                out.print("<th>" + i + "</th>");
                out.print("<td>" + bookingRecord.getVenueName() + "</td>");
                out.print("<td>" + bookingRecord.getBooking_date() + "</td>");
                out.print("<td>" + bookingRecord.getStart_time() + " - " + bookingRecord.getEnd_time() + "</td>");
                out.print("<td>" + bookingRecord.getStatus() + "</td>");
                out.print("</tr>");
                i++;
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showTotalBookingNumber() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords = bookingRecordDB.queryBookingRecord();

            int i = 0;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                i++;
            }

            out.print(i);

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showVenueBookingNumber() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords = bookingRecordDB.queryBookingRecordByVenue(venueId);

            int i = 0;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                i++;
            }
            out.print(i);
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showBookingNumberByTimeRange() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            ArrayList<BookingRecordBean> bookingRecords;
            if (radio.equals("year")) {
                bookingRecords = bookingRecordDB.queryBookingRecordByVenueAndYear(venueId, year);
            } else {
                bookingRecords = bookingRecordDB.queryBookingRecordByVenueAndMonth(venueId, month);
            }

            int i = 0;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                i++;
            }
            out.print(i);
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showBookingRecordByTimeRange() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            ArrayList<BookingRecordBean> bookingRecords;
            if (radio.equals("year")) {
                bookingRecords = bookingRecordDB.queryBookingRecordByVenueAndYear(venueId, year);
            } else {
                bookingRecords = bookingRecordDB.queryBookingRecordByVenueAndMonth(venueId, month);
            }

            int i = 1;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                out.print("<tr>");
                out.print("<th>" + i + "</th>");
                out.print("<td>" + bookingRecord.getVenueName() + "</td>");
                out.print("<td>" + bookingRecord.getBooking_date() + "</td>");
                out.print("<td>" + bookingRecord.getStart_time() + " - " + bookingRecord.getEnd_time() + "</td>");
                out.print("<td>" + bookingRecord.getStatus() + "</td>");
                out.print("</tr>");
                i++;
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showBookingRate() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords = bookingRecordDB.queryBookingRecordByVenue(venueId);

            int totalBooking = 0;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                totalBooking++;
            }

            if (radio.equals("year")) {
                bookingRecords = bookingRecordDB.queryBookingRecordByVenueAndYear(venueId, year);
            } else {
                bookingRecords = bookingRecordDB.queryBookingRecordByVenueAndMonth(venueId, month);
            }
            int bookingByTimeRange = 0;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                bookingByTimeRange++;
            }

            double bookingRate = (bookingByTimeRange * 100) / totalBooking;
            out.print(bookingRate + "%");

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showTotalApproveBooking() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords;
            if (radio.equals("year")) {
                bookingRecords = bookingRecordDB.queryApproveBookingRecordByYear(memberId, year);
            } else {
                bookingRecords = bookingRecordDB.queryApproveBookingRecordByMonth(memberId, month);
            }
            int totalApproveBooking = 0;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                totalApproveBooking++;
            }

            out.print(totalApproveBooking);

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showPresendBookingNumber() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords;
            if (radio.equals("year")) {
                bookingRecords = bookingRecordDB.queryPresendBookingRecordByYear(memberId, year);
            } else {
                bookingRecords = bookingRecordDB.queryPresendBookingRecordByMonth(memberId, month);
            }
            int PresendBookingNumber = 0;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                PresendBookingNumber++;
            }

            out.print(PresendBookingNumber);

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showAbsentBookingNumber() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords;
            if (radio.equals("year")) {
                bookingRecords = bookingRecordDB.queryAbsentBookingRecordByYear(memberId, year);
            } else {
                bookingRecords = bookingRecordDB.queryAbsentBookingRecordByMonth(memberId, month);
            }
            int AbsentBookingNumber = 0;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                AbsentBookingNumber++;
            }

            out.print(AbsentBookingNumber);

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showBookingAttendanceRate() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords;
            if (radio.equals("year")) {
                bookingRecords = bookingRecordDB.queryApproveBookingRecordByYear(memberId, year);
            } else {
                bookingRecords = bookingRecordDB.queryApproveBookingRecordByMonth(memberId, month);
            }
            int totalApproveBooking = 0;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                totalApproveBooking++;
            }

            if (radio.equals("year")) {
                bookingRecords = bookingRecordDB.queryPresendBookingRecordByYear(memberId, year);
            } else {
                bookingRecords = bookingRecordDB.queryPresendBookingRecordByMonth(memberId, month);
            }
            int PresendBookingNumber = 0;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                PresendBookingNumber++;
            }
            double bookingAttendanceRate = PresendBookingNumber * 100 / totalApproveBooking;
            out.print(bookingAttendanceRate + "%");

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showAttendanceNeededBookingByTable() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords;
            if (radio.equals("year")) {
                bookingRecords = bookingRecordDB.queryAttendanceNeededBookingByYear(memberId, year);
            } else {
                bookingRecords = bookingRecordDB.queryAttendanceNeededBookingByMonth(memberId, month);
            }

            int i = 1;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                out.print("<tr>");
                out.print("<th>" + i + "</th>");
                out.print("<td>" + bookingRecord.getVenueName() + "</td>");
                out.print("<td>" + bookingRecord.getBooking_date() + "</td>");
                out.print("<td>" + bookingRecord.getStart_time() + " - " + bookingRecord.getEnd_time() + "</td>");
                out.print("<td>" + bookingRecord.getStatus() + "</td>");
                out.print("</tr>");
                i++;
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void getIncomeByEachVenue() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            ArrayList<BookingRecordBean> bookingRecords;
            if (radio.equals("year")) {
                bookingRecords = bookingRecordDB.queryVenueIncomeByYear(year);
            } else {
                bookingRecords = bookingRecordDB.queryVenueIncomeByMonth(month);
            }

            int i = 1;
            for (BookingRecordBean bookingRecord : bookingRecords) {
                out.print("<tr>");
                out.print("<th>" + i + "</th>");
                out.print("<td>" + bookingRecord.getVenueName() + "</td>");
                out.print("<td>" + bookingRecord.getPrice() + "</td>");
                out.print("</tr>");
                i++;
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }
}
