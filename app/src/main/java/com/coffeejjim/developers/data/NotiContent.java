package com.coffeejjim.developers.data;

public class NotiContent implements java.io.Serializable {
    private static final long serialVersionUID = -7393982038145017634L;
    private String contentDesc;
    private int contentImageUrl;

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public int getContentImageUrl() {
        return contentImageUrl;
    }

    public void setContentImageUrl(int contentImageUrl) {
        this.contentImageUrl = contentImageUrl;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public NotiContent(String contentDesc) {

        this.contentDesc = contentDesc;
    }
}
