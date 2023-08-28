/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.util.ArrayList;

/**
 *
 * @author kikit
 */
public class GuestListBean {

    private int id;
    private String name;
    private int id_member;
    private ArrayList<GuestListDetailBean> guestListDetails;

    public ArrayList<GuestListDetailBean> getGuestListDetails() {
        return guestListDetails;
    }

    public void setGuestListDetails(ArrayList<GuestListDetailBean> guestListDetails) {
        this.guestListDetails = guestListDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_member() {
        return id_member;
    }

    public void setId_member(int id_member) {
        this.id_member = id_member;
    }
}
