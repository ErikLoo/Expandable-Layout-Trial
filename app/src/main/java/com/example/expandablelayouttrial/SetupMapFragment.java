package com.example.expandablelayouttrial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SetupMapFragment extends Fragment implements OnMapReadyCallback {


    //variables for storing useful data
    private String act_name;

    private GoogleMap mMap;

    private MapView mMapView;

    private EditText address_view;

    private AtomPayment item_data;

    private View v;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //don't use findByID here

        View view = inflater.inflate(R.layout.setup_map_layout,container,false);
        v = view;

//        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { //do find by ID here

        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) v.findViewById(R.id.map);

        if(mMapView!=null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

        initData();

    }

    public void initData(){
        address_view = (EditText) v.findViewById(R.id.address_id);
        address_view.setText(null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(43.659580, -79.397668);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Toronto"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void get_location_from_frag(AtomPayment data) {
        data.setLoation(address_view.getText().toString());
    }

    public void setLocation(AtomPayment activity_data){
        if(activity_data!=null && activity_data.getLocation()!=null){
             address_view.setText(activity_data.getLocation());
        }
    }
}
