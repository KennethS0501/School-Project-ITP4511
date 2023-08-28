/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.NotiTemplateBean;
import ict.bean.ReminderBean;
import ict.db.NotiTemplateDB;
import ict.db.ReminderDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kikit
 */
public class ReminderTag extends SimpleTagSupport {

    private String tagType;
    private int memberId;
    private ReminderDB reminderDB;

    public void setTagType(String type) {
        this.tagType = type;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public ReminderTag() {
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
        reminderDB = new ReminderDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            if ("show".equalsIgnoreCase(tagType)) {
                showReminder();
            } else if ("list".equalsIgnoreCase(tagType)) {

            } else {
                out.println("No such type");
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showReminder() {
        ArrayList<ReminderBean> reminders = reminderDB.queryReminderById(memberId);

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            if (reminders != null) {
                for (ReminderBean reminder : reminders) {
                    out.print("<li class=\"list-group-item\" style=\"margin-bottom:6px;\">");
                    out.print("<div class=\"media\">");
                    out.print("<div class=\"media-body\">");
                    out.print("<div class=\"media\" style=\"overflow:visible;\">");
                    out.print("<div class=\"media-body\" style=\"overflow:visible;\">");
                    out.print("<div class=\"row\">");
                    out.print("<div class=\"col-md-12\">");
                    out.print("<p><a style=\" color: blue;\">" + reminder.getStaffName() + " (Staff):</a>&nbsp;");
                    switch (reminder.getType()) {
                        case "waitPayment":
                            out.print("Booking record ID: " + reminder.getId_booking_record() + " is wait payment!");
                            break;
                        case "confirmed":
                            out.print("Booking record ID: " + reminder.getId_booking_record() + " is confirmed!");
                            break;
                        case "decline":
                            out.print("Booking record ID: " + reminder.getId_booking_record() + " is decline!");
                            break;
                        case "check-in":
                            out.print("Booking record ID: " + reminder.getId_booking_record() + " check-in!");
                            break;
                        case "complete":
                            out.print("Booking record ID: " + reminder.getId_booking_record() + " is complete!");
                            break;
                        case "absent":
                            out.print("Booking record ID: " + reminder.getId_booking_record() + " is absent!");
                            break;
                    }
                    out.print("<br>");
                    out.print("<small class=\"text-muted\">" + reminder.getDatetime() + "</small></p>");
                    out.print("</div></div></div></div></div></div></li>");
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }
}

//                                    <li class="list-group-item" style="margin-bottom:6px;">
//                                        <div class="media">
//                                            <div class="media-body">
//                                                <div class="media" style="overflow:visible;">
//                                                    <div class="media-body" style="overflow:visible;">
//                                                        <div class="row">
//                                                            <div class="col-md-12">
//                                                                <p><a href="#">Staff:</a>&nbsp;Approve your booking record ID 2<br>
//                                                                    <small class="text-muted">August 6, 2016 @ 10:35am </small></p>
//                                                            </div>
//                                                        </div>
//                                                    </div>
//                                                </div>
//                                            </div>
//                                        </div>
//                                    </li>
