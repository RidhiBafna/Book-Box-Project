package com.example.ridzi.helpteens1;

/**
 * Created by Ridzi on 14-07-2016.
 */


/**
 * Created by hp pc on 7/11/2016.
 */
public class BookBoxModel {
    public int id;
    public String name;
    public String phone;
    public String email;
    public String password;
    public String city;
    public String address;
    public String gender;


    public BookBoxModel(int id, String name, String phone, String email, String password, String city, String address, String gender) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.city = city;
        this.address = address;
        this.gender = gender;
    }

    public BookBoxModel() {
    }
}

