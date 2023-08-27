/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.db.BookingOrderDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alvin
 */
@WebServlet(name = "EditBookingOrderController", urlPatterns = {"/Staff/BookingFunction/EditBookingOrder"})
public class EditBookingOrderController extends HttpServlet {

    private BookingOrderDB ODB;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        ODB = new BookingOrderDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        ConfirmOrDecline
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if ("ConfirmOrDecline".equals(action)) {
            ConfirmOrDecline(request, response);
        } else if ("CheckIn".equals(action)) {
            CheckInOrAbsent(request, response);
        } else if ("CheckOut".equals(action)) {
            CheckOut(request, response);
        } else if ("BlockMember".equals(action)) {
            BlockMember(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void ConfirmOrDecline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("OrderId");
        String status = request.getParameter("status");
        String StaffName = request.getParameter("StaffName");
        String targetURL = "/Staff/BookingFunction/ConfirmOrDeclineBookingRequest.jsp";

        String error = ODB.ConfirmOrDecline(id, status, StaffName);
        HttpSession session = request.getSession(true);

        request.getSession().setAttribute("Error", error);
        RequestDispatcher rd;

        rd = getServletContext().getRequestDispatcher(targetURL);
        getServletInfo();
        rd.forward(request, response);

    }

    private void CheckInOrAbsent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("OrderId");
        String status = request.getParameter("status");
        String StaffName = request.getParameter("StaffName");

        String Remark = request.getParameter("Remark");
        String error;
        String targetURL = "/Staff/BookingFunction/CheckInBooking.jsp";

        error = ODB.CheckIn(id, status, StaffName);

        RequestDispatcher rd;

        rd = getServletContext().getRequestDispatcher(targetURL);
        getServletInfo();
        rd.forward(request, response);

    }

    private void CheckOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("OrderId");

        String StaffName = request.getParameter("StaffName");

        String Remark = request.getParameter("Remark");
        String error;
        String targetURL = "/Staff/BookingFunction/CheckoutBooking.jsp";

        error = ODB.CheckOut(id, StaffName, Remark);

        RequestDispatcher rd;

        rd = getServletContext().getRequestDispatcher(targetURL);
        getServletInfo();
        rd.forward(request, response);

    }

    private void BlockMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("MemberId");

        String Block = request.getParameter("Block");
        System.out.println(  "s:"+Block);
        String error;
        String targetURL = "/Staff/BookingFunction/BlockMemberUseBookingFunction.jsp";

        error= ODB.BlockMember(id, Block);

        RequestDispatcher rd;

        rd = getServletContext().getRequestDispatcher(targetURL);
        getServletInfo();
        rd.forward(request, response);

    }

}
