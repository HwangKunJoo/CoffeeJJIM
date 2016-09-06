package com.coffeejjim.developers.data;

public class Event implements java.io.Serializable {
    private static final long serialVersionUID = 8443633990382329990L;
    private String imageUrl;
    private int cafeId;
    private String thumbnailUrl;

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

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
