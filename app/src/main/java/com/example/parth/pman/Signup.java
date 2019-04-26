package com.example.parth.pman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText etU, etP,etC;
    private TextView loginhr;
    private Button supbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );
        loginhr = findViewById( R.id.loginhrbtn );
        loginhr.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent( Signup.this, Login.class );
                startActivity( signup );
            }
        } );
        loginhr.setPaintFlags( loginhr.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG );
       firebaseAuth = firebaseAuth.getInstance();

        supbtn = findViewById( R.id.signupbtn );
        etU = findViewById( R.id.ED_1 );
        etP = findViewById( R.id.ED_2 );
        etC = findViewById( R.id.ED_3 );

        supbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String user_email = etU.getText().toString().trim();
                    String user_password = etP.getText().toString().trim();
                   String user_cpassword = etC.getText().toString().trim();


                    firebaseAuth.createUserWithEmailAndPassword( user_email,user_password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {


                                Toast.makeText( Signup.this, "Registration Successful", Toast.LENGTH_SHORT ).show();
                                Log.i( "registration","success" );
                                Intent intent = new Intent( Signup.this,Login.class);
                                Bundle args = new Bundle();
                                args.putString("email",etU.getText().toString());
                                Fragment f = new ProfileFragment();
                                f.setArguments(args);
                                startActivity( intent );
                                finish();
                            }
                            else
                            {
                                String message = task.getException().getMessage();
                                Toast.makeText( Signup.this, "Registration failed" + message, Toast.LENGTH_SHORT ).show();
                                Log.i( "registration","failed" );
                            }
                        }
                    } );

                }
            }
        } );
    }

    private boolean validate() {

        String email = etU.getText().toString();
        String password = etP.getText().toString();
        String cpass = etC.getText().toString();

        if (TextUtils.isEmpty( email )) {
            Toast.makeText( this, "Please enter email", Toast.LENGTH_SHORT ).show();
        }

        else if (TextUtils.isEmpty( password )) {
            Toast.makeText( this, "Please enter password", Toast.LENGTH_SHORT ).show();
        }
        else if (TextUtils.isEmpty( cpass )) {
            Toast.makeText( this, "Please enter confirm password", Toast.LENGTH_SHORT ).show();
        }
        else if(!password.equals( cpass )){
            Toast.makeText( this, "Password are not match", Toast.LENGTH_SHORT ).show();
        }
        else {
          return true;
        }

        return false;
        }
    }


