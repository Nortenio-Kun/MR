package com.arggos.merecomiendas;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class Screen_One_LoginandRegister extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 7117;
    List<AuthUI.IdpConfig> providers;
    FirebaseDatabase database;
    DatabaseReference reference,reference2;
    FirebaseAuth mAuth;
    String userId;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginandregister);
        firebasestart();
        //Init providers
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );
        showSignInOptions();
        pref = this.getSharedPreferences("API", Context.MODE_PRIVATE);
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(providers)
                        .setTheme(R.style.ProvidersTheme)
                        .build(),MY_REQUEST_CODE
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MY_REQUEST_CODE){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){
                mAuth= FirebaseAuth.getInstance();
                userId = mAuth.getCurrentUser().getUid();
                //Get User
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                reference.child("Usuarios").child("Clientes").child(userId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("Confirmacion").getValue(String.class).equals("True")){
                            UserDetails.username=mAuth.getCurrentUser().getUid();
                            Intent intent = new Intent(Screen_One_LoginandRegister.this, Screen_Four_Menu.class);
                            reference.removeEventListener(this);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("Imagen",String.valueOf(user.getPhotoUrl()));
                            Screen_Four_Menu.nombres=dataSnapshot.child("Name").getValue(String.class);
                            Screen_Four_Menu.correos=dataSnapshot.child("Email").getValue(String.class);
                            editor.commit();
                            startActivity(intent);
                            finish();
                        }else{
                            Intent intent = new Intent(Screen_One_LoginandRegister.this, Screen_Two_FrmApi.class);
                            reference.removeEventListener(this);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("Imagen",String.valueOf(user.getPhotoUrl()));
                            editor.putString("Nombre",user.getDisplayName());
                            editor.putString("Correo",user.getEmail());
                            editor.commit();
                            Log.e("Ho"," "+String.valueOf(user.getPhotoUrl())+user.getDisplayName());
                            startActivity(intent);
                            finish();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }else{
                Toast.makeText(this, ""+response.getError().getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebasestart() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }
}
