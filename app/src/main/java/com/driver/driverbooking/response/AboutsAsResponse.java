package com.driver.driverbooking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutsAsResponse {
    @SerializedName("driverAboutUs")
    @Expose
    private String driverAboutUs;

    public String getDriverAboutUs() {
        return driverAboutUs;
    }

    public void setDriverAboutUs(String driverAboutUs) {
        this.driverAboutUs = driverAboutUs;
    }
}
