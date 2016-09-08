package com.coffeejjim.developers.data;

import java.util.List;

public class CafeInfo implements java.io.Serializable {
    private static final long serialVersionUID = -7214170776954261316L;
    Cafe cafeInfo;
    List<CafeImage> images;
    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Cafe getCafeInfo() {
        return cafeInfo;
    }

    public void setCafeInfo(Cafe cafeInfo) {
        this.cafeInfo = cafeInfo;
    }

    public List<CafeImage> getImages() {
        return images;
    }

    public void setImages(List<CafeImage> images) {
        this.images = images;
    }
}
