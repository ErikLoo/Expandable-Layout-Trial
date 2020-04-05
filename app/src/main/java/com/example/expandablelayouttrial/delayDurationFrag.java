package com.example.expandablelayouttrial;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class delayDurationFrag extends myFragment {

    private Bundle savedStates;

    private String sw_show_val="1";
    private String set_dl="0";
    private String set_dur="0";
    private String sw_in_view_val="1";
    private String dur_dl="0";
    private String dur_view="0";


    private Switch sw_show;
    private Switch sw_dl;
    private Switch sw_dur;
    private Switch sw_in_view;
    private Spinner sp_dl;
    private Spinner sp_dur;

    private View hold_off_remind;
    private View in_my_view;

    private ImageView state_view;

    public delayDurationFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delay_duration, container, false);

        Spinner spinner = view.findViewById(R.id.spinner1);
//        get data from the previous fragment
        int [] ids = {R.id.off_1_s,R.id.off_2_s,R.id.off_3_s,R.id.on_1_s,R.id.on_2_s,R.id.on_3_s};
        ArrayList<Integer> imgStat = null;

        //from the data we get from the preiovous frag, change the visibility of images
        state_view = getActivity().findViewById(R.id.state_view);

        sw_dl = view.findViewById(R.id.sw_delay);
        sw_dur = view.findViewById(R.id.sw_dur);
        sw_show = view.findViewById(R.id.sw_show2);
        sw_in_view = view.findViewById(R.id.sw_in_view);
        sp_dl =view.findViewById(R.id.spinner1);
        sp_dur = view.findViewById(R.id.spinner2);


        hold_off_remind = view.findViewById(R.id.hold_off_layout);
        in_my_view = view.findViewById(R.id.in_view_layout);

        savedStates = this.getArguments();

        if(savedStates!=null){
            if (savedStates.getString("sw_show_val")!=null){sw_show_val = savedStates.getString("sw_show_val");}
            if (savedStates.getString("set_dl")!=null){set_dl = savedStates.getString("set_dl");}
            if (savedStates.getString("set_dur")!=null){set_dur=savedStates.getString("set_dur");}
            if (savedStates.getString("sw_in_view_val")!=null){sw_in_view_val = savedStates.getString("sw_in_view_val");}
            if (savedStates.getString("dur_dl")!=null){dur_dl = savedStates.getString("dur_dl");}
            if (savedStates.getString("dur_view")!=null){ dur_view = savedStates.getString("dur_view");}
            if (savedStates.getIntegerArrayList("imgStat")!=null){ imgStat = savedStates.getIntegerArrayList("imgStat");}


        }else{
            savedStates = new Bundle();
        }

        setup_sw_sp();
        setUpImage(view,ids,imgStat);

        sw_dl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (hold_off_remind.getVisibility()==View.VISIBLE) {
                    hold_off_remind.setVisibility(View.GONE);
                    set_dl = "0";
                }else{
                    hold_off_remind.setVisibility(View.VISIBLE);
                    set_dl = "1";

                }
            }
        });

        sw_dur.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (in_my_view.getVisibility()==View.VISIBLE){
                    in_my_view.setVisibility(View.GONE);
                    set_dur = "0";
                }
                else{
                    in_my_view.setVisibility(View.VISIBLE);
                    set_dur = "1";

                }
            }
        });

        sw_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw_show.isChecked()){
                    sw_show_val = "1";

                    System.out.println("sw_show_val:" + sw_show_val + "!!!!");
                }else{

                    sw_show_val="0";
                    System.out.println("sw_show_val:" + sw_show_val + "!!!!");

                }
            }
        });
