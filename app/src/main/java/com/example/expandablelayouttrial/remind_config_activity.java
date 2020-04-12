package com.example.expandablelayouttrial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

    private Switch sw_i;
    private Switch sw_s;
    private Switch sw_t;
    private TextView txt_i;
    private TextView txt_s;
    private TextView txt_t;


    //    the tag has to match the frag itself
    private String[] fragList= {"rMsgFrag","qIFrag","ddlRFrag","qSFrag","qSSFrag","ssFrag","dDFrag","qTFrag","sTFrag","sumFrag"};
    private ArrayList<myFragment> essential_frag_list = new ArrayList<myFragment>();
//    private myFragment[] essential_frag_list = {rMsgFrag,ddlRFrag,ssFrag,dDFrag,sTFrag};

    private ArrayList<String> interact_hist = new ArrayList<String>();
    private String lastFrag;
    private String currentFragName;
    private Bundle currentData;
    private Bundle fragData;

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_config_activity);

        //init toggle buttons
        sw_i = findViewById(R.id.sw_i);
        sw_s = findViewById(R.id.sw_s);
        sw_t = findViewById(R.id.sw_t);

        txt_i = findViewById(R.id.txt_i);
        txt_s = findViewById(R.id.txt_s);
        txt_t = findViewById(R.id.txt_t);


        fragData = getIntent().getExtras();

        if(fragData!=null){
//            init here
            ImageView msg_view = findViewById(R.id.msg_view1);
            ImageView interact_view = findViewById(R.id.interact_view);
            ImageView state_view = findViewById(R.id.state_view);
            ImageView time_view = findViewById(R.id.time_view);


            if(fragData.getInt("msg_view",-1)!=-1){msg_view.setImageResource(fragData.getInt("msg_view"));}
            if(fragData.getInt("interact_view",-1)!=-1){interact_view.setImageResource(fragData.getInt("interact_view"));}
            if(fragData.getInt("state_view",-1)!=-1){state_view.setImageResource(fragData.getInt("state_view"));}
            if(fragData.getInt("time_view",-1)!=-1){time_view.setImageResource(fragData.getInt("time_view"));}
            if(fragData.getStringArrayList("interact_hist")!=null){interact_hist = fragData.getStringArrayList("interact_hist");}

            if(fragData.getBoolean("sw_i",false)!=false){
                sw_i.setChecked(true);
                findViewById(R.id.fragCon_i).setVisibility(View.VISIBLE);
            }
            if(fragData.getBoolean("sw_s",false)!=false) {
                sw_s.setChecked(true);
                findViewById(R.id.fragCon_s).setVisibility(View.VISIBLE);
            }
            if(fragData.getBoolean("sw_t",false)!=false){
                sw_t.setChecked(true);
                findViewById(R.id.fragCon_t).setVisibility(View.VISIBLE);
            };

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
//            remindMsgFrag rMsgFrag = new remindMsgFrag();
//            fragment = rMsgFrag;
            currentFragName = "ssFrag";
            interact_hist.add("ssFrag");
        }

        System.out.println("interaction hist: " + interact_hist +" Current Frag Name: "+ currentFragName);
//        remindMsgFrag rMsgFrag = new remindMsgFrag();
//        fragment.setArguments(fragData);
//        currentFrag = fragment;

        rMsgFrag = new remindMsgFrag();
//        qIFrag = new questionInteractionFrag();
        ddlRFrag = new fragment_ddl_repeats();
//        qSFrag =  new questionStateFrag();
        ssFrag = new selectStateFrag();
