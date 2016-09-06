package com.coffeejjim.developers.data;

import android.graphics.drawable.Drawable;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class Cafe {
    private String cafeName;
    private String address;
    private String price;
    private String distance;
    private Drawable photo;
    private Options options;
    private CafeImage cafeImage;
    private Drawable option;

    public Drawable getOption() {
        return option;
    }

    public void setOption(Drawable option) {
        this.option = option;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public CafeImage getCafeImage() {
        return cafeImage;
    }

    public void setCafeImage(CafeImage cafeImage) {
        this.cafeImage = cafeImage;
    }

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

}
