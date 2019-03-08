package com.driver.driverbooking.response;

import com.driver.driverbooking.model.DriverProfile;
import com.driver.driverbooking.model.FuturebookingModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FutureBookingResponse {

    @SerializedName("driverProfile")
    @Expose
    private DriverProfile driverProfile;

    @SerializedName("trips")
    @Expose
    private ArrayList<FuturebookingModel> futurebookingModelArrayList = null;

    public DriverProfile getDriverProfile() {
        return driverProfile;
    }

    public void setDriverProfile(DriverProfile driverProfile) {
        this.driverProfile = driverProfile;
    }

    public ArrayList<FuturebookingModel> getFuturebookingModelArrayList() {
        return futurebookingModelArrayList;
    }

    public void setFuturebookingModelArrayList(ArrayList<FuturebookingModel> futurebookingModelArrayList) {
        this.futurebookingModelArrayList = futurebookingModelArrayList;
    }
}
