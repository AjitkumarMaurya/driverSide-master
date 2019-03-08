package com.driver.driverbooking.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.driver.driverbooking.R;
import com.driver.driverbooking.response.DriverTermsConditionResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverTermsConditionActivity extends AppCompatActivity {
    TextView view;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_terms_condition);
        view = findViewById(R.id.termsCondison);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<DriverTermsConditionResponse> call = apiInterface.getDriverTermsCondition();
        call.enqueue(new Callback<DriverTermsConditionResponse>() {
            @Override
            public void onResponse(Call<DriverTermsConditionResponse> call, Response<DriverTermsConditionResponse> response) {


                if (response != null && response.isSuccessful()) {

                    view.setText(response.body().getDriverTermsCondition());
                }
            }

            @Override
            public void onFailure(Call<DriverTermsConditionResponse> call, Throwable t) {

            }
        });

    }
}
