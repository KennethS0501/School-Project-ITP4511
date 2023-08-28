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
@WebServlet(name = "AccountController", urlPatterns = {"/SeniorManagement/AccountManagement/Account"})
public class AccountController extends HttpServlet {

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
        if ("CreateAccount".equals(action)) {
            createAccount(request, response);
        } else if ("EditAccount".equals(action)) {
            editAccount(request, response);
        } else if ("DeleteAccount".equals(action)) {
            deleteAccount(request, response);
        }
    }

    public void createAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        String msg;
        int role = Integer.parseInt(request.getParameter("role"));
        if (accountDB.insertAccount(name, email, pwd, role)) {
            msg = "Create Account Success";
        } else {
            msg = "Create Account UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/SeniorManagement/AccountManagement/CreateAccount.jsp?message=" + msg);
        rd.forward(request, response);
    }

    public void editAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int role = Integer.parseInt(request.getParameter("role"));
        String msg;
        if (accountDB.editAccount(id, name, email, role)) {
            msg = "Edit Account Success";
        } else {
            msg = "Edit Account UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/SeniorManagement/AccountManagement/EditAccount.jsp?message=" + msg);
        rd.forward(request, response);
    }

    public void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        String msg;
        if (accountDB.deleteAccount(id)) {
            msg = "Delete Account Success";
        } else {
            msg = "Delete Account UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/SeniorManagement/AccountManagement/DeleteAccount.jsp?message=" + msg);
        rd.forward(request, response);
    }
}
