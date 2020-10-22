package com.sd.coronaviewer.Models;

public class CoronaData {

    public double latitude,longitude;
    public String cont;

    public CoronaData() {
    }

    public CoronaData(double latitude, double longitude, String cont) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.cont = cont;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
}
