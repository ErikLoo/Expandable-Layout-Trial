package com.example.expandablelayouttrial;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.webianks.library.scroll_choice.ScrollChoice;

import org.w3c.dom.Text;

import java.util.ArrayList;
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

    private View v;

    private AtomPayment activity_data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //don't use findByID here

        View view = inflater.inflate(R.layout.fragment_setup_time,container,false);
        v = view;

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { //do find by ID here
        activity_data = ((config_activity) this.getActivity()).getItemData();
        setup_scroll_clock();
        set_seek_bar(new AtomPayment("",0));
        setup_the_week();

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setup_scroll_clock(){

        iniViews();
        loadDatas();

        int default_hour;
        int default_min;

        currentTime = Calendar.getInstance();

        if(activity_data!=null && activity_data.getHour()!=null) { //if the saved data exist
            default_hour = Integer.parseInt(activity_data.getHour());
            default_min = Integer.parseInt(activity_data.getMins());
        }
        else {
            currentTime = Calendar.getInstance();
            default_hour= currentTime.get(Calendar.HOUR_OF_DAY);
            default_min = currentTime.get(Calendar.MINUTE);
        }

        hour = Integer.toString(default_hour);
        mins = Integer.toString(default_min);

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

    public void set_seek_bar(AtomPayment item_data) {
        seek_bar = (SeekBar)v.findViewById(R.id.seekBar3);
        int progress = 50;

        if(item_data.getDuration()!=null) {
            progress = 10*Integer.parseInt(item_data.getDuration());
        }

        seek_bar.setProgress(progress);  //set the defualt value of the seebar view to be 50

        seek_view_1 = (TextView) v.findViewById(R.id.seekView1);

        seek_view_1.setText(Integer.toString(seek_bar.getProgress()/10));
//
        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        duration = Integer.toString(progress_value);
                       seek_view_1.setText(String.valueOf(progress/10));
//                        seek_view_1.setText("Jesus");
//                        my_test_view.setText("1235");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        seek_view_1.setText(String.valueOf(progress_value/10));
//                        seek_view.setText(Integer.toString(progress_value/10));
//                        seek_view_1.setText("Jesu2s");
//                        my_test_view.setText("1234");
                        duration = Integer.toString(progress_value);
                    }
                }
        );
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

    private void setup_weekday(String weekday2,View v,int id) {
        Weekday mWeekday = new Weekday(weekday2,false,id);

        System.out.println("Week in: " + weekdata);

        if(weekdata!=null) {
            String[] weekinput = weekdata.split(",");
            if(weekinput[id].replaceAll("\\[","").replaceAll("\\]","").trim().equals("1")) {
                v.setBackgroundResource(R.drawable.redroundedbutton);
                mWeekday.setStatus(true);
                weekday[id] = 1;
            }
        }
        v.setTag(mWeekday);
    }

    public class Weekday{
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


    public void WeekdayPressed(View my_v) { //if one of the weekday was pressed
        Weekday mWeekday = (Weekday) my_v.getTag();
        String name = mWeekday.getName();
        boolean isPressed = mWeekday.getStatus();

        if(isPressed==false) {
            mWeekday.setStatus(true);
            my_v.setBackgroundResource(R.drawable.redroundedbutton);
            weekday[mWeekday.getID()] = 1;
        }
        else {
            mWeekday.setStatus(false);
            my_v.setBackgroundResource(R.drawable.whiteroundedbutton);
            weekday[mWeekday.getID()] = 0;
        }

        //record the current status of the weekdays
    }

    public void get_time_from_frag(AtomPayment data) {
        data.setHour(hour);
        data.setMins(mins);
        data.setDuration(duration);
    }

    public void prepopulate(AtomPayment data){
        //

    }

}
