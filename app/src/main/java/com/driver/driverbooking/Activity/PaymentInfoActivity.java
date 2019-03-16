package com.driver.driverbooking.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.driver.driverbooking.Common;
import com.driver.driverbooking.MainActivity;
import com.driver.driverbooking.R;
import com.driver.driverbooking.response.CompleteTripResponce;
import com.driver.driverbooking.response.PaymentInfoResponce;
import com.driver.driverbooking.response.StartTripResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;
import com.driver.driverbooking.utility.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentInfoActivity extends AppCompatActivity {


    PreferenceManager preferenceManager;
    Button btn_pay,btn_finish;

    Toolbar toolbar;
    ImageView iv_back;

    LinearLayout food_lin, acc_lin;
    TextView tv_date_time, tv_trip_no, tv_amount_trip, trip_hours, tv_start_trip_time,
            tv_start_trip_add, tv_end_trip_time, tv_end_trip_add,
            tv_trip_amount_2, tv_trip_amount_gst, tv_trip_amount_cgst,
            tv_trip_amount_accomodation, tv_trip_amount_food,
            tv_pay_type, tv_trip_amount_grand;
    ApiInterface apiInterface;


    PaymentInfoResponce paymentInfoResponce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        preferenceManager = new PreferenceManager(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_pay = findViewById(R.id.btn_pay);
        btn_finish  = findViewById(R.id.btn_finish);

        iv_back= findViewById(R.id.iv_back);

        food_lin= findViewById(R.id.food_lin);
        acc_lin= findViewById(R.id.acc_lin);
        tv_date_time= findViewById(R.id.tv_date_time);
        tv_trip_no= findViewById(R.id.tv_trip_no);
        tv_amount_trip= findViewById(R.id.tv_amount_trip);
        trip_hours= findViewById(R.id.trip_hours);
        tv_start_trip_time= findViewById(R.id.tv_start_trip_time);
        tv_start_trip_add= findViewById(R.id.tv_start_trip_add);
        tv_end_trip_time= findViewById(R.id.tv_end_trip_time);
        tv_end_trip_add= findViewById(R.id.tv_end_trip_add);
        tv_trip_amount_2= findViewById(R.id.tv_trip_amount_2);
        tv_trip_amount_gst= findViewById(R.id.tv_trip_amount_gst);
        tv_trip_amount_cgst= findViewById(R.id.tv_trip_amount_cgst);
        tv_trip_amount_accomodation= findViewById(R.id.tv_trip_amount_accomodation);
        tv_trip_amount_food= findViewById(R.id.tv_trip_amount_food);
        tv_pay_type= findViewById(R.id.tv_pay_type);
        tv_trip_amount_grand= findViewById(R.id.tv_trip_amount_grand);

        paymentInfoResponce  = Common.paymentInfoResponce;


        intCode();


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void intCode() {




        tv_date_time.setText("TRIP DATE:"+paymentInfoResponce.getRequestPayment().getStartTripDate());
        tv_trip_no.setText("TRIP NO:"+paymentInfoResponce.getRequestPayment().getTripUniqueId());

        tv_amount_trip.setText(""+paymentInfoResponce.getRequestPayment().getTripAmount());
        trip_hours.setText(""+paymentInfoResponce.getRequestPayment().getTripHours());
        tv_start_trip_time.setText(""+paymentInfoResponce.getRequestPayment().getStartTripTime());
        tv_start_trip_add.setText(""+paymentInfoResponce.getRequestPayment().getTripStartPointAddress());
        tv_end_trip_time.setText(""+paymentInfoResponce.getRequestPayment().getEndTripTime());
        tv_end_trip_add.setText(""+paymentInfoResponce.getRequestPayment().getTripDropPointAddress());
        tv_trip_amount_2.setText(""+paymentInfoResponce.getRequestPayment().getTripAmount());
        tv_trip_amount_gst.setText(""+paymentInfoResponce.getRequestPayment().getGstCharge());
        tv_trip_amount_cgst.setText(""+paymentInfoResponce.getRequestPayment().getCgstCharge());
        tv_trip_amount_accomodation.setText(""+paymentInfoResponce.getRequestPayment().getTripAccommodationCharges());
        tv_trip_amount_food.setText(""+paymentInfoResponce.getRequestPayment().getTripFoodCharges());




        if (paymentInfoResponce.getRequestPayment().getTripAmount().toString().equalsIgnoreCase("1"))
        {
            tv_pay_type.setText("Cash");

        }else {
            tv_pay_type.setText("Online");

        }

        tv_pay_type.setText(""+paymentInfoResponce.getRequestPayment().getTripAmount());


        tv_trip_amount_grand.setText(""+paymentInfoResponce.getRequestPayment().getTripAmount());

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentInfoActivity.this, MainActivity.class));
                finish();

            }
        });


        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(PaymentInfoActivity.this);
                builder1.setMessage("Make sure trip payment done.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();


                                apiInterface = ApiClient.getClient().create(ApiInterface.class);
                                Call<CompleteTripResponce> call1 = apiInterface.getCompleteTrip(Integer.valueOf(paymentInfoResponce.getRequestPayment().getTripsTblId()));


                                call1.enqueue(new Callback<CompleteTripResponce>() {
                                    @Override
                                    public void onResponse(Call<CompleteTripResponce> call, Response<CompleteTripResponce> response) {

                                        if (response!=null && response.isSuccessful()){

                                            if (response.body().isCompleteTrip()){

                                                startActivity(new Intent(PaymentInfoActivity.this, MainActivity.class));
                                                finish();


                                            }else {
                                                Toast.makeText(PaymentInfoActivity.this, "faild to complete trip ", Toast.LENGTH_SHORT).show();

                                            }

                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<CompleteTripResponce> call, Throwable t) {

                                        Toast.makeText(PaymentInfoActivity.this, "faild to complete trip "+t.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });


                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();



            }
        });



    }


    @Override
    public void onBackPressed() {

    }
}
