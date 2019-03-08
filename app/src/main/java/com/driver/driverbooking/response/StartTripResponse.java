package com.driver.driverbooking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartTripResponse {
    @SerializedName("startTrip")
    @Expose
    private Boolean startTrip;

    public Boolean getStartTrip() {
        return startTrip;
    }

    public void setStartTrip(Boolean startTrip) {
        this.startTrip = startTrip;
    }
}
