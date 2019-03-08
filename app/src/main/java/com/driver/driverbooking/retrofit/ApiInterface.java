package com.driver.driverbooking.retrofit;

import com.driver.driverbooking.response.AboutsAsResponse;
import com.driver.driverbooking.response.ChengePasswordRespnse;
import com.driver.driverbooking.response.CompleteTripResponce;
import com.driver.driverbooking.response.ContactUsResponse;
import com.driver.driverbooking.response.DriverTermsConditionResponse;
import com.driver.driverbooking.response.EditResponse;
import com.driver.driverbooking.response.ForgetMechOtpResponse;
import com.driver.driverbooking.response.ForgetPassResponse;
import com.driver.driverbooking.response.ForgotPassNumberResponse;
import com.driver.driverbooking.response.FutureBookingResponse;
import com.driver.driverbooking.response.LoginResponse;
import com.driver.driverbooking.response.MyTripsResponse;
import com.driver.driverbooking.response.PaymentInfoResponce;
import com.driver.driverbooking.response.PrivacyPolicyResponse;
import com.driver.driverbooking.response.ProfileResponse;
import com.driver.driverbooking.response.ReachedPickupPointResponse;
import com.driver.driverbooking.response.RequestCompleteTripOTPResponce;
import com.driver.driverbooking.response.RequestStartTripOTPResponse;
import com.driver.driverbooking.response.StartTripResponse;
import com.driver.driverbooking.response.TripsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("driver")
    Call<LoginResponse> getLogin(@Query("username") String userName, @Query("password") String password);

    @GET("driver/driverProfile")
    Call<ProfileResponse> getProfile(@Query("driverId") Integer DriverId);

    @GET("driver/dashboard")
    Call<TripsResponse> getDashboard(@Query("driverId") String DriverId);

    @GET("driver/futureBookings")
    Call<FutureBookingResponse> getFucherBooking(@Query("driverId") Integer DriverId);

    @GET("driver/editProfile")
    Call<EditResponse> getEditProfile(@Query("driver_id") Integer DriverId, @Query("firstname") String fName, @Query("lastname") String lName, @Query("email") String Email, @Query("permenant_address") String perAddres, @Query("resident_address") String resAddress);

    @GET("driver/driverPrivacyPolicy")
    Call<PrivacyPolicyResponse> getPrivacypolicy();


    @GET("users/userContactUs")
    Call<ContactUsResponse> getContactUs();

    @GET("driver/driverAboutUs")
    Call<AboutsAsResponse> getAboutsAs();


    // ForgetPass
    @GET("driver/forgotPasswordOtp")
    Call<ForgotPassNumberResponse> getNumPassFor(@Query("mobile_no") String mobile);

    @GET("driver/forgotPasswordMatchOtp")
    Call<ForgetMechOtpResponse> getForPassMatcheOtp(@Query("mobile_no") String mobile, @Query("otp") String otp);


    @GET("driver/updateNewPasswod")
    Call<ForgetPassResponse> getForNewPass(@Query("mobile_no") String mobile, @Query("password") String password);


    @GET("driver/updatePassword")
    Call<ChengePasswordRespnse> getChengePassw(@Query("driver_id") Integer DriverId,@Query("old_password")String oldPass,@Query("new_password")String NewPass);

    @GET("driver/startTrip")
    Call<StartTripResponse>getStartTrip(@Query("tripId") Integer id,
                                        @Query("start_trip_date") String date,
                                        @Query("start_trip_time") String time,
                                        @Query("trip_start_point_address") String addredd,
                                        @Query("trip_start_point_city") String city,
                                        @Query("trip_start_point_lat") String lat,
                                        @Query("trip_start_point_lang") String lang);


    @GET("driver/myTrips")
    Call<MyTripsResponse> getMytrip(@Query("driverId") Integer driverId);

    @GET("driver/arrivedPickupPoint")
    Call<ReachedPickupPointResponse> getReachedPickupPoint(@Query("tripId") String TripId,
                                                           @Query("arrived_pickup_point_date") String date,
                                                            @Query("arrived_pickup_point_time") String time,
                                                           @Query("trip_pickup_point_address") String address,
                                                           @Query("trip_pickup_point_lat") String lat,
                                                           @Query("trip_pickup_point_lang") String lang,
                                                           @Query("trip_pickup_point_city") String city);

    @GET("driver/driverTermsCondition")
    Call<DriverTermsConditionResponse> getDriverTermsCondition();

    @GET("driver/requestStartTripOTP")
    Call<RequestStartTripOTPResponse> getRequestStartTripOTP(@Query("tripId") Integer TripId);

    @GET("driver/requestCompleteTripOTP")
    Call<RequestCompleteTripOTPResponce> getRequestEndTripOTP(@Query("tripId") Integer TripId);


    @GET("driver/requestPayment")
    Call<PaymentInfoResponce>getrequestPayment(@Query("tripId") Integer id,
                                          @Query("trip_end_date") String date,
                                          @Query("trip_end_time") String time,
                                          @Query("trip_drop_point_address") String addredd,
                                          @Query("trip_drop_point_city") String city,
                                          @Query("trip_drop_point_lat") String lat,
                                          @Query("trip_drop_point_lang") String lang);

    @GET("driver/completeTrip")
    Call<CompleteTripResponce> getCompleteTrip(@Query("tripId") Integer TripId);

}
