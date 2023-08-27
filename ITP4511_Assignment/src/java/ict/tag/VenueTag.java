/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.VenueBean;
import ict.db.VenueDB;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author alvin
 */
public class VenueTag extends SimpleTagSupport {

    private String tagType;
    private VenueDB VDB;

    public VenueTag() {
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
        VDB = new VenueDB(dbUrl, dbUser, dbPassword);
    }

    public void setTagType(String type) {
        this.tagType = type;
    }

    @Override
    public void doTag() {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        if ("showVenue".equalsIgnoreCase(tagType)) {
            showVenue();
        } else if ("showVenueWithDelete".equalsIgnoreCase(tagType)) {
            showVenueWithDelete();
        }
    }

    public void showVenue() {
        ArrayList<VenueBean> venues = VDB.queryVenueList();
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 1;
            for (VenueBean v : venues) {
                out.print("<tr scope='row'>");
                out.print("<td>" + v.getId() + "</td>");
                out.print("<td>" + v.getName() + "</td>");
                out.print("<td>" + v.getType() + "</td>");
                out.print("<td>" + v.getCapacity() + "</td>");
                out.print("<td>" + v.getLocation() + "</td>");

                out.print("<td>" + v.getDesciption() + "</td>");
                out.print("<td>" + v.getPerson_in_charge() + "</td>");
                out.print("<td>" + v.getBooking_fee() + "</td>");
                out.print("<td>" + v.getStatus() + "</td>");

                out.print("<td><img src='" + v.getImage_path() + "' style='width:50px;height:50px;'/></td>");
                out.print("</tr>");
                i++;
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showVenueWithDelete() {
        ArrayList<VenueBean> venues = VDB.queryVenueList();
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 1;
            for (VenueBean v : venues) {
                out.print("<tr scope='row'>");
                out.print("<td>" + v.getId() + "</td>");
                out.print("<td>" + v.getName() + "</td>");
                out.print("<td>" + v.getType() + "</td>");
                out.print("<td>" + v.getCapacity() + "</td>");
                out.print("<td>" + v.getLocation() + "</td>");

                out.print("<td>" + v.getDesciption() + "</td>");
                out.print("<td>" + v.getPerson_in_charge() + "</td>");
                out.print("<td>" + v.getBooking_fee() + "</td>");
                out.print("<td>" + v.getStatus() + "</td>");

                out.print("<td><img src='" + v.getImage_path() + "' style='width:50px;height:50px;'/></td>");
                out.print("<td>"
                        + "<form method='post' action='EditVenue' enctype='multipart/form-data'>"
                        + "<input type='hidden' name='action' value='DeleteVenue' />"
                        + "<button class='btn btn-primary' type='submit' style='margin-top: 10px;' name='Id'  value='" + v.getId() + "'>Delete</button>"
                        + "</form>"
                        + "</td>");
                out.print("</tr>");
                i++;
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }
}
