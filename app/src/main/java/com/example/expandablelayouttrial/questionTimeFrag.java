package com.example.expandablelayouttrial;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class questionTimeFrag extends myFragment {

    private Bundle savedStates;

    private ImageView time_view;

    public questionTimeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_time, container, false);

        time_view = getActivity().findViewById(R.id.time_view);


        ImageButton no_but = view.findViewById(R.id.no_s);
        ImageButton yes_but = view.findViewById(R.id.yes_s);

        savedStates = this.getArguments();

        if(savedStates!=null){

        }else{
            savedStates = new Bundle();
        }

        if(savedStates.getInt("time_view",-1)==-1){
            time_view.setImageResource(R.drawable.edit_p);
            savedStates.putInt("time_view",R.drawable.edit_p);}


        no_but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //navigate to the summary frag
                time_view.setImageResource(R.drawable.skip_p);
                savedStates.putInt("time_view",R.drawable.skip_p);
                savedStates.putString("set_time_rmd","0");
                ((NavigationHost) getActivity()).navigateTo("sumFrag",
                        R.id.fragCon,true, "sumFrag", savedStates);
            }
        });

//        navigate to the regular setup time frag
        yes_but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo("sTFrag",
                        R.id.fragCon,true, "sTFrag", savedStates);
            }
        });

        return view;
    }

}
