package com.example.expandablelayouttrial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class config_activity extends AppCompatActivity {


    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    private View myView;

    private AtomPayment item_data;

    private String act_name;

    private EditText act_name_view;
    private TextView time_view;
    private TextView location_view;
    private Switch switch_view;

    String time_view_name = "Specify a time and date";
    String location_view_name = "Specify a location";

    FragmentManager fragmentManager;
    SetupTimeFragment timeFrag;
    SetupMapFragment mapFrag;
    SetupConstraint constraintFrag;

    Switch switch_time_view;
    Switch switch_location_view;
    Switch switch_constraint_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_fragments);
        time_view = (TextView)findViewById(R.id.label_name_time);
        location_view = (TextView) findViewById(R.id.label_name_location);

        switch_view = (Switch) findViewById(R.id.switch_time);
//        switch_view.
//        switch_view.getTrackDrawable().setTintList(R.drawable.);

        fragmentManager = getSupportFragmentManager();

        timeFrag = (SetupTimeFragment) fragmentManager.findFragmentById(R.id.time_fragmemt);
        mapFrag = (SetupMapFragment) fragmentManager.findFragmentById(R.id.map_frag);
        constraintFrag = (SetupConstraint) fragmentManager.findFragmentById(R.id.constraint_frag);

        switch_time_view = (Switch) findViewById(R.id.switch_time);
        switch_location_view = (Switch) findViewById(R.id.switch_location);
        switch_constraint_view = (Switch) findViewById(R.id.switch_contraints);

        setUp();
        prepopulate();
    }

    private void setUp(){
        setupData((TextView) findViewById(R.id.timeCriteria),(Switch) findViewById(R.id.switch_time),(View) findViewById(R.id.my_time_frag),"time");
        setupData((TextView) findViewById(R.id.locationCriteria),(Switch) findViewById(R.id.switch_location),(View) findViewById(R.id.my_map_frag),"location");
        setupData((TextView) findViewById(R.id.constraintCriteria),(Switch) findViewById(R.id.switch_contraints),(View) findViewById(R.id.my_constraint_frag),"constraints");
    }

    private void setupData(TextView tv,Switch sv,View cv,String Id){
        sv.setTag(new dataHolder(cv,false,Id));
        cv.setVisibility(View.GONE);
    }

    public  class dataHolder {
        View contentView;
        boolean visible;
        String Id;


        public dataHolder(View cv,boolean visible,String Id){
            this.contentView = cv;
            this.visible = visible;
            this.Id = Id;
        }

        public void setFalse(){visible=false;}
        public void setTrue(){visible=true;}
        public String getID(){return Id;}
        public boolean getVisiblity(){return visible;}
    }
//    public  class dataHolder {
//        Switch switch_view;
//        View contentView;
//        boolean visible;
//        String Id;
//
//
//        public dataHolder(Switch sv,View cv,boolean visible,String Id){
//            this.switch_view = sv;
//            this.contentView = cv;
//            this.visible = visible;
//            this.Id = Id;
//        }
//
//        public void setFalse(){visible=false;}
//        public void setTrue(){visible=true;}
//        public String getID(){return Id;}
//    }

