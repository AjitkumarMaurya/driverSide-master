package com.driver.driverbooking.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.driver.driverbooking.MainActivity;
import com.driver.driverbooking.R;
import com.driver.driverbooking.response.ChengePasswordRespnse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText edtOldPassword, edtNewPassword, edtConfirm;
    String newPassword, old_Password, confirm;
    Boolean isPasswordSame = false;
    Button chePassword;
    int idUser;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        chePassword = findViewById(R.id.btnChengPass);
        chePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtOldPassword = findViewById(R.id.edtPass);

                edtNewPassword = findViewById(R.id.edtNewPass);
                edtConfirm = findViewById(R.id.edtConfPass);


                newPassword = edtNewPassword.getText().toString();
                old_Password = edtOldPassword.getText().toString();
                confirm = edtConfirm.getText().toString();
                SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
                idUser = prefs.getInt("userID", 0);


                if (TextUtils.isEmpty(old_Password)) {
                    edtOldPassword.setError("Enter old Password");
                }

                if (TextUtils.isEmpty(newPassword)) {
                    edtNewPassword.setError("Enter New Password");
                }
                if (TextUtils.isEmpty(confirm)) {
                    edtConfirm.setError("Enter Confirm Password");
                }


                if (!TextUtils.isEmpty(newPassword) && !TextUtils.isEmpty(confirm)) {
                    if (!newPassword.equals(confirm)) {
                        edtConfirm.setError("Enter same password");

                        isPasswordSame = false;
                    } else {


                        isPasswordSame = true;
                    }

                }

                if (!TextUtils.isEmpty(old_Password) && !TextUtils.isEmpty(newPassword) && !TextUtils.isEmpty(confirm) && isPasswordSame) {


                    forgetPassword();

                }

            }

            private void forgetPassword() {
                apiInterface = ApiClient.getClient().create(ApiInterface.class);

                Call<ChengePasswordRespnse> call = apiInterface.getChengePassw(idUser, old_Password, newPassword);
                call.enqueue(new Callback<ChengePasswordRespnse>() {
                    @Override
                    public void onResponse(Call<ChengePasswordRespnse> call, Response<ChengePasswordRespnse> response) {

                        if (response != null && response.isSuccessful()) {
                            if (!response.body().getUpdatePassword()) {
                                Toast.makeText(ResetPasswordActivity.this, response.body().getErrors(), Toast.LENGTH_SHORT).show();
                            } else {

                                Log.d("test", "onResponse: " + response.body().getUpdatePassword());
                                startActivity(new Intent(ResetPasswordActivity.this, MainActivity.class
                                ));
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ChengePasswordRespnse> call, Throwable t) {

                    }
                });


            }
        });


    }
}
