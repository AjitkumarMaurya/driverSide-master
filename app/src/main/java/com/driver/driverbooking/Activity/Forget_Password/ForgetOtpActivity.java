package com.driver.driverbooking.Activity.Forget_Password;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.driver.driverbooking.R;
import com.driver.driverbooking.response.ForgetMechOtpResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;
import com.alahammad.otp_view.OTPListener;
import com.alahammad.otp_view.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetOtpActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    OtpView otpviewForPass;

    String mobileNo;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_otp);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        otpviewForPass = findViewById(R.id.otpForPass);
        dialog = new ProgressDialog(this);
        SharedPreferences prefs = getSharedPreferences("ForPassMobileNoDriver", MODE_PRIVATE);
        mobileNo = prefs.getString("MobileNoForPassDriver", "No name defined");


        otpviewForPass.setOnOtpFinished(new OTPListener() {
            @Override
            public void otpFinished(String s) {

                dialog.setMessage("please wait...");
                dialog.show();
                retrofit2.Call<ForgetMechOtpResponse> call = apiInterface.getForPassMatcheOtp(mobileNo, s);
                call.enqueue(new Callback<ForgetMechOtpResponse>() {
                    @Override
                    public void onResponse(Call<ForgetMechOtpResponse> call, Response<ForgetMechOtpResponse> response) {
                        dialog.dismiss();
                        if (response != null && response.isSuccessful()) {
                            if (!response.body().getMatchOtp()) {
                                Toast.makeText(ForgetOtpActivity.this, response.body().getErrors(), Toast.LENGTH_SHORT).show();
                            } else {
                              //  Toast.makeText(ForgetOtpActivity.this, mobileNo, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgetOtpActivity.this, ForgetUpdarePassActivity.class);
                                startActivity(intent);

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgetMechOtpResponse> call, Throwable t) {
                        Toast.makeText(ForgetOtpActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });


            }
        });


    }
}
