/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.GuestListBean;
import ict.bean.GuestListDetailBean;
import ict.db.GuestListDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kikit
 */
public class GuestListTag extends SimpleTagSupport {

    private String tagType;
    private GuestListDB guestListDB;
    private int memberId;
    private int guestListIndex;

    public void setTagType(String type) {
        this.tagType = type;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setGuestListIndex(int guestListIndex) {
        this.guestListIndex = guestListIndex;
    }

    public GuestListTag() {
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
        guestListDB = new GuestListDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            if ("ShowNameByOption".equalsIgnoreCase(tagType)) {
                showGuestListNameByOption();
            } else if ("ShowNameByTable".equalsIgnoreCase(tagType)) {
                showGuestListNameByTable();
            } else if ("ShowDetailByTable".equalsIgnoreCase(tagType)) {
                showGuestListDetailByTable();
            } else {
                out.println("No such type");
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showGuestListNameByOption() {
        ArrayList<GuestListBean> guestLists = guestListDB.queryGuestList(memberId);

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            for (GuestListBean guestList : guestLists) {
                out.print("<option value='" + guestList.getId() + "'>");
                out.print(guestList.getName());
                out.print("</option>");
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showGuestListNameByTable() {
        ArrayList<GuestListBean> guestLists = guestListDB.queryGuestList(memberId);

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 1;
            for (GuestListBean guestList : guestLists) {
                out.print("<tr onclick=\"location.href='GuestListDetail.jsp?id=" + i + "';\" style=\"cursor:pointer; \">");
                out.print("<th>");
                out.print(i);
                out.print("</th>");
                out.print("<td>");
                out.print(guestList.getName());
                out.print("</td>");
                out.print("</tr>");
                i++;
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showGuestListDetailByTable() {
        ArrayList<GuestListBean> guestLists = guestListDB.queryGuestList(memberId);
        ArrayList<GuestListDetailBean> guestListDetails = guestLists.get(guestListIndex - 1).getGuestListDetails();
        System.out.println(guestListIndex);
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 1;
            if (guestListDetails != null) {
                for (GuestListDetailBean guestListDetail : guestListDetails) {
                    out.print("<tr>");
                    out.print("<th>");
                    out.print(i);
                    out.print("</th>");
                    out.print("<td>");
                    out.print(guestListDetail.getGuest_name());
                    out.print("</td>");
                    out.print("<td>");
                    out.print(guestListDetail.getGuest_email());
                    out.print("</td>");
                    out.print("</tr>");
                    i++;
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }
}
