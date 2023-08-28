/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.servlet;

import ict.db.AccountDB;
import ict.db.RoleDB;
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
@WebServlet(name = "RoleController", urlPatterns = {"/SeniorManagement/RoleManagement/Role"})
public class RoleController extends HttpServlet {

    private RoleDB roleDB;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        roleDB = new RoleDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("AddRole".equals(action)) {
            addRole(request, response);
        } else if ("DeleteRole".equals(action)) {
            deleteRole(request, response);
        } else if ("DeleteAccount".equals(action)) {
            //deleteAccount(request, response);
        }
    }

    public void addRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String msg;
        if (roleDB.insertRole(name)) {
            msg = "Add Role Success";
        } else {
            msg = "Add Role UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/SeniorManagement/RoleManagement/AddRole.jsp?message=" + msg);
        rd.forward(request, response);
    }

    public void deleteRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String msg;
        if (roleDB.deleteRole(id)) {
            msg = "Delete Role Success";
        } else {
            msg = "Delete Role UnSuccess";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/SeniorManagement/RoleManagement/DeleteRole.jsp?message=" + msg);
        rd.forward(request, response);
    }
}
