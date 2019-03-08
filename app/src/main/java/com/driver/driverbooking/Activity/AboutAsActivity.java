package com.driver.driverbooking.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.driver.driverbooking.response.AboutsAsResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;

import com.driver.driverbooking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutAsActivity extends AppCompatActivity {
    TextView aboustAs;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_as);
        aboustAs = findViewById(R.id.aboutAs);


        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<AboutsAsResponse> call = apiInterface.getAboutsAs();

        call.enqueue(new Callback<AboutsAsResponse>() {
            @Override
            public void onResponse(Call<AboutsAsResponse> call, Response<AboutsAsResponse> response) {
                if (response != null && response.isSuccessful()) {

                    aboustAs.setText(response.body().getDriverAboutUs());
                }
            }

            @Override
            public void onFailure(Call<AboutsAsResponse> call, Throwable t) {

            }
        });


    }
}
