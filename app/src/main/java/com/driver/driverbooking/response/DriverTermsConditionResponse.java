package com.driver.driverbooking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverTermsConditionResponse {
    @SerializedName("driverTermsCondition")
    @Expose
    private String driverTermsCondition;

    public String getDriverTermsCondition() {
        return driverTermsCondition;
    }

    public void setDriverTermsCondition(String driverTermsCondition) {
        this.driverTermsCondition = driverTermsCondition;
    }
}
