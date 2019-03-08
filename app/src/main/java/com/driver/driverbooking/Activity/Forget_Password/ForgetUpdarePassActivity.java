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
import android.widget.Toast;

import com.driver.driverbooking.Activity.LoginDiverActivity;
import com.driver.driverbooking.R;
import com.driver.driverbooking.response.ForgetPassResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetUpdarePassActivity extends AppCompatActivity {

    String password, conpassword;
    EditText edtpassword, edtconfirmPassword;
    Button button;
    Boolean isPasswordSame = false;
    String mobileNo;
    ApiInterface apiInterface;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_updare_pass);

        dialog = new ProgressDialog(this);
        edtconfirmPassword = findViewById(R.id.forgotPassConfirm);
        edtpassword = findViewById(R.id.forgotPass);
        button = findViewById(R.id.btnForgotPass);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                password = edtpassword.getText().toString();
                conpassword = edtconfirmPassword.getText().toString();


                boolean isPasswordSame = false;

                if (TextUtils.isEmpty(password)) {
                    edtpassword.setError("Enter Password");
                }

                if (TextUtils.isEmpty(conpassword)) {
                    edtconfirmPassword.setError("Enter Confirm Password");
                }

                if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(conpassword)) {
                    if (!password.equals(conpassword)) {
                        edtconfirmPassword.setError("Enter same password");

                        isPasswordSame = false;
                    } else {


                        isPasswordSame = true;
                    }
                }

                if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(conpassword) && isPasswordSame) {

                    forPassOtp();
                }

            }
        });

    }

    private void forPassOtp() {

        dialog.setMessage("Your password changed...");
        dialog.show();
        SharedPreferences prefs = getSharedPreferences("ForPassMobileNoDriver", MODE_PRIVATE);
        mobileNo = prefs.getString("MobileNoForPassDriver", "");

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<ForgetPassResponse> call = apiInterface.getForNewPass(mobileNo, password);
        call.enqueue(new Callback<ForgetPassResponse>() {
            @Override
            public void onResponse(Call<ForgetPassResponse> call, Response<ForgetPassResponse> response) {
                dialog.dismiss();
                if (response != null && response.isSuccessful()) {
                    if (!response.body().getUpdateNewPasswod()) {
                        Toast.makeText(ForgetUpdarePassActivity.this, response.body().getErrors(), Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(ForgetUpdarePassActivity.this, LoginDiverActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgetPassResponse> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

}
