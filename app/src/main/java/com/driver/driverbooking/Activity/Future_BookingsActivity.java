package com.driver.driverbooking.Activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.driver.driverbooking.R;
import com.driver.driverbooking.adepter.FutureBookinAdepter;
import com.driver.driverbooking.model.FuturebookingModel;
import com.driver.driverbooking.response.FutureBookingResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Future_BookingsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ApiInterface apiInterface;
    FutureBookinAdepter futureBookinAdepter;
    ArrayList<FuturebookingModel> futureArrayList = new ArrayList<>();
    FuturebookingModel futurebookingModel;
    ImageView imageView;
    TextView tvname, tvnamelast, tvbooking;
    int idUser;
    private ProgressDialog dialog;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future__bookings);

        imageView = findViewById(R.id.img_fuBooling);
        tvname = findViewById(R.id.tv_name_fu);
        tvbooking = findViewById(R.id.tv_booking_fu);
        tvnamelast = findViewById(R.id.tv_nameLast_Fu);

        dialog = new ProgressDialog(this);
        dialog.setMessage("please wait...");
        dialog.show();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        recyclerView = (RecyclerView) findViewById(R.id.futureBookingRecyclerview);


        // Adepter in  Recyclerview  add
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        idUser = prefs.getInt("userID", 0);


        Call<FutureBookingResponse> call = apiInterface.getFucherBooking(idUser);
        call.enqueue(new Callback<FutureBookingResponse>() {
            @Override
            public void onResponse(Call<FutureBookingResponse> call, Response<FutureBookingResponse> response) {
                dialog.dismiss();
                if (response != null && response.isSuccessful()) {

                    tvname.setText(response.body().getDriverProfile().getDriversFirstname());

                    tvnamelast.setText(response.body().getDriverProfile().getDriversLastname());


                    tvbooking.setText(response.body().getDriverProfile().getTripCount() + "");

                    futureArrayList = response.body().getFuturebookingModelArrayList();


                    Picasso.get().load(response.body().getDriverProfile().getProfileImageUrl()).into(imageView);

                 /*   futureBookinAdepter = new FutureBookinAdepter(Future_BookingsActivity.this, futureArrayList);
                 //   recyclerView.setLayoutManager(new LinearLayoutManager(Future_BookingsActivity.this, LinearLayout.VERTICAL, false));
                   // recyclerView.setHasFixedSize(true);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(futureBookinAdepter);
                 */

                    futureBookinAdepter = new FutureBookinAdepter(Future_BookingsActivity.this, futureArrayList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Future_BookingsActivity.this, LinearLayout.VERTICAL, false));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(futureBookinAdepter);
                    futureBookinAdepter.setOnItemClickListener(new FutureBookinAdepter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            futurebookingModel = futureBookinAdepter.getItem(position);

                            if (futurebookingModel != null) {
                               // Toast.makeText(Future_BookingsActivity.this, futurebookingModel.getDriversUserId(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<FutureBookingResponse> call, Throwable t) {
                dialog.dismiss();
                Log.d("test", "onFailure: " + t.getMessage());
            }
        });

    }


}
