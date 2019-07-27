package com.example.expandablelayouttrial;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapater extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;

    private static final Integer CHILD_TYPE_1  = 0;
    private static final Integer CHILD_TYPE_2  = 1;
    private static final Integer CHILD_TYPE_3  = 2;
    private static final Integer CHILD_TYPE_UNDEFINED = 3;

    public ExpandableListAdapater(Context context, List<String> listdataHeader, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listDataHeader = listdataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);


        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group,null);
        }

        TextView listHeader = (TextView) convertView.findViewById(R.id.ListHeader);
        listHeader.setTypeface(null, Typeface.BOLD);
        listHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {

        switch (groupPosition) {
            case 0:
                switch (childPosition) {
                    case 0:
                        return CHILD_TYPE_1 ;
                    case 1:
                        return CHILD_TYPE_UNDEFINED ;
                    case 2:
                        return CHILD_TYPE_UNDEFINED ;
                }
                break;
            case 1:
                switch (childPosition) {
                    case 0:
                        return CHILD_TYPE_2;
                    case 1:
                        return CHILD_TYPE_3;
                    case 2:
                        return CHILD_TYPE_3;
                }
                break;
            case 2:
                switch (childPosition) {
                    case 0:
                        return CHILD_TYPE_3;
                    case 1:
                        return CHILD_TYPE_UNDEFINED ;
                    case 2:
                        return CHILD_TYPE_UNDEFINED ;
                }
                break;
            default:
                return CHILD_TYPE_UNDEFINED;
        }

        return CHILD_TYPE_UNDEFINED;
    }

    @Override
    public int getChildTypeCount() {
        return 4;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String)getChild(groupPosition,childPosition);

        System.out.println("Group: " + getGroup(groupPosition) + "  " + "Child: " + childText);

        Integer childType = new Integer(getChildType(groupPosition, childPosition));

        if(convertView==null||!(convertView.getTag().equals(childType))) {

            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if(childType.equals(CHILD_TYPE_1 )) {

                convertView = inflater.inflate(R.layout.add_time_fragment, null); //implement a checker to check the name of the activitye
                convertView.setTag(childType);
            }
            else if(childType.equals(CHILD_TYPE_2 )) {
                convertView = inflater.inflate(R.layout.add_map_fragment,null); //implement a checker to check the name of the activitye
                convertView.setTag(childType);
            }
            else if(childType.equals(CHILD_TYPE_3 )) {
                convertView = inflater.inflate(R.layout.add_constraints_fragment,null); //implement a checker to check the name of the activitye
                convertView.setTag(childType);
            }

        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
