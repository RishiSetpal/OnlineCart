package com.rishistech.onlinecart;

import org.jetbrains.annotations.NotNull;

public class UserHelperClass {
    private String id;
    private String name;
    private String email;
    private String phoneno;
    private String photourl;
    private String password;
    private String money;

    public UserHelperClass() {
    }

    @NotNull
    public String toString() {
        return "\nName : " + name + "\n Email : " + email + "\n Money : " + money + "\n";
    }

    public UserHelperClass(String id, String name, String email, String phoneno , String photourl, String password, String money) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneno = phoneno;
        this.photourl = photourl;
        this.password = password;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
