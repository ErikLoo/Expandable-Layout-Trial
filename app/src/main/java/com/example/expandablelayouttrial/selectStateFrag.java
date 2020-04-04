package com.example.expandablelayouttrial;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class selectStateFrag extends Fragment {

    private Bundle savedStates;

    public selectStateFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_state, container, false);

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
        setup_check(off_1,off_1_check);
        setup_check(off_2,off_2_check);
        setup_check(off_3,off_3_check);
        setup_check(on_1,on_1_check);
        setup_check(on_2,on_2_check);
        setup_check(on_3,on_3_check);

        Button next_but = view.findViewById(R.id.next_step);


        savedStates = this.getArguments();

        if(savedStates!=null){

        }else{
            savedStates = new Bundle();
        }

        navNextFrag(view,next_but,savedStates);

        return view;
    }

    private void setup_check(final CheckBox img, final ImageView check){
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

    private void navNextFrag(final View view,final Button next_but,final Bundle savedStates){
        next_but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int[] ids = {R.id.off_1,R.id.off_2,R.id.off_3,R.id.on_1,R.id.on_2,R.id.on_3};
                savedStates.putIntArray("ids",ids);
                savedStates.putIntegerArrayList("imgStat",getImageStatus(view,ids));
//                data.putIntegerArrayList("status", 'a');
                ((NavigationHost) getActivity()).navigateTo("dDFrag",
                        R.id.fragCon,true, "dDFrag", savedStates);
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
            }
            else{
                image_status.add(0);
            }
        }

        return image_status;
    }

}
