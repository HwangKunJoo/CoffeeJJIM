package com.coffeejjim.developers.data;

public class Customer implements java.io.Serializable {
    private static final long serialVersionUID = -1099703235608839862L;
    private int parking;
    private int bookmark;
    private int visitTime;
    private int wifi;
    private String reservationTime;
    private int customerId;
    private String nickname;
    private int days;
    private int socket;
    private int people;
    private int bidPrice;

    public int getParking() {
        return this.parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    public int getBookmark() {
        return this.bookmark;
    }

    public void setBookmark(int bookmark) {
        this.bookmark = bookmark;
    }

    public int getVisitTime() {
        return this.visitTime;
    }

    public void setVisitTime(int visitTime) {
        this.visitTime = visitTime;
    }

    public int getWifi() {
        return this.wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public String getReservationTime() {
        return this.reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getSocket() {
        return this.socket;
    }

    public void setSocket(int socket) {
        this.socket = socket;
    }

    public int getPeople() {
        return this.people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getBidPrice() {
        return this.bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }
}
