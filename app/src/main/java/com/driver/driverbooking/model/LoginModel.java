package com.driver.driverbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("drivers_user_id")
    @Expose
    private int driversUserId;
    @SerializedName("drivers_driver_id")
    @Expose
    private String driversDriverId;
    @SerializedName("drivers_firstname")
    @Expose
    private String driversFirstname;
    @SerializedName("drivers_lastname")
    @Expose
    private String driversLastname;
    @SerializedName("drivers_username")
    @Expose
    private String driversUsername;
    @SerializedName("drivers_password")
    @Expose
    private String driversPassword;
    @SerializedName("drivers_profile_picture")
    @Expose
    private String driversProfilePicture;
    @SerializedName("drivers_contact_no")
    @Expose
    private String driversContactNo;
    @SerializedName("drivers_perm_address")
    @Expose
    private String driversPermAddress;
    @SerializedName("drivers_resi_address")
    @Expose
    private String driversResiAddress;
    @SerializedName("drivers_email")
    @Expose
    private String driversEmail;
    @SerializedName("drivers_licence_no")
    @Expose
    private String driversLicenceNo;
    @SerializedName("drivers_licence_image")
    @Expose
    private String driversLicenceImage;
    @SerializedName("drivers_aadhar_no")
    @Expose
    private String driversAadharNo;
    @SerializedName("drivers_aadhar_image")
    @Expose
    private String driversAadharImage;
    @SerializedName("drivers_resi_address_image")
    @Expose
    private String driversResiAddressImage;
    @SerializedName("drivers_blood_group_id")
    @Expose
    private String driversBloodGroupId;
    @SerializedName("drivers_trip_status")
    @Expose
    private String driversTripStatus;
    @SerializedName("drivers_isActive")
    @Expose
    private String driversIsActive;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    public int getDriversUserId() {

        return driversUserId;
    }

    public void setDriversUserId(int driversUserId) {
        this.driversUserId = driversUserId;
    }

    public String getDriversDriverId() {
        return driversDriverId;
    }

    public void setDriversDriverId(String driversDriverId) {
        this.driversDriverId = driversDriverId;
    }

    public String getDriversFirstname() {
        return driversFirstname;
    }

    public void setDriversFirstname(String driversFirstname) {
        this.driversFirstname = driversFirstname;
    }

    public String getDriversLastname() {
        return driversLastname;
    }

    public void setDriversLastname(String driversLastname) {
        this.driversLastname = driversLastname;
    }

    public String getDriversUsername() {
        return driversUsername;
    }

    public void setDriversUsername(String driversUsername) {
        this.driversUsername = driversUsername;
    }

    public String getDriversPassword() {
        return driversPassword;
    }

    public void setDriversPassword(String driversPassword) {
        this.driversPassword = driversPassword;
    }

    public String getDriversProfilePicture() {
        return driversProfilePicture;
    }

    public void setDriversProfilePicture(String driversProfilePicture) {
        this.driversProfilePicture = driversProfilePicture;
    }

    public String getDriversContactNo() {
        return driversContactNo;
    }

    public void setDriversContactNo(String driversContactNo) {
        this.driversContactNo = driversContactNo;
    }

    public String getDriversPermAddress() {
        return driversPermAddress;
    }

    public void setDriversPermAddress(String driversPermAddress) {
        this.driversPermAddress = driversPermAddress;
    }

    public String getDriversResiAddress() {
        return driversResiAddress;
    }

    public void setDriversResiAddress(String driversResiAddress) {
        this.driversResiAddress = driversResiAddress;
    }

    public String getDriversEmail() {
        return driversEmail;
    }

    public void setDriversEmail(String driversEmail) {
        this.driversEmail = driversEmail;
    }

    public String getDriversLicenceNo() {
        return driversLicenceNo;
    }

    public void setDriversLicenceNo(String driversLicenceNo) {
        this.driversLicenceNo = driversLicenceNo;
    }

    public String getDriversLicenceImage() {
        return driversLicenceImage;
    }

    public void setDriversLicenceImage(String driversLicenceImage) {
        this.driversLicenceImage = driversLicenceImage;
    }

    public String getDriversAadharNo() {
        return driversAadharNo;
    }

    public void setDriversAadharNo(String driversAadharNo) {
        this.driversAadharNo = driversAadharNo;
    }

    public String getDriversAadharImage() {
        return driversAadharImage;
    }

    public void setDriversAadharImage(String driversAadharImage) {
        this.driversAadharImage = driversAadharImage;
    }

    public String getDriversResiAddressImage() {
        return driversResiAddressImage;
    }

    public void setDriversResiAddressImage(String driversResiAddressImage) {
        this.driversResiAddressImage = driversResiAddressImage;
    }

    public String getDriversBloodGroupId() {
        return driversBloodGroupId;
    }

    public void setDriversBloodGroupId(String driversBloodGroupId) {
        this.driversBloodGroupId = driversBloodGroupId;
    }

    public String getDriversTripStatus() {
        return driversTripStatus;
    }

    public void setDriversTripStatus(String driversTripStatus) {
        this.driversTripStatus = driversTripStatus;
    }

    public String getDriversIsActive() {
        return driversIsActive;
    }

    public void setDriversIsActive(String driversIsActive) {
        this.driversIsActive = driversIsActive;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
