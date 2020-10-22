package com.sd.coronaviewer.Models;

public class CoronaCount {

    public String totalc,deaths,recov,ncond,ccond;

    public CoronaCount() {
    }

    public CoronaCount(String totalc, String deaths, String recov, String ncond, String ccond) {
        this.totalc = totalc;
        this.deaths = deaths;
        this.recov = recov;
        this.ncond = ncond;
        this.ccond = ccond;
    }


    public String getTotalc() {
        return totalc;
    }

    public void setTotalc(String totalc) {
        this.totalc = totalc;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecov() {
        return recov;
    }

    public void setRecov(String recov) {
        this.recov = recov;
    }

    public String getNcond() {
        return ncond;
    }

    public void setNcond(String ncond) {
        this.ncond = ncond;
    }

    public String getCcond() {
        return ccond;
    }

    public void setCcond(String ccond) {
        this.ccond = ccond;
    }
}
