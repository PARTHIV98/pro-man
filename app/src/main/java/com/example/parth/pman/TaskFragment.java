package com.example.parth.pman;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.parth.pman.Cardadapter.ViewHolder;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TaskFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    Cardadapter cardadapter;
    ArrayList<Task> modelList;

    ProgressDialog mProgressDialog;
    TextView txt1;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    private String TAG;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate( R.layout.fragment_task, container, false );
        materialDesignFAM = v.findViewById( R.id.floating_action_menu );
        floatingActionButton1 = v.findViewById( R.id.task_create );
        floatingActionButton2 = v.findViewById( R.id.create_project );
        floatingActionButton1.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( TaskFragment.this.getActivity(), CreateTask.class );
                startActivity( intent );
            }
        } );

        floatingActionButton2.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( TaskFragment.this.getActivity(), CreateProject.class );
                startActivity( intent );
            }
        } );
//        getActionBar().setTitle("Tasks");
        ((MainActivity) getActivity())
                .setActionBarTitle( "Tasks" );
        mAuth = FirebaseAuth.getInstance();
        Task task = new Task();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();


        recyclerView = v.findViewById( R.id.recyclerview );
        recyclerView.setLayoutManager( new LinearLayoutManager( this.getActivity() ) );

        mDatabase = FirebaseDatabase.getInstance().getReference().child( "Task" ).child( uId );

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                {
                    modelList = new ArrayList<Task>();
                    for (DataSnapshot dataSnapShot : dataSnapshot.getChildren()) {
                        Task t = dataSnapShot.getValue( Task.class );

                        modelList.add( t );



                    }
                    cardadapter = new Cardadapter( TaskFragment.this.getActivity(), modelList );
                    recyclerView.setAdapter( cardadapter );
                    Log.i( "child updated", "success" );
//                Toast.makeText( TaskFragment.this.getActivity(), "child update", Toast.LENGTH_SHORT ).show();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return v;
    }

    //
    @Override
    public void onClick(View view) {

    }
}









