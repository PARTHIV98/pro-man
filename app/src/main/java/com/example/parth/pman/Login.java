package com.example.parth.pman;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

TextView signin,forget_pass;
//    public int count=0;
//    int tempInt = 0;

private FirebaseAuth firebaseAuth;

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
//    private ProgressBar progressBar;
    private Button btnSignup;
//    btnLogin, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        if (auth.getCurrentUser() != null) {
            Log.i("a user is logged in: ", String.valueOf( user ) );
//            count = readSharedPreferenceInt("cntSP","cntKey");
//            if(count==0){
//                Intent i = new Intent(Login.this, MainActivity.class);
//                startActivity(i);
//                count++;
//                writeSharedPreference(count,"cntSP","cntKey");
//            }
//
//            else {
//
//                Intent i = new Intent(Login.this, UserDetail.class);
//                startActivity(i);
//                // close this activity
//
//            }
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
        else{
            Log.i("Username", "there is no user");
        }
        // set the view now
        setContentView(R.layout.activity_login);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
forget_pass = findViewById( R.id.forgetpass );
        inputEmail = (EditText) findViewById(R.id.ED_email);
        inputPassword = (EditText) findViewById(R.id.ED_pass);
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_create_account);
       signin = (TextView) findViewById(R.id.signinbtn);
//        btnReset = (Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
       forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ResetPassword.class));
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });

//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
//            }
//        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

//                progressBar.setVisibility(View.VISIBLE);
                final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
//                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if(!email.matches(emailPattern)){
                                        inputEmail.setError( "Enter valid Email" );
                                        inputEmail.requestFocus();
                                    }
                                    else if (password.length() < 6) {
                                        inputPassword.setError("Enter password between 6 to 12 characters!");
                                        inputPassword.requestFocus();
                                    }
                                    else {
                                        Toast.makeText(Login.this, "Failed", Toast.LENGTH_LONG).show();
                                    }
                                } else {

                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });


            }
        });
    }

//    private void onAuthSuccess(FirebaseUser user) {
//        String username = usernameFromEmail(user.getEmail());
//
//        // Write new user
//        writeNewUser(user.getUid(), username, user.getEmail());
//    }
//    private String usernameFromEmail(String email) {
//        if (email.contains("@")) {
//            return email.split("@")[0];
//        } else {
//            return email;
//        }
//    }
////
////    private void writeNewUser(String userId, String name, String email) {
////        User user = new User(name, email);
////
////        mDatabase.child("users").child(userId).setValue(user);
////        ArrayList<String> userNames = new ArrayList<>();
////        userNames.add(name);
////        mDatabase.child("usernamelist").setValue(userNames);
////    }
////

//    public int readSharedPreferenceInt(String spName,String key){
//        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
//        return tempInt = sharedPreferences.getInt(key, 0);
//    }
//
//    //write shared preferences in integer
//    public void writeSharedPreference(int ammount,String spName,String key ){
//
//        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putInt(key, ammount);
//        editor.commit();
//    }
}

