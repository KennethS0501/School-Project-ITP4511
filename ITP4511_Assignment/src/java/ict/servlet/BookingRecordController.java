/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.bean.BookingRecordBean;
import ict.db.BookingRecordDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kikit
 */
@WebServlet(name = "BookingRecordController", urlPatterns = {"/Member/BookingRecordFunction/BookingRecord"})
public class BookingRecordController extends HttpServlet {

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
        if ("ChangeGuestList".equals(action)) {
            changeGuestList(request, response);
        } else if ("ChangeNotiTemplate".equals(action)) {
            changeNotiTemplate(request, response);
        } else if ("sendBookingRequest".equals(action)) {
            //sendBookingRequest(request, response);
        }
    }

    public void changeGuestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        int orderId = getOrderId(memberId, Integer.parseInt(request.getParameter("OrderIndex")));
        int guest_list_id = Integer.parseInt(request.getParameter("guest_list_id"));
        String msg = null;

        if (bookingRecordDB.updateGusetList(orderId, guest_list_id)) {
            msg = "Change Guest List Success";
        } else {
            msg = "Change Guest List UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/Member/BookingRecordFunction/UpdateBookingRecord.jsp?message=" + msg);
        System.out.println(rd);
        rd.forward(request, response);
    }

    public void changeNotiTemplate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        int orderId = getOrderId(memberId, Integer.parseInt(request.getParameter("OrderIndex")));
        int noti_Template_id = Integer.parseInt(request.getParameter("noti_Template_id"));
        String msg = null;

        if (bookingRecordDB.updateNotiTemplate(orderId, noti_Template_id)) {
            msg = "Change Notification template Success";
        } else {
            msg = "Change Notification template UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/Member/BookingRecordFunction/UpdateBookingRecord.jsp?message=" + msg);
        System.out.println(rd);
        rd.forward(request, response);
    }

    public int getOrderId(int memberId, int orderIndex) {
        return bookingRecordDB.queryBookingRecordByMemberId(memberId).get(orderIndex - 1).getId();
    }
}
