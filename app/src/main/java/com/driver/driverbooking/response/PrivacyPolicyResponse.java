package com.driver.driverbooking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrivacyPolicyResponse {

    @SerializedName("driverPrivacyPolicy")
    @Expose
    private String driverPrivacyPolicy;

    public String getDriverPrivacyPolicy() {
        return driverPrivacyPolicy;
    }

    public void setDriverPrivacyPolicy(String driverPrivacyPolicy) {
        this.driverPrivacyPolicy = driverPrivacyPolicy;
    }
}
