package com.coffeejjim.developers.data;

import java.util.List;

public class CafeDetail implements java.io.Serializable {
    private static final long serialVersionUID = -7214170776954261316L;
    Cafe cafeInfo;
    List<CafeImage> images;

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
