package com.batchmates.android.sqllightproject;

import android.content.Context;

/**
 * Created by Android on 6/26/2017.
 */

public class Person {

    String name;
    String phone;
    String birthday;
    byte[] picture;


    //,byte[] picture

    public Person(String name, String phone, String birthday, byte[] picture) {
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.picture=picture;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public byte[] getPicture() {
        return picture;
    }
}