//        qTFrag = new questionTimeFrag();
        sTFrag = new SetupTimeFragment();

        dDFrag = new delayDurationFrag();

        rMsgFrag.setArguments(fragData);
        ddlRFrag.setArguments(fragData);
        ssFrag.setArguments(fragData);
        sTFrag.setArguments(fragData);
        dDFrag.setArguments(fragData);

        if (savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();


            //add all the frags
            transaction.add(R.id.fragCon_m,rMsgFrag);
            transaction.add(R.id.fragCon_i,ddlRFrag);
            transaction.add(R.id.fragCon_s,getFragFromName(currentFragName));
//            transaction.add(R.id.fragCon_s,ssFrag);

            transaction.add(R.id.fragCon_t,sTFrag);
            transaction.commit();


        }

        essential_frag_list.add(rMsgFrag);
        essential_frag_list.add(ddlRFrag);
        essential_frag_list.add(ssFrag);
        essential_frag_list.add(sTFrag);
        essential_frag_list.add(dDFrag);

        init_toggles();

    }

    private void init_toggles(){




        sw_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw_i.isChecked()){
                    findViewById(R.id.fragCon_i).setVisibility(View.VISIBLE);
                    Toast.makeText(remind_config_activity.this, "Interaction-based Reminder On!", Toast.LENGTH_LONG).show();
                    fragData.putBoolean("sw_i",true);
                }else {
                    findViewById(R.id.fragCon_i).setVisibility(View.GONE);
                    Toast.makeText(remind_config_activity.this, "Interaction-based Reminder Off!", Toast.LENGTH_LONG).show();
                    fragData.putBoolean("sw_i",false);
                }
            }
        });

        sw_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw_s.isChecked()){
                    findViewById(R.id.fragCon_s).setVisibility(View.VISIBLE);
                    Toast.makeText(remind_config_activity.this, "State-based Reminder On!", Toast.LENGTH_LONG).show();
                    fragData.putBoolean("sw_s",true);
                }else {
                    findViewById(R.id.fragCon_s).setVisibility(View.GONE);
                    Toast.makeText(remind_config_activity.this, "State-based Reminder Off!", Toast.LENGTH_LONG).show();
                    fragData.putBoolean("sw_s",false);
                }
            }
        });

        sw_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw_t.isChecked()){
                    findViewById(R.id.fragCon_t).setVisibility(View.VISIBLE);
                    Toast.makeText(remind_config_activity.this, "Time-based Reminder On!", Toast.LENGTH_LONG).show();
                    fragData.putBoolean("sw_t",true);

                }else {
                    findViewById(R.id.fragCon_t).setVisibility(View.GONE);
                    Toast.makeText(remind_config_activity.this, "Time-based Reminder Off!", Toast.LENGTH_LONG).show();
                    fragData.putBoolean("sw_t",false);

                }
            }
        });
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
            fragment = ssFrag;
        }
        else if (fragName.equals(fragList[6])){
            fragment = dDFrag;
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

        System.out.println("Navigating to " + fragName);

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

    @Override
    public void navigateBackTo(String fragName, int layout, boolean addToBackStack, String tag, Bundle data) {
        frag_in = R.anim.slide_in_left_r;
        frag_out = R.anim.slide_out_right_r;
        navigateTo(fragName,layout,true, tag, currentData);
    }
//
//    public void navigateBack(View view){
//        String target_frag="";
//        for(int i=0;i<interact_hist.size();i++){
//            if(interact_hist.get(i).equals(currentFragName)&&i!=0){
//                target_frag = interact_hist.get(i-1);
//                break;
//            }
//        }
//
////        System.out.println("backword pass" + currentData.getString("rMsg"));
//        if(interact_hist.size()>1){
//
//            frag_in = R.anim.slide_in_left_r;
//            frag_out = R.anim.slide_out_right_r;
//
//            navigateTo(target_frag, R.id.fragCon_m,true, target_frag, currentData);
//        }else{
//            //pass the data to the original activity
//            finish();
//
//        }
//    }


    public void navEditI(View view){
        String target_frag= "qIFrag";
        interact_hist = editInteractHist(target_frag);
        frag_in = R.anim.slide_in_left_r;
        frag_out = R.anim.slide_out_right_r;
        navigateTo(target_frag, R.id.fragCon_m,true, target_frag, currentData);
    }

    public void navEditS(View view){
        String target_frag= "qSFrag";
        interact_hist = editInteractHist(target_frag);
        frag_in = R.anim.slide_in_left_r;
        frag_out = R.anim.slide_out_right_r;
        navigateTo(target_frag, R.id.fragCon_m,true, target_frag, currentData);
    }

    public void navEditT(View view){
        String target_frag= "qTFrag";
        interact_hist = editInteractHist(target_frag);
        frag_in = R.anim.slide_in_left_r;
        frag_out = R.anim.slide_out_right_r;
        navigateTo(target_frag, R.id.fragCon_m,true, target_frag, currentData);
    }

    public void navEditMsg(View view){
        String target_frag= "rMsgFrag";
        interact_hist = editInteractHist(target_frag);
        frag_in = R.anim.slide_in_left_r;
        frag_out = R.anim.slide_out_right_r;
        navigateTo(target_frag, R.id.fragCon_m,true, target_frag, currentData);
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
//        } else {
////            item_data.setName(act_name);
//            finish();
//        }
        finish();
    }

    @Override
    public void onBackPressed() { //if the "back" key was pressed...

        ready_to_cancel_activity();
//        finish();

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

//    public void finish( ) {
//        Intent data = new Intent();
//////        update_data();
////        item_data.setName(act_name);
//////        updateAll();
//        if (currentData!=null){
//            //append history to the data
//            //call saveStatus on all the frags
//            currentFrag.saveStatus();
//            currentData.putStringArrayList("interact_hist",interact_hist);
//            data.putExtras(currentData);
//            setResult(RESULT_OK,data);
//        }
//        super.finish();
//    }

    public void finish( ) {
        Intent data = new Intent();
////        update_data();
//        item_data.setName(act_name);
////        updateAll();
        if (currentData!=null){
            //append history to the data
            //call saveStatus on all the frags
//            if(currentFragName=="dDFrag"){
//                essential_frag_list.remove(ssFrag);
//                System.out.println("frag list after removal: " + essential_frag_list);
//            }

            for(int i=0;i<essential_frag_list.size();i++){
                //save status for the frags
//               essential_frag_list[i].saveStatus();
                System.out.println("current frag: " + currentFragName + " saved frag: " + essential_frag_list.get(i));
               essential_frag_list.get(i).saveStatus();
            }
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
//        data.putExtra("return_data",item_data);
//        data.putExtra("comment",comment);
//        currentData = new Bundle();
//        data.putExtras(currentData);
        setResult(RESULT_CANCELED,null);
        super.finish();
    }

}
