/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import ict.db.GuestListDB;
import ict.db.NotiTemplateDB;
import ict.db.VenueDB;

/**
 *
 * @author kikit
 */
public class BookingRecordBean {

    private int id;
    private int id_member;
    private int id_venue;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    String dbUser = "root";
    String dbPassword = "";
    String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
    VenueDB venueDB = new VenueDB(dbUrl, dbUser, dbPassword);
    GuestListDB guestListDB = new GuestListDB(dbUrl, dbUser, dbPassword);
    NotiTemplateDB notiTemplateDB = new NotiTemplateDB(dbUrl, dbUser, dbPassword);

    public String getVenueName() {
        VenueBean venue = venueDB.getVenueInformation(id_venue);
        return venue.getName();
    }

    public String getGuestListName() {
        return guestListDB.getGuestListName(id_guest_list);
    }

    public String getNotiTemplateName() {
        return notiTemplateDB.getTemplateName(id_notifi_template);
    }

    private int id_guest_list;
    private int id_notifi_template;

    public int getId_guest_list() {
        return id_guest_list;
    }

    public void setId_guest_list(int id_guest_list) {
        this.id_guest_list = id_guest_list;
    }

    public int getId_notifi_template() {
        return id_notifi_template;
    }

    public void setId_notifi_template(int id_notifi_template) {
        this.id_notifi_template = id_notifi_template;
    }
    private String booking_date;
    private String start_time;
    private String end_time;
    private String order_date;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_member() {
        return id_member;
    }

    public void setId_member(int id_member) {
        this.id_member = id_member;
    }

    public int getId_venue() {
        return id_venue;
    }

    public void setId_venue(int id_venue) {
        this.id_venue = id_venue;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
