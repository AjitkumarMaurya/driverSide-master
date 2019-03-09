package com.driver.driverbooking.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.driver.driverbooking.R;
import com.driver.driverbooking.adepter.MyTripAdepter;
import com.driver.driverbooking.model.TripDetailsModel;
import com.driver.driverbooking.response.MyTripsResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;
import com.driver.driverbooking.utility.PreferenceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTripsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ApiInterface apiInterface;
    TripDetailsModel tripDetailsModel;
    ArrayList<TripDetailsModel> tripDetailsList = new ArrayList<>();
    MyTripAdepter myTripAdepter;
    String idUser;

    PreferenceManager preferenceManager;

    TextView tv_no_data;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);
        recyclerView = findViewById(R.id.MyTripRecycler);

        tv_no_data = findViewById(R.id.tv_no_data);
        progressBar = findViewById(R.id.ps_br);
        swipeRefreshLayout = findViewById(R.id.swipe);

        preferenceManager = new PreferenceManager(this);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        idUser = preferenceManager.getRegisteredDriverId();

        setTitle("My History");

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        tv_no_data.setVisibility(View.GONE);

        initCode();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                initCode();

            }
        });

    }

    public void initCode(){

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MyTripsResponse> call1 = apiInterface.getMytrip(idUser);

        call1.enqueue(new Callback<MyTripsResponse>() {
            @Override
            public void onResponse(Call<MyTripsResponse> call, Response<MyTripsResponse> response) {

                swipeRefreshLayout.setRefreshing(false);

                if (response != null && response.isSuccessful()) {
                    tripDetailsList = response.body().getTripDetailsModels();


                    try {
                        if (tripDetailsList.size()>0) {
                            recyclerView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            tv_no_data.setVisibility(View.GONE);

                            myTripAdepter = new MyTripAdepter(MyTripsActivity.this, tripDetailsList);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MyTripsActivity.this, LinearLayout.VERTICAL, false));
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(myTripAdepter);


                            myTripAdepter.setOnItemClickListener(new MyTripAdepter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {

                                    tripDetailsModel = myTripAdepter.getItema(position);

                                    if (tripDetailsModel != null) {
                                        Toast.makeText(MyTripsActivity.this, tripDetailsModel.getTripAstDropPointCityName(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(MyTripsActivity.this, "sd", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MyTripsActivity.this, "sdsdsdsd", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }else {

                            recyclerView.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            tv_no_data.setVisibility(View.VISIBLE);


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        recyclerView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        tv_no_data.setVisibility(View.VISIBLE);


                    }


                }else {

                    recyclerView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<MyTripsResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

                Toast.makeText(MyTripsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
