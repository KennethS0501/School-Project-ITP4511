/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.User;
import ict.db.UserDB;
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
    private UserDB userDB;

    public void setTagType(String type) {
        this.tagType = type;
    }

    public RoleTag() {
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
        userDB = new UserDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            if ("show".equalsIgnoreCase(tagType)) {
                showUsersData();
            } else if ("list".equalsIgnoreCase(tagType)) {

            } else {
                out.println("No such type");
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showUsersData() {
        ArrayList<User> users = userDB.queryUserList();

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();


            int i = 1;
            for (User user : users) {
                out.print("<tr>");
                out.print("<th>" + i + "</th>");
                out.print("<td>" + user.getName() + "</td>");
                out.print("<td>" + user.getEmail() + "</td>");
                out.print("<td>" + user.getRole()+ "</td>");
                out.print("</tr>");
                i++;
              }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }
}
