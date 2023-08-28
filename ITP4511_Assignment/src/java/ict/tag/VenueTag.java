/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.VenueBean;
import ict.bean.VenueBean1;
import ict.db.VenueDB;
import ict.db.VenueDB1;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kikit
 */
public class VenueTag extends SimpleTagSupport {

    private String tagType;
    private int venueId;
    private VenueDB venueDB;
    private VenueDB1 VDB;

    public void setTagType(String type) {
        this.tagType = type;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public VenueTag() {
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
        venueDB = new VenueDB(dbUrl, dbUser, dbPassword);
        VDB = new VenueDB1(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            VenueBean venue = venueDB.getVenueInformation(this.venueId);
            if ("id".equalsIgnoreCase(tagType)) {
                out.print(venue.getId());
            } else if ("name".equalsIgnoreCase(tagType)) {
                out.print(venue.getName());
            } else if ("type".equalsIgnoreCase(tagType)) {
                out.print(venue.getType());
            } else if ("capacity".equalsIgnoreCase(tagType)) {
                out.print(venue.getCapacity());
            } else if ("location".equalsIgnoreCase(tagType)) {
                out.print(venue.getLocation());
            } else if ("description".equalsIgnoreCase(tagType)) {
                out.print(venue.getDescription());
            } else if ("person_in_charge".equalsIgnoreCase(tagType)) {
                out.print(venue.getPerson_in_charge());
            } else if ("booking_fee".equalsIgnoreCase(tagType)) {
                out.print(venue.getBooking_fee());
            } else if ("showVenueByOption".equalsIgnoreCase(tagType)) {
                showVenueByOption();
            } else if ("showVenue".equalsIgnoreCase(tagType)) {
                showVenue();
            } else if ("showVenueWithDelete".equalsIgnoreCase(tagType)) {
                showVenueWithDelete();
            } else {
                out.println("No such type");
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showVenueByOption() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            ArrayList<VenueBean> venues = venueDB.queryVenue();

            for (VenueBean venue : venues) {
                out.print("<option value=\"" + venue.getId() + "\">");
                out.print(venue.getName());
                out.print("</option>");
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showVenue() {
        ArrayList<VenueBean1> venues = VDB.queryVenueList();
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 1;
            for (VenueBean1 v : venues) {
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
        ArrayList<VenueBean1> venues = VDB.queryVenueList();
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            int i = 1;
            for (VenueBean1 v : venues) {
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
