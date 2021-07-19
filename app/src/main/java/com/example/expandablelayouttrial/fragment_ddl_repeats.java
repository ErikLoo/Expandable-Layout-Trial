package com.example.expandablelayouttrial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.webianks.library.scroll_choice.ScrollChoice;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_ddl_repeats extends myFragment{


    List<String> datasHour = new ArrayList<>();
    List<String> datasMins = new ArrayList<>();

    int[] weekday = new int[] {0,0,0,0,0,0,0};
    //variables for making the scroll clock interface
    ScrollChoice scrollChoiceHour;
    ScrollChoice scrollChoiceMins;

    //variables for storing useful data
    private String act_name;

    private SeekBar seek_bar;
    private TextView seek_view_1;
    private TextView my_test_view;
    private Switch sw_ddl;
    private Switch sw_r;
    private Switch sw_see;
    private View scroll_clock;
    private View week_days;
    private Spinner num_interc_view;
    private Spinner freq_spin;

    Calendar currentTime;

    private String hour;
    private String mins;
    private String repeats;
    private String duration;
    private String weekdata;
    private String set_ddl="0";
    private String set_rep="0";
    private String sw_see_val="0";
    private String num_interact="0";
    private String freq;

    private Bundle savedStates;

    private View v;
    private ImageView interact_view;
    private ImageView show_img;
    private VideoView vid_view;

    public fragment_ddl_repeats() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_ddl_repeats, container, false);
        v = view;

        Button confirm_but = view.findViewById(R.id.next_step);
//        show_img = view.findViewById(R.id.show_img);

//        set up video view
        vid_view = view.findViewById(R.id.vid);


        //        private String[] fragList= {"rMsgFrag","qIFrag","ddlRFrag","qSFrag","qSSFrag","ssFrag","dDFrag","qTFrag","sTFrag","sumFrag"};
        interact_view = getActivity().findViewById(R.id.interact_view);

        sw_ddl = view.findViewById(R.id.sw_ddl);
        sw_r = view.findViewById(R.id.sw_r);

        scroll_clock = view.findViewById(R.id.scroll_clock);
        week_days = view.findViewById(R.id.week_days);
        sw_see  = view.findViewById(R.id.sw_see);

        num_interc_view = view.findViewById(R.id.spinner3);
        freq_spin = view.findViewById(R.id.spinner_freq);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.time_arrays, R.layout.spinner_item);
        num_interc_view.setAdapter(adapter);

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.freq_arrays, R.layout.spinner_item_hour);
        freq_spin.setAdapter(adapter2);

        sw_see.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (sw_see.isChecked()){
                    sw_see_val = "1";
                }else{
                    sw_see_val = "0";
                }
            }
        });



        sw_ddl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (scroll_clock.getVisibility()==View.VISIBLE) {
                    scroll_clock.setVisibility(View.GONE);
                    set_ddl = "0";
                    Toast.makeText(getActivity(), "Set Fire-off deadline: Off!", Toast.LENGTH_SHORT).show();

                }else{
                    scroll_clock.setVisibility(View.VISIBLE);
                    set_ddl = "1";
                    Toast.makeText(getActivity(), "Set Fire-off deadline: On!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        sw_r.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (week_days.getVisibility()==View.VISIBLE){
                    week_days.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Set Reminder Repeats: Off!", Toast.LENGTH_SHORT).show();
                    set_rep = "0";
                }
                else{
                    week_days.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Set Reminder Repeats: On!", Toast.LENGTH_SHORT).show();
                    set_rep = "1";
                }
            }
        });
        //initiate the scroll clock and the week days

        weekdata = Arrays.toString(new int[] {0,0,0,0,0,0,0});

        savedStates = this.getArguments();

        String vid_path = "android.resource://" + getActivity().getPackageName()+ "/" + R.raw.stove_hand;

        if(savedStates!=null){
//            load data from hisotry
            if (savedStates.getString("weekday")!=null){weekdata = savedStates.getString("weekday");}
            if (savedStates.getString("set_ddl_bar")!=null){set_ddl = savedStates.getString("set_ddl_bar");}
            if (savedStates.getString("set_rep")!=null){set_rep=savedStates.getString("set_rep");}
            if (savedStates.getString("sw_see")!=null){sw_see_val = savedStates.getString("sw_see");}
            if (savedStates.getString("num_interac")!=null){num_interact = savedStates.getString("num_interac");}
            if (savedStates.getString("freq_spin")!=null){freq = savedStates.getString("freq_spin");}
            if (savedStates.getString("fhour")!=null){ hour = savedStates.getString("fhour");}
            if (savedStates.getString("fmins")!=null){ mins = savedStates.getString("fmins");}
            if (savedStates.getInt("rmd_type",-1)!=-1){
                if(savedStates.getInt("rmd_type",-1)==0){
//                    show_img.setImageResource(R.drawable.stove_pic_hand);
                    vid_path = "android.resource://" + getActivity().getPackageName()+ "/" + R.raw.stove_hand;
                }else if(savedStates.getInt("rmd_type",-1)==1){
//                    show_img.setImageResource(R.drawable.wp_i_1);
                    vid_path = "android.resource://" + getActivity().getPackageName()+ "/" + R.raw.wp_hand;
                }else{
                    //set image source 3
                }
            }

        }else{
            //create a new data bundle if there is none
            savedStates = new Bundle();
        }

        Uri uri = Uri.parse(vid_path);
        vid_view.setVideoURI(uri);
        MediaController mediaController = new MediaController(getActivity());
        vid_view.setMediaController(mediaController);
        mediaController.setAnchorView(vid_view);


        initData();

        confirm_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                change trackbar status
                interact_view.setImageResource(R.drawable.pass_p);
                savedStates.putInt("interact_view",R.drawable.pass_p);
