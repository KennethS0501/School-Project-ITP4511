/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.NotiTemplateBean;
import ict.db.NotiTemplateDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kikit
 */
public class NotiTemplateTag extends SimpleTagSupport {

    private String tagType;
    private NotiTemplateDB notiTemplateDB;

    public void setTagType(String type) {
        this.tagType = type;
    }

    public NotiTemplateTag() {
        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
        notiTemplateDB = new NotiTemplateDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            if ("showByOption".equalsIgnoreCase(tagType)) {
                showNonTemplateByOption();
            } else if ("list".equalsIgnoreCase(tagType)) {

            } else {
                out.println("No such type");
            }

        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }

    public void showNonTemplateByOption() {
        ArrayList<NotiTemplateBean> notiTemplates = notiTemplateDB.queryNotiTamplate();

        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();

            for (NotiTemplateBean notiTemplate : notiTemplates) {
                out.print("<option value='"+ notiTemplate.getId() +"'>");
                out.print(notiTemplate.getName());
                out.print("</option>");
            }
        } catch (IOException ioe) {
            System.out.println("Error generation prime: " + ioe);
        }
    }
}
