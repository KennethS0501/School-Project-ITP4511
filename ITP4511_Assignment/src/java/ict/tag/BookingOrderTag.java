/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.BookingOrderBean;

import ict.db.BookingOrderDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author alvin
 */
public class BookingOrderTag extends SimpleTagSupport {

    private String tagType;
    private BookingOrderDB BookingOrderDB;
    private BookingOrderDB ODB;

    public BookingOrderTag() {
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
        BookingOrderDB = new BookingOrderDB(dbUrl, dbUser, dbPassword);
    }

    public void setTagType(String type) {
        this.tagType = type;
    }

    @Override
    public void doTag() {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        if ("ConfirmOrDecline".equalsIgnoreCase(tagType)) {
            showBookingOrdersDataWP();
        } else if ("CheckIn".equalsIgnoreCase(tagType)) {
            showBookingOrdersDataC();
        } else if ("CheckOut".equalsIgnoreCase(tagType)) {
            showBookingOrdersDataO();
        }
    }

    public void showBookingOrdersDataWP() {

        ArrayList<BookingOrderBean> orders = BookingOrderDB.queryBookingOrderListWP();

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 1;
            for (BookingOrderBean Order : orders) {
                out.print("<tr scope='row'>");
                out.print("<td>" + Order.getId() + "</td>");
                out.print("<td>" + Order.getVenue_name() + "</td>");
                out.print("<td>" + Order.getBooking_date() + "</td>");
                out.print("<td>" + Order.getStart_time() + "-" + Order.getEnd_time() + "</td>");
                out.print("<td>" + Order.getMember_name() + "</td>");
                out.print("<td>" + Order.getStatus() + "</td>");
                out.print("<td><img src='" + Order.getImagePath() + "' style='width:50px;height:50px;'/></td>");
                out.print("</tr>");
                i++;
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showBookingOrdersDataC() {

        ArrayList<BookingOrderBean> orders = BookingOrderDB.queryBookingOrderListC();

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 1;
            for (BookingOrderBean Order : orders) {
                out.print("<tr scope='row'>");
                out.print("<td>" + Order.getId() + "</td>");
                out.print("<td>" + Order.getVenue_name() + "</td>");
                out.print("<td>" + Order.getBooking_date() + "</td>");
                out.print("<td>" + Order.getStart_time() + "-" + Order.getEnd_time() + "</td>");
                out.print("<td>" + Order.getMember_name() + "</td>");
                out.print("<td>" + Order.getStatus() + "</td>");
                out.print("<td><img src='" + Order.getImagePath() + "' style='width:50px;height:50px;'/></td>");
                out.print("</tr>");
                i++;
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showBookingOrdersDataO() {

        ArrayList<BookingOrderBean> orders = BookingOrderDB.queryBookingOrderListO();

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 1;
            for (BookingOrderBean Order : orders) {
                out.print("<tr scope='row'>");
                out.print("<td>" + Order.getId() + "</td>");
                out.print("<td>" + Order.getVenue_name() + "</td>");
                out.print("<td>" + Order.getBooking_date() + "</td>");
                out.print("<td>" + Order.getStart_time() + "-" + Order.getEnd_time() + "</td>");
                out.print("<td>" + Order.getMember_name() + "</td>");
                out.print("<td>" + Order.getStatus() + "</td>");
                out.print("<td><img src='" + Order.getImagePath() + "' style='width:50px;height:50px;'/></td>");
                out.print("</tr>");
                i++;
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }
}
