package com.driver.driverbooking.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.driver.driverbooking.Common;
import com.driver.driverbooking.R;
import com.driver.driverbooking.retrofit.ApiInterface;
import com.driver.driverbooking.utility.FetchURL;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class HistoryMyTripActivity extends AppCompatActivity implements OnMapReadyCallback {
    ApiInterface apiInterface;
    TextView curuntlocation, droplocation, curuntAddress, droupAddress, triptype, time, date, amount, kM, review, userName;
    RatingBar ratingBar;

    ImageView imgRound,imgOnewey;
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    Marker dest, curr;
    float red = 0, blue = 240;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_my_trip);
        setTitle("History");
/*
        curuntlocation=findViewById(R.id.tvGreenText);
        droplocation=findViewById(R.id.tvradeText);*/
        curuntAddress = findViewById(R.id.tvGreenText);
        droupAddress = findViewById(R.id.tvradeText);

        triptype = findViewById(R.id.tvTripType);
        time = findViewById(R.id.tvTime);
        date = findViewById(R.id.tvTrip_Date);
        amount = findViewById(R.id.tvPayment);
        kM = findViewById(R.id.tvDistonsh_Km);
        review = findViewById(R.id.tvreview);
        ratingBar = findViewById(R.id.retingBar);
        userName = findViewById(R.id.tvUserName);
        imgOnewey=findViewById(R.id.onetrip);
        imgRound=findViewById(R.id.roundtrip);

        Intent intent = getIntent();
        String user_name = intent.getStringExtra("curontL");



        if (triptype.equals("One Way Trip")) {
        //    triptype.setText("One Way Trip");
            imgRound.setVisibility(View.GONE);
            imgOnewey.setVisibility(View.VISIBLE);

        } else if (triptype.equals("Round Trip")){
         //   triptype.setText("Round Trip");
            imgOnewey.setVisibility(View.GONE);
            imgRound.setVisibility(View.VISIBLE);
        }



        //curuntlocation.setText(user_name);
        // curuntlocation.setText(Common.curont);
        curuntAddress.setText(Common.curuntA+" , "+ Common.curontL);
        droupAddress.setText(Common.dropA+","+ Common.dropL);
        triptype.setText(Common.tripTyp);
        time.setText(Common.time);
        date.setText(Common.date);
        amount.setText(Common.payment);
        kM.setText(Common.km);

        userName.setText(Common.userName);

        try {
            ratingBar.setRating(Float.parseFloat(Common.reting));
        } catch (Exception e) {
            e.printStackTrace();
        }

        review.setText(Common.review);




        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map_22);
        if (mapFragment!=null){
            mapFragment.getMapAsync(this);

        }


    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mMap = googleMap;
                LatLng destination = new LatLng(Common.myfirstLat2,Common.myfirstLog2);
                LatLng Current_loc = new LatLng(Common.mylasttLat1,Common.mylastlog1);

                if (destination != null) {
                    if (dest != null) {
                        dest.remove();

                    }
                    dest = mMap.addMarker(new MarkerOptions().position(destination).title("" + Common.dropL).icon(BitmapDescriptorFactory.defaultMarker(red)));
                    Log.e("deeee", "" + dest);

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 15f));


                }
                if (Current_loc != null) {
                    if (curr != null) {
                        Log.e("blankkk", "" + curr);
                        curr.remove();


                    }
                    curr = mMap.addMarker(new MarkerOptions().position(Current_loc).title("" + Common.curontL).icon(BitmapDescriptorFactory.defaultMarker(blue)));

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Current_loc, 12f));

                }

                if (destination != null && Current_loc != null) {


                    String url = getUrl(destination, Current_loc, "driving");

                    Log.e("url", " 11111      " + url);

                    FetchURL fetchURL = new FetchURL(HistoryMyTripActivity.this, mMap);

                    fetchURL.execute(url);


                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 11f));


                }
            }
        }, 1000);


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
}
