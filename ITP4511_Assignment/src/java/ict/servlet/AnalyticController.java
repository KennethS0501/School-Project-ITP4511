/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.db.AccountDB;
import java.io.IOException;
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
@WebServlet(name = "AnalyticController", urlPatterns = {"/SeniorManagement/AnalyticSystem/Analytic"})
public class AnalyticController extends HttpServlet {

    private AccountDB accountDB;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        accountDB = new AccountDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("ShowBookingRecordByVenue".equals(action)) {
            showBookingRecordByVenue(request, response);
        } else if ("ShowBookingRateByVenue".equals(action)) {
            ShowBookingRateByVenue(request, response);
        } else if ("ShowAttendanceRateByUser".equals(action)) {
            ShowAttendanceByMember(request, response);
        } else if ("showIncome".equals(action)) {
            showIncome(request, response);
        }
    }

    public void showBookingRecordByVenue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("venueId"));

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/SeniorManagement/AnalyticSystem/ShowBookingRecordByVenue.jsp?venueId=" + id);
        rd.forward(request, response);
    }

    public void ShowBookingRateByVenue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("venueId"));
        String radio = request.getParameter("radio");
        String select = "";

        if (radio.equals("year")) {
            select = "year=" + request.getParameter("yearNumber");
        } else if (radio.equals("month")) {
            select = "month=" + request.getParameter("monthNumber");
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/SeniorManagement/AnalyticSystem/BookingRateByVenue.jsp?venueId=" + id + "&" + select);
        rd.forward(request, response);
    }

    public void ShowAttendanceByMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("memberId"));
        String radio = request.getParameter("radio");
        String select = "";

        if (radio.equals("year")) {
            select = "year=" + request.getParameter("yearNumber");
        } else if (radio.equals("month")) {
            select = "month=" + request.getParameter("monthNumber");
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/SeniorManagement/AnalyticSystem/BookingAttendanceByUser.jsp?memberId=" + id + "&" + select);
        rd.forward(request, response);
    }

    public void showIncome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String radio = request.getParameter("radio");
        String select = "";
        if (radio.equals("year")) {
            select = "year=" + request.getParameter("yearNumber");
        } else if (radio.equals("month")) {
            select = "month=" + request.getParameter("monthNumber");
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/SeniorManagement/AnalyticSystem/IncomeGenerated.jsp?" + select);
        rd.forward(request, response);
    }
}
