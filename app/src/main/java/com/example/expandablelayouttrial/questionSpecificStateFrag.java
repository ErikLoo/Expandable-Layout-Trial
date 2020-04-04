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
public class questionSpecificStateFrag extends Fragment {

    private Bundle savedStates;
    private ImageView state_view;
    public questionSpecificStateFrag() {
        // Required empty public constructor
    }

//    private String[] fragList= {"rMsgFrag","qIFrag","ddlRFrag","qSFrag","qSSFrag","ssFrag","dDFrag","qTFrag","sTFrag","sumFrag"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_question_specific_state, container, false);


        ImageButton no_but = view.findViewById(R.id.no_ss);
        ImageButton yes_but = view.findViewById(R.id.yes_ss);

        state_view = getActivity().findViewById(R.id.state_view);

        savedStates = this.getArguments();

        if(savedStates!=null){

        }else{
            savedStates = new Bundle();
        }


        yes_but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo("ssFrag",
                        R.id.fragCon,true, "ssFrag", savedStates);
            }
        });

        no_but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                state_view.setImageResource(R.drawable.pass_p);
                savedStates.putInt("state_view",R.drawable.pass_p);


                savedStates.putString("set_state_rmd","1");

                ((NavigationHost) getActivity()).navigateTo("qTFrag",
                        R.id.fragCon,true, "qTFrag", savedStates);
            }
        });

        return view;
    }

}
