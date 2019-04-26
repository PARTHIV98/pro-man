package com.example.parth.pman;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UserDetail extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    EditText uname, uemail, uRole, umobno;
    Button savebtn;
    ProgressBar progressBar;
    Spinner spinner;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    final int PICK_IMAGE_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_detail );

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        uname = (EditText) findViewById( R.id.pname );
//        imageView = (ImageView) findViewById(R.id.imageView);
//        progressBar = (ProgressBar) findViewById(R.id.progdp);
        savebtn = findViewById( R.id.savebtn );
        uemail = findViewById( R.id.pEid );
        uRole = findViewById( R.id.pRole );
        umobno = findViewById( R.id.pMobileno );
        spinner = (Spinner) findViewById( R.id.pGender );
//        spinner.setOnItemSelectedListener( new ItemSelectedListener() );
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showImageChooser();
//            }
//        });


        savebtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    String une = uname.getText().toString().trim();
                    String ema = uemail.getText().toString().trim();
                    String gendr = spinner.getSelectedItem().toString();
                    String mono = umobno.getText().toString().trim();
                    String rle = uRole.getText().toString().trim();
                    //create user object and set all the properties
//                    User user=new User();
//                    user.setUsername( une );
//                    user.setEmail(ema);
//                    user.setGender( gendr );
//                    user.setMobile(mono);
//                    user.setRole(rle);

                    if (mAuth.getCurrentUser() != null) {
                        if (validateForm()) {
                            mAuth = FirebaseAuth.getInstance();
                            FirebaseUser mUser = mAuth.getCurrentUser();
                            String uId = mUser.getUid();
                            mDatabase = FirebaseDatabase.getInstance().getReference().child( "Profile" ).child( uId );
                            String id = mDatabase.push().getKey();
                            // save the user at UserNode under user UID
                            User user = new User( id, une, ema, gendr, mono, rle );
                            mDatabase.child( id ).setValue( user );
                            Toast.makeText( getApplicationContext(), "data inserted", Toast.LENGTH_SHORT ).show();
                        } else {
                            Toast.makeText( UserDetail.this, "Data not inserted", Toast.LENGTH_SHORT ).show();
                        }
                    }
                }
            }
//
        } );

    }

    private boolean validateForm() {


        boolean alldone = true;
        String une = uname.getText().toString().trim();
        String ema = uemail.getText().toString().trim();
        String gendr = spinner.getSelectedItem().toString();
        String mono = umobno.getText().toString().trim();
        String rle = uRole.getText().toString().trim();
        if (TextUtils.isEmpty( une )) {
            uname.setError( "Enter your first name" );
            return false;
        } else {
            alldone = true;
            uname.setError( null );
        }
        if (TextUtils.isEmpty( ema )) {
            uemail.setError( "Enter your last name" );
            return false;
        } else {
            alldone = true;
            uemail.setError( null );
        }
        if (TextUtils.isEmpty( gendr )) {

            return false;
        } else {
            alldone = true;

        }
        if (TextUtils.isEmpty( mono )) {
            umobno.setError( "Enter your Email" );
            return false;
        } else {
            alldone = true;
            umobno.setError( null );
        }
        return alldone;

    }

    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        //get strings of first item
        String firstItem = String.valueOf( spinner.getSelectedItem() );

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals( String.valueOf( spinner.getSelectedItem() ) )) {
                // ToDo when first item is selected
            } else {
                Toast.makeText( parent.getContext(),
                        "You have selected : " + parent.getItemAtPosition( pos ).toString(),
                        Toast.LENGTH_LONG ).show();
                // Todo when item is selected by the user
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }
}

//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (mAuth.getCurrentUser() == null) {
//            finish();
//            startActivity(new Intent(this, MainActivity.class));
//        }
//    }

//    private void loadUserInformation() {
//        final FirebaseUser user = mAuth.getCurrentUser();
//
//        if (user != null) {
//            if (user.getPhotoUrl() != null) {
//                Glide.with(this)
//                        .load(user.getPhotoUrl().toString())
//                        .into(imageView);
//            }
//
//            if (user.getDisplayName() != null) {
//                editText.setText(user.getDisplayName());
//            }
//
//            if (user.isEmailVerified()) {
//                textView.setText("Email Verified");
//            } else {
//                textView.setText("Email Not Verified (Click to Verify)");
//                textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Toast.makeText(UserDetail.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//            }
//        }
//    }


//    private void saveUserInformation() {
//
//
//        String displayName = editText.getText().toString();
//
//        if (displayName.isEmpty()) {
//            editText.setError("Name required");
//            editText.requestFocus();
//            return;
//        }
//
//        FirebaseUser user = mAuth.getCurrentUser();
//
//        if (user != null && profileImageUrl != null) {
//            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
//                    .setDisplayName(displayName)
//                    .build();
//
//            user.updateProfile(profile)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(UserDetail.this, "Profile Updated", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            uriProfileImage = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
//                imageView.setImageBitmap(bitmap);
//
//                uploadImageToFirebaseStorage();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    private void uploadImageToFirebaseStorage() {
//        StorageReference profileImageRef =
//                FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");
//
//        if (uriProfileImage != null) {
//            progressBar.setVisibility(View.VISIBLE);
//            profileImageRef.putFile(uriProfileImage)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            progressBar.setVisibility(View.GONE);
//                            profileImageUrl = taskSnapshot.getTask().getResult( ).toString();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            progressBar.setVisibility(View.GONE);
//                            Toast.makeText(UserDetail.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
//    }


//
//    private void showImageChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), PICK_IMAGE_REQUEST);
//    }




