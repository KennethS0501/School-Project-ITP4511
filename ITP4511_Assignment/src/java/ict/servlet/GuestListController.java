/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.db.GuestListDB;
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
@WebServlet(name = "GuestListController", urlPatterns = {"/Member/GuestListFunction/GuestList"})
public class GuestListController extends HttpServlet {

    private GuestListDB guestListDB;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        guestListDB = new GuestListDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("AddGuestList".equals(action)) {
            addGuestList(request, response);
        } else if ("DeleteGuestList".equals(action)) {
            deleteGuestList(request, response);
        } else if ("EditGuestList".equals(action)) {
            editGuestList(request, response);
        }
    }

    public void addGuestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        String msg;
        if (guestListDB.insertGuestList(name, memberId)) {
            msg = "Add Guest List Success";
        } else {
            msg = "Add Guest List UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/Member/GuestListFunction/CreateGuestList.jsp?message=" + msg);
        rd.forward(request, response);
    }

    public void deleteGuestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        String msg;
        if (guestListDB.deleteGuestList(id, memberId)) {
            msg = "Delete Guest List Success";
        } else {
            msg = "Delete Guest List UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/Member/GuestListFunction/DeleteGuestList.jsp?message=" + msg);
        rd.forward(request, response);
    }

    public void editGuestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        String name = request.getParameter("name");
        String msg;
        if (guestListDB.editGuestList(id, name, memberId)) {
            msg = "Edit Guest List Success";
        } else {
            msg = "Edit Guest List UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/Member/GuestListFunction/EditGuestList.jsp?message=" + msg);
        rd.forward(request, response);
    }

    
}
