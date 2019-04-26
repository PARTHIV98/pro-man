package com.example.parth.pman;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateTask extends AppCompatActivity{
    private static final String TAG ="" ;
    EditText ET_1,ET_2,ET_3;
    TextView txt;
    Button btn;
    ImageView img;
    private static final int REQUEST_INVITE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate( savedInstanceState );
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled( true );
            getSupportActionBar().setDisplayShowHomeEnabled( true );
        }
        setContentView( R.layout.activity_create_task );
        String dt;

          ET_1 = (EditText) findViewById( R.id.ET1 );
      ET_2 = (EditText) findViewById( R.id.ET2 );
      ET_3 =(EditText) findViewById( R.id.ET3 );
       txt = findViewById( R.id.txtdate );
        String date_n = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        txt.setText(date_n);
        Button bt = (Button) findViewById( R.id.crtbtn );
        btn = findViewById( R.id.btnaddmember );


        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String ts = ET_1.getText().toString().trim();
                    String ds = ET_2.getText().toString().trim();
                    String dt = txt.getText().toString().trim();

                    if (TextUtils.isEmpty( ts )) {
                        ET_1.setError( "Enter  name" );
                        ET_1.requestFocus();
                    }
                    if (TextUtils.isEmpty( ds )) {
                        ET_2.setError( "Enter Description" );
                        ET_2.requestFocus();
                    }
                    if (TextUtils.isEmpty( dt )) {
                        txt.setError( "Choose Date" );
                        txt.requestFocus();
                    }
                    Intent intent = new Intent( CreateTask.this, UpdateTask.class );
                    intent.putExtra( "text1", ET_1.getText().toString() );
                    intent.putExtra( "text2", ET_2.getText().toString() );
                    intent.putExtra( "date", txt.getText().toString() );
                    startActivity( intent );
                    finish();
                    Log.i( "update", "fail" );
                }
            }
        } );

        ImageView img = findViewById( R.id.imageView3 );
        Calendar dateSelected = Calendar.getInstance();
        DatePickerDialog datePickerDialog;
        final Calendar myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                txt.setText(sdf.format(myCalendar.getTime()));
            }

        };


        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreateTask.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });





    }


    private boolean validate() {
        boolean alldone=true;
        String ts = ET_1.getText().toString().trim();
        String ds = ET_2.getText().toString().trim();
        String dt = txt.getText().toString().trim();
        if(TextUtils.isEmpty(ts))
        {
            ET_1.setError("Enter your first name");
            return false;
        }else
        {
            alldone=true;
            ET_1.setError(null);
        }
        if(TextUtils.isEmpty(ds))
        {
            ET_2.setError("Enter your last name");
            return false;
        }else
        {
            alldone=true;
            ET_2.setError(null);
        }
        if(TextUtils.isEmpty(dt))
        {
            txt.setError("Enter your Age");
            return false;
        }else
        {
            alldone=true;
            txt.setError(null);
        }
        return alldone;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return super.onOptionsItemSelected( item );
        }
        return false;
    }



}