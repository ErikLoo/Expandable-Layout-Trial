package com.example.expandablelayouttrial;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class SetupTimeFragment extends Fragment {


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

    Calendar currentTime;

    private String hour;
    private String mins;
    private String repeats;
    private String duration;
    private String weekdata;

    private Bundle savedStates;

    private View v;

    private ImageView time_view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //don't use findByID here

        View view = inflater.inflate(R.layout.fragment_setup_time,container,false);
        v = view;


        time_view = getActivity().findViewById(R.id.time_view);

        Button confirm_but = view.findViewById(R.id.next_step);


//            private String[] fragList= {"rMsgFrag","qIFrag","ddlRFrag","qSFrag","qSSFrag","ssFrag","dDFrag","qTFrag","sTFrag","sumFrag"};

        weekdata = Arrays.toString(new int[] {0,0,0,0,0,0,0});

        savedStates = this.getArguments();

        System.out.println("saved states");
        System.out.println(savedStates==null);

        if(savedStates!=null){
//            load data from hisotry
            if (savedStates.getString("Tweekday")!=null){
                weekdata = savedStates.getString("Tweekday");
                System.out.println("weekdata: " + weekdata);
            }
            if (savedStates.getString("Thour")!=null){ hour = savedStates.getString("Thour");}
            if (savedStates.getString("Tmins")!=null){ mins = savedStates.getString("Tmins");}


        }else{
            //create a new data bundle if there is none
            savedStates = new Bundle();
        }

        initData();


        confirm_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                time_view.setImageResource(R.drawable.pass_p);
                savedStates.putInt("time_view",R.drawable.pass_p);

                savedStates.putString("set_time_rmd","1");

                savedStates.putIntArray("Tweekday_int",weekday);
                savedStates.putString("Tweekday",Arrays.toString(weekday));
                savedStates.putString("Thour",hour);
                savedStates.putString("Tmins",mins);

                ((NavigationHost) getActivity()).navigateTo("sumFrag",
                        R.id.fragCon,true, "sumFrag", savedStates);
            }
        });
        //prepopulate();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { //do find by ID here
//        initData();

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initData(){
        setup_scroll_clock();
        setup_the_week();
    }


    private void setup_scroll_clock(){

        iniViews();
        loadDatas();

        int default_hour;
        int default_min;

        currentTime = Calendar.getInstance();
        default_hour= currentTime.get(Calendar.HOUR_OF_DAY);
        default_min = currentTime.get(Calendar.MINUTE);

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




}
