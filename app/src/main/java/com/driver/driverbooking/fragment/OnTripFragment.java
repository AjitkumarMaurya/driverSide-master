package com.driver.driverbooking.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.driver.driverbooking.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnTripFragment extends Fragment {


    TextView tv_no_data;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;

    public OnTripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_on_trip, container, false);

        tv_no_data = v.findViewById(R.id.swipe);
        recyclerView = v.findViewById(R.id.onRecyclerview);
        refreshLayout = v.findViewById(R.id.swipe);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





    }
}
