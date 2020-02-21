package com.arggos.merecomiendas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Screen_Three_Otp extends AppCompatActivity {

    private CountryCodePicker ccp;
    private EditText phonetext,codetext;
    private Button next;
    private String cheker = "", phoneNumber = "";
    private RelativeLayout layout;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mresendingToken;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        mAuth = FirebaseAuth.getInstance();
        loadingBar =  new ProgressDialog(this);

        phonetext = findViewById(R.id.phoneText);
        codetext = findViewById(R.id.codeText);
        next = findViewById(R.id.continueNextButton);
        layout = findViewById(R.id.phoneAuth);
        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phonetext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(next.getText().equals("Submit") || cheker.equals("Code Sent")){
                    String verificationcode = codetext.getText().toString();
                    if(verificationcode.equals("")){
                        Mensaje("Escribe numero");
                    }else {
                        loadingBar.setTitle("Verificacion codigo");
                        loadingBar.setMessage("Estamos verificando tu numero");
                        loadingBar.setCanceledOnTouchOutside(false);
                        loadingBar.show();
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationcode);
                        signInWithPhoneAuthCredential(credential);
                    }
                }else{
                    phoneNumber = ccp.getFullNumberWithPlus();
                    if(!phoneNumber.equals("")){
                        loadingBar.setTitle("Verificacion Telefonica");
                        loadingBar.setMessage("Estamos verificando tu numero");
                        loadingBar.setCanceledOnTouchOutside(false);
                        loadingBar.show();
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, Screen_Three_Otp.this, mCallbacks);        // OnVerificationStateChangedCallbacks
                    }else {
                        Mensaje("Escribe un numero valido");
                    }
                }
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Mensaje("Numero telefono invalido");
                layout.setVisibility(View.VISIBLE);
                next.setText("Continue");
                codetext.setVisibility(View.GONE);
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                mVerificationId = s;
                mresendingToken = forceResendingToken;

                layout.setVisibility(View.GONE);
                cheker = "Code Sent";
                next.setText("Submit");
                codetext.setVisibility(View.VISIBLE);
                loadingBar.dismiss();
                Mensaje("El Codigo ha sido enviado");
            }
        };

    }
    public void Mensaje(String texto){
        Toast.makeText(Screen_Three_Otp.this, texto, Toast.LENGTH_SHORT).show();
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loadingBar.dismiss();
                            Intent second = new Intent(Screen_Three_Otp.this, Screen_Four_Menu.class);
                            startActivity(second);
                            finish();
                        } else {
                            loadingBar.dismiss();
                            //Mensaje(task.getException().toString());
                        }
                    }
                });}

}
