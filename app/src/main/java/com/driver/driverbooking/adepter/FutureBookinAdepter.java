package com.driver.driverbooking.adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abc.driverbooking.R;
import com.driver.driverbooking.model.FuturebookingModel;

import java.util.ArrayList;

public class FutureBookinAdepter extends RecyclerView.Adapter<FutureBookinAdepter.ViewHolderFutureBooking> {
    private static OnItemClickListener mListener;

    Context context;
    ArrayList<FuturebookingModel> futurebookingModelArrayList;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public FutureBookinAdepter(Context context, ArrayList<FuturebookingModel> futurebookingModelArrayList) {
        this.context = context;
        this.futurebookingModelArrayList = futurebookingModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolderFutureBooking onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_future_booking, viewGroup, false);


        return new ViewHolderFutureBooking(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFutureBooking viewHolderFutureBooking, int i) {
        String mytriptype;

        if (futurebookingModelArrayList.get(i).getTripsType().equals("One Way Trip")) {
            viewHolderFutureBooking.iconRoundTrip.setVisibility(View.GONE);
            viewHolderFutureBooking.iconOneTrip.setVisibility(View.VISIBLE);

        } else if (futurebookingModelArrayList.get(i).getTripsType().equals("Round Trip")) {
            viewHolderFutureBooking.desh.setVisibility(View.GONE);
            viewHolderFutureBooking.iconOneTrip.setVisibility(View.GONE);
            viewHolderFutureBooking.iconRoundTrip.setVisibility(View.VISIBLE);
        }

        mytriptype = futurebookingModelArrayList.get(i).getTripsType();

        viewHolderFutureBooking.tripType.setText(futurebookingModelArrayList.get(i).getTripsType());


//-----------------
        //  viewHolderFutureBooking.tvbooking.setText(futurebookingModelArrayList.get(i).getTripsTitle());

        viewHolderFutureBooking.tvaddresh.setText(futurebookingModelArrayList.get(i).getTripPickupPointAddress());

        viewHolderFutureBooking.tvaddresh2.setText(futurebookingModelArrayList.get(i).getTripDropPointAddress());

        viewHolderFutureBooking.tvtreep.setText(futurebookingModelArrayList.get(i).getTripsType());

        viewHolderFutureBooking.tvtime.setText(futurebookingModelArrayList.get(i).getTripStartTime());

        viewHolderFutureBooking.tvdate.setText(futurebookingModelArrayList.get(i).getTripStartDate());
        viewHolderFutureBooking.tvDriverIdFuter.setText(futurebookingModelArrayList.get(i).getTripUniqueId());

        viewHolderFutureBooking.tvPaymentfuture.setText(futurebookingModelArrayList.get(i).getTripAmount());


      /*  viewHolderFutureBooking.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "FutureBooking ", Toast.LENGTH_SHORT).show();


            }
        });*/
    }

    @Override
    public int getItemCount() {
        return futurebookingModelArrayList.size();
    }


    public FuturebookingModel getItem(int position) {
        return futurebookingModelArrayList.get(position);
    }

    // Define the mListener interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public class ViewHolderFutureBooking extends RecyclerView.ViewHolder {

        TextView tvbooking, tvaddresh, tvaddresh2, tvtreep, tvtime, tvdate,tvDriverIdFuter,tvPaymentfuture;
        CardView cardView;
        TextView desh, tripType;
        ImageView iconOneTrip, iconRoundTrip;

        public ViewHolderFutureBooking(@NonNull View itemView) {


            super(itemView);
            // tvbooking = itemView.findViewById(R.id.tvbooking_Future);

            tvaddresh = itemView.findViewById(R.id.tv_startlocation__Future);

            tvaddresh2 = itemView.findViewById(R.id.tv_endlocation2_Future);

            tvtreep = itemView.findViewById(R.id.tvTrip_Future);

            tvtime = itemView.findViewById(R.id.tvTime_Future);

            tvdate = itemView.findViewById(R.id.tvDate_Future);
            cardView = itemView.findViewById(R.id.cardviewFuture);
            tvDriverIdFuter=itemView.findViewById(R.id.tvDriverIdFuter);
            tvPaymentfuture=itemView.findViewById(R.id.tvPaymentfuture);
            desh = itemView.findViewById(R.id.desh);
            tripType = itemView.findViewById(R.id.tvTrip_Future);
            iconOneTrip = itemView.findViewById(R.id.tvoneweytrip);
            iconRoundTrip = itemView.findViewById(R.id.tvroundtrip);



/*
            tvtreep.setVisibility(itemView.GONE);*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) mListener.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }
}
