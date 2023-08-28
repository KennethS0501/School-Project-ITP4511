/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.bean.GuestListBean;
import ict.db.GuestListDB;
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
@WebServlet(name = "GuestListDetailController", urlPatterns = {"/Member/GuestListFunction/GuestListDetail"})
public class GuestListDetailController extends HttpServlet {

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
        if ("AddGuest".equals(action)) {
            addGuest(request, response);
        } else if ("DeleteGuest".equals(action)) {
            deleteGuest(request, response);
        }
    }

    public void addGuest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        int guestListId = getGuestListId(Integer.parseInt(request.getParameter("guestListIndex")), memberId);
        String msg;
        if (guestListDB.insertGuest(name, email, memberId, guestListId)) {
            msg = "Add Guest List Success";
        } else {
            msg = "Add Guest List UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/Member/GuestListFunction/GuestListDetail.jsp?id=" + Integer.parseInt(request.getParameter("guestListIndex")) + "&message=" + msg);
        rd.forward(request, response);
    }

    public void deleteGuest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        ArrayList<GuestListBean> guestLists = guestListDB.queryGuestList(memberId);
        int guestListId = getGuestListId(Integer.parseInt(request.getParameter("guestListIndex")), memberId);
        System.out.println("GuetsListId " + guestListId);
        int guestId = getGuestListDetailId(Integer.parseInt(request.getParameter("guestListIndex")), Integer.parseInt(request.getParameter("id")), memberId);
        System.out.println(guestId);
        String msg;
        if (guestListDB.deleteGuest(guestId, memberId, guestListId)) {
            msg = "Delete Guest List Success";
        } else {
            msg = "Delete Guest List UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/Member/GuestListFunction/GuestListDetail.jsp?id=" + Integer.parseInt(request.getParameter("guestListIndex")) + "&message=" + msg);
        rd.forward(request, response);
    }

    public int getGuestListId(int guestListIndex, int memberId) {
        return guestListDB.queryGuestList(memberId).get(guestListIndex - 1).getId();
    }

    public int getGuestListDetailId(int guestListIndex, int guestId, int memberId) {
        return guestListDB.queryGuestList(memberId).get(guestListIndex - 1).getGuestListDetails().get(guestId - 1).getId();
    }
}
