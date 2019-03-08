package com.driver.driverbooking.response;

import com.driver.driverbooking.model.DriverProfile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompleteTripResponce {

    @SerializedName("completeTrip")
    @Expose
    private boolean completeTrip;

    public boolean isCompleteTrip() {
        return completeTrip;
    }

    public void setCompleteTrip(boolean completeTrip) {
        this.completeTrip = completeTrip;
    }
}
