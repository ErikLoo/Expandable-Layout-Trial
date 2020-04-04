package com.example.expandablelayouttrial;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link questionInteractionFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link questionInteractionFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class questionInteractionFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Bundle savedStates;

    private ImageView interact_view;

    public questionInteractionFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment questionInteractionFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static questionInteractionFrag newInstance(String param1, String param2) {
        questionInteractionFrag fragment = new questionInteractionFrag();
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
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_interaction, container, false);

        ImageButton no_but = view.findViewById(R.id.i_no);
        ImageButton yes_but = view.findViewById(R.id.i_yes);
        interact_view = getActivity().findViewById(R.id.interact_view);

        savedStates = this.getArguments();


        if(savedStates!=null){

        }else{
            savedStates = new Bundle();
        }

        if(savedStates.getInt("interact_view",-1)==-1){
            interact_view.setImageResource(R.drawable.edit_p);
            savedStates.putInt("interact_view",R.drawable.edit_p);}

        no_but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                interact_view.setImageResource(R.drawable.skip_p);
                savedStates.putInt("interact_view",R.drawable.skip_p);
                savedStates.putString("set_interact_rmd","0");
                ((NavigationHost) getActivity()).navigateTo("qSFrag",
                        R.id.fragCon,true, "qSFrag", savedStates);
            }
        });

        yes_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo("ddlRFrag",
                        R.id.fragCon,true, "ddlRFrag", savedStates);
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void saveStatus(){

    }
}
