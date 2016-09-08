package com.coffeejjim.developers.data;

public class Cafe implements java.io.Serializable {
    private static final long serialVersionUID = -1373791085815832808L;
    private int parking;
    private int wifi;
    private String cafeAddress;
    private String imageUrl;
    private int days;
    private String cafeName;
    private int socket;
    private int cafeId;
    private double distance;
    private double latitude;
    private double longitude;
    private String cafePhoneNumber;
    private String businessHour;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCafePhoneNumber() {
        return cafePhoneNumber;
    }

    public void setCafePhoneNumber(String cafePhoneNumber) {
        this.cafePhoneNumber = cafePhoneNumber;
    }

    public String getBusinessHour() {
        return businessHour;
    }

    public void setBusinessHour(String businessHour) {
        this.businessHour = businessHour;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

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

    public String getCafeAddress() {
        return this.cafeAddress;
    }

    public void setCafeAddress(String cafeAddress) {
        this.cafeAddress = cafeAddress;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public int getCafeId() {
        return this.cafeId;
    }

    public void setCafeId(int cafeId) {
        this.cafeId = cafeId;
    }
}
