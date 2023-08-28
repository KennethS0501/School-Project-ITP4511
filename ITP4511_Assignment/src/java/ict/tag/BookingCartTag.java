/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.BookingRecordBean;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kikit
 */
public class BookingCartTag extends SimpleTagSupport {

    private String tagType;
    private ArrayList<BookingRecordBean> cart;

    public void setTagType(String type) {
        this.tagType = type;
    }

    public void setCartSession(ArrayList cartSession) {
        this.cart = (ArrayList<BookingRecordBean>) cartSession;
    }

    public BookingCartTag() {
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
        //guestListDB = new GuestListDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            if ("ShowByTable".equalsIgnoreCase(tagType)) {
                showCartByTable();
            } else if ("ShowNameByTable".equalsIgnoreCase(tagType)) {
                //showGuestListNameByTable();
            } else if ("ShowDetailByTable".equalsIgnoreCase(tagType)) {
                //showGuestListDetailByTable();
            } else {
                out.println("No such type");
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showCartByTable() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 0;
            for (BookingRecordBean bookingRecord : cart) {
                out.print("<tr>");
                out.print("<th scope=\"row\" class=\"border-0\">");
                out.print("<div class=\"p-2\">");
                out.print("<img src=\"https://res.cloudinary.com/mhmd/image/upload/v1556670479/product-1_zrifhn.jpg\" alt=\"\" width=\"70\" class=\"img-fluid rounded shadow-sm\">");
                out.print("<div class=\"ml-3 d-inline-block align-middle\">");
                out.print("<h5 class=\"mb-0\"> <a href=\"#\" class=\"text-dark d-inline-block align-middle\">" + bookingRecord.getVenueName() + "</a></h5><span class=\"text-muted font-weight-normal font-italic d-block\"></span>");
                out.print("</div></div></th>");
                out.print("<td class=\"border-0 align-middle\"><strong>" + bookingRecord.getBooking_date() + "</strong></td>");
                out.print("<td class=\"border-0 align-middle\">");
                out.print(bookingRecord.getStart_time() + "<br />To<br />" + bookingRecord.getEnd_time());
                out.print("</td>");
                out.print("<td class=\"border-0 align-middle\"><strong>" + bookingRecord.getGuestListName() + "</strong></td>");
                out.print("<td class=\"border-0 align-middle\"><strong>" + bookingRecord.getNotiTemplateName() + "</strong></td>");
                out.print("<td class=\"border-0 align-middle\"><a href=\"BookingCart?action=removeFromCart&index="+ i +" \" class=\"text-dark\"><i class=\"fa fa-trash\"></i></a></td>");
                out.print("</tr>");
                i++;
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }
}
