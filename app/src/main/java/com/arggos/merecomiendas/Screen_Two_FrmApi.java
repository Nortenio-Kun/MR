package com.arggos.merecomiendas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class Screen_Two_FrmApi extends AppCompatActivity {

    //Variables generales de Firebase
    private FirebaseAuth mAuth;
    //Variables formulario
    EditText name,correo;
    ImageView imagen;
    Button otp,map;
    EditText calle,colonia,num,cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_api);

        name = findViewById(R.id.Name);
        correo = findViewById(R.id.Correo);
        imagen = findViewById(R.id.ImgProfile);
        otp = findViewById(R.id.otp);
        calle = findViewById(R.id.Calle);
        colonia = findViewById(R.id.Colonia);
        num = findViewById(R.id.NumeroInt);
        cp = findViewById(R.id.Cp);

        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent second = new Intent(Screen_Two_FrmApi.this, Screen_Three_Otp.class);
                startActivity(second);
                finish();
            }
        });

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
    }



    public void Mensaje(String texto){
        Toast.makeText(Screen_Two_FrmApi.this, texto, Toast.LENGTH_SHORT).show();
    }

    public void auto_llenado(View view){

        Fragment mFragment = null;
        mFragment = new MapViewFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
       fragmentManager.beginTransaction().replace(R.id.Form, mFragment).addToBackStack("hola").commit();
     //   fragmentManager.beginTransaction().add(mFragment,"hola").addToBackStack("hola").commit();
    }

    public void llenar(){
        SharedPreferences sp = this.getPreferences( MODE_PRIVATE);
        calle.setText(sp.getString("direccion","hola"));
    }
}
