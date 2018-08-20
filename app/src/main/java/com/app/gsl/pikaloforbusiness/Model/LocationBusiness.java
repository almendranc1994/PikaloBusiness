package com.app.gsl.pikaloforbusiness.Model;

public class LocationBusiness {
    private String name;
    private String latitude;
    private String longitude;

    public LocationBusiness(){

    }

    public LocationBusiness(String Name, String latitude, String longitude){
        this.setName(name);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
