package com.coffeejjim.developers.data;

public class Proposal implements java.io.Serializable {
    private static final long serialVersionUID = 3536168157491843015L;
    private int parking;
    private int wifi;
    private double distance;
    private String imageUrl;
    private String cafeAddress;
    private int days;
    private String cafeName;
    private int socket;
    private int proposalId;
    private int cafeId;
    private int bidPrice;

    public int getParking() {
        return this.parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    public int getWifi() {
        return this.wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCafeAddress() {
        return this.cafeAddress;
    }

    public void setCafeAddress(String cafeAddress) {
        this.cafeAddress = cafeAddress;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getCafeName() {
        return this.cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public int getSocket() {
        return this.socket;
    }

    public void setSocket(int socket) {
        this.socket = socket;
    }

    public int getProposalId() {
        return this.proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public int getCafeId() {
        return this.cafeId;
    }

    public void setCafeId(int cafeId) {
        this.cafeId = cafeId;
    }

    public int getBidPrice() {
        return this.bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }
}
