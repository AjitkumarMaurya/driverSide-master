package com.driver.driverbooking.MapActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.driver.driverbooking.Activity.ContactUsActivity;
import com.driver.driverbooking.Activity.PaymentInfoActivity;
import com.driver.driverbooking.Common;
import com.driver.driverbooking.MainActivity;
import com.driver.driverbooking.R;
import com.driver.driverbooking.directionhelpers.FetchURL;
import com.driver.driverbooking.directionhelpers.TaskLoadedCallback;
import com.driver.driverbooking.response.PaymentInfoResponce;
import com.driver.driverbooking.response.ReachedPickupPointResponse;
import com.driver.driverbooking.response.RequestCompleteTripOTPResponce;
import com.driver.driverbooking.response.RequestStartTripOTPResponse;
import com.driver.driverbooking.response.StartTripResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import im.delight.android.location.SimpleLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    ApiInterface apiInterface;
    private Polyline currentPolyline;
    Double myFirstLat, myFirstLog, myLastLat, myLastLog;
    Button btnpickup, btnStartTrip, btnEndTrip;
    TextView tv_bookdetel_Date, tv_bookdetel_Time, tv_bookdetel_Trip, tv_bookdetel_Name, tv_bookdetel_Address, tv_bookdetel_Help, tv_bookdetel_Contact, tv_bookdetel_GetDirection, tvTimeCount, tvUsageTime;
    int myid;
    AlertDialog dialog;
    int numberOtp;
    String otpsend = "";
    ImageView back;
    LocationManager locationManager;
    private SimpleLocation locationcurrent;
    String address;
    String city;
    @SuppressLint("WrongViewCast")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        back = findViewById(R.id.backMove);
        tv_bookdetel_Name = findViewById(R.id.tv_bookdetel_Name);
        tv_bookdetel_Date = findViewById(R.id.tv_bookdetel_Date);
        tv_bookdetel_Time = findViewById(R.id.tv_bookdetel_Time);
        tv_bookdetel_Address = findViewById(R.id.tv_bookdetel_Address);
        tv_bookdetel_Help = findViewById(R.id.tv_bookdetel_Help);
        tv_bookdetel_Contact = findViewById(R.id.tv_bookdetel_Contact);
        tv_bookdetel_GetDirection = findViewById(R.id.tv_bookdetel_GetDirection);
        tv_bookdetel_Trip = findViewById(R.id.tv_bookdetel_Trip);

        btnStartTrip = findViewById(R.id.btnStartTrip);
        btnEndTrip = findViewById(R.id.btnEndTrip);
        btnpickup = findViewById(R.id.btnpickup);

        tvTimeCount = findViewById(R.id.Tv_countdown_timer);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        tvUsageTime = findViewById(R.id.tvuageTime);

        Intent intent = getIntent();
        myid = intent.getIntExtra("id", 0);


        Log.d("demo", "onCreate: " + myid);

        locationcurrent = new SimpleLocation(this);

        // if we can't access the location yet
        if (!locationcurrent.hasLocationEnabled()) {
            // ask the user to enable location access
            SimpleLocation.openSettings(this);
        }

        locationcurrent.beginUpdates();



        if (Common.statusId.equalsIgnoreCase("2")){
            btnpickup.setVisibility(View.VISIBLE);
            btnEndTrip.setVisibility(View.GONE);
            btnStartTrip.setVisibility(View.GONE);

        }else if(Common.statusId.equalsIgnoreCase("3")){
            btnpickup.setVisibility(View.GONE);
            btnEndTrip.setVisibility(View.GONE);
            btnStartTrip.setVisibility(View.VISIBLE);

        }else if(Common.statusId.equalsIgnoreCase("4")){
            btnpickup.setVisibility(View.GONE);
            btnEndTrip.setVisibility(View.VISIBLE);
            btnStartTrip.setVisibility(View.GONE);
        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this, MainActivity.class));
            }
        });

        btnpickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MapsActivity.this, "Comming Soon...", Toast.LENGTH_SHORT).show();

                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                @SuppressLint("DefaultLocale") String cuurentTime= String.format("%02d:%02d", mHour, mMinute);
                String currentDate = mYear + "-" + checkDigit(mMonth) + "-" +checkDigit(mDay);

                getAddress(MapsActivity.this,locationcurrent.getLatitude(),locationcurrent.getLongitude());

                Log.e("@@",""+String.valueOf(myid)+"  "+currentDate+"  "+cuurentTime+"   "+address+"  "+String.valueOf(locationcurrent.getLatitude())+"  "+String.valueOf(locationcurrent.getLongitude())+"  "+city);

                Call<ReachedPickupPointResponse> call = apiInterface.getReachedPickupPoint(String.valueOf(myid),currentDate,cuurentTime,address,String.valueOf(locationcurrent.getLatitude()),String.valueOf(locationcurrent.getLongitude()),city);
                call.enqueue(new Callback<ReachedPickupPointResponse>() {
                    @Override
                    public void onResponse(Call<ReachedPickupPointResponse> call, Response<ReachedPickupPointResponse> response) {


                        if (response != null && response.isSuccessful()) {
                            if (!response.body().getArrivedPickupPoint()) {
                                Toast.makeText(MapsActivity.this, "Error..", Toast.LENGTH_SHORT).show();
                                btnpickup.setVisibility(View.VISIBLE);

                            } else {
                               // Toast.makeText(MapsActivity.this, "OTP Msg Send to customer", Toast.LENGTH_SHORT).show();


                                    btnpickup.setVisibility(View.GONE);
                                    btnStartTrip.setVisibility(View.VISIBLE);


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ReachedPickupPointResponse> call, Throwable t) {

                    }
                });


            }
        });

        btnEndTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert_start_otp = new AlertDialog.Builder(MapsActivity.this);
                View view = getLayoutInflater().inflate(R.layout.alert_start_otp, null);
                //       OtpView otpView = (OtpView) view.findViewById(R.id.otpStartTrip);
                Button cancel = (Button) view.findViewById(R.id.btnCancelStartTrip);
                final Button sendOtp = (Button) view.findViewById(R.id.send_otp);

                final EditText otpView = (EditText) view.findViewById(R.id.otpStartTrip);


                Call<RequestCompleteTripOTPResponce> call = apiInterface.getRequestEndTripOTP(myid);
                call.enqueue(new Callback<RequestCompleteTripOTPResponce>() {
                    @Override
                    public void onResponse(Call<RequestCompleteTripOTPResponce> call, Response<RequestCompleteTripOTPResponce> response) {
                        if (response != null && response.isSuccessful()) {

                            numberOtp = response.body().getRequestStartTripOTP();
                            Log.e("@@","OTP---"+ response.body().getRequestStartTripOTP());

                        }

                    }

                    @Override
                    public void onFailure(Call<RequestCompleteTripOTPResponce> call, Throwable t) {

                    }
                });


                sendOtp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        otpsend = otpView.getText().toString();
                        //  int inum = Integer.parseInt(otpsend);
                        Log.e("@@","OTP enterd---"+otpsend);

                        if (otpsend.isEmpty()) {
                            //    Toast.makeText(MapsActivity.this, "Enter otp.. ", Toast.LENGTH_SHORT).show();
                            otpView.setError("Enter Otp ");
                            //   | PASS numberOtp
                        } else if (numberOtp == Integer.parseInt(otpsend)) {
                            Toast.makeText(MapsActivity.this, "Otp varified...", Toast.LENGTH_SHORT).show();
                            RequestPaymentApiCall();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(MapsActivity.this, "Wrong Otp entered.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                alert_start_otp.setTitle("Enter Otp ");
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                alert_start_otp.setView(view);
                dialog = alert_start_otp.create();
                dialog.setCancelable(false);
                dialog.show();



            }
        });


        btnStartTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert_start_otp = new AlertDialog.Builder(MapsActivity.this);
                View view = getLayoutInflater().inflate(R.layout.alert_start_otp, null);
                //       OtpView otpView = (OtpView) view.findViewById(R.id.otpStartTrip);
                Button cancel = (Button) view.findViewById(R.id.btnCancelStartTrip);
                final Button sendOtp = (Button) view.findViewById(R.id.send_otp);

                final EditText otpView = (EditText) view.findViewById(R.id.otpStartTrip);


                Call<RequestStartTripOTPResponse> call = apiInterface.getRequestStartTripOTP(myid);
                call.enqueue(new Callback<RequestStartTripOTPResponse>() {
                    @Override
                    public void onResponse(Call<RequestStartTripOTPResponse> call, Response<RequestStartTripOTPResponse> response) {
                        if (response != null && response.isSuccessful()) {

                            numberOtp = response.body().getRequestStartTripOTP();

                            Log.e("@@","OTP---"+ response.body().getRequestStartTripOTP());

                        }

                    }

                    @Override
                    public void onFailure(Call<RequestStartTripOTPResponse> call, Throwable t) {

                    }
                });


                sendOtp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        otpsend = otpView.getText().toString();
                        Log.e("@@","OTP enterd---"+otpsend);

                        //  int inum = Integer.parseInt(otpsend);
                        if (otpsend.isEmpty()) {
                            //    Toast.makeText(MapsActivity.this, "Enter otp.. ", Toast.LENGTH_SHORT).show();
                            otpView.setError("Enter Otp ");
                            //   | PASS numberOtp
                        } else if (numberOtp == Integer.parseInt(otpsend)) {
                            Toast.makeText(MapsActivity.this, "Otp varified...", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            StartTripApiCall();
                        } else {
                            Toast.makeText(MapsActivity.this, "Wrong Otp entered.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                alert_start_otp.setTitle("Enter Otp ");
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                alert_start_otp.setView(view);
                dialog = alert_start_otp.create();
                dialog.setCancelable(false);
                dialog.show();


            }
        });


        tv_bookdetel_GetDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MapsActivity.this, "Comming Soon ...", Toast.LENGTH_SHORT).show();
            }
        });

        tv_bookdetel_Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", Common.userMobileNumInTrip, null));
                startActivity(intent);
            }
        });

        tv_bookdetel_Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }


        });

        if (Common.myTripType.equals("One Way Trip")) {
            tv_bookdetel_Trip.setText("One Way Trip");

        } else {
            tv_bookdetel_Trip.setText("Round Trip");
        }


        if (Common.myTripType.equals("One Way Trip")) {
            myFirstLat = Common.myfirstLat;
            myFirstLog = Common.myfirstLog;
            myLastLat = Common.mylasttLat;
            myLastLog = Common.mylastlog;
            String myfTitle = Common.startTitle;
            String mylTitle = Common.lastTitle;
            Log.d("test", "myfTitle: " + myfTitle);

            Log.e("@@",""+myFirstLat+"   "+myFirstLog+"  "+myLastLat+"  "+myLastLog);

            tv_bookdetel_Name.setText(Common.userNameinTrip);
            tv_bookdetel_Time.setText(Common.startTime);
            tv_bookdetel_Date.setText(Common.tripstartDate);
            tv_bookdetel_Address.setText(Common.userAddressinTrip);


            tvUsageTime.setText(Common.UsageTime);


            place1 = new MarkerOptions().position(new LatLng(myFirstLat, myFirstLog)).title(myfTitle);
            place2 = new MarkerOptions().position(new LatLng(myLastLat, myLastLog)).title(mylTitle);//.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_green_location_24dp));


            new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");

            Location loc1 = new Location("");
            loc1.setLatitude(myFirstLat);
            loc1.setLongitude(myFirstLog);

            Location loc2 = new Location("");
            loc2.setLatitude(myLastLat);
            loc2.setLongitude(myLastLog);

            float distanceInMeters = loc1.distanceTo(loc2);

            double km = distanceInMeters * 0.001;

        } else {

            myFirstLat = Common.myfirstLat;
            myFirstLog = Common.myfirstLog;
            String myfTitle = Common.startTitle;

            tv_bookdetel_Name.setText(Common.userNameinTrip);
            tv_bookdetel_Time.setText(Common.startTime);
            tv_bookdetel_Date.setText(Common.tripstartDate);
            tv_bookdetel_Address.setText(Common.userAddressinTrip);
            tvUsageTime.setText(Common.UsageTime);

            place1 = new MarkerOptions().position(new LatLng(myFirstLat, myFirstLog)).title(myfTitle);

            //new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");


            Location loc1 = new Location("");
            loc1.setLatitude(myFirstLat);
            loc1.setLongitude(myFirstLog);


        }


        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapNearBy);
        mapFragment.getMapAsync(this);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("mylog", "Added Markers");

        if (Common.myTripType.equals("One Way Trip")) {
            mMap.addMarker(place1);
            mMap.addMarker(place2);
        } else {
            mMap.addMarker(place1);
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myFirstLat, myFirstLog), 10f));
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }


    public void getAddress(Context context, double LATITUDE, double LONGITUDE) {
        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {

// If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                address = addresses.get(0).getAddressLine(0)+"";
                city = addresses.get(0).getLocality()+"";

                String state = addresses.get(0).getAdminArea()+"";
                String country = addresses.get(0).getCountryName()+"";
                String postalCode = addresses.get(0).getPostalCode()+"";
                String knownName = addresses.get(0).getFeatureName()+""; // Only if available else return NULL

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String checkDigit(int number)
    {
        return number<=9?"0"+number:String.valueOf(number);
    }

    public void StartTripApiCall(){


        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        @SuppressLint("DefaultLocale") String cuurentTime= String.format("%02d:%02d", mHour, mMinute);
        String currentDate = mYear + "-" + checkDigit(mMonth) + "-" +checkDigit(mDay+1);

        getAddress(MapsActivity.this,locationcurrent.getLatitude(),locationcurrent.getLongitude());

        Log.e("@@",""+String.valueOf(myid)+"  "+currentDate+"  "+cuurentTime+"   "+address+"  "+String.valueOf(locationcurrent.getLatitude())+"  "+String.valueOf(locationcurrent.getLongitude())+"  "+city);


        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        //startTrip Load Map Data this api , pass deful data
        Call<StartTripResponse> call1 = apiInterface.getStartTrip(myid,currentDate,cuurentTime,address, city, String.valueOf(locationcurrent.getLatitude()),String.valueOf(locationcurrent.getLongitude()));

        call1.enqueue(new Callback<StartTripResponse>() {
            @Override
            public void onResponse(Call<StartTripResponse> call, Response<StartTripResponse> response) {
                if (response != null && response.isSuccessful()) {
                    btnStartTrip.setVisibility(View.GONE);
                    btnEndTrip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<StartTripResponse> call, Throwable t) {

            }
        });
    }

    public void RequestPaymentApiCall(){

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        @SuppressLint("DefaultLocale") String cuurentTime= String.format("%02d:%02d", mHour, mMinute);
        String currentDate = mYear + "-" + checkDigit(mMonth+1) + "-" +checkDigit(mDay);

        getAddress(MapsActivity.this,locationcurrent.getLatitude(),locationcurrent.getLongitude());

        Log.e("@@",""+String.valueOf(myid)+"  "+currentDate+"  "+cuurentTime+"   "+address+"  "+String.valueOf(locationcurrent.getLatitude())+"  "+String.valueOf(locationcurrent.getLongitude())+"  "+city);


        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        //startTrip Load Map Data this api , pass deful data
        Call<PaymentInfoResponce> call1 = apiInterface.getrequestPayment(myid,currentDate,cuurentTime,address, city, String.valueOf(locationcurrent.getLatitude()),String.valueOf(locationcurrent.getLongitude()));

        call1.enqueue(new Callback<PaymentInfoResponce>() {
            @Override
            public void onResponse(Call<PaymentInfoResponce> call, Response<PaymentInfoResponce> response) {


                if (response != null && response.isSuccessful()){


                    Common.paymentInfoResponce = response.body();

                    startActivity(new Intent(MapsActivity.this, PaymentInfoActivity.class));



                }



            }

            @Override
            public void onFailure(Call<PaymentInfoResponce> call, Throwable t) {

                Toast.makeText(MapsActivity.this, "Faild to stop "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}