package com.driver.driverbooking.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentInfoResponce {

    @SerializedName("requestPayment")
    @Expose
    private RequestPayment requestPayment;

    public RequestPayment getRequestPayment() {
        return requestPayment;
    }

    public void setRequestPayment(RequestPayment requestPayment) {
        this.requestPayment = requestPayment;
    }

    public class RequestPayment {

        @SerializedName("trip_details_id")
        @Expose
        private Integer tripDetailsId;
        @SerializedName("trips_tbl_id")
        @Expose
        private String tripsTblId;
        @SerializedName("trip_unique_id")
        @Expose
        private String tripUniqueId;
        @SerializedName("users_id")
        @Expose
        private String usersId;
        @SerializedName("drivers_user_id")
        @Expose
        private Object driversUserId;
        @SerializedName("trip_type_id")
        @Expose
        private String tripTypeId;
        @SerializedName("trip_pickup_point_address")
        @Expose
        private String tripPickupPointAddress;
        @SerializedName("trip_pickup_point_city")
        @Expose
        private String tripPickupPointCity;
        @SerializedName("trip_pickup_point_lat")
        @Expose
        private String tripPickupPointLat;
        @SerializedName("trip_pickup_point_lang")
        @Expose
        private String tripPickupPointLang;
        @SerializedName("arrived_pickup_point_date")
        @Expose
        private String arrivedPickupPointDate;
        @SerializedName("arrived_pickup_point_time")
        @Expose
        private String arrivedPickupPointTime;
        @SerializedName("trip_start_point_address")
        @Expose
        private String tripStartPointAddress;
        @SerializedName("trip_start_point_city")
        @Expose
        private String tripStartPointCity;
        @SerializedName("trip_start_point_lat")
        @Expose
        private String tripStartPointLat;
        @SerializedName("trip_start_point_lang")
        @Expose
        private String tripStartPointLang;
        @SerializedName("start_trip_date")
        @Expose
        private String startTripDate;
        @SerializedName("start_trip_time")
        @Expose
        private String startTripTime;
        @SerializedName("trip_drop_point_address")
        @Expose
        private Object tripDropPointAddress;
        @SerializedName("trip_drop_point_city")
        @Expose
        private Object tripDropPointCity;
        @SerializedName("trip_drop_point_lat")
        @Expose
        private Object tripDropPointLat;
        @SerializedName("trip_drop_point_lang")
        @Expose
        private Object tripDropPointLang;
        @SerializedName("end_trip_date")
        @Expose
        private Object endTripDate;
        @SerializedName("end_trip_time")
        @Expose
        private Object endTripTime;
        @SerializedName("trip_hours")
        @Expose
        private Object tripHours;
        @SerializedName("trip_amount")
        @Expose
        private Object tripAmount;
        @SerializedName("trip_payment_type")
        @Expose
        private String tripPaymentType;
        @SerializedName("trip_payment_status")
        @Expose
        private Object tripPaymentStatus;
        @SerializedName("gst_charge")
        @Expose
        private Object gstCharge;
        @SerializedName("cgst_charge")
        @Expose
        private Object cgstCharge;
        @SerializedName("trip_accommodation_charges")
        @Expose
        private Integer tripAccommodationCharges;
        @SerializedName("trip_food_charges")
        @Expose
        private Integer tripFoodCharges;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
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

        public Integer getTripDetailsId() {
            return tripDetailsId;
        }

        public void setTripDetailsId(Integer tripDetailsId) {
            this.tripDetailsId = tripDetailsId;
        }

        public String getTripsTblId() {
            return tripsTblId;
        }

        public void setTripsTblId(String tripsTblId) {
            this.tripsTblId = tripsTblId;
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

        public Object getDriversUserId() {
            return driversUserId;
        }

        public void setDriversUserId(Object driversUserId) {
            this.driversUserId = driversUserId;
        }

        public String getTripTypeId() {
            return tripTypeId;
        }

        public void setTripTypeId(String tripTypeId) {
            this.tripTypeId = tripTypeId;
        }

        public String getTripPickupPointAddress() {
            return tripPickupPointAddress;
        }

        public void setTripPickupPointAddress(String tripPickupPointAddress) {
            this.tripPickupPointAddress = tripPickupPointAddress;
        }

        public String getTripPickupPointCity() {
            return tripPickupPointCity;
        }

        public void setTripPickupPointCity(String tripPickupPointCity) {
            this.tripPickupPointCity = tripPickupPointCity;
        }

        public String getTripPickupPointLat() {
            return tripPickupPointLat;
        }

        public void setTripPickupPointLat(String tripPickupPointLat) {
            this.tripPickupPointLat = tripPickupPointLat;
        }

        public String getTripPickupPointLang() {
            return tripPickupPointLang;
        }

        public void setTripPickupPointLang(String tripPickupPointLang) {
            this.tripPickupPointLang = tripPickupPointLang;
        }

        public String getArrivedPickupPointDate() {
            return arrivedPickupPointDate;
        }

        public void setArrivedPickupPointDate(String arrivedPickupPointDate) {
            this.arrivedPickupPointDate = arrivedPickupPointDate;
        }

        public String getArrivedPickupPointTime() {
            return arrivedPickupPointTime;
        }

        public void setArrivedPickupPointTime(String arrivedPickupPointTime) {
            this.arrivedPickupPointTime = arrivedPickupPointTime;
        }

        public String getTripStartPointAddress() {
            return tripStartPointAddress;
        }

        public void setTripStartPointAddress(String tripStartPointAddress) {
            this.tripStartPointAddress = tripStartPointAddress;
        }

        public String getTripStartPointCity() {
            return tripStartPointCity;
        }

        public void setTripStartPointCity(String tripStartPointCity) {
            this.tripStartPointCity = tripStartPointCity;
        }

        public String getTripStartPointLat() {
            return tripStartPointLat;
        }

        public void setTripStartPointLat(String tripStartPointLat) {
            this.tripStartPointLat = tripStartPointLat;
        }

        public String getTripStartPointLang() {
            return tripStartPointLang;
        }

        public void setTripStartPointLang(String tripStartPointLang) {
            this.tripStartPointLang = tripStartPointLang;
        }

        public String getStartTripDate() {
            return startTripDate;
        }

        public void setStartTripDate(String startTripDate) {
            this.startTripDate = startTripDate;
        }

        public String getStartTripTime() {
            return startTripTime;
        }

        public void setStartTripTime(String startTripTime) {
            this.startTripTime = startTripTime;
        }

        public Object getTripDropPointAddress() {
            return tripDropPointAddress;
        }

        public void setTripDropPointAddress(Object tripDropPointAddress) {
            this.tripDropPointAddress = tripDropPointAddress;
        }

        public Object getTripDropPointCity() {
            return tripDropPointCity;
        }

        public void setTripDropPointCity(Object tripDropPointCity) {
            this.tripDropPointCity = tripDropPointCity;
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

        public Object getEndTripDate() {
            return endTripDate;
        }

        public void setEndTripDate(Object endTripDate) {
            this.endTripDate = endTripDate;
        }

        public Object getEndTripTime() {
            return endTripTime;
        }

        public void setEndTripTime(Object endTripTime) {
            this.endTripTime = endTripTime;
        }

        public Object getTripHours() {
            return tripHours;
        }

        public void setTripHours(Object tripHours) {
            this.tripHours = tripHours;
        }

        public Object getTripAmount() {
            return tripAmount;
        }

        public void setTripAmount(Object tripAmount) {
            this.tripAmount = tripAmount;
        }

        public String getTripPaymentType() {
            return tripPaymentType;
        }

        public void setTripPaymentType(String tripPaymentType) {
            this.tripPaymentType = tripPaymentType;
        }

        public Object getTripPaymentStatus() {
            return tripPaymentStatus;
        }

        public void setTripPaymentStatus(Object tripPaymentStatus) {
            this.tripPaymentStatus = tripPaymentStatus;
        }

        public Object getGstCharge() {
            return gstCharge;
        }

        public void setGstCharge(Object gstCharge) {
            this.gstCharge = gstCharge;
        }

        public Object getCgstCharge() {
            return cgstCharge;
        }

        public void setCgstCharge(Object cgstCharge) {
            this.cgstCharge = cgstCharge;
        }

        public Integer getTripAccommodationCharges() {
            return tripAccommodationCharges;
        }

        public void setTripAccommodationCharges(Integer tripAccommodationCharges) {
            this.tripAccommodationCharges = tripAccommodationCharges;
        }

        public Integer getTripFoodCharges() {
            return tripFoodCharges;
        }

        public void setTripFoodCharges(Integer tripFoodCharges) {
            this.tripFoodCharges = tripFoodCharges;
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
    }
    }
