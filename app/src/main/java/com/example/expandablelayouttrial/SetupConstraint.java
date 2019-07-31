package com.example.expandablelayouttrial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class SetupConstraint extends Fragment {

    private View v;

    private CriteriaListAdapter criteria_adapter;

    private String act_name;
    private String start_time_hr;
    private String start_time_min;
    private String duration;
    private String week_day; //in the form of 0010010

    private TextView act_name_view;

    private int[] checkStatus = new int[6];

    private AtomPayment item_data;

    private ListView atomPaysListView;

    private String conditions;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //don't use findByID here

        View view = inflater.inflate(R.layout.setup_constraints,container,false);
        v = view;

//        setup_constraints();

        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { //do find by ID here

        super.onViewCreated(view, savedInstanceState);
    }

    public void initData(){
        conditions = Arrays.toString(new int[] {0,0,0,0,0,0});
        setup_constraints();
    }

    public void setup_constraints(){
        add_constraints((CheckBox) v.findViewById(R.id.checkBox_walk),false,"0");
        add_constraints((CheckBox) v.findViewById(R.id.checkBox_shower),false,"1");
        add_constraints((CheckBox) v.findViewById(R.id.checkBox_cook),false,"2");
        add_constraints((CheckBox) v.findViewById(R.id.checkBox_hold),false,"3");
        add_constraints((CheckBox) v.findViewById(R.id.checkBox_look),false,"4");
        add_constraints((CheckBox) v.findViewById(R.id.checkBox_not_moving),false,"5");

    }

    public void add_constraints(CheckBox check_view,boolean checked,String ID){

        dataHolder mDataHolder = new dataHolder(checked,ID);

        if(conditions!=null){
            String[] condtion_input = conditions.split(",");

            if(condtion_input[Integer.parseInt(ID)].replaceAll("\\[","").replaceAll("\\]","").trim().equals("1")) {
                mDataHolder.setChecked(true);
                checkStatus[Integer.parseInt(ID)] = 1;
                check_view.setChecked(true);
            }
            else{
                mDataHolder.setChecked(false);
                checkStatus[Integer.parseInt(ID)] = 0;
                check_view.setChecked(false);
            }
        }

        check_view.setTag(mDataHolder);
    }


    public void checkTracker(View v) { //if one of the weekday was pressed
        dataHolder condition = (dataHolder) v.getTag();

        if(condition.getChecked()==false) {
            condition.setChecked(true);
            checkStatus[Integer.parseInt(condition.getID())] = 1;
        }
        else {
            condition.setChecked(false);
            checkStatus[Integer.parseInt(condition.getID())] = 0;
        }
        //record the current status of the weekdays
    }

    public class dataHolder {
        boolean checked;
        String Id;


        public dataHolder(boolean checked,String Id){
            this.checked = checked;
            this.Id = Id;
        }

        public void setChecked(boolean checked){this.checked = checked;}
        public boolean getChecked() {return checked;}
        public String getID(){return Id;}
    }


    public void get_constraints_from_frag(AtomPayment data) {
        data.setConditions(Arrays.toString(checkStatus));
    }

    public void setConstraints(AtomPayment activity_data){
        if(activity_data!=null&&activity_data.getConditions()!=null){
            conditions = activity_data.getConditions();
            setup_constraints();
        }
    }
}
