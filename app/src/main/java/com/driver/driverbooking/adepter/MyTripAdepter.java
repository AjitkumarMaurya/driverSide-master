package com.driver.driverbooking.adepter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.driver.driverbooking.Activity.HistoryMyTripActivity;
import com.driver.driverbooking.Common;
import com.driver.driverbooking.R;
import com.driver.driverbooking.model.TripDetailsModel;

import java.util.ArrayList;

public class MyTripAdepter extends RecyclerView.Adapter<MyTripAdepter.MyTripModel> {

    private static OnItemClickListener mListener;

    Context context;
    ArrayList<TripDetailsModel> tripDetailsList;


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MyTripAdepter(Context context, ArrayList<TripDetailsModel> tripDetailsList) {
        this.context = context;
        this.tripDetailsList = tripDetailsList;
    }


    @NonNull
    @Override
    public MyTripAdepter.MyTripModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_my_trips, viewGroup, false);

        return new MyTripModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyTripAdepter.MyTripModel myTripModel, final int i) {

        final String curubtLocaton, dropLocation, curuntAddresh, dropAddresh, tripType, CompliteTripTime, date, time, payment, Km, userName, reting, review, TripType;

        if (tripDetailsList.get(i).getTripsType().equals("One Way Trip")) {

            myTripModel.round.setVisibility(View.GONE);
            myTripModel.onewey.setVisibility(View.VISIBLE);
        } else if (tripDetailsList.get(i).getTripsType().equals("Round Trip")) {
            myTripModel.onewey.setVisibility(View.GONE);
            myTripModel.round.setVisibility(View.VISIBLE);
        }


        myTripModel.tvCityLocation.setText(tripDetailsList.get(i).getTripAstPickupPointCityName());
        myTripModel.tvCityDropLocation.setText(tripDetailsList.get(i).getTripAstDropPointCityName());
        myTripModel.tvGreenAddresh.setText(tripDetailsList.get(i).getTripAstPickupPointName());
        myTripModel.tvRedAddreshh.setText(tripDetailsList.get(i).getTripAstDropPointName());

        myTripModel.tvTripType.setText(tripDetailsList.get(i).getTripsType());
        myTripModel.tvTripTime.setText(tripDetailsList.get(i).getTripStartTime());
        myTripModel.tvTime.setText(tripDetailsList.get(i).getTripEndTime());
        myTripModel.tvDate.setText(tripDetailsList.get(i).getTripEndDate());
        myTripModel.tvPayment.setText(tripDetailsList.get(i).getTripAmount());


        //Click Lisner
        curubtLocaton = tripDetailsList.get(i).getTripAstPickupPointCityName();
        dropLocation = tripDetailsList.get(i).getTripAstDropPointCityName();
        curuntAddresh = tripDetailsList.get(i).getTripAstPickupPointName();
        dropAddresh = tripDetailsList.get(i).getTripAstDropPointName();
        tripType = tripDetailsList.get(i).getTripsType();
        CompliteTripTime = tripDetailsList.get(i).getTripStartTime();

        date = tripDetailsList.get(i).getTripEndDate();
        time = tripDetailsList.get(i).getTripEndTime();
        payment = tripDetailsList.get(i).getTripAmount();
        Km = tripDetailsList.get(i).getTripKmsDriven();
        reting = tripDetailsList.get(i).getUsersTripRating();
        userName = tripDetailsList.get(i).getTripUserName();
        review = tripDetailsList.get(i).getUsersTripNote();


        /*userName=tripDetailsList.get(i).getname*/
        myTripModel.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.curontL = curubtLocaton;
                Common.dropL = dropLocation;
                Common.curuntA = curuntAddresh;
                Common.dropA = dropAddresh;
                Common.tripTyp = tripType;
                Common.CompliteTripTi = CompliteTripTime;
                Common.time = time;
                Common.date = date;
                Common.payment = payment;
                Common.km = Km;
                Common.reting = reting;
                Common.userName = userName;

                try {
                    Common.myfirstLat2 = Double.valueOf(tripDetailsList.get(i).getTripDropPointLat());
                    Common.myfirstLog2 = Double.valueOf(tripDetailsList.get(i).getTripDropPointLang());
                    Common.mylasttLat1 = Double.valueOf(tripDetailsList.get(i).getTripPickupPointLat());
                    Common.mylastlog1 = Double.valueOf(tripDetailsList.get(i).getTripPickupPointLang());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Common.review = review;


                Intent intent = new Intent(context, HistoryMyTripActivity.class);


                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return tripDetailsList.size();
    }

    public TripDetailsModel getItema(int position) {
        return tripDetailsList.get(position);
    }

    // Define the mListener interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public class MyTripModel extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvCityLocation, tvCityDropLocation, tvGreenAddresh, tvRedAddreshh, tvTripType, tvTripTime, tvTime, tvDate, tvPayment;
        ImageView onewey, round;

        public MyTripModel(@NonNull View itemView) {
            super(itemView);
            tvCityLocation = itemView.findViewById(R.id.tvPleshGoing);

            tvCityDropLocation = itemView.findViewById(R.id.tvPleshFrom);
            tvGreenAddresh = itemView.findViewById(R.id.tvGreenText);
            tvRedAddreshh = itemView.findViewById(R.id.tvradeText);
            tvTripTime = itemView.findViewById(R.id.tvTimeText);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPayment = itemView.findViewById(R.id.tvPayment);
            cardView = itemView.findViewById(R.id.cardViewMyTrip);
            tvTripType = itemView.findViewById(R.id.tvTripType);
            onewey = itemView.findViewById(R.id.onewey);
            round = itemView.findViewById(R.id.round);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) mListener.onItemClick(v, getAdapterPosition());
                }
            });

        }
    }
}
