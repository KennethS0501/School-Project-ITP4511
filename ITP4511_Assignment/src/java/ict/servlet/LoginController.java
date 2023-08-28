/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.bean.MemberBean;
import ict.bean.StaffBean;
import ict.bean.User;
import ict.db.UserDB;
import ict.bean.MemberBean1;
import ict.bean.StaffBean1;
import ict.bean.User1;
import ict.db.UserDB1;
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
@WebServlet(name = "LoginController", urlPatterns = {"/Login"})
public class LoginController extends HttpServlet {

    private UserDB userDB;
    private UserDB1 userDB1;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        userDB = new UserDB(dbUrl, dbUser, dbPassword);
        userDB1 = new UserDB1(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (!isAuthenticated(request)
                && !("authenticate".equals(action))) {
            doLogin(request, response);
            return;
        }
        if ("authenticate".equals(action)) {
            doAuthenticate(request, response);
        } else if ("logout".equals(action)) {
            //doLogout(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void doAuthenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputEmail");
        String pwd = request.getParameter("inputPassword");
        String type = userDB.getUserType(email);
        String targetURL = "index.jsp";

        if (userDB.isValidUser(email, pwd, type)) {
            HttpSession session = request.getSession(true);

            if (type.equals("member")) {
                User member = userDB.getMemberDetail(email);
                session.setAttribute("userInfo", member);
                targetURL = "index.jsp";
            } else if (type.equals("staff")) {
                StaffBean staff = userDB.getStaffDetail(email);
                StaffBean1 staff1 = userDB1.getStaffDetail(email);
                session.setAttribute("userInfo", staff);
                if ("Seniro_Management".equals(staff.getRole())) {

                    targetURL = "SeniorManagement/Account.jsp";
                } else {
                    System.out.println("asdasdsad");
                    //targetURL = "Senior_Management/Account.jsp";
                    session.setAttribute("userInfo", staff1);
                    targetURL = "Staff/Account.jsp";
                }
            }
        } else {
            targetURL = "loginError.jsp";
        }
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String targetURL = "login.jsp";
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("userInfo");
            session.invalidate();
        }
        doLogin(request, response);
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        boolean result = false;
        HttpSession session = request.getSession();

        if (session.getAttribute("userInfo") != null) {
            result = true;
        }

        return result;
    }

}
