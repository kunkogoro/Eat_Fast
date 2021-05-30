package com.example.eat_fast.menuHome;

public class ItemCategory {
    private int image;
    private String text;

    public ItemCategory(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public ItemCategory() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
