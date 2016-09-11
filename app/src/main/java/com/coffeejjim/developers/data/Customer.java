package com.coffeejjim.developers.data;

public class Customer implements java.io.Serializable {
    private static final long serialVersionUID = -1099703235608839862L;
    private String nickName;
    private int visitCount;
    private boolean isLiked;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
