package com.coffeejjim.developers.data;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class Customer {
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
