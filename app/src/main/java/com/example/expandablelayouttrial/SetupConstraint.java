package com.example.expandablelayouttrial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class SetupConstraint extends Fragment {

    private View v;

    private CriteriaListAdapter criteria_adapter;

    private String act_name;
    private String start_time_hr;
    private String start_time_min;
    private String duration;
    private String week_day; //in the form of 0010010

    private TextView act_name_view;

    private int[] checkStatus = new int[6];

    private AtomPayment item_data;

    private ListView atomPaysListView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //don't use findByID here

        View view = inflater.inflate(R.layout.setup_constraints,container,false);
        v = view;


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { //do find by ID here

        super.onViewCreated(view, savedInstanceState);
    }



//    public void checkTracker(View v) { //if one of the weekday was pressed
//        AtomPayment condition = (AtomPayment) v.getTag();
//
//        if(condition.getCheck()==false) {
//            condition.setChecked(true);
//            checkStatus[Integer.parseInt(condition.getID())] = 1;
//        }
//        else {
//            condition.setChecked(false);
//            checkStatus[Integer.parseInt(condition.getID())] = 0;
//        }
//
//        //record the current status of the weekdays
//    }
}
