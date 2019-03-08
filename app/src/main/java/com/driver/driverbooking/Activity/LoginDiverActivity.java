package com.driver.driverbooking.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.driver.driverbooking.Activity.Forget_Password.ForgetMobilenoActivity;
import com.driver.driverbooking.MainActivity;
import com.driver.driverbooking.R;
import com.driver.driverbooking.response.LoginResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;
import com.driver.driverbooking.utility.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginDiverActivity extends AppCompatActivity {
    private static final String TAG = "LoginDiverActivity";
    EditText edtName, edtpassword;
    String name, password;
    TextView forgetPass, tvError;
    Button btnLogin;
    private ApiInterface apiInterface;
    private ProgressDialog dialog;

    PreferenceManager preferenceManager;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_diver);

        preferenceManager = new PreferenceManager(this);
        edtName = findViewById(R.id.edtname);
        edtpassword = findViewById(R.id.edtpassword);
        btnLogin = findViewById(R.id.btnlogin);
        dialog = new ProgressDialog(this);
        forgetPass = findViewById(R.id.tvForgetPass);
        tvError = findViewById(R.id.tvError);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginDiverActivity.this, ForgetMobilenoActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edtName.getText().toString();
                password = edtpassword.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    edtName.setError("Please enter valid username or mobile number");
                }
                if (TextUtils.isEmpty(password)) {
                    edtpassword.setError("Please enter valid password");

                }
                dialog.setMessage("please wait...");
                dialog.show();

                Call<LoginResponse> call = apiInterface.getLogin(name, password);//developer1   developer1
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        dialog.dismiss();
                        if (response != null && response.isSuccessful()) {

                            if (!response.body().getLogin()) {
                        //        Toast.makeText(LoginDiverActivity.this, response.body().getErrors(), Toast.LENGTH_SHORT).show();
                                tvError.setText(response.body().getErrors());

                            } else {

                                userID = response.body().getLoginModel().getDriversUserId();
                                preferenceManager.setRegisteredDriverId(String.valueOf(userID));
                                preferenceManager.setLoginSession();
                                preferenceManager.setKeyValueString("driverfName",response.body().getLoginModel().getDriversFirstname()+"");
                                preferenceManager.setKeyValueString("driverlName",response.body().getLoginModel().getDriversLastname()+"");

                                preferenceManager.setKeyValueString("driveremail",response.body().getLoginModel().getDriversEmail()+"");

                                preferenceManager.setKeyValueString("driverlmobile",response.body().getLoginModel().getDriversContactNo()+"");

                                SharedPreferences.Editor editor = getSharedPreferences("MyPref", MODE_PRIVATE).edit();
                                editor.putInt("userID", userID);
                                editor.apply();

                                Intent intent = new Intent(LoginDiverActivity.this, MainActivity.class);
                                startActivity(intent);

                                edtName.setText("");
                                edtpassword.setText("");

                            }
                        }


                    }

                    @Override

                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        dialog.dismiss();
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
