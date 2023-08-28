/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import ict.db.UserDB;

/**
 *
 * @author kikit
 */
public class ReminderBean {

    private int id;
    private int id_member;
    private int id_staff;
    private String type;
    private int id_booking_record ;
    private String datetime;

    String dbUser = "root";
    String dbPassword = "";
    String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
    private UserDB userDB = new UserDB(dbUrl, dbUser, dbPassword);
    
    public int getId() {
        return id;
    }

    public String getStaffName(){
        return userDB.queryStaffNameById(id_staff);
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

    public int getId_staff() {
        return id_staff;
    }

    public void setId_staff(int id_staff) {
        this.id_staff = id_staff;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_booking_record() {
        return id_booking_record;
    }

    public void setId_booking_record(int id_booking_record) {
        this.id_booking_record = id_booking_record;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

}
