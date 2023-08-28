/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.RoleBean;
import ict.bean.User;
import ict.bean.User1;
import ict.db.RoleDB;
import ict.db.UserDB1;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kikit
 */
public class RoleTag extends SimpleTagSupport {

    private String tagType;
    private RoleDB roleDB;
    private UserDB1 userDB;

    public void setTagType(String type) {
        this.tagType = type;
    }

    public RoleTag() {
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
        roleDB = new RoleDB(dbUrl, dbUser, dbPassword);
        userDB = new UserDB1(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            if ("show1".equalsIgnoreCase(tagType)) {
                showRolesDataByOption();
            } else if ("table".equalsIgnoreCase(tagType)) {
                showRolesDataByTable();
            } else if ("show".equalsIgnoreCase(tagType)) {
                showUsersData();
            } else {
                out.println("No such type");
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showRolesDataByOption() {
        ArrayList<RoleBean> roles = roleDB.queryRoles();

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            for (RoleBean role : roles) {
                out.print("<option value='" + role.getId() + "' >");
                out.print(role.getName());
                out.print("</option>");
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showRolesDataByTable() {
        ArrayList<RoleBean> roles = roleDB.queryRoles();

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            for (RoleBean role : roles) {
                out.print("<tr>");
                out.print("<th>" + role.getId() + "</th>");
                out.print("<td>" + role.getName() + "</td>");
                out.print("</tr>");
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showUsersData() {
        ArrayList<User1> users = userDB.queryUserList();

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 1;
            for (User1 user : users) {
                out.print("<tr>");
                out.print("<th>" + i + "</th>");
                out.print("<td>" + user.getName() + "</td>");
                out.print("<td>" + user.getEmail() + "</td>");
                out.print("<td>" + user.getRole() + "</td>");
                out.print("</tr>");
                i++;
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }
}
