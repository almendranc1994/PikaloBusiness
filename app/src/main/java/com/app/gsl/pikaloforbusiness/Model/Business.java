package com.app.gsl.pikaloforbusiness.Model;

import java.io.Serializable;

public class Business implements Serializable {
    private String businessName;
    private String category;
    private String description;
    private String email;
    private String password;
    private String typeBusiness;
    private String idPlace;

    public Business(){

    }

    public Business(String businessName, String category, String description, String email, String password, String typeBusiness, String idPlace){
        this.setBusinessName(businessName);
        this.setCategory(category);
        this.setDescription(description);
        this.setEmail(email);
        this.setPassword(password);
        this.setTypeBusiness(typeBusiness);
        this.setIdPlace(idPlace);
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeBusiness() {
        return typeBusiness;
    }

    public void setTypeBusiness(String typeBusiness) {
        this.typeBusiness = typeBusiness;
    }

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }
}
