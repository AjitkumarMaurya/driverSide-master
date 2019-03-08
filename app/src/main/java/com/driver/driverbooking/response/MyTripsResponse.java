package com.driver.driverbooking.response;

import com.driver.driverbooking.model.FuturebookingModel;
import com.driver.driverbooking.model.TripDetailsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyTripsResponse {

    @SerializedName("driverMyTrips")
    @Expose
    private ArrayList<TripDetailsModel> tripDetailsModels = null;

    public ArrayList<TripDetailsModel> getTripDetailsModels() {
        return tripDetailsModels;
    }

    public void setTripDetailsModels(ArrayList<TripDetailsModel> tripDetailsModels) {
        this.tripDetailsModels = tripDetailsModels;
    }

    @SerializedName("trips")
    @Expose
    private ArrayList<FuturebookingModel> futurebookingModelArrayList = null;


    public ArrayList<FuturebookingModel> getFuturebookingModelArrayList() {
        return futurebookingModelArrayList;
    }

    public void setFuturebookingModelArrayList(ArrayList<FuturebookingModel> futurebookingModelArrayList) {
        this.futurebookingModelArrayList = futurebookingModelArrayList;
    }
}
