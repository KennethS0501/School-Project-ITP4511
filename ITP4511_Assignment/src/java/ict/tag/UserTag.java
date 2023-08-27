/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.MemberBean;
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
public class UserTag extends SimpleTagSupport {

    private String tagType;
    private UserDB userDB;

    public void setTagType(String type) {
        this.tagType = type;
    }

    public UserTag() {
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
            } else if ("showMember".equalsIgnoreCase(tagType)) {
                ShowMemberData();
            } else if ("StaffShow".equalsIgnoreCase(tagType)) {

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
                out.print("<td>" + user.getRole() + "</td>");
                out.print("</tr>");
                i++;
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    //pan
    public void ShowMemberData() {
        ArrayList<MemberBean> members = userDB.queryMemberList();

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 1;
            for (MemberBean member : members) {
                out.print("<tr>");
                out.print("<th>" + member.getId() + "</th>");
                out.print("<td>" + member.getName() + "</td>");
                out.print("<td>" + member.getEmail() + "</td>");
                if (member.getBlock() == 0) {
                    out.print("<td>UnBlock</td>");
                } else if (member.getBlock() == 1) {
                    out.print("<td>Blocked</td>");
                }

                out.print("</tr>");
                i++;
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }
}
