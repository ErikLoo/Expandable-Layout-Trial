package com.example.expandablelayouttrial;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomArrayListAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    //to store the animal images
//    private final Integer[] imageIDarray;
//
//    //to store the list of countries
//    private final String[] nameArray;

    //to store the list of countries
    private final String[] infoArray;

    public CustomArrayListAdapter(Activity context,String[] infoArrayParam){

        super(context,R.layout.summary_item , infoArrayParam);

        this.context=context;
//        this.imageIDarray = imageIDArrayParam;
////        this.nameArray = nameArrayParam;
        this.infoArray = infoArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.summary_item, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView infoTextField = (TextView) rowView.findViewById(R.id.sum_cond);

        //this code sets the values of the objects to values from the arrays
        infoTextField.setText(infoArray[position]);

        return rowView;

    };

}
