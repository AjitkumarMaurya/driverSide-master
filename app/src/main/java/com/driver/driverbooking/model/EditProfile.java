package com.driver.driverbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditProfile {

    @SerializedName("driver_id")
    @Expose
    private Integer driversUserId;

    @SerializedName("firstname")
    @Expose
    private String firstName;

    @SerializedName("lastname")
    @Expose
    private String lastName;



    @SerializedName("email")
    @Expose
    private String email;


    @SerializedName("permenant_address")
    @Expose
    private String permenantAddress;


    @SerializedName("resident_address")
    @Expose
    private String residentAddress;


    public Integer getDriversUserId() {
        return driversUserId;
    }

    public void setDriversUserId(Integer driversUserId) {
        this.driversUserId = driversUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermenantAddress() {
        return permenantAddress;
    }

    public void setPermenantAddress(String permenantAddress) {
        this.permenantAddress = permenantAddress;
    }

    public String getResidentAddress() {
        return residentAddress;
    }

    public void setResidentAddress(String residentAddress) {
        this.residentAddress = residentAddress;
    }
}