//                save all the changes
                savedStates.putIntArray("weekday_int",weekday);
                savedStates.putString("weekday",Arrays.toString(weekday));
                savedStates.putString("fhour",hour);
                savedStates.putString("fmins",mins);

                if(sw_see.isChecked()){sw_see_val="1";}else{
                    sw_see_val="0";
                }

                savedStates.putString("sw_see",sw_see_val);
                savedStates.putString("set_ddl_bar",set_ddl);
                savedStates.putString("set_rep",set_rep);
                savedStates.putString("num_interac",Integer.toString(num_interc_view.getSelectedItemPosition()));
                savedStates.putString("freq_spin",Integer.toString(freq_spin.getSelectedItemPosition()));
//                System.out.println("weekday: " + weekdata);
                savedStates.putString("set_interact_rmd","1");
                ((NavigationHost) getActivity()).navigateTo("qSFrag",
                        R.id.fragCon_m,true, "qSFrag", savedStates);
            }
        });

//        start the video as soon as the frag is created
        startVid();

        return view;
    }


    private void initData(){
        setup_sw();
        setup_scroll_clock();
        setup_the_week();
    }

    private void setup_sw(){
        if(set_ddl.equals("0")){
            sw_ddl.setChecked(false);
            scroll_clock.setVisibility(View.GONE);
        } else{
            sw_ddl.setChecked(true);
            scroll_clock.setVisibility(View.VISIBLE);
        }

        if(set_rep.equals("0")){
            sw_r.setChecked(false);
            week_days.setVisibility(View.GONE);
        } else{
            sw_r.setChecked(true);
            week_days.setVisibility(View.VISIBLE);
//            Toast.makeText(getActivity(), "Set Reminder Repeats: Off!", Toast.LENGTH_SHORT).show();
        }
        if(sw_see_val.equals("0")){
            sw_see.setChecked(false);
        }
        else{
            sw_see.setChecked(true);
        }

        if (!num_interact.equals("0")){
            num_interc_view.setSelection(Integer.parseInt(num_interact));
        }

        if(freq!=null){
            freq_spin.setSelection(Integer.parseInt(freq));
        }

    }

    private void setup_scroll_clock(){

        iniViews();
        loadDatas();

        int default_hour;
        int default_min;

        currentTime = Calendar.getInstance();
        default_hour= currentTime.get(Calendar.HOUR_OF_DAY);
        default_min = currentTime.get(Calendar.MINUTE);

//        if (hour!=null && mins!=null){
//            default_hour = Integer.parseInt(hour);
//            default_min = Integer.parseInt(mins);
//
//            System.out.println("hours: "+ default_hour + ", mins: " + default_min);
//        }

        if (hour!=null){
            default_hour = Integer.parseInt(hour);

//            System.out.println("hours: "+ default_hour + ", mins: " + default_min);
        }else{
            hour = Integer.toString(default_hour);
        }

        if (mins!=null){
            default_min = Integer.parseInt(mins);

//            System.out.println("mins: "+ default_hour + ", mins: " + default_min);
        }else{
            mins = Integer.toString(default_min);
        }



        scrollChoiceHour.addItems(datasHour,default_hour); //set the default hour to the current hour
        scrollChoiceHour.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                hour = name;
            }
        });

        scrollChoiceMins.addItems(datasMins,default_min); //set the defualt minute to the current minute
        scrollChoiceMins.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                //do some action please
                mins = name;
            }
        });

