package com.example.parth.pman;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProject extends AppCompatActivity {
    Button bt;
    EditText txt1,txt2;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase,mDatabase2;
    String sts=null,prrt=null,dte=null,dscrp=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_create_project );
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled( true );
            getSupportActionBar().setDisplayShowHomeEnabled( true );
        }
        setContentView( R.layout.activity_create_project );

        txt1 = findViewById( R.id.P_ET1 );
        txt2= findViewById( R.id.P_ET2 );
        bt = (Button) findViewById( R.id.crtprjbtn );

        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateForm()) {
//                    String pjn = txt1.getText().toString().trim();
//
//                    mAuth = FirebaseAuth.getInstance();
//                    FirebaseUser mUser = mAuth.getCurrentUser();
//                    String uId = mUser.getUid();
//                    mDatabase = FirebaseDatabase.getInstance().getReference().child( "Project" ).child( uId );
//                    String pid = mDatabase.push().getKey();
//                    Project project = new Project( pid, pjn );
//                    mDatabase.child( pid ).setValue( project );
//                    Toast.makeText( getApplicationContext(), "data inserted", Toast.LENGTH_SHORT ).show();

                    String proj_name = txt1.getText().toString().trim();
                    String Task_name = txt2.getText().toString().trim();


                    // create user object and set all the properties

                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser mUser = mAuth.getCurrentUser();
                    String uId= mUser.getUid();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child( "Project" ).child( uId );
                    String id = mDatabase.push().getKey();

                   Project project = new Project(id,proj_name);
                    mDatabase.child( id ).setValue( project );
//                    Toast.makeText(getApplicationContext(), "data inserted", Toast.LENGTH_SHORT ).show();

                    Toast.makeText( CreateProject.this, "Project created", Toast.LENGTH_SHORT ).show();


                    //new code on 24-04-2019 added below


//                mDatabase2 =FirebaseDatabase.getInstance().getReference().child( "Task" ).child( uId ).child( id );
//                Task task = new Task(id,Task_name,sts,prrt,dte,dscrp);
//                mDatabase2.setValue( task );

                    finish();
                }

                }

        } );


    }

    private boolean validateForm() {
        boolean alldone=true;
        String projnam=txt1.getText().toString().trim();
        if(TextUtils.isEmpty(projnam))
        {
            txt1.setError("Enter Project Name");
            return false;
        }else
        {
            alldone=true;
            txt1.setError(null);
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

