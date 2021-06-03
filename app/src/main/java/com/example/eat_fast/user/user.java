package com.example.eat_fast.user;

import com.example.eat_fast.date.DateTime;

public class user {
    private String id;
    private int type;
    private String tai_khoan;
    private String mat_khau;
    private String email;
    private String phone;
    private int ttkh;
    private int ttdg;

    public user() {
    }

    public user(String id, int type, String tai_khoan, String mat_khau, String email, String phone, int ttkh, int ttdg) {
        this.id = id;
        this.type = type;
        this.tai_khoan = tai_khoan;
        this.mat_khau = mat_khau;
        this.email = email;
        this.phone = phone;
        this.ttkh = ttkh;
        this.ttdg = ttdg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTai_khoan() {
        return tai_khoan;
    }

    public void setTai_khoan(String tai_khoan) {
        this.tai_khoan = tai_khoan;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTtkh() {
        return ttkh;
    }

    public void setTtkh(int ttkh) {
        this.ttkh = ttkh;
    }

    public int getTtdg() {
        return ttdg;
    }

    public void setTtdg(int ttdg) {
        this.ttdg = ttdg;
    }
}
