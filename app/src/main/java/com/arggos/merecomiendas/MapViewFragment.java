
        package com.arggos.merecomiendas;

        import androidx.annotation.NonNull;

        import androidx.annotation.Nullable;

        import androidx.core.app.ActivityCompat;

        import androidx.core.content.ContextCompat;

        import androidx.fragment.app.Fragment;

        import android.location.Location;

        import android.location.LocationListener;

        import android.Manifest;

        import android.app.Activity;

        import android.content.Context;

        import android.content.SharedPreferences;

        import android.content.pm.PackageManager;

        import android.location.Address;

        import android.location.Geocoder;

        import android.os.Build;

        import android.os.Bundle;

        import android.view.LayoutInflater;

        import android.view.View;

        import android.view.ViewGroup;

        import android.widget.Button;

        import android.widget.Toast;



        import com.google.android.gms.common.ConnectionResult;

        import com.google.android.gms.common.api.GoogleApiClient;

        import com.google.android.gms.location.FusedLocationProviderClient;

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



        import java.util.List;

        import java.util.Locale;



        import static android.content.Context.MODE_PRIVATE;



        public class MapViewFragment extends Fragment implements OnMapReadyCallback,

        GoogleApiClient.ConnectionCallbacks,

        GoogleApiClient.OnConnectionFailedListener,

        LocationListener{



        private GoogleMap mMap;

        Button btn;

        Fragment f = this;

        private FusedLocationProviderClient client;

        Marker mark;

        GoogleApiClient mGoogleApiClient;

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

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        checkLocationPermission();

        }

        btn = view.findViewById(R.id.starte);

        btn.setOnClickListener(new View.OnClickListener() {

        @Override

        public void onClick(View v) {

        getFragmentManager().beginTransaction().remove(f).commit();

        }

        });

        return view;



        }

        @Override

        public void onMapReady(GoogleMap googleMap) {

        mMap=googleMap;



        //Initialize Google Play Services

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        if (ContextCompat.checkSelfPermission(getContext(),

        Manifest.permission.ACCESS_FINE_LOCATION)

        == PackageManager.PERMISSION_GRANTED) {

        buildGoogleApiClient();

        mMap.setMyLocationEnabled(true);

        }

        }

        else {

        buildGoogleApiClient();

        mMap.setMyLocationEnabled(true);

        }





        client = LocationServices.getFusedLocationProviderClient(getContext());

        client.getLastLocation().addOnSuccessListener((Activity) getContext(), new OnSuccessListener<Location>() {

        @Override

        public void onSuccess(Location location) {

        // Add a marker in Sydney and move the camera

        LatLng startlatlng = new LatLng(location.getLatitude(),location.getLongitude());

        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

        try

        {

        List<Address> myadress = geocoder.getFromLocation(startlatlng.latitude,startlatlng.longitude,1);

        String adress = myadress.get(0).getAddressLine(0);

        SharedPreferences sp = getActivity().getPreferences( MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        editor.putString("direccion",adress);

        editor.apply();

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





        SharedPreferences sp = this.getActivity().getPreferences( MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {



        @Override

        public void onMapClick(LatLng position) {

        //mark.remove();

        //mark = mMap.addMarker(new MarkerOptions().position(position));

        }

        });

        }







        protected synchronized void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())

        .addConnectionCallbacks(this)

        .addOnConnectionFailedListener(this)

        .addApi(LocationServices.API)

        .build();

        mGoogleApiClient.connect();

        }







        @Override

        public void onDestroyView(){

        super.onDestroyView();

        ((Screen_Two_FrmApi) getContext()).llenar();



        }





        public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

        public boolean checkLocationPermission(){

        if (ContextCompat.checkSelfPermission(getContext(),

        Manifest.permission.ACCESS_FINE_LOCATION)

        != PackageManager.PERMISSION_GRANTED) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),

        Manifest.permission.ACCESS_FINE_LOCATION)) {

        ActivityCompat.requestPermissions(getActivity(),

        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},

        MY_PERMISSIONS_REQUEST_LOCATION);

        } else {

        ActivityCompat.requestPermissions(getActivity(),

        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},

        MY_PERMISSIONS_REQUEST_LOCATION);

        }

        return false;

        } else {

        return true;

        }

        }



        @Override

        public void onRequestPermissionsResult(int requestCode,

        String permissions[], int[] grantResults) {

        switch (requestCode) {

        case MY_PERMISSIONS_REQUEST_LOCATION: {

        if (grantResults.length > 0

        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        if (ContextCompat.checkSelfPermission(getContext(),

        Manifest.permission.ACCESS_FINE_LOCATION)

        == PackageManager.PERMISSION_GRANTED) {



        if (mGoogleApiClient == null) {

        buildGoogleApiClient();

        }

        mMap.setMyLocationEnabled(true);

        }



        } else {

        // Permission denied, Disable the functionality that depends on this permission.

        Toast.makeText(getContext(), "permission denied", Toast.LENGTH_LONG).show();

        }

        return;

        }

        }

        }



        @Override

        public void onLocationChanged(Location location) {



        }



        @Override

        public void onStatusChanged(String s, int i, Bundle bundle) {



        }



        @Override

        public void onProviderEnabled(String s) {



        }



        @Override

        public void onProviderDisabled(String s) {



        }



        @Override

        public void onConnected(@Nullable Bundle bundle) {



        }



        @Override

        public void onConnectionSuspended(int i) {



        }



        @Override

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {



        }

        }




