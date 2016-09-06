package com.coffeejjim.developers.data;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class Options {
    boolean wifi;
    boolean days;
    boolean socket;
    boolean parking;

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isDays() {
        return days;
    }

    public void setDays(boolean days) {
        this.days = days;
    }

    public boolean isSocket() {
        return socket;
    }

    public void setSocket(boolean socket) {
        this.socket = socket;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }
}
