package com.arggos.merecomiendas;

import androidx.appcompat.app.AppCompatActivity;

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

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class Screen_Two_FrmApi extends AppCompatActivity {

    //Variables generales de Firebase
    private FirebaseAuth mAuth;
    //Variables formulario
    EditText name,correo;
    ImageView imagen;
    Button otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_api);

        name = findViewById(R.id.Name);
        correo = findViewById(R.id.Correo);
        imagen = findViewById(R.id.ImgProfile);
        otp = findViewById(R.id.otp);

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
}
