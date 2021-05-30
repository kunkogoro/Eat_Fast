package com.example.eat_fast.menuHome;

public class ItemProductPopular {
    private int image;
    private String title;
    private int price;
    private double rate;

    public ItemProductPopular() {
    }

    public ItemProductPopular(int image, String title, int price, double rate) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.rate = rate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
