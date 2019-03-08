package com.driver.driverbooking.response;

import com.driver.driverbooking.model.DriverProfile;
import com.driver.driverbooking.model.TripsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TripsResponse {


    @SerializedName("driverProfile")
    @Expose
    private DriverProfile driverProfile;


    @SerializedName("trips")
    @Expose
    private ArrayList<TripsModel> tripsModels = null;

    @SerializedName("ongoingTrips")
    @Expose
    private List<OngoingTrip> ongoingTrips = null;


    public class OngoingTrip {

        @SerializedName("trip_id")
        @Expose
        private Integer tripId;
        @SerializedName("trip_unique_id")
        @Expose
        private String tripUniqueId;
        @SerializedName("users_id")
        @Expose
        private String usersId;
        @SerializedName("drivers_user_id")
        @Expose
        private String driversUserId;
        @SerializedName("states_id")
        @Expose
        private Object statesId;
        @SerializedName("trip_start_date")
        @Expose
        private String tripStartDate;
        @SerializedName("trip_start_time")
        @Expose
        private String tripStartTime;
        @SerializedName("trip_type_id")
        @Expose
        private String tripTypeId;
        @SerializedName("trip_ast_pickup_point_lat")
        @Expose
        private String tripAstPickupPointLat;
        @SerializedName("trip_ast_pickup_point_lang")
        @Expose
        private String tripAstPickupPointLang;
        @SerializedName("trip_ast_pickup_point_name")
        @Expose
        private String tripAstPickupPointName;
        @SerializedName("trip_ast_pickup_point_city_name")
        @Expose
        private String tripAstPickupPointCityName;
        @SerializedName("trip_ast_drop_point_lat")
        @Expose
        private String tripAstDropPointLat;
        @SerializedName("trip_ast_drop_point_lang")
        @Expose
        private String tripAstDropPointLang;
        @SerializedName("trip_ast_drop_point_name")
        @Expose
        private String tripAstDropPointName;
        @SerializedName("trip_ast_drop_point_city_name")
        @Expose
        private String tripAstDropPointCityName;
        @SerializedName("trip_ast_usage")
        @Expose
        private String tripAstUsage;
        @SerializedName("trip_pickup_point_lat")
        @Expose
        private Object tripPickupPointLat;
        @SerializedName("trip_pickup_point_lang")
        @Expose
        private Object tripPickupPointLang;
        @SerializedName("trip_pickup_point_name")
        @Expose
        private Object tripPickupPointName;
        @SerializedName("trip_pickup_point_city_name")
        @Expose
        private Object tripPickupPointCityName;
        @SerializedName("trip_drop_point_lat")
        @Expose
        private Object tripDropPointLat;
        @SerializedName("trip_drop_point_lang")
        @Expose
        private Object tripDropPointLang;
        @SerializedName("trip_drop_point_name")
        @Expose
        private Object tripDropPointName;
        @SerializedName("trip_drop_point_city_name")
        @Expose
        private Object tripDropPointCityName;
        @SerializedName("trip_end_date")
        @Expose
        private Object tripEndDate;
        @SerializedName("trip_end_time")
        @Expose
        private Object tripEndTime;
        @SerializedName("trip_usage")
        @Expose
        private Object tripUsage;
        @SerializedName("trip_kms_driven")
        @Expose
        private Object tripKmsDriven;
        @SerializedName("trip_amount")
        @Expose
        private String tripAmount;
        @SerializedName("drivers_trip_note")
        @Expose
        private Object driversTripNote;
        @SerializedName("users_trip_rating")
        @Expose
        private Object usersTripRating;
        @SerializedName("users_trip_note")
        @Expose
        private Object usersTripNote;
        @SerializedName("trip_reviewed_by_user")
        @Expose
        private Object tripReviewedByUser;
        @SerializedName("trip_status_id")
        @Expose
        private String tripStatusId;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("userMobileNumber")
        @Expose
        private String userMobileNumber;
        @SerializedName("userAddress")
        @Expose
        private String userAddress;
        @SerializedName("trip_pickup_point_address")
        @Expose
        private String tripPickupPointAddress;
        @SerializedName("trip_drop_point_address")
        @Expose
        private String tripDropPointAddress;
        @SerializedName("tripsType")
        @Expose
        private String tripsType;

        public Integer getTripId() {
            return tripId;
        }

        public void setTripId(Integer tripId) {
            this.tripId = tripId;
        }

        public String getTripUniqueId() {
            return tripUniqueId;
        }

        public void setTripUniqueId(String tripUniqueId) {
            this.tripUniqueId = tripUniqueId;
        }

        public String getUsersId() {
            return usersId;
        }

        public void setUsersId(String usersId) {
            this.usersId = usersId;
        }

        public String getDriversUserId() {
            return driversUserId;
        }

        public void setDriversUserId(String driversUserId) {
            this.driversUserId = driversUserId;
        }

        public Object getStatesId() {
            return statesId;
        }

        public void setStatesId(Object statesId) {
            this.statesId = statesId;
        }

        public String getTripStartDate() {
            return tripStartDate;
        }

        public void setTripStartDate(String tripStartDate) {
            this.tripStartDate = tripStartDate;
        }

        public String getTripStartTime() {
            return tripStartTime;
        }

        public void setTripStartTime(String tripStartTime) {
            this.tripStartTime = tripStartTime;
        }

        public String getTripTypeId() {
            return tripTypeId;
        }

        public void setTripTypeId(String tripTypeId) {
            this.tripTypeId = tripTypeId;
        }

        public String getTripAstPickupPointLat() {
            return tripAstPickupPointLat;
        }

        public void setTripAstPickupPointLat(String tripAstPickupPointLat) {
            this.tripAstPickupPointLat = tripAstPickupPointLat;
        }

        public String getTripAstPickupPointLang() {
            return tripAstPickupPointLang;
        }

        public void setTripAstPickupPointLang(String tripAstPickupPointLang) {
            this.tripAstPickupPointLang = tripAstPickupPointLang;
        }

        public String getTripAstPickupPointName() {
            return tripAstPickupPointName;
        }

        public void setTripAstPickupPointName(String tripAstPickupPointName) {
            this.tripAstPickupPointName = tripAstPickupPointName;
        }

        public String getTripAstPickupPointCityName() {
            return tripAstPickupPointCityName;
        }

        public void setTripAstPickupPointCityName(String tripAstPickupPointCityName) {
            this.tripAstPickupPointCityName = tripAstPickupPointCityName;
        }

        public String getTripAstDropPointLat() {
            return tripAstDropPointLat;
        }

        public void setTripAstDropPointLat(String tripAstDropPointLat) {
            this.tripAstDropPointLat = tripAstDropPointLat;
        }

        public String getTripAstDropPointLang() {
            return tripAstDropPointLang;
        }

        public void setTripAstDropPointLang(String tripAstDropPointLang) {
            this.tripAstDropPointLang = tripAstDropPointLang;
        }

        public String getTripAstDropPointName() {
            return tripAstDropPointName;
        }

        public void setTripAstDropPointName(String tripAstDropPointName) {
            this.tripAstDropPointName = tripAstDropPointName;
        }

        public String getTripAstDropPointCityName() {
            return tripAstDropPointCityName;
        }

        public void setTripAstDropPointCityName(String tripAstDropPointCityName) {
            this.tripAstDropPointCityName = tripAstDropPointCityName;
        }

        public String getTripAstUsage() {
            return tripAstUsage;
        }

        public void setTripAstUsage(String tripAstUsage) {
            this.tripAstUsage = tripAstUsage;
        }

        public Object getTripPickupPointLat() {
            return tripPickupPointLat;
        }

        public void setTripPickupPointLat(Object tripPickupPointLat) {
            this.tripPickupPointLat = tripPickupPointLat;
        }

        public Object getTripPickupPointLang() {
            return tripPickupPointLang;
        }

        public void setTripPickupPointLang(Object tripPickupPointLang) {
            this.tripPickupPointLang = tripPickupPointLang;
        }

        public Object getTripPickupPointName() {
            return tripPickupPointName;
        }

        public void setTripPickupPointName(Object tripPickupPointName) {
            this.tripPickupPointName = tripPickupPointName;
        }

        public Object getTripPickupPointCityName() {
            return tripPickupPointCityName;
        }

        public void setTripPickupPointCityName(Object tripPickupPointCityName) {
            this.tripPickupPointCityName = tripPickupPointCityName;
        }

        public Object getTripDropPointLat() {
            return tripDropPointLat;
        }

        public void setTripDropPointLat(Object tripDropPointLat) {
            this.tripDropPointLat = tripDropPointLat;
        }

        public Object getTripDropPointLang() {
            return tripDropPointLang;
        }

        public void setTripDropPointLang(Object tripDropPointLang) {
            this.tripDropPointLang = tripDropPointLang;
        }

        public Object getTripDropPointName() {
            return tripDropPointName;
        }

        public void setTripDropPointName(Object tripDropPointName) {
            this.tripDropPointName = tripDropPointName;
        }

        public Object getTripDropPointCityName() {
            return tripDropPointCityName;
        }

        public void setTripDropPointCityName(Object tripDropPointCityName) {
            this.tripDropPointCityName = tripDropPointCityName;
        }

        public Object getTripEndDate() {
            return tripEndDate;
        }

        public void setTripEndDate(Object tripEndDate) {
            this.tripEndDate = tripEndDate;
        }

        public Object getTripEndTime() {
            return tripEndTime;
        }

        public void setTripEndTime(Object tripEndTime) {
            this.tripEndTime = tripEndTime;
        }

        public Object getTripUsage() {
            return tripUsage;
        }

        public void setTripUsage(Object tripUsage) {
            this.tripUsage = tripUsage;
        }

        public Object getTripKmsDriven() {
            return tripKmsDriven;
        }

        public void setTripKmsDriven(Object tripKmsDriven) {
            this.tripKmsDriven = tripKmsDriven;
        }

        public String getTripAmount() {
            return tripAmount;
        }

        public void setTripAmount(String tripAmount) {
            this.tripAmount = tripAmount;
        }

        public Object getDriversTripNote() {
            return driversTripNote;
        }

        public void setDriversTripNote(Object driversTripNote) {
            this.driversTripNote = driversTripNote;
        }

        public Object getUsersTripRating() {
            return usersTripRating;
        }

        public void setUsersTripRating(Object usersTripRating) {
            this.usersTripRating = usersTripRating;
        }

        public Object getUsersTripNote() {
            return usersTripNote;
        }

        public void setUsersTripNote(Object usersTripNote) {
            this.usersTripNote = usersTripNote;
        }

        public Object getTripReviewedByUser() {
            return tripReviewedByUser;
        }

        public void setTripReviewedByUser(Object tripReviewedByUser) {
            this.tripReviewedByUser = tripReviewedByUser;
        }

        public String getTripStatusId() {
            return tripStatusId;
        }

        public void setTripStatusId(String tripStatusId) {
            this.tripStatusId = tripStatusId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserMobileNumber() {
            return userMobileNumber;
        }

        public void setUserMobileNumber(String userMobileNumber) {
            this.userMobileNumber = userMobileNumber;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public String getTripPickupPointAddress() {
            return tripPickupPointAddress;
        }

        public void setTripPickupPointAddress(String tripPickupPointAddress) {
            this.tripPickupPointAddress = tripPickupPointAddress;
        }

        public String getTripDropPointAddress() {
            return tripDropPointAddress;
        }

        public void setTripDropPointAddress(String tripDropPointAddress) {
            this.tripDropPointAddress = tripDropPointAddress;
        }

        public String getTripsType() {
            return tripsType;
        }

        public void setTripsType(String tripsType) {
            this.tripsType = tripsType;
        }
    }

  public DriverProfile getDriverProfile() {
        return driverProfile;
    }


    public List<OngoingTrip> getOngoingTrips() {
        return ongoingTrips;
    }

    public void setOngoingTrips(List<OngoingTrip> ongoingTrips) {
        this.ongoingTrips = ongoingTrips;
    }

    public void setDriverProfile(DriverProfile driverProfile) {
        this.driverProfile = driverProfile;
    }

    public ArrayList<TripsModel> getTripsModels() {
        return tripsModels;
    }

    public void setTripsModels(ArrayList<TripsModel> tripsModels) {
        this.tripsModels = tripsModels;
    }
}
