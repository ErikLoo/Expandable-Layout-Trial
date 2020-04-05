package com.example.expandablelayouttrial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class remind_config_activity extends AppCompatActivity implements NavigationHost {


    private AtomPayment item_data;

    private String act_name;

//    private EditText act_name_view;
//    private TextView time_view;
//    private TextView location_view;
//    private Switch switch_view;

    int frag_in = R.anim.slide_in_left;
    int frag_out = R.anim.slide_out_right;

    String time_view_name = "Specify a time and date";
    String location_view_name = "Specify a location";

    FragmentManager fragmentManager;

    //declare all the frags
    private remindMsgFrag rMsgFrag;
    private questionInteractionFrag qIFrag;
    private fragment_ddl_repeats ddlRFrag;
    private questionStateFrag qSFrag;
    private questionSpecificStateFrag qSSFrag;
    private selectStateFrag ssFrag;
    private delayDurationFrag dDFrag;
    private questionTimeFrag qTFrag;
    private SetupTimeFragment sTFrag;
    private summaryFrag sumFrag;
    private myFragment currentFrag;

//    the tag has to match the frag itself
    private String[] fragList= {"rMsgFrag","qIFrag","ddlRFrag","qSFrag","qSSFrag","ssFrag","dDFrag","qTFrag","sTFrag","sumFrag"};
    private ArrayList<String> interact_hist = new ArrayList<String>();
    private String lastFrag;
    private String currentFragName;
    private Bundle currentData;

    Switch switch_time_view;
    Switch switch_location_view;
    Switch switch_constraint_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_config_activity);
//        time_view = (TextView)findViewById(R.id.label_name_time);
//        location_view = (TextView) findViewById(R.id.label_name_location);
//
//        switch_view = (Switch) findViewById(R.id.switch_time);
//
//        switch_time_view = (Switch) findViewById(R.id.switch_time);
//        switch_location_view = (Switch) findViewById(R.id.switch_location);
//        switch_constraint_view = (Switch) findViewById(R.id.switch_contraints);
//
//        act_name_view = (EditText) findViewById(R.id.act_name);
//        setUp();

//        initialize all the frags


//        add frag
//        if (savedInstanceState == null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.fragCon, new remindMsgFrag())
//                    .commit();
//        }
        //get data from main activity
        Bundle fragData = getIntent().getExtras();

        if(fragData!=null){
            ImageView msg_view = findViewById(R.id.msg_view1);
            ImageView interact_view = findViewById(R.id.interact_view);
            ImageView state_view = findViewById(R.id.state_view);
            ImageView time_view = findViewById(R.id.time_view);

            if(fragData.getInt("msg_view",-1)!=-1){msg_view.setImageResource(fragData.getInt("msg_view"));}
            if(fragData.getInt("interact_view",-1)!=-1){interact_view.setImageResource(fragData.getInt("interact_view"));}
            if(fragData.getInt("state_view",-1)!=-1){state_view.setImageResource(fragData.getInt("state_view"));}
            if(fragData.getInt("time_view",-1)!=-1){time_view.setImageResource(fragData.getInt("time_view"));}
            if(fragData.getStringArrayList("interact_hist")!=null){interact_hist = fragData.getStringArrayList("interact_hist");}
        }else{
            fragData = new Bundle();
        }

        currentData = fragData;

        myFragment fragment;

        if(interact_hist.size()>0){
            //jump to the last editted page
            fragment = getFragFromName(interact_hist.get(interact_hist.size()-1));
            currentFragName = interact_hist.get(interact_hist.size()-1);
        }else{
            remindMsgFrag rMsgFrag = new remindMsgFrag();
            fragment = rMsgFrag;
            currentFragName = "rMsgFrag";
            interact_hist.add("rMsgFrag");
        }

