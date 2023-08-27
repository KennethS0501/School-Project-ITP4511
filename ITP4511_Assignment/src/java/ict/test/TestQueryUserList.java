/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.test;

import ict.bean.User;
import ict.db.UserDB;
import java.util.ArrayList;

/**
 *
 * @author kikit
 */
public class TestQueryUserList {

    public static void main(String[] args) {

        String dbUser = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/ITP4511_Assignment";
        UserDB userDB = new UserDB(dbUrl, dbUser, dbPassword);

        ArrayList<User> users = userDB.queryUserList();

        for (User user : users) {
            System.out.println(user.getEmail());
            System.out.println(user.getName());
            System.out.println(user.getRole());
        }
    }
}
