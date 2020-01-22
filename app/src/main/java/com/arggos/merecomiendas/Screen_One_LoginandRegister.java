package com.arggos.merecomiendas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class Screen_One_LoginandRegister extends AppCompatActivity implements View.OnClickListener {

    //Declaracion de Variables y animaciones.
    ImageView Imgvwelcome;
    Animation Bottom;
    LinearLayout Textwelcome,Textregistro;
    RelativeLayout frm;
    ImageButton Btnregister;
    TextView help;
    //Botones para incio sesion
    Button InicioFirebase;
    ImageButton InicioFacebook,Iniciogoogle;
    //Variables generales de Firebase
    private FirebaseAuth mAuth;
    //Variables personalizadas para el uso de GoogleAPI
    GoogleSignInClient googleSignInClient;
    static final int GOOGLE_SIGN = 123;
    //Variables perzonalizadas para el uso de FacebookAPI
    private CallbackManager mCallbackManager;
    //Shared preferences
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Codigo para evitar foco automatico del edittext
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        //Codigo para evitar foco automatico del edittext
        setContentView(R.layout.activity_loginandregister);
        //shared preferences
        pref = this.getSharedPreferences("API", Context.MODE_PRIVATE);
        //Codigo exlusivo para las animaciones y efectos visuales del formulario de inicio de sesion
        //Asignacion de las animaciones.
        Bottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        //Asignacion de las Variables.
        Imgvwelcome = findViewById(R.id.ImgvWelcome);
        Textwelcome = findViewById(R.id.TextWelcome);
        Textregistro = findViewById(R.id.TextRegistro);
        Btnregister = findViewById(R.id.BtnRegister);
        frm = findViewById(R.id.Formulario);
        help = findViewById(R.id.Help);
        InicioFirebase = findViewById(R.id.BtnInicioSesion);
        InicioFacebook = findViewById(R.id.InicioFacebook);
        Iniciogoogle = findViewById(R.id.InicioGoogle);
        mAuth = FirebaseAuth.getInstance();
        //Declaracion listeners botones inicio sesion
        InicioFirebase.setOnClickListener(this);
        InicioFacebook.setOnClickListener(this);
        Iniciogoogle.setOnClickListener(this);
        //Uso de las animaciones para el mensaje de inicio
        Imgvwelcome.animate().translationY(-2500).setDuration(800).setStartDelay(300);
        Textwelcome.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);
        Textregistro.startAnimation(Bottom);
        frm.startAnimation(Bottom);
        //Declaracion OnClickListeners
        Btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Screen_One_LoginandRegister.this, Register_Activity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(help,"help");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(Screen_One_LoginandRegister.this, pairs);
                startActivity(i);
            }
        });
        //Fin Codigo exlusivo para las animaciones y efectos visuales del formulario de inicio de sesion
        //Inicio Codigo Exclusivo Google API
        GoogleSignInOptions  googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        //Inicio Codigo Exclusivo Facebook API
        mCallbackManager = CallbackManager.Factory.create();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.BtnInicioSesion: /** Inicio de Sesion con Firebase via usuario y contraseña */
                Intent i = new Intent(Screen_One_LoginandRegister.this,Screen_Four_Menu.class);
                startActivity(i);
                break;
            case R.id.InicioFacebook:
                LoginManager.getInstance().logInWithReadPermissions(Screen_One_LoginandRegister.this, Arrays.asList("email","public_profile"));
                LoginManager.getInstance().registerCallback(
                        mCallbackManager, new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                Log.d("TAGFACE", "facebook:onSuccess:" + loginResult.getAccessToken());
                                handleFacebookAccessToken(loginResult.getAccessToken());
                            }

                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onError(FacebookException error) {
                                Mensaje(error.toString());
                            }
                        }
                );
                break;
            case R.id.InicioGoogle:
                Intent signintent = googleSignInClient.getSignInIntent();
                startActivityForResult(signintent, GOOGLE_SIGN);
                break;
        }
    }

    public void Mensaje(String texto){
        Toast.makeText(Screen_One_LoginandRegister.this, texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_SIGN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if(account != null){
                    Log.e("TAGGO", "firebaseauthwithgoogle "+account.getId());
                    AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                    mAuth.signInWithCredential(credential).addOnCompleteListener(this, tasks -> {
                        if(tasks.isSuccessful()){
                            Log.e("TAGGO", "signin success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("Imagen",String.valueOf(user.getPhotoUrl()));
                            editor.putString("Nombre",user.getDisplayName());
                            editor.putString("Correo",user.getEmail());
                            editor.commit();
                            updateUI();
                        }else{
                            Log.d("TAGGO", "signin failure");
                        }
                    });
                }
            }catch (ApiException e){
                e.printStackTrace();
            }
        }
        else {
            // Pass the activity result back to the Facebook SDK
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credentials = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credentials)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("Imagen",String.valueOf(user.getPhotoUrl()));
                            editor.putString("Nombre",user.getDisplayName());
                            editor.putString("Correo",user.getEmail());
                            editor.commit();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAGFACE", "signInWithCredential:failure", task.getException());
                            Mensaje("Error");
                        }
                    }
                });
    }

    private void updateUI(){
        Intent second = new Intent(Screen_One_LoginandRegister.this, Screen_Two_FrmApi.class);
        startActivity(second);
        finish();
    }

}
