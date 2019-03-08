package com.driver.driverbooking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUsResponse {

    @SerializedName("userContactUsNumber")
    @Expose
    private String userContactUsNumber;
    @SerializedName("userContactUsEmail")
    @Expose
    private String userContactUsEmail;
    @SerializedName("userContactUs")
    @Expose
    private String userContactUs;

    public String getUserContactUsNumber() {
        return userContactUsNumber;
    }

    public void setUserContactUsNumber(String userContactUsNumber) {
        this.userContactUsNumber = userContactUsNumber;
    }

    public String getUserContactUsEmail() {
        return userContactUsEmail;
    }

    public void setUserContactUsEmail(String userContactUsEmail) {
        this.userContactUsEmail = userContactUsEmail;
    }

    public String getUserContactUs() {
        return userContactUs;
    }

    public void setUserContactUs(String userContactUs) {
        this.userContactUs = userContactUs;
    }

    /*  @SerializedName("driverContactUsNumber")
    @Expose
    private String driverContactUsNumber;
    @SerializedName("driverContactUs")
    @Expose
    private String driverContactUs;
    @Expose

    private String userContactUsEmail;

    public String getUserContactUsEmail() {
        return userContactUsEmail;
    }

    public void setUserContactUsEmail(String userContactUsEmail) {
        this.userContactUsEmail = userContactUsEmail;
    }

    @SerializedName("userContactUsEmail")





    public String getDriverContactUsNumber() {
        return driverContactUsNumber;
    }

    public void setDriverContactUsNumber(String driverContactUsNumber) {
        this.driverContactUsNumber = driverContactUsNumber;
    }

    public String getDriverContactUs() {
        return driverContactUs;
    }

    public void setDriverContactUs(String driverContactUs) {
        this.driverContactUs = driverContactUs;
    }*/
}
