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
public class questionStateFrag extends Fragment {

    private Bundle savedStates;
    private ImageView state_view;

    public questionStateFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_state, container, false);

        state_view = getActivity().findViewById(R.id.state_view);

        ImageButton no_but = view.findViewById(R.id.s_no);
        ImageButton yes_but = view.findViewById(R.id.s_yes);

        savedStates = this.getArguments();

        if(savedStates!=null){

        }else{
            savedStates = new Bundle();
        }

        if(savedStates.getInt("state_view",-1)==-1){
            state_view.setImageResource(R.drawable.edit_p);
            savedStates.putInt("state_view",R.drawable.edit_p);}

        yes_but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo("qSSFrag",
                        R.id.fragCon,true, "qSSFrag", savedStates);
            }
        });

        no_but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                state_view.setImageResource(R.drawable.skip_p);
                savedStates.putInt("state_view",R.drawable.skip_p);

                savedStates.putString("set_state_rmd","0");
                ((NavigationHost) getActivity()).navigateTo("qTFrag",
                        R.id.fragCon,true, "qTFrag", savedStates);
            }
        });


        return view;
    }

}
