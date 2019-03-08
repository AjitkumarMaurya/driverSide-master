package com.driver.driverbooking.Activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.driver.driverbooking.R;
import com.driver.driverbooking.response.FutureBookingResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentsActivity extends AppCompatActivity {
    ImageView addar, lisansh, residant;

    private ApiInterface apiInterface;
    private ProgressDialog dialog;
int idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        addar = findViewById(R.id.documentAddar);
        lisansh = findViewById(R.id.documentLisansh);
        residant = findViewById(R.id.documentAddress);
        dialog = new ProgressDialog(this);

        setTitle("My Documents");
        dialog.setMessage("please wait...");
        dialog.show();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        idUser = prefs.getInt("userID", 0);


        Call<FutureBookingResponse> call = apiInterface.getFucherBooking(idUser);
        call.enqueue(new Callback<FutureBookingResponse>() {
                         @Override
                         public void onResponse(Call<FutureBookingResponse> call, Response<FutureBookingResponse> response) {
                             dialog.dismiss();
                             if (response != null && response.isSuccessful()) {
                              /*   Glide.with(DocumentsActivity.this).load(response.body().getDriverProfile().getAadharImageUrl()).into(addar);
                                 Glide.with(DocumentsActivity.this).load(response.body().getDriverProfile().getLicenceImageUrl()).into(lisansh);
                                 Glide.with(DocumentsActivity.this).load(response.body().getDriverProfile().getResidenceImageUrl()).into(residant);
                              */   Picasso.get().load(response.body().getDriverProfile().getAadharImageUrl()).into(addar);
                                 Picasso.get().load(response.body().getDriverProfile().getLicenceImageUrl()).into(lisansh);
                                 Picasso.get().load(response.body().getDriverProfile().getResidenceImageUrl()).into(residant);


                             }
                         }

                         @Override
                         public void onFailure(Call<FutureBookingResponse> call, Throwable t) {
                             dialog.dismiss();
                             t.getMessage();
                         }
                     }
        );


    }
}