//        private String[] fragList= {"rMsgFrag","qIFrag","ddlRFrag","qSFrag","qSSFrag","ssFrag","dDFrag","qTFrag","sTFrag","sumFrag"};

        sw_in_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw_in_view.isChecked()){
                    sw_in_view_val="1";
                    System.out.println("sw_in_view:" + sw_in_view + "!!!!");
                }else{
                    sw_in_view_val="0";
                    System.out.println("sw_in_view:" + sw_in_view + "!!!!");
                }
            }
        });

        Button confirm_but = view.findViewById(R.id.next_step);
        confirm_but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                state_view.setImageResource(R.drawable.pass_p);
                savedStates.putInt("state_view",R.drawable.pass_p);

                savedStates.putString("set_dl",set_dl);
                savedStates.putString("set_dur",set_dur);

                //for some reason setOnclickListenr sometimes does not work for sw_show and sw_in_view
                if(sw_show.isChecked()){sw_show_val="1";}else{
                    sw_show_val="0";
                }
                if(sw_in_view.isChecked()){sw_in_view_val="1";}else{
                    sw_in_view_val = "0";
                }

                savedStates.putString("sw_show_val",sw_show_val);
                savedStates.putString("sw_in_view_val",sw_in_view_val);
                savedStates.putString("dur_dl",Integer.toString(sp_dl.getSelectedItemPosition()));
                savedStates.putString("dur_view",Integer.toString(sp_dur.getSelectedItemPosition()));

                System.out.println("sw_show_val: " + sw_show_val + "set_dur: " + sw_in_view_val);

                savedStates.putString("set_state_rmd","1");

                ((NavigationHost) getActivity()).navigateTo("qTFrag",
                        R.id.fragCon,true, "qTFrag", savedStates);
            }
        });


        return view;
    }

    private void setUpImage(View v,int[] ids,ArrayList<Integer> imgStat){
        for (int i =0;i<ids.length;i++){
            ImageView img = v.findViewById(ids[i]);
            if(imgStat.get(i)==1){
                img.setVisibility(View.VISIBLE);
            }
            else{
                img.setVisibility(View.GONE);
            }
        }
    }

    private void setup_sw_sp(){


        if(set_dl.equals("0")){
            sw_dl.setChecked(false);
            hold_off_remind.setVisibility(View.GONE);
        } else{
            sw_dl.setChecked(true);
            hold_off_remind.setVisibility(View.VISIBLE);
        }

        if(sw_in_view_val.equals("0")){
            sw_in_view.setChecked(false);
        } else{
            sw_in_view.setChecked(true);
        }

        if(set_dur.equals("0")){
            sw_dur.setChecked(false);
            in_my_view.setVisibility(View.GONE);
        } else{
            sw_dur.setChecked(true);
            in_my_view.setVisibility(View.VISIBLE);
        }
        if(sw_show_val.equals("0")){
            sw_show.setChecked(false);
        }
        else{
            sw_show.setChecked(true);
        }

        if (!dur_dl.equals("0")){
            sp_dl.setSelection(Integer.parseInt(dur_dl));
        }

        if (!dur_view.equals("0")){
            sp_dur.setSelection(Integer.parseInt(dur_view));
        }

    }

    public void saveStatus(){
        state_view.setImageResource(R.drawable.pass_p);
        savedStates.putInt("state_view",R.drawable.pass_p);

        savedStates.putString("set_dl",set_dl);
        savedStates.putString("set_dur",set_dur);

        //for some reason setOnclickListenr sometimes does not work for sw_show and sw_in_view
        if(sw_show.isChecked()){sw_show_val="1";}else{
            sw_show_val="0";
        }
        if(sw_in_view.isChecked()){sw_in_view_val="1";}else{
            sw_in_view_val = "0";
        }

        savedStates.putString("sw_show_val",sw_show_val);
        savedStates.putString("sw_in_view_val",sw_in_view_val);
        savedStates.putString("dur_dl",Integer.toString(sp_dl.getSelectedItemPosition()));
        savedStates.putString("dur_view",Integer.toString(sp_dur.getSelectedItemPosition()));

        System.out.println("sw_show_val: " + sw_show_val + "set_dur: " + sw_in_view_val);

        savedStates.putString("set_state_rmd","1");
    }

}
