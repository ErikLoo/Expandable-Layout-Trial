package com.example.expandablelayouttrial;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link remindMsgFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class remindMsgFrag extends myFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Bundle savedStates;

    private View v;

    private ImageView msg_view;

    public remindMsgFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment remindMsgFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static remindMsgFrag newInstance(String param1, String param2) {
        remindMsgFrag fragment = new remindMsgFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_remind_msg, container, false);
        Button button = view.findViewById(R.id.next_step);
        msg_view = getActivity().findViewById(R.id.msg_view1);

//        recreate a variable to preserve all the states
        savedStates = this.getArguments();

        if(savedStates!=null){
            String rMsg = savedStates.getString("rMsg");

            System.out.println("rMsg: " + rMsg);

            EditText rmd_view = view.findViewById(R.id.rmd_name);
            rmd_view.setText(rMsg);


        }else{
            savedStates = new Bundle();
        }

        if (msg_view!=null && savedStates.getInt("msg_view",-1)==-1)
        {
            msg_view.setImageResource(R.drawable.edit_p);
            savedStates.putInt("msg_view",R.drawable.edit_p);
        }

//        System.out.println("rMSG is: " + savedStates.getString("rMsg"));

        v  = view;

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

//                String rMsg = ((EditText)v.findViewById(R.id.rmd_name)).getText().toString();
//
//                savedStates.putString("rMsg",rMsg);
//                msg_view.setImageResource(R.drawable.pass_p);
//                savedStates.putInt("msg_view",R.drawable.pass_p);
//                saveStatus();

//                System.out.println("sStates: "+ savedStates.getString("rMsg"));

                if(((EditText)v.findViewById(R.id.rmd_name)).getText().toString().length()>5){
                    saveStatus();
                    ((NavigationHost) getActivity()).navigateTo("qIFrag",
                            R.id.fragCon,true, "qIFrag", savedStates);
                }else{
                    Toast.makeText(getActivity(),"Message cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        Toast.makeText(getActivity(),"Hello Javatpoint", Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
//    public void onPause() {
//        saveStatus();
//        super.onPause();
//    }

    public void saveStatus(){

        System.out.println(v==null);

        String rMsg = ((EditText)v.findViewById(R.id.rmd_name)).getText().toString();
        savedStates.putString("rMsg",rMsg);
        msg_view.setImageResource(R.drawable.pass_p);
        savedStates.putInt("msg_view",R.drawable.pass_p);

//        ((NavigationHost) getActivity()).navigateTo("qIFrag",
//                R.id.fragCon,true, "qIFrag", savedStates);

//        ((NavigationHost) getActivity()).saveStatus(savedStates);
    }

}
