package com.example.eat_fast.beens;


import android.graphics.Bitmap;
import android.net.Uri;

import com.example.eat_fast.date.DateTime;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String loai;
    private String tai_khoan;
    private String email;
    private String phone;
    private int ttdg;
    private int ttkh;
    private String name;
    private String hinh;
    private DateTime time;
    private Bitmap bitmap;
    private Uri uri;

    public User(String id, String loai, String tai_khoan, String email, String phone, int ttdg, int ttkh, String name, String hinh, DateTime time) {
        this.id = id;
        this.loai = loai;
        this.tai_khoan = tai_khoan;
        this.email = email;
        this.phone = phone;
        this.ttdg = ttdg;
        this.ttkh = ttkh;
        this.name = name;
        this.hinh = hinh;
        this.time = time;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getTai_khoan() {
        return tai_khoan;
    }

    public void setTai_khoan(String tai_khoan) {
        this.tai_khoan = tai_khoan;
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

    public int getTtdg() {
        return ttdg;
    }

    public void setTtdg(int ttdg) {
        this.ttdg = ttdg;
    }

    public int getTtkh() {
        return ttkh;
    }

    public void setTtkh(int ttkh) {
        this.ttkh = ttkh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", loai='" + loai + '\'' +
                ", tai_khoan='" + tai_khoan + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", ttdg=" + ttdg +
                ", ttkh=" + ttkh +
                ", name='" + name + '\'' +
                ", hinh='" + hinh + '\'' +
                ", time=" + time +
                '}';
    }
}
