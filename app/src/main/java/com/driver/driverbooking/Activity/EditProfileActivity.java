package com.driver.driverbooking.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.driver.driverbooking.MainActivity;
import com.driver.driverbooking.R;
import com.driver.driverbooking.response.EditResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    EditText edtFname, edtLname, edtEmail, edtPermanent, edtResidential;
    String fName, lName, email, preAddress, resAddress;
    private ProgressDialog dialog;

    Button btnSubmiit;
    ApiInterface apiInterface;
    int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        edtFname = findViewById(R.id.edtNameEdit);
        edtLname = findViewById(R.id.edtLastEdit);
        edtEmail = findViewById(R.id.edtEmailEdit);
        edtPermanent = findViewById(R.id.edtPermanentEdit);
        edtResidential = findViewById(R.id.edtResidentialEdit);
        btnSubmiit = findViewById(R.id.btnEdit);
        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        idUser = prefs.getInt("userID", 0);

        dialog = new ProgressDialog(this);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        if (TextUtils.isEmpty(fName)) {
            edtFname.setError("Please enter valid firest name");
        }
        if (TextUtils.isEmpty(lName)) {
            edtLname.setError("Please enter valid last name");
        }
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Please enter valid email id");
        }
        if (TextUtils.isEmpty(preAddress)) {
            edtPermanent.setError("Please enter valid permanent address");
        }
        if (TextUtils.isEmpty(resAddress)) {
            edtResidential.setError("Please enter valid residential address");
        }


        btnSubmiit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("please wait...");
                dialog.show();
                Call<EditResponse> call = apiInterface.getEditProfile(idUser, edtFname.getText().toString(), edtLname.getText().toString(), edtEmail.getText().toString(), edtPermanent.getText().toString(), edtResidential.getText().toString());
                call.enqueue(new Callback<EditResponse>() {
                    @Override
                    public void onResponse(Call<EditResponse> call, Response<EditResponse> response) {
                        dialog.dismiss();
                        if (response != null && response.isSuccessful() && response.body().equals("")) {
                            try {

                                //      Toast.makeText(EditProfileActivity.this, "sdasda", Toast.LENGTH_SHORT).show();

                                fName = edtFname.getText().toString();
                                lName = edtLname.getText().toString();
                                email = edtEmail.getText().toString();
                                preAddress = edtPermanent.getText().toString();
                                resAddress = edtResidential.getText().toString();


                                response.body().setFirstName(edtFname.getText().toString());
                                response.body().setLastName(edtLname.getText().toString());
                                response.body().setEmail(edtEmail.getText().toString());
                                response.body().setPermenantAddress(edtPermanent.getText().toString());
                                response.body().setResidentAddress(edtResidential.getText().toString());

                              /*  Log.d("test", "f__Name: " + fName);

                                Log.d("test", "l__Name: " + lName);

                                Log.d("test", "email__: " + email);
                                Log.d("test", "pre__Address: " + preAddress);
                                Log.d("test", "edt___Residential: " + resAddress);

*/

                            } catch (Exception e) {
                                e.getLocalizedMessage();
                            }

                        } else {
                            //       Toast.makeText(EditProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                        startActivity(new Intent(EditProfileActivity.this, MainActivity.class));

                    }


                    @Override
                    public void onFailure(Call<EditResponse> call, Throwable t) {
                        dialog.dismiss();
                        t.getMessage();
                    }
                });
            }
        });


    }

}
