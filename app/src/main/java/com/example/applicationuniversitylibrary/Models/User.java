package com.example.applicationuniversitylibrary.Models;

public class User {

    String userid, fn, sn, pw, em;

    public User() {

    }

    public User(String userId, String firstname, String surname, String email, String password){

        userid = userId;
        fn = firstname;
        sn = surname;
        em = email;
        pw = password;

    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getUserid() {
        return userid;
    }

    public String getFn() {
        return fn;
    }

    public String getSn() {
        return sn;
    }

    public String getPw() {
        return pw;
    }

    public String getEm() {
        return em;
    }
}