//    public void expand_and_collapse(View v){
//        dataHolder dataPoint = (dataHolder) v.getTag();
//
//        if(dataPoint.visible==false) {
//            dataPoint.contentView.setVisibility(View.VISIBLE);
//            dataPoint.setTrue();
//
//        }
//        else { //update when the view disappears
//            dataPoint.contentView.setVisibility(View.GONE);
//            dataPoint.setFalse();
//            if(dataPoint.getID().equals("time")) {
//                updateTime();
//            } else if(dataPoint.getID().equals("location")) {
//                updateLocation();
//            }else if(dataPoint.getID().equals("constraints"))
//            {
//                updateConstraints();
//            }
//        }
//    }

    public void expand_and_collapse(View v){
        dataHolder dataPoint = (dataHolder) v.getTag();

        if(dataPoint.getVisiblity()==false) {
            dataPoint.contentView.setVisibility(View.VISIBLE);
            dataPoint.setTrue();
        }
        else { //update when the view disappears
            dataPoint.contentView.setVisibility(View.GONE);
            dataPoint.setFalse();

            if(dataPoint.getID().equals("time")){
                time_view.setText("");
                timeFrag.initData();

            }else if(dataPoint.getID().equals("location")){
                location_view.setText("");
                mapFrag.initData();

            }
            else if(dataPoint.getID().equals("constraints")){
                constraintFrag.initData();
            }
            //clean up the result
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void CheckWeekPressedInFrag(View v) {

        if (timeFrag==null) {
            System.out.println("this is null!!!");
        }
        else {
            timeFrag.WeekdayPressed(v);
            System.out.println("this is not a null!!!!");
        }
    }

    public void checkTracker(View v) {

        if (constraintFrag==null) {
            System.out.println("this is null!!!");
        }
        else {
            constraintFrag.checkTracker(v);
            System.out.println("this is not a null!!!!");
        }
    }

    public void updateTime(){

        dataHolder timeData = (dataHolder) switch_time_view.getTag();

        if (timeFrag==null) {
            System.out.println("this is null!!!");
        }
        else {
            if(timeData.getVisiblity()==true)
            {
                timeFrag.get_time_from_frag(item_data);
                if(item_data.getHour()!=null) {time_view_name = item_data.getHour()+ " : " + item_data.getMins();}
                if(item_data.getRepeats()!=null) { time_view_name = time_view_name + getWeek(item_data.getRepeats());}
                time_view.setText(time_view_name);
                System.out.println("this is not a null!!!!");
            }
            else{//cleanup the data
                item_data.setHour(null);
                item_data.setMins(null);
                item_data.setRepeats(null);
                item_data.setDuration(null);

                time_view.setText("");//reset the text for time view
            }
        }
    }

    public void updateLocation(){

        if (mapFrag==null) {
            System.out.println("this is null!!!");
        }
        else {
            dataHolder locationData = (dataHolder) switch_location_view.getTag();

            if(locationData.getVisiblity()==true)
            {
                mapFrag.get_location_from_frag(item_data);
                location_view_name = item_data.getLocation();
                location_view.setText(location_view_name);
                System.out.println("this is not a null!!!!");
            }
            else{
                item_data.setLoation(null);

                location_view.setText("");
            }
        }
    }

    public void updateConstraints(){

        if (constraintFrag==null) {
            System.out.println("this is null!!!");
        }
        else {

            dataHolder constraintData = (dataHolder) switch_constraint_view.getTag();

            if(constraintData.getVisiblity()==true){
                constraintFrag.get_constraints_from_frag(item_data);
                TextView cV = (TextView) findViewById(R.id.label_name_constraints);
//            cV.setText(item_data.getConditions());
                System.out.println("this is not a null!!!!");
            }else {
                item_data.setConditions(null);
            }
        }
    }





    public void save_setting_data(View v) //onClick function associated with button "save"
    {
        act_name = act_name_view.getText().toString();

        System.out.println("Print the name: " + act_name);

        if(act_name.trim().length()<=0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You have not named the activity yet!")
                    .setCancelable(false)
                    .setPositiveButton("Exit without saving", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancel();
                        }
                    })
                    .setNegativeButton("Continue editing", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel(); //do nothing if "no" is pressed
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else
        {
//            item_data.setName(act_name);
            finish();
        }

    }

    @Override
    public void onBackPressed() { //if the "back" key was pressed...

        ready_to_cancel_activity();

    }

    public void cancel_edit(View v) { //if the "cancel" button has been pressed
        ready_to_cancel_activity();
    }

    public void ready_to_cancel_activity() { //if the "back" key was pressed...

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit without saving?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); //do nothing if "no" is pressed
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void finish( ) {
        Intent data = new Intent();
//        update_data();
        item_data.setName(act_name);
        updateAll();
        data.putExtra("return_data",item_data);
        setResult(RESULT_OK,data);
        super.finish();
    }

    public void updateAll(){

            updateTime();
            updateLocation();
            updateConstraints();
        }

    public void cancel() {
        Intent data = new Intent();
//        data.putExtra("return_data",item_data);
//        data.putExtra("comment",comment);
        setResult(RESULT_CANCELED,data);
        super.finish();
    }

    private void prepopulate() {
        item_data = (AtomPayment) getIntent().getSerializableExtra("push_data");
        act_name_view = (EditText) findViewById(R.id.act_name);

        if(item_data.getName()!=null) {act_name_view.setText(item_data.getName());}
        if(item_data.getHour()!=null) {
            time_view_name = item_data.getHour()+ " : " + item_data.getMins();
            time_view.setText(time_view_name);
            expand_and_collapse(switch_time_view);//pressed the expand button
            switch_time_view.setChecked(true);//pressed the expand button
        }
        if(item_data.getRepeats()!=null) {
            time_view_name = time_view_name + getWeek(item_data.getRepeats());
            time_view.setText(time_view_name);
        }
        if(item_data.getLocation()!=null) {
            location_view_name = item_data.getLocation();
            location_view.setText(location_view_name);

            expand_and_collapse(switch_location_view);//pressed the expand button
            switch_location_view.setChecked(true);//pressed the expand button
        }
        if(item_data.getConditions()!=null) {
            TextView cV = (TextView) findViewById(R.id.label_name_constraints);
//            cV.setText(item_data.getConditions());

            expand_and_collapse(switch_constraint_view);//pressed the expand button
            switch_constraint_view.setChecked(true);//pressed the expand button
        }

        timeFrag.setTime(item_data);
        mapFrag.setLocation(item_data);
        constraintFrag.setConstraints(item_data);
    }

    private String getWeek(String weekdaystr)
    {
        String[] week = new String[]{"Su","Mon","Tu","We","Th","Fr","Sa"};
        String mWeek = " ";
        String[] weekday = weekdaystr.split(",");

        System.out.println("Weehday: " + weekdaystr);

        for(int i=0;i<7;i++)
        {
            if(weekday[i].replaceAll("\\[","").replaceAll("\\]","").trim().equals("1")) {
////                System.out.println("Weekday: " + week[i]);
                mWeek = mWeek + " " + week[i];
            }
        }

        return mWeek;
    }



}
