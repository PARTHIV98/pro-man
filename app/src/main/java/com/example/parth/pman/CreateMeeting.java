package com.example.parth.pman;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateMeeting extends AppCompatActivity {
ImageView cal;
TextView dttxt;
Button btn;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    EditText ET_1,ET_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_create_meeting );

        Calendar dateSelected = Calendar.getInstance();
        DatePickerDialog datePickerDialog;
        final Calendar myCalendar = Calendar.getInstance();
         ET_1 = (EditText) findViewById( R.id.meetname );
      ET_2 = (EditText) findViewById( R.id.meetdesc );

        String date_n = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

      btn = findViewById( R.id.crtmeetbtn );
dttxt = findViewById( R.id.meetdatetxt );
cal = findViewById( R.id.meetcal );
        dttxt.setText(date_n);
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

                dttxt.setText(sdf.format(myCalendar.getTime()));
            }

        };


        cal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreateMeeting.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm())
                {
                    String tsn = ET_1.getText().toString().trim();
                    String des = ET_2.getText().toString().trim();

//
                    String dte = dttxt.getText().toString().trim();

                    // create user object and set all the properties

                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser mUser = mAuth.getCurrentUser();
                    String uId= mUser.getUid();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child( "Meeting" ).child( uId );
                    String id = mDatabase.push().getKey();

                    Meeting meeting = new Meeting(id,tsn,des,dte);
                    mDatabase.child( id ).setValue( meeting );
                    Toast.makeText(getApplicationContext(), "data inserted", Toast.LENGTH_SHORT ).show();

                    Toast.makeText( CreateMeeting.this, "Meeting created", Toast.LENGTH_SHORT ).show();
                    finish();
                }
            }

            public boolean validateForm()
            {
                boolean alldone=true;
                String tsk=ET_1.getText().toString().trim();
                String dsc=ET_2.getText().toString().trim();
                String dt=dttxt.getText().toString().trim();

                if(TextUtils.isEmpty(tsk))
                {
                    ET_1.setError("Enter name");
                    return false;
                }else
                {
                    alldone=true;
                    ET_1.setError(null);
                }
                if(TextUtils.isEmpty(dsc))
                {
                    ET_2.setError("Enter Description");
                    return false;
                }else
                {
                    alldone=true;
                    ET_2.setError(null);
                }

                if(TextUtils.isEmpty(dt))
                {
                    dttxt.setError("Choose Date");
                    return false;
                }else
                {
                    alldone=true;
                    dttxt.setError(null);
                }

                return alldone;


            }
        } );
    }
}
