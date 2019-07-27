package com.example.expandablelayouttrial;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class config_activity extends AppCompatActivity {


    private ExpandableListView listView;
    private ExpandableListAdapater listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    private View myView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_fragments);

        setUp();
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


//        listView = (ExpandableListView) findViewById(R.id.lvExp);
//        initData();
//        listAdapter = new ExpandableListAdapater(config_activity.this,listDataHeader,listHash);
//        listView.setAdapter(listAdapter);
    }

    private void setUp(){
        setupData((TextView) findViewById(R.id.label_name_time),(Switch) findViewById(R.id.switch_time),(View) findViewById(R.id.my_time_frag) );
        setupData((TextView) findViewById(R.id.label_name_location),(Switch) findViewById(R.id.switch_location),(View) findViewById(R.id.my_map_frag) );
        setupData((TextView) findViewById(R.id.label_name_constraints),(Switch) findViewById(R.id.switch_contraints),(View) findViewById(R.id.my_constraint_frag) );
    }

    private void setupData(TextView tv,Switch sv,View cv){
        tv.setTag(new dataHolder(sv,cv,false));
        cv.setVisibility(View.GONE);
    }

    public  class dataHolder {
        Switch switch_view;
        View contentView;
        boolean visible;

        public dataHolder(Switch sv,View cv,boolean visible){
            this.switch_view = sv;
            this.contentView = cv;
            this.visible = visible;
        }

        public void setFalse(){visible=false;}
        public void setTrue(){visible=true;}
    }

    public void expand_and_collapse(View v){
        dataHolder dataPoint = (dataHolder) v.getTag();

        if(dataPoint.visible==false) {
            dataPoint.contentView.setVisibility(View.VISIBLE);
            dataPoint.setTrue();
        }
        else {
            dataPoint.contentView.setVisibility(View.GONE);
            dataPoint.setFalse();
        }
    }

    private void initData() {

        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        listDataHeader.add("Time criteria");
        listDataHeader.add("Location criteria");
        listDataHeader.add("Other criteria");

        List<String> item_1 = new ArrayList<>();
        item_1.add("time");

        List<String> item_2 = new ArrayList<>();
        item_2.add("location");
//        item_2.add("the child of the 2nd item 2");

        List<String> item_3 = new ArrayList<>();
        item_3.add("other");
//        item_3.add("the child of the 3rd item 3");

        List<String> item_4 = new ArrayList<>();
        item_4.add("the child of the 4nd item 4");
//        item_4.add("the child of the 4nd item 4");

        listHash.put(listDataHeader.get(0),item_1);
        listHash.put(listDataHeader.get(1),item_2);
        listHash.put(listDataHeader.get(2),item_3);
//        listHash.put(listDataHeader.get(3),item_4);
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        SetupTimeFragment currentFragment = (SetupTimeFragment) fragmentManager.findFragmentById(R.id.time_fragmemt);

        if (currentFragment==null) {
            System.out.println("this is null!!!");
        }
        else {
            currentFragment.WeekdayPressed(v);
            System.out.println("this is not a null!!!!");
        }
    }

    public void checkTracker(View v) {

    }
}
