package com.driver.driverbooking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.driver.driverbooking.Activity.AboutAsActivity;
import com.driver.driverbooking.Activity.ContactUsActivity;
import com.driver.driverbooking.Activity.DocumentsActivity;
import com.driver.driverbooking.Activity.DriverTermsConditionActivity;
import com.driver.driverbooking.Activity.Future_BookingsActivity;
import com.driver.driverbooking.Activity.MyTripsActivity;
import com.driver.driverbooking.Activity.ProfileActivity;
import com.driver.driverbooking.Activity.ResetPasswordActivity;
import com.abc.driverbooking.R;
import com.driver.driverbooking.adepter.DashboardAdepter;
import com.driver.driverbooking.model.TripsModel;
import com.driver.driverbooking.response.TripsResponse;
import com.driver.driverbooking.retrofit.ApiClient;
import com.driver.driverbooking.retrofit.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView imageView, imageView1;
    RecyclerView recyclerView;
    RecyclerView recyclerViewOn;
    ApiInterface apiInterface;
    DashboardAdepter dashboardAdepter;
    DashboardAdepter dashboardAdepterOn;

    SwipeRefreshLayout swipe;
    ArrayList<TripsModel> tripsModelsarray = new ArrayList<>();
    ArrayList<TripsResponse.OngoingTrip> tripOn = new ArrayList<>();

    TextView firestName, lastName;

     ImageView back;

    TextView tvNameToday, tvDateToday, tvTodaybooking, tvNameLast;
     private ProgressDialog dialog;

    TripsModel tripsModel;
    String fisrt, last, palesh, first1, last1, palesh1;
    int restoredText;
    String myid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        //


        View header = navigationView.getHeaderView(0);
        View headerfname = navigationView.getHeaderView(0);
        View headerlname = navigationView.getHeaderView(0);
        imageView1 = (ImageView) header.findViewById(R.id.imgeroun);

        firestName = (TextView) headerfname.findViewById(R.id.firstNameheder);
        lastName = (TextView) headerlname.findViewById(R.id.lastNameheder);


        navigationView.setNavigationItemSelectedListener(this);

//===============================================
        tvNameToday = findViewById(R.id.tvNameToday);
        tvDateToday = findViewById(R.id.tvDateYourbooking);
        tvTodaybooking = findViewById(R.id.tvYourBooking);
        tvNameLast = findViewById(R.id.tvNameTodaylast);

        imageView = findViewById(R.id.imgTodayBooking);
        swipe = findViewById(R.id.swipe);
        dialog = new ProgressDialog(this);
        //=================================
        recyclerView = (RecyclerView) findViewById(R.id.dashboradRecyclerview);
        recyclerViewOn = findViewById(R.id.dashboradOnRecyclerview);


        // Adepter in  Recyclerview  add
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOn.setLayoutManager(new LinearLayoutManager(this));


        // userId = getIntent().getStringExtra("userIdKey");

        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        restoredText = prefs.getInt("userID", 0);

        myid = Integer.toString(restoredText);


        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                dashboard();
            }
        });

        dialog.setMessage("please wait...");
        dialog.show();
        dashboard();


    }

    private void dashboard() {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<TripsResponse> call = apiInterface.getDashboard(myid);
        call.enqueue(new Callback<TripsResponse>() {

            @Override
            public void onResponse(Call<TripsResponse> call, Response<TripsResponse> response) {
                swipe.setRefreshing(false);

                dialog.dismiss();
                if (response.isSuccessful()) {

                    Picasso.get().load(response.body().getDriverProfile().getProfileImageUrl()).into(imageView1);





                /*    firestName.setText(response.body().getDriverProfile().getDriversFirstname());
                    lastName.setText(response.body().getDriverProfile().getDriversLastname());
*/
                    Picasso.get().load(response.body().getDriverProfile().getProfileImageUrl()).into(imageView);
                    //    response.body().getDriverProfile().getDriversFirstname()
                    firestName.setText(response.body().getDriverProfile().getDriversFirstname());
                    lastName.setText(response.body().getDriverProfile().getDriversLastname());



//Glide.with(MainActivity.this).load(response.body().getDriverProfile().getProfileImageUrl()).into(imageView2);
                    //   userId=response.body().getDriverProfile().getDriversUserId();

                    tvDateToday.setText(response.body().getDriverProfile().getTodayDate());
                    tvTodaybooking.setText(response.body().getDriverProfile().getTripCount() + "");
                    tvNameLast.setText(response.body().getDriverProfile().getDriversLastname());
                    tvNameToday.setText(response.body().getDriverProfile().getDriversFirstname());


                    tripsModelsarray = response.body().getTripsModels();


                    recyclerView = (RecyclerView) findViewById(R.id.dashboradRecyclerview);
                    recyclerViewOn = (RecyclerView) findViewById(R.id.dashboradRecyclerview);


                    dashboardAdepter = new DashboardAdepter(MainActivity.this, tripsModelsarray,tripOn,false);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.VERTICAL, false));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(dashboardAdepter);


                    dashboardAdepterOn = new DashboardAdepter(MainActivity.this, tripsModelsarray,tripOn,true);
                    recyclerViewOn.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.VERTICAL, false));
                    recyclerViewOn.setHasFixedSize(true);
                    recyclerViewOn.setItemAnimator(new DefaultItemAnimator());
                    recyclerViewOn.setAdapter(dashboardAdepterOn);


                    dashboardAdepter.setOnItemClickListener(new DashboardAdepter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                           // tripsModel = dashboardAdepter.getItem(position);
                            //    Toast.makeText(MainActivity.this, first1, Toast.LENGTH_SHORT).show();

                            fisrt = tripsModel.getTripAstPickupPointLat();
                            last = tripsModel.getTripAstPickupPointLang();
                            palesh = tripsModel.getTripAstPickupPointName();
                            first1 = tripsModel.getTripAstDropPointLat();
                            last1 = tripsModel.getTripAstDropPointLang();
                            palesh1 = tripsModel.getTripAstDropPointName();


                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<TripsResponse> call, Throwable t) {
                swipe.setRefreshing(false);

                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Data not recived "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.cuntactUsSeting) {
            startActivity(new Intent(this, ContactUsActivity.class));
            return true;
        }
        if (id == R.id.aboutAsSeting) {
            startActivity(new Intent(this, AboutAsActivity.class));

        }
        if (id == R.id.terms_Condition) {
            startActivity(new Intent(this, DriverTermsConditionActivity.class));

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_My_Profile) {
            startActivity(new Intent(this, ProfileActivity.class));
        }
        if (id == R.id.nav_My_Trips) {
            startActivity(new Intent(this, MyTripsActivity.class));
        }
        if (id == R.id.nav_Cureewnt_booking) {
            // startActivity(new Intent(this,));
        }
        if (id == R.id.nav_Future_Bookings) {
            startActivity(new Intent(this, Future_BookingsActivity.class));

        }

        if (id == R.id.nav_Documents) {
            startActivity(new Intent(MainActivity.this, DocumentsActivity.class));

        }
        if (id == R.id.nav_ResetPassword) {
            startActivity(new Intent(MainActivity.this, ResetPasswordActivity.class));
        }
        if (id == R.id.nav_Logout) {

            SharedPreferences preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            finish();


            //startActivity(new Intent(MainActivity.this, LoginDiverActivity.class));
        }


   /*
        else if (id==R.id.nav_Telugu) {
            Constants.SELECTED = Constants.TeluguFragment;
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


}
