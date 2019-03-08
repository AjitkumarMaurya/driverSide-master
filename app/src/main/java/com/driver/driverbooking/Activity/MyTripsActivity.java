package com.driver.driverbooking.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.abc.driverbooking.R;
import com.driver.driverbooking.adepter.MyTripAdepter;
import com.driver.driverbooking.model.TripDetailsModel;
import com.driver.driverbooking.response.MyTripsResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;

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
    int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);
        recyclerView = findViewById(R.id.MyTripRecycler);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        idUser = prefs.getInt("userID", 0);

        setTitle("History");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MyTripsResponse> call1 = apiInterface.getMytrip(idUser);

        call1.enqueue(new Callback<MyTripsResponse>() {
            @Override
            public void onResponse(Call<MyTripsResponse> call, Response<MyTripsResponse> response) {

                if (response != null && response.isSuccessful()) {


                    tripDetailsList = response.body().getTripDetailsModels();
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
                            }else{
                                Toast.makeText(MyTripsActivity.this, "sdsdsdsd", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

/*

                    myTripAdepter.setOnItemClickListener(new MyTripAdepter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            tripDetailsModel = myTripAdepter.getItemMyTrip(position);

                            if (tripDetailsModel != null) {
                                Toast.makeText(MyTripsActivity.this, tripDetailsModel.getTripTypeId(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(MyTripsActivity.this, "dffsdf", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
*/


                }
            }

            @Override
            public void onFailure(Call<MyTripsResponse> call, Throwable t) {

            }
        });


    }
}
