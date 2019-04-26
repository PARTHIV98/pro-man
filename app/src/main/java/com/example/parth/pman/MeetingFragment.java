package com.example.parth.pman;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MeetingFragment extends Fragment {
    Button addbtn;
    CardadapterM cardadapterM;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    RecyclerView recyclerView;
    ArrayList<Meeting> modelList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate( R.layout.fragment_meeting,null);
        View v =inflater.inflate( R.layout.fragment_meeting,container,false );
        ((MainActivity) getActivity())
                .setActionBarTitle("Meetings");
        addbtn = v.findViewById( R.id.add_meetbtn );
        addbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MeetingFragment.this.getActivity(),CreateMeeting.class);
                startActivity( i );
                        }
        } );


        mAuth = FirebaseAuth.getInstance();
        Meeting meeting = new Meeting(  );
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();


        recyclerView = v.findViewById( R.id.meetrcv );
        recyclerView.setLayoutManager( new LinearLayoutManager( this.getActivity() ) );

        mDatabase = FirebaseDatabase.getInstance().getReference().child( "Meeting" ).child( uId );

        mDatabase.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelList = new ArrayList<Meeting>();
                for (DataSnapshot dataSnapShot : dataSnapshot.getChildren()) {
                    Meeting meet = dataSnapShot.getValue( Meeting.class );

                    modelList.add( meet);



                }
                cardadapterM = new CardadapterM( MeetingFragment.this.getActivity(), modelList );
                recyclerView.setAdapter( cardadapterM );
                Log.i( "child updated", "success" );
//                Toast.makeText( TaskFragment.this.getActivity(), "child update", Toast.LENGTH_SHORT ).show();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( MeetingFragment.this.getActivity(), "no data", Toast.LENGTH_SHORT ).show();
            }

        } );
        return v;
    }

}
