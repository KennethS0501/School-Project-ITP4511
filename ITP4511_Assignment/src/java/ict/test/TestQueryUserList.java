/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.test;

import ict.bean.BookingRecordBean;
import ict.bean.RoleBean;
import ict.bean.User;
import ict.bean.VenueBean;
import ict.db.AccountDB;
import ict.db.BookingRecordDB;
import ict.db.RoleDB;
import ict.db.UserDB;
import ict.db.VenueDB;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kikit
 */
public class TestQueryUserList {

    public static void main(String[] args) {

        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
//        UserDB userDB = new UserDB(dbUrl, dbUser, dbPassword);
//        RoleDB roleDB = new RoleDB(dbUrl, dbUser, dbPassword);
//        AccountDB accountDB = new AccountDB(dbUrl, dbUser, dbPassword);
//        VenueDB venueDB = new VenueDB(dbUrl, dbUser, dbPassword);
//        VenueBean venue = venueDB.getVenueInformation(2);
//        Date date = new Date();
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(ft.format(date));
        BookingRecordDB db = new BookingRecordDB(dbUrl, dbUser, dbPassword);

        ArrayList<BookingRecordBean> bookingRecords = db.queryVenueIncomeByYear("2023");
        for (BookingRecordBean bookingRecord : bookingRecords) {
            System.out.println(bookingRecord.getPrice());
        }
    }
}