//        remindMsgFrag rMsgFrag = new remindMsgFrag();
        fragment.setArguments(fragData);
        currentFrag = fragment;

        if (savedInstanceState == null) {


            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragCon, fragment)
                    .commit();

        }


    }

    private myFragment getFragFromName(String fragName){
        myFragment fragment;

//        rMsgFrag = new remindMsgFrag();
//        qIFrag = new questionInteractionFrag();
//        ddlRFrag = new fragment_ddl_repeats();
//        qSFrag =  new questionStateFrag();
//        qSSFrag = new questionSpecificStateFrag();
//        ssFrag = new selectStateFrag();
//        dDFrag = new delayDurationFrag();
//        qTFrag = new questionTimeFrag();
//        sTFrag = new SetupTimeFragment();
//        sumFrag = new summaryFrag();


        if (fragName.equals(fragList[0])){
            fragment = new remindMsgFrag();
        }
        else if (fragName.equals(fragList[1])){
            fragment = new questionInteractionFrag();
        }
        else if (fragName.equals(fragList[2])){
            fragment =new fragment_ddl_repeats();
        }
        else if (fragName.equals(fragList[3])){
            fragment =  new questionStateFrag();
        }
        else if (fragName.equals(fragList[4])){
            fragment = new questionSpecificStateFrag();
        }
        else if (fragName.equals(fragList[5])){
            fragment = new selectStateFrag();
        }
        else if (fragName.equals(fragList[6])){
            fragment = new delayDurationFrag();
        }
        else if (fragName.equals(fragList[7])){
            fragment = new questionTimeFrag();
        }
        else if (fragName.equals(fragList[8])){
            fragment = new SetupTimeFragment();
        }
        else{
            fragment = new summaryFrag();
        }

        return fragment;
    }



    public void navigateTo(String fragName, int layout, boolean addToBackStack, String tag, Bundle data) {

//        add fragname as history
        if (interact_hist.contains(fragName)==false){
            interact_hist.add(fragName);
        }else{
//            pop the last element
            interact_hist.remove(interact_hist.size()-1);
        }

        myFragment fragment;

        fragment = getFragFromName(fragName);

        currentFragName = fragName;

        currentFrag = fragment;

        currentData = data;

        fragment.setArguments(data);
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(frag_in,frag_out)
                        .replace(layout, fragment, tag);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();

        frag_in = R.anim.slide_in_left;
        frag_out = R.anim.slide_out_right;


//        System.out.println("backword pass" + currentData.getString("rMsg"));
//
        System.out.println("interaction history " + interact_hist);
    }

    public void navigateBack(View view){
        String target_frag="";
        for(int i=0;i<interact_hist.size();i++){
            if(interact_hist.get(i).equals(currentFragName)&&i!=0){
                target_frag = interact_hist.get(i-1);
                break;
            }
        }

//        System.out.println("backword pass" + currentData.getString("rMsg"));
        if(interact_hist.size()>1){

            frag_in = R.anim.slide_in_left_r;
            frag_out = R.anim.slide_out_right_r;

            navigateTo(target_frag, R.id.fragCon,true, target_frag, currentData);
        }else{
            //pass the data to the original activity
            finish();

        }
    }

    public void navEditI(View view){
        String target_frag= "qIFrag";
        interact_hist = editInteractHist(target_frag);
        frag_in = R.anim.slide_in_left_r;
        frag_out = R.anim.slide_out_right_r;
        navigateTo(target_frag, R.id.fragCon,true, target_frag, currentData);
    }

    public void navEditS(View view){
        String target_frag= "qSFrag";
        interact_hist = editInteractHist(target_frag);
        frag_in = R.anim.slide_in_left_r;
        frag_out = R.anim.slide_out_right_r;
        navigateTo(target_frag, R.id.fragCon,true, target_frag, currentData);
    }

    public void navEditT(View view){
        String target_frag= "qTFrag";
        interact_hist = editInteractHist(target_frag);
        frag_in = R.anim.slide_in_left_r;
        frag_out = R.anim.slide_out_right_r;
        navigateTo(target_frag, R.id.fragCon,true, target_frag, currentData);
    }

    private ArrayList<String> editInteractHist(String target_frag){
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0;i<interact_hist.size();i++){
            temp.add(interact_hist.get(i));
            if(interact_hist.get(i).equals(target_frag)&&i!=0){
                break;
            }
        }

        return temp;
    }

    @Override
    public void saveStatus(Bundle data) {

        if(data!=null){System.out.println("current data: " + data.getString("rMsg"));}
        currentData = data;
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



    public void save_setting_data(View v) //onClick function associated with button "save"
    {
//        act_name = act_name_view.getText().toString();
//
//        System.out.println("Print the name: " + act_name);
//
//        if(act_name.trim().length()<=0) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("You have not named the activity yet!")
//                    .setCancelable(false)
//                    .setPositiveButton("Exit without saving", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            cancel();
//                        }
//                    })
//                    .setNegativeButton("Continue editing", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel(); //do nothing if "no" is pressed
//                        }
//                    });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//        } else
//        {
////            item_data.setName(act_name);
//            finish();
//        navigateTo(currentFrag, R.id.fragCon,true, currentFrag, currentData);
        finish();
    }

    @Override
    public void onBackPressed() { //if the "back" key was pressed...

//        ready_to_cancel_activity();
        finish();

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
////        update_data();
//        item_data.setName(act_name);
////        updateAll();
        if (currentData!=null){
            //append history to the data
            currentFrag.saveStatus();
            currentData.putStringArrayList("interact_hist",interact_hist);
            data.putExtras(currentData);
            setResult(RESULT_OK,data);
        }
        super.finish();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    public void cancel() {
        Intent data = new Intent();
//        data.putExtra("return_data",item_data);
//        data.putExtra("comment",comment);
        data.putExtras(currentData);
        setResult(RESULT_CANCELED,data);
        super.finish();
    }



}
