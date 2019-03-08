package com.driver.driverbooking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReachedPickupPointResponse {
    @SerializedName("arrivedPickupPoint")
    @Expose
    private Boolean arrivedPickupPoint;

    public Boolean getArrivedPickupPoint() {
        return arrivedPickupPoint;
    }

    public void setArrivedPickupPoint(Boolean arrivedPickupPoint) {
        this.arrivedPickupPoint = arrivedPickupPoint;
    }
}
