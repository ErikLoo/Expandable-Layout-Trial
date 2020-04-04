package com.example.expandablelayouttrial;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class summaryFrag extends Fragment {

    private Bundle savedStates;
    private View i_rmd;
    private View s_rmd;
    private View t_rmd;
    private TextView r_msg;

    private Spinner sp_dl;
    private Spinner sp_dur;


    private String[] dur_array= {"30 minutes","0 minute","60 minutes","2 hours","5 hours"};
    private String[] interact_array = {"0","1","2","3","4","5"};
    private String[] weekdays = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    public summaryFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        i_rmd = view.findViewById(R.id.i_rmd);
        s_rmd = view.findViewById(R.id.s_rmd);
        t_rmd = view.findViewById(R.id.t_rmd);

        r_msg = view.findViewById(R.id.rmd_msg);


        savedStates = this.getArguments();

        if(savedStates!=null){

            if(savedStates.getString("rMsg")!=null){r_msg.setText(savedStates.getString("rMsg"));}

            if(savedStates.getString("set_state_rmd")!=null && !savedStates.getString("set_state_rmd").equals("0") ) {
                //change the visibility of the layout
                s_rmd.setVisibility(View.VISIBLE);

                String delay_info = "immediately";
                String dur_info = "";
                String see_info = "see";
                String in_view_info = "";

                TextView see_val = view.findViewById(R.id.see_val_s);
                TextView conditions = view.findViewById(R.id.conditions_s);
                sp_dl =view.findViewById(R.id.spinner1);
                sp_dur = view.findViewById(R.id.spinner2);

                //get the info
                if(savedStates.getString("set_dl")!=null && savedStates.getString("set_dl").equals("1") && savedStates.getString("dur_dl")!=null){
//                    delay_info = "I will hold off the reminder until "+ sp_dl.getItemAtPosition(Integer.parseInt(savedStates.getString("dur_dl"))) + " later.";
                    delay_info = "I will hold off the reminder until "+ dur_array[Integer.parseInt(savedStates.getString("dur_dl"))] + " later.";


                }

                if (savedStates.getString("sw_in_view_val")!=null){
                    if(savedStates.getString("sw_in_view_val").equals("0")){
                        in_view_info = " in my view ";
                    }else{
                        in_view_info = " not in my view ";
                    }
                }

                if(savedStates.getString("set_dur")!=null&&savedStates.getString("set_dur").equals("1")  && savedStates.getString("dur_view")!=null){
                    System.out.println(" dur view" + savedStates.getString("dur_view"));
                    dur_info="I will fire off the reminder when the selected frames are" + in_view_info +"for more than " + dur_array[Integer.parseInt(savedStates.getString("dur_view"))] + ".";
                }
                if(savedStates.getString("sw_show_val")!=null){
                    if (savedStates.getString("sw_show_val").equals("0")){
                        see_info = "don't see";
                    }else{
                        see_info = "see";

                    }
                }

                see_val.setText(see_info);

                conditions.setText(delay_info+"\n"+dur_info);

                System.out.println("conditions: " + conditions.getText());
                //fill the info
            }

            if(savedStates.getString("set_interact_rmd")!=null && !savedStates.getString("set_interact_rmd").equals("0") ) {
                //change the visibility of the layout
                i_rmd.setVisibility(View.VISIBLE);


                String ddl_info = "";
                String repeat_info = "";
                String see_info = "";
                String num_interact_info="";

                TextView see_val = view.findViewById(R.id.see_val_i);
                TextView num_interact_view = view.findViewById(R.id.interact_num_i);
                TextView conditions = view.findViewById(R.id.conditions_i);
                Spinner sp_num_interct =view.findViewById(R.id.spinner3);

                //get the info
                if(savedStates.getString("set_ddl").equals("1") && savedStates.getString("fhour")!=null && savedStates.getString("fmins")!=null){
//                    delay_info = "I will hold off the reminder until "+ sp_dl.getItemAtPosition(Integer.parseInt(savedStates.getString("dur_dl"))) + " later.";
                    ddl_info = "before "+ savedStates.getString("fhour") + " : " + savedStates.getString("fmins")+"\n";
                }

                if(savedStates.getString("set_rep").equals("1")  && savedStates.getIntArray("weekday_int")!=null){

                    int[] week = savedStates.getIntArray("weekday_int");

                    for(int i=0;i<week.length;i++){
                        if(week[i]==1){
                            repeat_info+=weekdays[i];
                            if(i<week.length-1){
                                repeat_info+=",";
                            }
                        }

                    }

                    if(repeat_info.length()>0){
                        repeat_info = "on every " + repeat_info;
                    }else{
                        repeat_info = "tomorrow";
                    }


                }
                if(savedStates.getString("sw_show_val")!=null){
                    if (savedStates.getString("sw_show_val").equals("0")){
                        see_info = "don't see";
                    }else{
                        see_info = "see";

                    }
                    see_val.setText(see_info);
                }

                if(savedStates.getString("num_interac")!=null){
//                    delay_info = "I will hold off the reminder until "+ sp_dl.getItemAtPosition(Integer.parseInt(savedStates.getString("dur_dl"))) + " later.";
                    num_interact_info =  interact_array[Integer.parseInt(savedStates.getString("num_interac"))] + " instances of";
                    num_interact_view.setText(num_interact_info);

                }

                conditions.setText(ddl_info + repeat_info);

                System.out.println("conditions: " + conditions.getText());
                //fill the info
            }

            if(savedStates.getString("set_time_rmd")!=null && !savedStates.getString("set_time_rmd").equals("0") ){

                t_rmd.setVisibility(View.VISIBLE);

                String repeat_info = "";
                String ddl_info = "";

                TextView conditions = view.findViewById(R.id.conditions_t);

                if(savedStates.getString("Thour")!=null && savedStates.getString("Tmins")!=null){
                    ddl_info = "at "+ savedStates.getString("Thour") + " : " + savedStates.getString("Tmins") +"\n";
                }

                if(savedStates.getIntArray("Tweekday_int")!=null){

                    int[] week = savedStates.getIntArray("Tweekday_int");

                    for(int i=0;i<week.length;i++){
                        if(week[i]==1){
                            repeat_info+=weekdays[i];
                            if(i<week.length-1){
                                repeat_info+=", ";
                            }
                        }

                    }

                    if(repeat_info.length()>0){
                        repeat_info = "on every " + repeat_info;
                    }else{
                        repeat_info = "tomorrow";
                    }

                }

                conditions.setText(ddl_info + repeat_info);

            }


        }else{
            savedStates = new Bundle();
        }

        //mark the summary frag as created by access
        savedStates.putInt("sumCreated",1);

        return view;
    }

}