//        set clock text size
        scrollChoiceHour.setItemTextSize(40);
        scrollChoiceMins.setItemTextSize(40);
    }

    private void loadDatas(){

        for(int i=0;i<=23;i++){
            String time = Integer.toString(i);
            if(i<10) time = '0' + time;
            datasHour.add(time);
        }

        for(int i=0;i<=59;i++){
            String time = Integer.toString(i);
            if(i<10) time = '0' + time;
            datasMins.add(time);
        }
    }

    private void iniViews() {
        scrollChoiceHour = (ScrollChoice) v.findViewById(R.id.scroll_choice_hour);
        scrollChoiceMins = (ScrollChoice) v.findViewById(R.id.scroll_choice_minute);
    }


    private void setup_the_week() {
        setup_weekday("Monday",(Button)v.findViewById(R.id.mon1),1);
        setup_weekday("Tuesday",(Button)v.findViewById(R.id.tue2),2);
        setup_weekday("Wednesday",(Button)v.findViewById(R.id.wed3),3);
        setup_weekday("Thursday",(Button)v.findViewById(R.id.th4),4);
        setup_weekday("Friday",(Button)v.findViewById(R.id.fri5),5);
        setup_weekday("Saturday",(Button)v.findViewById(R.id.sat6),6);
        setup_weekday("Sunday",(Button)v.findViewById(R.id.sun7),0);
    }

    private void setup_weekday(String weekday2, final View v, int id) {
        Weekday mWeekday = new Weekday(weekday2,false,id);

        System.out.println("Week in: " + weekdata);

        if(weekdata!=null) {
            String[] weekinput = weekdata.split(",");
            if(weekinput[id].replaceAll("\\[","").replaceAll("\\]","").trim().equals("1")) {
                v.setBackgroundResource(R.drawable.circle);
                mWeekday.setStatus(true);
                weekday[id] = 1;
            }
            else{
                v.setBackgroundResource(R.drawable.whiteroundedbutton);
                mWeekday.setStatus(false);
                weekday[id] = 0;
            }
        }
        v.setTag(mWeekday);

        //set weekday listener
        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                WeekdayPressed(v);
            }
        });
    }


    private class Weekday{
        private String name;
        private boolean isPressed = false;
        private int id;

        public Weekday(String name,boolean isPressed,int id) {
            this.setName(name);
            this.setStatus(isPressed);
            this.setID(id);
        }

        public String getName() {return name;}
        public boolean getStatus(){return isPressed;}

        public void setStatus(boolean isPressed) {this.isPressed = isPressed;}
        public void setName(String name) {this.name = name;}

        public int getID() {return id;}
        public void setID(int id) {this.id = id;}
    }


    private void WeekdayPressed(View my_v) { //if one of the weekday was pressed
        Weekday mWeekday = (Weekday) my_v.getTag();
        String name = mWeekday.getName();
        boolean isPressed = mWeekday.getStatus();

        if(isPressed==false) {
            mWeekday.setStatus(true);
            my_v.setBackgroundResource(R.drawable.circle);
            weekday[mWeekday.getID()] = 1;
        }
        else {
            mWeekday.setStatus(false);
            my_v.setBackgroundResource(R.drawable.whiteroundedbutton);
            weekday[mWeekday.getID()] = 0;
        }

        System.out.println("my weekday" + weekdata);
        //record the current status of the weekdays
    }

    public void saveStatus(){
        interact_view.setImageResource(R.drawable.pass_p);
        savedStates.putInt("interact_view",R.drawable.pass_p);
//                save all the changes
        savedStates.putIntArray("weekday_int",weekday);
        savedStates.putString("weekday",Arrays.toString(weekday));
        savedStates.putString("fhour",hour);
        savedStates.putString("fmins",mins);

        if(sw_see.isChecked()){sw_see_val="1";}else{
            sw_see_val="0";
        }

        savedStates.putString("sw_see",sw_see_val);
        savedStates.putString("set_ddl_bar",set_ddl);
        savedStates.putString("set_rep",set_rep);
        savedStates.putString("num_interac",Integer.toString(num_interc_view.getSelectedItemPosition()));
//                System.out.println("weekday: " + weekdata);
        savedStates.putString("set_interact_rmd","1");
        savedStates.putString("freq_spin",Integer.toString(freq_spin.getSelectedItemPosition()));

    }

    public void startVid(){
        vid_view.start();
    }


}
