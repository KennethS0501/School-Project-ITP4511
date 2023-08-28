/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.db.CommentDB;
import java.io.IOException;
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
@WebServlet(name = "CommentController", urlPatterns = {"/Member/Comment"})
public class CommentController extends HttpServlet {

    private CommentDB commentDB;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        commentDB = new CommentDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("InsertComment".equals(action)) {
            insertComment(request, response);
        } else if ("logout".equals(action)) {
            //doLogout(request, response);
        } else {

        }
    }

    public void insertComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int confirmBookingRecordIndex = Integer.parseInt(request.getParameter("confirmBookingRecordIndex"));
        int memberId = Integer.parseInt(request.getParameter("confirmBookingRecordIndex"));
        String comment = request.getParameter("comment");
        String msg;
        if (commentDB.insertComment(confirmBookingRecordIndex, memberId, comment)) {
            msg = "Submit comment success";
        } else {
            msg = "Submit comment unsuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/Member/Comment.jsp?message=" + msg);
        rd.forward(request, response);
    }
}
