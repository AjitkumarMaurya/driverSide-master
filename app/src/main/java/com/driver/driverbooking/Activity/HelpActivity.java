package com.driver.driverbooking.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.abc.driverbooking.R;
import com.driver.driverbooking.response.PrivacyPolicyResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpActivity extends AppCompatActivity {

    TextView txt_privacyPolicy;
    private ApiInterface apiInterface;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        dialog = new ProgressDialog(this);

        dialog.setMessage("Please wait..");
        dialog.show();


        txt_privacyPolicy = findViewById(R.id.txt_privacyPolicy);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<PrivacyPolicyResponse> call = apiInterface.getPrivacypolicy();//developer1   developer1
        call.enqueue(new Callback<PrivacyPolicyResponse>() {
            @Override
            public void onResponse(Call<PrivacyPolicyResponse> call, Response<PrivacyPolicyResponse> response) {
                dialog.dismiss();
                if (response != null && response.isSuccessful()) {

                    txt_privacyPolicy.setText(response.body().getDriverPrivacyPolicy());


                }


            }

            @Override

            public void onFailure(Call<PrivacyPolicyResponse> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }
}
