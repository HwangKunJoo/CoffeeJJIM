package com.coffeejjim.developers.data;

import java.util.Date;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class Estimate {
    private String reservationTime;
    private String cafeName;
    private String address;
    private boolean isReserved;

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
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
