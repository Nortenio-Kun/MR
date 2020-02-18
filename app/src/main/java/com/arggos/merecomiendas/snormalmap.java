package com.arggos.merecomiendas;



import androidx.fragment.app.FragmentActivity;



import android.location.Address;

import android.location.Geocoder;

import android.location.Location;

import android.location.LocationListener;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.Toast;



import com.firebase.geofire.GeoFire;

import com.firebase.geofire.GeoLocation;

import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationRequest;

import com.google.android.gms.location.LocationServices;

import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.Marker;

import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;



import java.io.IOException;

import java.util.List;

import java.util.Locale;



public class snormalmap extends FragmentActivity implements OnMapReadyCallback, LocationListener {



    private GoogleMap mMap;

    private FusedLocationProviderClient client;

    LatLng startlatlng;

    Location lastlocation;

    Button dir,confirmar;

    LatLng pickuprequest;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_snormalmap);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()

                .findFragmentById(R.id.map1);

        mapFragment.getMapAsync(this);

        dir =  findViewById(R.id.tudir);

        confirmar = findViewById(R.id.ConfirmarPosition);

        confirmar.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("CostumerRequest");

                GeoFire geoFire = new GeoFire(databaseReference);

                geoFire.setLocation(userid, new GeoLocation(lastlocation.getLatitude(),lastlocation.getLongitude()));

                pickuprequest = new LatLng(lastlocation.getLatitude(),lastlocation.getLongitude());

            }

        });

    }





    @Override

    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;



        client = LocationServices.getFusedLocationProviderClient(this);

        client.getLastLocation().addOnSuccessListener(snormalmap.this, new OnSuccessListener<Location>() {

            @Override

            public void onSuccess(Location location) {

                lastlocation = location;

                // Add a marker in Sydney and move the camera

                startlatlng = new LatLng(location.getLatitude(),location.getLongitude());

                Geocoder geocoder = new Geocoder(snormalmap.this, Locale.getDefault());

                try

                {

                    List<Address> myadress = geocoder.getFromLocation(startlatlng.latitude,startlatlng.longitude,1);

                    String adress = myadress.get(0).getAddressLine(0);

                    dir.setText(adress);

                } catch (Exception e) {

                    e.printStackTrace();

                }

                startlatlng = new LatLng(location.getLatitude(), location.getLongitude());

                mMap.addMarker(new MarkerOptions().position(startlatlng).title("Aqui te encuentras").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(startlatlng));

                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

            }

        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {



            @Override

            public void onMapClick(LatLng position) {

                //mark.remove();

                //mark = mMap.addMarker(new MarkerOptions().position(position));

            }

        });

    }



    @Override

    public void onLocationChanged(Location location) {



    }



    @Override

    public void onStatusChanged(String provider, int status, Bundle extras) {



    }



    @Override

    public void onProviderEnabled(String provider) {



    }



    @Override

    public void onProviderDisabled(String provider) {



    }

    public void Mensaje(String texto){

        Toast.makeText(snormalmap.this, texto, Toast.LENGTH_SHORT).show();

    }

}