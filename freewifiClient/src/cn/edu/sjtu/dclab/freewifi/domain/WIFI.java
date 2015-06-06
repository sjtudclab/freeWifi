package cn.edu.sjtu.dclab.freewifi.domain;

/**
 * Created by Yang on 2015/6/6.
 */

import java.io.Serializable;

public class WIFI implements Serializable {
    private long id;
    private String ssid;
    private String password;
    private Merchant merchant;
    private double longitude;
    private double latitude;

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public WIFI(String ssid, String password, Merchant merchant,
                double longitude, double latitude) {
        super();
        this.ssid = ssid;
        this.password = password;
        this.merchant = merchant;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public WIFI() {
        super();
    }

}