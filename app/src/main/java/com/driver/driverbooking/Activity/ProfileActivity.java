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
import android.widget.ImageView;
import android.widget.Toast;

import com.driver.driverbooking.MainActivity;
import com.abc.driverbooking.R;
import com.driver.driverbooking.response.EditResponse;
import com.driver.driverbooking.response.ProfileResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    EditText fName, lName,mobile, email, address, residAddress;
    //TextView mobile;
    ImageView imageView;
    ApiInterface apiInterface;
    Button button;
    Integer userId;
    String eFirst, eLast, eGmail, eAddress, eresidAddress;
    private ProgressDialog dialog;
    int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fName = findViewById(R.id.textView);
        email = findViewById(R.id.textView2);
        mobile = findViewById(R.id.textView3);
        address = findViewById(R.id.textView4);
        residAddress = findViewById(R.id.textView5);
        button = findViewById(R.id.btnEdit);
        lName = findViewById(R.id.nameLast);
      dialog = new ProgressDialog(this);

        imageView = findViewById(R.id.imgeroun);

       dialog.setMessage("please wait...");
        dialog.show();

        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        idUser = prefs.getInt("userID", 0);


        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileResponse> call = apiInterface.getProfile(idUser);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

              dialog.dismiss();

                if (response != null && response.isSuccessful()) {

                    //userId=response.body().getDriverProfile().getDriversUserId();
                    fName.setText(response.body().getDriverProfile().getDriversFirstname());
                    lName.setText(response.body().getDriverProfile().getDriversLastname());
                    email.setText(response.body().getDriverProfile().getDriversEmail());
                    mobile.setText(response.body().getDriverProfile().getDriversContactNo());

                    address.setText(response.body().getDriverProfile().getDriversPermAddress());
                    residAddress.setText(response.body().getDriverProfile().getDriversResiAddress());
                    Picasso.get().load(response.body().getDriverProfile().getProfileImageUrl()).into(imageView);


                }
            }


            @Override

            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.d("MYERROR", "onFailure: " + t.getMessage());
                dialog.dismiss();
            }


        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eFirst = fName.getText().toString();
                eLast = lName.getText().toString();

                eGmail = email.getText().toString();
                eAddress = address.getText().toString();
                eresidAddress = residAddress.getText().toString();



           /*     eGmail = email.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                // onClick of button perform this simplest code.
                if (eGmail.matches(emailPattern)) {

                } else {
                    email.setError("Please enter valid email address");
                }


                if (TextUtils.isEmpty(eFirst)) {

                    fName.setError("Please enter valid first name");
                }

                if (TextUtils.isEmpty(eLast)) {

                    lName.setError("Please enter valid last name");
                }


                if (TextUtils.isEmpty(eGmail) && isValidEmail(eGmail)) {
                    email.setError("Please enter valid email");

                }


                if (TextUtils.isEmpty(eAddress)) {
                    address.setError("Please enter valid address");


                    if (TextUtils.isEmpty(eresidAddress)) {
                        residAddress.setError("Please enter valid password");
                    }


                    if (!TextUtils.isEmpty(eFirst) && !TextUtils.isEmpty(eLast) && !TextUtils.isEmpty(eGmail) && !TextUtils.isEmpty(eAddress) && !TextUtils.isEmpty(eresidAddress)) {
*/
                        editProfile();


               //     }


      //          }
            }
        });


    }


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void editProfile() {


       dialog.setMessage("Data is Updating...");
        dialog.show();
        Call<EditResponse> call = apiInterface.getEditProfile(idUser, fName.getText().toString(), lName.getText().toString(), email.getText().toString(), address.getText().toString(), residAddress.getText().toString());
        call.enqueue(new Callback<EditResponse>() {
            @Override
            public void onResponse(Call<EditResponse> call, Response<EditResponse> response) {

              //  dialog.dismiss();

                if (response != null && response.isSuccessful()&&response.body().equals("")) {
                    try {

                        response.body().setFirstName(fName.getText().toString());
                        response.body().setLastName(lName.getText().toString());
                        response.body().setEmail(email.getText().toString());
                        response.body().setPermenantAddress(address.getText().toString());
                        response.body().setResidentAddress(residAddress.getText().toString());




                        Toast.makeText(ProfileActivity.this, "Data Sucessful update.", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.getLocalizedMessage();
                    }

                } else {
                    // Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }


            @Override
            public void onFailure(Call<EditResponse> call, Throwable t) {
                dialog.dismiss();
                t.getMessage();
            }
        });
    }


}
