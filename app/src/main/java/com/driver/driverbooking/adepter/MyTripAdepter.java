package com.driver.driverbooking.adepter;

import android.annotation.SuppressLint;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyTripAdepter.MyTripModel myTripModel, final int i) {

        myTripModel.tvTripType.setText(tripDetailsList.get(i).getTripsType());

        if (tripDetailsList.get(i).getTripsType().equals("One Way Trip")) {


        } else if (tripDetailsList.get(i).getTripsType().equals("Round Trip")) {


        }


        myTripModel.tv_drop_address.setText(""+tripDetailsList.get(i).getTripAstDropPointName());
        myTripModel.tv_address_pick.setText(""+tripDetailsList.get(i).getTripPickupPointName());
        myTripModel.tv_end_time.setText(""+tripDetailsList.get(i).getTripEndTime());
        myTripModel.tv_start_time.setText(""+tripDetailsList.get(i).getTripStartTime());

        myTripModel.tv_trip_hour.setText("TRIP HOUR:"+tripDetailsList.get(i).getTripUsage());
        myTripModel.tv_trip_no.setText("TRIP NO:"+tripDetailsList.get(i).getTripUniqueId());

        myTripModel.tvDate.setText(""+tripDetailsList.get(i).getTripEndDate());
        myTripModel.tv_pay_type.setText(""+tripDetailsList.get(i).getTripPaymentType());
       myTripModel.tvPayment.setText(""+tripDetailsList.get(i).getTripAmount());

        if (tripDetailsList.get(i).getTripStatusId().equalsIgnoreCase("5")){
            myTripModel.tv_trip_status.setVisibility(View.VISIBLE);

            myTripModel.tv_trip_status.setImageResource(R.drawable.completw_bg);

        }else if (tripDetailsList.get(i).getTripStatusId().equalsIgnoreCase("6")){
            myTripModel.tv_trip_status.setVisibility(View.VISIBLE);

            myTripModel.tv_trip_status.setImageResource(R.drawable.cancel_bg);

        }else {
            myTripModel.tv_trip_status.setVisibility(View.GONE);

        }





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
        TextView tv_pay_type, tv_drop_address, tvDate, tv_address_pick,tv_end_time,tv_start_time,tvTripType,tv_trip_hour,
                tv_trip_no,tvPayment;
        ImageView tv_trip_status;

        public MyTripModel(@NonNull View itemView) {
            super(itemView);
            tv_pay_type = itemView.findViewById(R.id.tv_pay_type);

            tv_drop_address = itemView.findViewById(R.id.tv_drop_address);
            tvDate = itemView.findViewById(R.id.tvDate);
            tv_address_pick = itemView.findViewById(R.id.tv_address_pick);
            tv_end_time = itemView.findViewById(R.id.tv_end_time);
            tv_start_time = itemView.findViewById(R.id.tv_start_time);
            tvDate = itemView.findViewById(R.id.tvDate);
            tv_trip_status = itemView.findViewById(R.id.tv_trip_status);
            cardView = itemView.findViewById(R.id.cardViewMyTrip);
            tvTripType = itemView.findViewById(R.id.tvTripType);
            tv_trip_hour = itemView.findViewById(R.id.tv_trip_hour);
            tv_trip_no = itemView.findViewById(R.id.tv_trip_no);
            tvPayment = itemView.findViewById(R.id.tvPayment);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) mListener.onItemClick(v, getAdapterPosition());
                }
            });

        }
    }
}
