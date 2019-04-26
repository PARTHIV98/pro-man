package com.example.parth.pman;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.support.constraint.Constraints.TAG;

public class ProfileFragment extends Fragment {
    private Button btnsignout;

    private StorageReference mStorageRef;
    String Item;
    Button dlt;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    ImageView imageView;
    FirebaseAuth mAuth;
    DatabaseReference mdatabase;
    FirebaseUser currentFirebaseUser;
    TextView txtname, txtmail, txtverify, txtRole, txtGender, txtMob, edit;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate( R.layout.fragment_profile,null);



    View v = inflater.inflate( R.layout.fragment_profile, container, false );
    ((MainActivity) getActivity())
            .setActionBarTitle( "Account" );
    mStorageRef = FirebaseStorage.getInstance().getReference();
    mAuth = FirebaseAuth.getInstance();



//        loadData();
//   loadUserInformation();
    btnsignout = v.findViewById( R.id.sign_out );
    txtname = v.findViewById( R.id.profilename );
    txtmail = v.findViewById( R.id.profileEid );
    txtRole = v.findViewById( R.id.roletxt );
    txtGender = v.findViewById( R.id.gendertxt );
    txtMob = v.findViewById( R.id.mobtxt );
    txtverify = v.findViewById( R.id.vrfytxt );
    imageView = v.findViewById( R.id.imageprofile );
    edit = v.findViewById( R.id.editprof );


//        Item = getActivity().getIntent().getExtras().getString("email");
//        txtmail.setText( Item );

    edit.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            Intent i = new Intent( ProfileFragment.this.getActivity(), UserDetail.class );
            startActivity( i );

        }
    } );
    btnsignout.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            logOut();
        }

    } );

    mdatabase = FirebaseDatabase.getInstance().getReference();
 currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    if (currentFirebaseUser != null)

    {
        Log.d( TAG, "onComplete: currentUserUid---->" + currentFirebaseUser.getUid() );
        Log.d( TAG, "onComplete: currentUserEmail---->" + currentFirebaseUser.getEmail() );
        Log.d( TAG, "onComplete: currentUserDisplayName---->" + currentFirebaseUser.getDisplayName() );

        String prfemail = currentFirebaseUser.getEmail();
        txtmail.setText( prfemail );
        String email = mAuth.getCurrentUser().getEmail();
        if (email.contains( "@" )) {
            String n = email.split( "@" )[0];
            txtname.setText( n );
        }


    } else

    {
        Log.d( TAG, "onComplete: currentUserUid is null" );
    }


    return v;
    }

    private void logOut() {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent( ProfileFragment.this.getActivity(), Login.class );
        startActivity( i );
        getActivity().finish();
    }

    final ValueEventListener userListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot :
                        dataSnapshot.getChildren()) {
                    String uId = currentFirebaseUser.getUid();
                    String id = mdatabase.child( "Profile" ).child( uId ).getKey();
                    String username = (String) snapshot.child( "Profile" ).child( uId ).child( id ).getValue();

                    Log.i( TAG, "onDataChange: " + username );

                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
//        public String usernameOfCurrentUser() {
//            mAuth = FirebaseAuth.getInstance();
//            String email = mAuth.getCurrentUser().getEmail();
//            if (email.contains( "@" )) {
//                String n = email.split( "@" )[0];
//                return n;
//            } else {
//                return email;
//            }
//        }


//    private void loadUserInformation() {
//        firebaseUser = mAuth.getCurrentUser();
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
//
//
//        if (firebaseUser != null) {
//            if (firebaseUser.getPhotoUrl() != null) {
//                Glide.with( this.getActivity() )
//                        .load(storageReference)
//                        .into(imageView);
//            }
//
//            if (firebaseUser.getDisplayName() != null) {
//                txtname.setText(firebaseUser.getDisplayName());
//            }
//
//            if (firebaseUser.isEmailVerified()) {
//                txtverify.setText("Email Verified");
//            } else {
//                txtverify.setText("Email Not Verified (Click to Verify)");
//                txtverify.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Toast.makeText(ProfileFragment.this.getActivity(), "Verification Email Sent", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//            }
//        }
//    }





//    @Override
//    public void onDetach() {
//        super.onDetach();
//        ((MainActivity) getActivity())
//                .setActionBarTitle("Pro Man");
//    }



