package com.arggos.merecomiendas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class snormal extends AppCompatActivity {

    Drawable icon;
    ImageButton btn;
    Calendar c;
    DatePickerDialog datePickerDialog;
    TextView fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        setContentView(R.layout.activity_snormal);
        btn = findViewById(R.id.f1);
        fecha = findViewById(R.id.fecha);
    }

    public void Cargarimg(View view) {

        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(i.createChooser(i,"Seleccione Aplicacion"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri uri = data.getData();
            InputStream is;
            try {
                is = this.getContentResolver().openInputStream( uri );
                BitmapFactory.Options options=new BitmapFactory.Options();
                options.inSampleSize = 16;
                Bitmap preview_bitmap=BitmapFactory.decodeStream(is,null,options);

                icon = new BitmapDrawable(getResources(),preview_bitmap);

            } catch (FileNotFoundException e) {
                //set default image from the button
                icon = getResources().getDrawable(R.drawable.camara);
            }
            btn.setImageDrawable(null);
            btn.setBackground(icon);
        }
    }


    public void CargarF(View view) {
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(snormal.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int myear, int mmonth, int mday) {
                //Aqui se pone que se hace con la fecha
                fecha.setText(mday +"/"+(mmonth+1)+"/"+myear);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void subir(View view) {
        Intent i = new Intent(snormal.this,snormalmap.class);
        startActivity(i);
    }
}
