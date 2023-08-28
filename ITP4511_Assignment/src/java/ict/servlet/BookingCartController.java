/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.bean.BookingRecordBean;
import ict.bean.VenueBean;
import ict.db.BookingRecordDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kikit
 */
@WebServlet(name = "BookingCartController", urlPatterns = {"/BookingCart"})
public class BookingCartController extends HttpServlet {

    ArrayList<BookingRecordBean> cart = new ArrayList<BookingRecordBean>();
    private BookingRecordDB bookingRecordDB;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        bookingRecordDB = new BookingRecordDB(dbUrl, dbUser, dbPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("addToCart".equals(action)) {
            addToCart(request, response);
        } else if ("removeFromCart".equals(action)) {
            removeFromCart(request, response);
        } else if ("sendBookingRequest".equals(action)) {
            sendBookingRequest(request, response);
        }
    }

    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int venueId = Integer.parseInt(request.getParameter("id_venue"));
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        String booking_date = request.getParameter("booking_date");
        String start_time = request.getParameter("start_time");
        String end_time = request.getParameter("end_time");
        int guest_list_id = Integer.parseInt(request.getParameter("guest_list"));
        int notification_template_id = Integer.parseInt(request.getParameter("notification_template"));

        HttpSession session = request.getSession(true);
        if (session.getAttribute("cart") != null) {
            cart = (ArrayList<BookingRecordBean>) session.getAttribute("cart");
        }

        BookingRecordBean bookingRecord = new BookingRecordBean();
        bookingRecord.setId_member(memberId);
        bookingRecord.setId_venue(venueId);
        bookingRecord.setBooking_date(booking_date);
        bookingRecord.setStart_time(start_time);
        bookingRecord.setEnd_time(end_time);
        bookingRecord.setId_guest_list(guest_list_id);
        bookingRecord.setId_notifi_template(notification_template_id);

        cart.add(bookingRecord);
        session.setAttribute("cart", cart);
        String targetURL = "BookingCart.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        System.out.println(rd);
        rd.forward(request, response);
    }

    public void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int cartIndex = Integer.parseInt(request.getParameter("index"));
        cart = (ArrayList<BookingRecordBean>) session.getAttribute("cart");

        cart.remove(cartIndex);
        session.setAttribute("cart", cart);
        String targetURL = "BookingCart.jsp";

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        System.out.println(rd);
        rd.forward(request, response);
    }

    public void sendBookingRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        cart = (ArrayList<BookingRecordBean>) session.getAttribute("cart");
        String msg = null;
        for (BookingRecordBean bookingRecord : cart) {
            if (bookingRecordDB.insertBookingRecord(bookingRecord.getId_member(),
                    bookingRecord.getId_venue(),
                    bookingRecord.getBooking_date(),
                    bookingRecord.getStart_time(),
                    bookingRecord.getEnd_time(),
                    bookingRecord.getId_guest_list(),
                    bookingRecord.getId_notifi_template()
            )) {
                msg = "Send Booking Request Success";
            } else {
                msg = "Send Booking Request UnSuccess";
            }
        }
        cart.clear();
        session.setAttribute("cart", cart);
        String targetURL = "BookingCart.jsp?message=" + msg;
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        System.out.println(rd);
        rd.forward(request, response);
    }
}
