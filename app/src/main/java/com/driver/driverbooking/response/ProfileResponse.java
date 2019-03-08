package com.driver.driverbooking.response;

import com.driver.driverbooking.model.DriverProfile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    @SerializedName("driverProfile")
    @Expose
    private DriverProfile driverProfile;

    public DriverProfile getDriverProfile() {
        return driverProfile;
    }

    public void setDriverProfile(DriverProfile driverProfile) {
        this.driverProfile = driverProfile;
    }
}
