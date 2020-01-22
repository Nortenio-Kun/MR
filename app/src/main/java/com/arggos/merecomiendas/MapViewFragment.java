package com.arggos.merecomiendas;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arggos.merecomiendas.Screen_Two_FrmApi;
import com.arggos.merecomiendas.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;

public class MapViewFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap mMap;
    Fragment f = this;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    Marker mark;
    public  MapViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.location_fragment, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.main_branch_map);
        mapFragment.getMapAsync(this);
        ActivityCompat.requestPermissions(this.getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
        return view;

    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        LatLng UCA = new LatLng(20.588793, -100.389885);

        mark = mMap.addMarker(new MarkerOptions().position(UCA));
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(MAP_TYPE_NORMAL);
        SharedPreferences sp = this.getActivity().getPreferences( MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng position) {
                Geocoder geocoder;
                mark.remove();
                mark = mMap.addMarker(new MarkerOptions().position(position));
                geocoder = new Geocoder(getContext(), Locale.getDefault());
                try {
                    List<Address> adress = geocoder.getFromLocation(mark.getPosition().latitude, mark.getPosition().longitude, 1);
                    String direccion = adress.get(0).getAddressLine(0).toString();
                    Toast.makeText(getContext(), "Tu Dirrecion" + direccion , Toast.LENGTH_SHORT).show();
                    editor.putString("direccion",direccion);
                    editor.apply();
                    getFragmentManager().beginTransaction().remove(f).commit();
                } catch(Throwable e){
                    e.printStackTrace();
                }



            }



        });
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        ((Screen_Two_FrmApi) getContext()).llenar();

    }

        /*Location my = mMap.getMyLocation();
        LatLng UCA = new LatLng(my.getLatitude(), my.getLongitude());
        mMap.addMarker(new MarkerOptions().position(UCA).title("Tu Ubicacion")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UCA,17));*/

}




