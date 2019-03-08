package com.driver.driverbooking.Activity.Forget_Password;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.driver.driverbooking.R;
import com.driver.driverbooking.response.ForgotPassNumberResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;

import retrofit2.Callback;
import retrofit2.Response;

public class ForgetMobilenoActivity extends AppCompatActivity {
    EditText edtmobileForgetPass;
    Button sendOtp;
    private ApiInterface apiInterface;
    String mobile;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_mobileno);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
dialog=new ProgressDialog(this);

        edtmobileForgetPass = findViewById(R.id.edt_MobileForPassOtp);
        sendOtp = findViewById(R.id.btn_SubmitForPassOtp);

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 mobile = edtmobileForgetPass.getText().toString();

                if (TextUtils.isEmpty(mobile)) {
                    edtmobileForgetPass.setError("Please enter valid mobile number");

                } else {
                    forPass();
                }
            }
        });


    }

    private void forPass() {
        dialog.setMessage("please wait...");
        dialog.show();
        SharedPreferences.Editor editor = getSharedPreferences("ForPassMobileNoDriver", MODE_PRIVATE).edit();
        editor.putString("MobileNoForPassDriver", edtmobileForgetPass.getText().toString());

        editor.apply();




        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        // apiInterface = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<ForgotPassNumberResponse> call = apiInterface.getNumPassFor(mobile);
        call.enqueue(new Callback<ForgotPassNumberResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ForgotPassNumberResponse> call, Response<ForgotPassNumberResponse> response) {
              dialog.dismiss();
                if (response != null && response.isSuccessful()) {
                    if (!response.body().getOtpRequest()) {
                        edtmobileForgetPass.setError(response.body().getErrors());
                     //   Toast.makeText(ForgetMobilenoActivity.this, response.body().getErrors(), Toast.LENGTH_SHORT).show();
                    } else {


                        Intent intent = new Intent(ForgetMobilenoActivity.this, ForgetOtpActivity.class);



                        startActivity(intent);

                    }

                }
            }

            @Override
            public void onFailure(retrofit2.Call<ForgotPassNumberResponse> call, Throwable t) {
dialog.dismiss();
            }
        });


    }
}
