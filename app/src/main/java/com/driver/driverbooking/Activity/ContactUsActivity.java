package com.driver.driverbooking.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.abc.driverbooking.R;
import com.driver.driverbooking.response.ContactUsResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity {
    TextView tvMobile, tvContactUs,tvEmail;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        tvMobile = findViewById(R.id.contactUS_No);
        tvContactUs = findViewById(R.id.contactUS_Text);
        tvEmail=findViewById(R.id.tvEmail);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ContactUsResponse> call = apiInterface.getContactUs();
        call.enqueue(new Callback<ContactUsResponse>() {
            @Override
            public void onResponse(Call<ContactUsResponse> call, Response<ContactUsResponse> response) {
                if (response != null && response.isSuccessful()) {
                    tvMobile.setText(response.body().getUserContactUsNumber());
                    tvContactUs.setText(response.body().getUserContactUs());
                    tvEmail.setText(response.body().getUserContactUsEmail());

                }
            }

            @Override
            public void onFailure(Call<ContactUsResponse> call, Throwable t) {

            }
        });

    }
}
