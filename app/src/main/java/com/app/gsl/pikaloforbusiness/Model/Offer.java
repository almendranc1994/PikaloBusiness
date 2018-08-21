package com.app.gsl.pikaloforbusiness.Model;

import java.io.Serializable;

public class Offer implements Serializable {

    private String name;
    private String description;
    private String realPrice;
    private String offerPrice;
    private String startDate;
    private String finishDate;
    private String stock;
    private String businessID;
    private String imageURL;

    public Offer(){

    }

    public Offer(String name, String description, String realPrice, String offerPrice, String startDate, String finishDate, String stock, String businessID, String imageURL){
        setName(name);
        setDescription(description);
        setRealPrice(realPrice);
        setOfferPrice(offerPrice);
        setStartDate(startDate);
        setFinishDate(finishDate);
        setStock(stock);
        setBusinessID(businessID);
        setImageURL(imageURL);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getBusinessID() {
        return businessID;
    }

    public void setBusinessID(String businessID) {
        this.businessID = businessID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
