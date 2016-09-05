package com.coffeejjim.developers.data;

public class CafeImage implements java.io.Serializable {
    private static final long serialVersionUID = 303039484497258858L;
    private String imageUrl;
    private int cafeId;

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCafeId() {
        return this.cafeId;
    }

    public void setCafeId(int cafeId) {
        this.cafeId = cafeId;
    }
}
