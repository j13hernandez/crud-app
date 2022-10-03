package com.aquent.crudapp.shared;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Address {

    @NotNull
    @Size(min = 1, max = 50, message = "Street address is required with maximum length of 50")
    private String streetAddress;

    @NotNull
    @Size(min = 1, max = 50, message = "City is required with maximum length of 50")
    private String city;

    @NotNull
    @Size(min = 2, max = 2, message = "State is required with length 2")
    private String state;

    @NotNull
    @Size(min = 5, max = 5, message = "Zip code is required with length 5")
    private String zipCode;

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
  
    public String getStreetAddress() {
        return streetAddress;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }
}
