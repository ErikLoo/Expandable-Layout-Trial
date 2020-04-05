package com.example.expandablelayouttrial;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class selectStateFrag extends myFragment {

    private Bundle savedStates;
    private View view;
    private ArrayList<Integer> imgStat = new ArrayList<Integer>( Arrays.asList(0,0,0,0,0,0) );
    private int count = 0;
    public selectStateFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_select_state, container, false);

        savedStates = this.getArguments();

        if(savedStates!=null){
            if(savedStates.getIntegerArrayList("imgStat")!=null){
                imgStat = savedStates.getIntegerArrayList("imgStat");
            }
        }else{
            savedStates = new Bundle();
        }

        CheckBox off_1 = view.findViewById(R.id.off_1);
        CheckBox off_2 = view.findViewById(R.id.off_2);
        CheckBox off_3 = view.findViewById(R.id.off_3);
        CheckBox on_1 = view.findViewById(R.id.on_1);
        CheckBox on_2 = view.findViewById(R.id.on_2);
        CheckBox on_3 = view.findViewById(R.id.on_3);

        ImageView off_1_check = view.findViewById(R.id.off_1_check);
        ImageView off_2_check = view.findViewById(R.id.off_2_check);
        ImageView off_3_check = view.findViewById(R.id.off_3_check);
        ImageView on_1_check = view.findViewById(R.id.on_1_check);
        ImageView on_2_check = view.findViewById(R.id.on_2_check);
        ImageView on_3_check = view.findViewById(R.id.on_3_check);

//        click on the images to select the ones you want
        setup_check(off_1,off_1_check,imgStat.get(0));
        setup_check(off_2,off_2_check,imgStat.get(1));
        setup_check(off_3,off_3_check,imgStat.get(2));
        setup_check(on_1,on_1_check,imgStat.get(3));
        setup_check(on_2,on_2_check,imgStat.get(4));
        setup_check(on_3,on_3_check,imgStat.get(5));

        Button next_but = view.findViewById(R.id.next_step);


        navNextFrag(next_but,savedStates);

//        Toast.makeText(getActivity(),"Hello Javatpoint", Toast.LENGTH_SHORT).show();

        return view;
    }

    private void setup_check(final CheckBox img, final ImageView check,int checked){

        if(checked==1){
            check.setVisibility(View.VISIBLE);
            img.setChecked(true);
        }else{
            check.setVisibility(View.GONE);
            img.setChecked(false);
        }


        img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(img.isChecked()==true){
                    check.setVisibility(View.VISIBLE);
                }
                else{
                    check.setVisibility(View.GONE);
                }
            }
        });
    }


//    private String[] fragList= {"rMsgFrag","qIFrag","ddlRFrag","qSFrag","qSSFrag","ssFrag","dDFrag","qTFrag","sTFrag","sumFrag"};

    private void navNextFrag(final Button next_but,final Bundle savedStates){
        next_but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                int[] ids = {R.id.off_1,R.id.off_2,R.id.off_3,R.id.on_1,R.id.on_2,R.id.on_3};
                savedStates.putIntArray("ids",ids);
                savedStates.putIntegerArrayList("imgStat",getImageStatus(view,ids));
//                data.putIntegerArrayList("status", 'a');

                if(count>0){
                    ((NavigationHost) getActivity()).navigateTo("dDFrag",
                            R.id.fragCon,true, "dDFrag", savedStates);
                }else{
                    Toast.makeText(getActivity(),"Need to select at least 1 image", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

//    this method will detect if an image has been checked or not
    private ArrayList<Integer> getImageStatus(final View v,int[] ids){
        ArrayList<Integer> image_status = new ArrayList<Integer>();


        for (int i=0;i<ids.length;i++){
            CheckBox img = v.findViewById(ids[i]);

            if (img.isChecked()){
                image_status.add(1);
                count++;
            }
            else{
                image_status.add(0);
            }
        }

        return image_status;
    }

//    private void warning() { //if the "back" key was pressed...
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage("Are you sure you want to exit without saving?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        cancel();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel(); //do nothing if "no" is pressed
//                    }
//                });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//
//    }

    public void saveStatus(){
        int[] ids = {R.id.off_1,R.id.off_2,R.id.off_3,R.id.on_1,R.id.on_2,R.id.on_3};
        savedStates.putIntArray("ids",ids);
        savedStates.putIntegerArrayList("imgStat",getImageStatus(view,ids));
    }
}
