package com.example.parth.pman;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;


public class HomeFragment extends Fragment {
    CardView card;
    DatabaseReference mDatabase;
  FirebaseAuth mAuth;
    TextView txt,txtc,txtin,txtrev,txt1,txt2,txt3,txt4;
    ProgressBar prgbr;
int ntcount = 0;
    int i;

    String count;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate( R.layout.fragment_home,null);
        final View v = inflater.inflate( R.layout.fragment_home, container, false );
        ((MainActivity) getActivity())
                .setActionBarTitle( "Dashboard" );
        card = v.findViewById( R.id.card1 );

        card.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TaskFragment llf = new TaskFragment();
                ft.replace( R.id.frame, llf );
                ft.commit();
            }
        } );

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();
        txt = v.findViewById( R.id.nts );
        txtc = v.findViewById( R.id.completedcnt );
        txtin = v.findViewById( R.id.inprogcnt );
        txtrev = v.findViewById( R.id.reviewdcnt );
        txt1 =v.findViewById( R.id.txt1 );
        txt2 =v.findViewById( R.id.txt2 );
        txt3 =v.findViewById( R.id.txt3 );
        txt4 =v.findViewById( R.id.txt4 );
        prgbr= v.findViewById( R.id.progressBar1 );

        mDatabase = FirebaseDatabase.getInstance().getReference().child( "Task" ).child( uId );
        mDatabase.orderByChild( "stats" ).equalTo( "Not started" ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                {

             Task task = dataSnapshot.getValue(Task.class);


                    Log.i( "hello", dataSnapshot.getChildrenCount() + "Count" );

                i = (int)dataSnapshot.getChildrenCount();
                    txt.setText(Integer.toString(i));



                    }

//                Toast.makeText( TaskFragment.this.getActivity(), "child update", Toast.LENGTH_SHORT ).show();
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        mDatabase.orderByChild( "stats" ).equalTo( "Completed" ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                {

                    Task task = dataSnapshot.getValue(Task.class);


                    Log.i( "hello", dataSnapshot.getChildrenCount() + "Count" );

                    i = (int)dataSnapshot.getChildrenCount();
                    txtc.setText(Integer.toString(i));


                }

//                Toast.makeText( TaskFragment.this.getActivity(), "child update", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });



        mDatabase.orderByChild( "stats" ).equalTo( "Reviewed" ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                {

                    Task task = dataSnapshot.getValue(Task.class);


                    Log.i( "hello", dataSnapshot.getChildrenCount() + "Count" );

                    i = (int)dataSnapshot.getChildrenCount();
                    txtrev.setText(Integer.toString(i));


                }

//                Toast.makeText( TaskFragment.this.getActivity(), "child update", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });



        mDatabase.orderByChild( "stats" ).equalTo( "In progress" ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                {

                    Task task = dataSnapshot.getValue(Task.class);


                    Log.i( "hello", dataSnapshot.getChildrenCount() + "Count" );

                    i = (int)dataSnapshot.getChildrenCount();
                    txtin.setText(Integer.toString(i));


                }

//                Toast.makeText( TaskFragment.this.getActivity(), "child update", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });



        //For priorities

        mDatabase.orderByChild( "prrity" ).equalTo( "Medium" ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                {

                    Task task = dataSnapshot.getValue(Task.class);


                    Log.i( "hello", dataSnapshot.getChildrenCount() + "Count" );

                    i = (int)dataSnapshot.getChildrenCount();
                    txt1.setText(Integer.toString(i)+" "+"Task");
                    prgbr.setProgress( Integer.parseInt( Integer.toString(i) ));


                }

//                Toast.makeText( TaskFragment.this.getActivity(), "child update", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


        return v;
    }
}
