package com.coffeejjim.developers.data;

public class Estimate implements java.io.Serializable {
    private static final long serialVersionUID = -401744136212757770L;
    private String reservationDate;
    private String reservationTime;
    private String cafeName;
    private String address;
    private String nickname;
    private int people;
    private int bidPrice;
    private int auctionTime;
    private String deadlineTime;
    private int wifi;
    private int parking;
    private int days;
    private int socket;
    private boolean isReserved;
    private int proposalState;
    private int estimateId;
    private int proposalId;
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public int getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(int estimateId) {
        this.estimateId = estimateId;
    }

    public int getProposalState() {
        return proposalState;
    }

    public void setProposalState(int proposalState) {
        this.proposalState = proposalState;
    }

    public int getAuctionTime() {
        return auctionTime;
    }

    public void setAuctionTime(int auctionTime) {
        this.auctionTime = auctionTime;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getParking() {
        return parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getSocket() {
        return socket;
    }

    public void setSocket(int socket) {
        this.socket = socket;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

}
