package com.driver.driverbooking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestStartTripOTPResponse {
    @SerializedName("requestStartTripOTP")
    @Expose
    private Integer requestStartTripOTP;


    public Integer getRequestStartTripOTP() {
        return requestStartTripOTP;
    }

    public void setRequestStartTripOTP(Integer requestStartTripOTP) {
        this.requestStartTripOTP = requestStartTripOTP;
    }
}
