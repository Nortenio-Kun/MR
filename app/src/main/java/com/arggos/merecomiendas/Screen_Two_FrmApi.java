package com.arggos.merecomiendas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.TRUE;

public class Screen_Two_FrmApi extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    //Variables generales de Firebase
    private FirebaseAuth mAuth;
    //Variables formulario
    EditText name,correo,direccion,cp;
    ImageView imagen;
    Button otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_frm_api);

        mAuth = FirebaseAuth.getInstance();
        UserDetails.username=mAuth.getCurrentUser().getUid();

        name = findViewById(R.id.Name);
        correo = findViewById(R.id.Correo);
        imagen = findViewById(R.id.ImgProfile);
        direccion = findViewById(R.id.Direccion);
        otp = findViewById(R.id.otp);


        SharedPreferences pref = this.getSharedPreferences("API", Context.MODE_PRIVATE);
        String nombre = pref.getString("Nombre","No");
        if(nombre != "No"){
           name.setText(nombre);
        }
        String mail = pref.getString("Correo","No");
        if(mail != "No"){
            correo.setText(mail);
        }
        String foto = pref.getString("Imagen","No");
        if(foto != "No"){
            Picasso.get().load(foto+"?type=large").into(imagen);
        }
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Usuarios").child("Clientes").child(user_id);
                //current_user_db.setValue(true);
                Map<String, Object> datosUsuario = new HashMap<>();
                datosUsuario.put("Name",name.getText().toString());
                datosUsuario.put("Email",correo.getText().toString());
                datosUsuario.put("Direction",direccion.getText().toString());
                datosUsuario.put("profileimage",foto);
                datosUsuario.put("Confirmacion","True");
//                datosUsuario.put("Cp",cp.getText().toString());
                current_user_db.updateChildren(datosUsuario);
                Intent second = new Intent(Screen_Two_FrmApi.this, Screen_Four_Menu.class);
                startActivity(second);
                finish();
            }
        });
    }



    public void Mensaje(String texto){
        Toast.makeText(Screen_Two_FrmApi.this, texto, Toast.LENGTH_SHORT).show();
    }

    public void auto_llenado(View view){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            //Toast.makeText(Cont, "permission denied", Toast.LENGTH_LONG).show();


        } else {
            Fragment mFragment = null;
            mFragment = new MapViewFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.Form, mFragment).addToBackStack("hola").commit();

            // Toast.makeText(Cont, "permission denied", Toast.LENGTH_LONG).show();

        }
    }


    public void auto_permiso(){
        Fragment mFragment = null;
            mFragment = new MapViewFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.Form, mFragment).addToBackStack("hola").commit();



    }

    public void llenar(){
        SharedPreferences sp = this.getPreferences( MODE_PRIVATE);
        direccion.setText(sp.getString("direccion","hola"));
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Toast.makeText(this, "Permiso llego", Toast.LENGTH_SHORT).show();
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        auto_permiso();

                    }

                } else {

                    Toast.makeText(this, "Imposible Entrar al Mapa", Toast.LENGTH_SHORT).show();
                    // Permission denied, Disable the functionality that depends on this permission.
                    //   Toast.makeText(Cont, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }



            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }
}
