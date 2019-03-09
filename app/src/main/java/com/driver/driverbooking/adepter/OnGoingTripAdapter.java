package com.driver.driverbooking.adepter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.driver.driverbooking.R;
import com.driver.driverbooking.Common;
import com.driver.driverbooking.MapActivity.MapsActivity;
import com.driver.driverbooking.response.TripsResponse;

import java.util.ArrayList;
import java.util.List;

public class OnGoingTripAdapter extends RecyclerView.Adapter<OnGoingTripAdapter.ViewHolder> {



    Context context;
    List<TripsResponse.OngoingTrip> trips2;


    public OnGoingTripAdapter(Context context,List<TripsResponse.OngoingTrip> trips2) {
        this.context = context;
        this.trips2 = trips2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_dashboard, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        final String fisrt, last, palesh, first1, last1, palesh1, name, mobileNum, address, date, time, mytriptype, tvUsageTime;
        final int Id;

            holder.startlocation.setText(trips2.get(i).getTripPickupPointAddress());
            holder.endlocation.setText(trips2.get(i).getTripDropPointAddress());
            holder.date.setText(trips2.get(i).getTripStartDate());


            holder.tvDriverId.setText(trips2.get(i).getTripUniqueId());


            holder.payment.setText((CharSequence) trips2.get(i).getTripAmount());
            // holder.payment.setText(trips.get(i).getTripAmount());


            //  holder.round.setText(trips.get(i).getTripsType());
            holder.time.setText(trips2.get(i).getTripStartTime());

            if (trips2.get(i).getTripsType().equalsIgnoreCase("One Way Trip")) {
                holder.wayImagea.setVisibility(View.GONE);
                holder.icon.setVisibility(View.VISIBLE);

            } else if (trips2.get(i).getTripsType().equalsIgnoreCase("Round Trip")) {
                holder.desh.setVisibility(View.GONE);
                holder.icon.setVisibility(View.GONE);
                holder.wayImagea.setVisibility(View.VISIBLE);
            }

            final int itemType = getItemViewType(i);


            mytriptype = trips2.get(i).getTripsType();
            holder.firest.setText(trips2.get(i).getTripsType());
            Id = trips2.get(i).getTripId();
            Log.d("demo", "onBindViewHolder: " + Id);

            fisrt = trips2.get(i).getTripAstPickupPointLat();
            last = trips2.get(i).getTripAstPickupPointLang();
            palesh = trips2.get(i).getTripAstPickupPointName();
            first1 = trips2.get(i).getTripAstDropPointLat();
            last1 = trips2.get(i).getTripAstDropPointLang();
            palesh1 = trips2.get(i).getTripAstDropPointName();
            name = trips2.get(i).getUserName();
            mobileNum = trips2.get(i).getUserMobileNumber();
            address = trips2.get(i).getTripPickupPointAddress();//getUserAddress
            date = trips2.get(i).getTripStartDate();
            time = trips2.get(i).getTripStartTime();
            tvUsageTime = trips2.get(i).getTripAstUsage();

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (mytriptype.equals("One Way Trip")) {
                        Common.myfirstLat = Double.parseDouble(fisrt);
                        Common.myfirstLog = Double.parseDouble(last);
                        Common.mylasttLat = Double.parseDouble(first1);
                        Common.mylastlog = Double.parseDouble(last1);
                        Common.startTitle = palesh;
                        Common.lastTitle = palesh1;
                        Common.userNameinTrip = name;
                        Common.userMobileNumInTrip = mobileNum;
                        Common.userAddressinTrip = address;
                        Common.tripstartDate = date;
                        Common.startTime = time;
                        Common.myTripType = mytriptype;
                        Common.statusId =trips2.get(i).getTripStatusId()+"";
                        Common.UsageTime = tvUsageTime;
                        Intent intent = new Intent(context, MapsActivity.class);
                        intent.putExtra("id", Id);
                        context.startActivity(intent);

                    } else {

                        Common.myfirstLat = Double.parseDouble(fisrt);
                        Common.myfirstLog = Double.parseDouble(last);
                        Common.mylasttLat = Double.parseDouble(first1);
                        Common.mylastlog = Double.parseDouble(last1);
                        Common.startTitle = palesh;
                        Common.lastTitle = palesh1;
                        Common.userNameinTrip = name;
                        Common.userMobileNumInTrip = mobileNum;
                        Common.userAddressinTrip = address;
                        Common.tripstartDate = date;
                        Common.startTime = time;
                        Common.myTripType = mytriptype;
                        Common.UsageTime = tvUsageTime;
                        Common.statusId =trips2.get(i).getTripStatusId()+"";
                        Intent intent = new Intent(context, MapsActivity.class);
                        intent.putExtra("id", Id);
                        context.startActivity(intent);


                    }


                }

            });




    }


    @Override
    public int getItemCount() {

        return trips2.size();
    }




    // Define the mListener interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView booking, startlocation, endlocation, round, time;
        CardView cardView;
        TextView firest, second, desh;
        ImageView icon, wayImagea;
        TextView date, km, payment, tvDriverId;

        public ViewHolder(@NonNull final View view) {
            super(view);

            booking = (TextView) view.findViewById(R.id.tvbooking);
            startlocation = (TextView) view.findViewById(R.id.tv_startlocation);
            endlocation = (TextView) view.findViewById(R.id.tv_endlocation2);
            round = (TextView) view.findViewById(R.id.tvTrip);
            time = (TextView) view.findViewById(R.id.tvTime);
            cardView = (CardView) view.findViewById(R.id.cardview);
            desh = view.findViewById(R.id.desh);

            firest = view.findViewById(R.id.tvFirestTrip);
            icon = view.findViewById(R.id.wayImage);
            wayImagea = view.findViewById(R.id.wayImagea);

            date = view.findViewById(R.id.tvTrip_Date);
            km = view.findViewById(R.id.tvDistonsh_Km);
            payment = view.findViewById(R.id.tvPayment);
            tvDriverId = view.findViewById(R.id.tvDriverId);




        }
    }
}
