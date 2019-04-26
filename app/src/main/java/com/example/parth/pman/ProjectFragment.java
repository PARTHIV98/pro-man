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
import android.widget.TextView;
import android.widget.Toast;

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

public class ProjectFragment extends Fragment {
   DatabaseReference mDatabase;
    Button dlt;
    private RecyclerView recyclerView;
  CardadapterP cardadapterp;
    ArrayList<Project> modelList;
    private FirebaseAuth mAuth;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate( R.layout.fragment_project,null);
        View v = inflater.inflate( R.layout.fragment_project, container, false );
        materialDesignFAM = v.findViewById( R.id.floating_action_menu );
        floatingActionButton1 = v.findViewById( R.id.task_create );
        floatingActionButton2 = v.findViewById( R.id.create_project );
        floatingActionButton1.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( ProjectFragment.this.getActivity(), CreateTask.class );
                startActivity( intent );
            }
        } );

        floatingActionButton2.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( ProjectFragment.this.getActivity(), CreateProject.class );
                startActivity( intent );
            }
        } );
        ((MainActivity) getActivity())
                .setActionBarTitle( "Projects" );


        mAuth = FirebaseAuth.getInstance();
        Project project = new Project(  );
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();



        recyclerView = v.findViewById( R.id.projectrcv );
        recyclerView.setLayoutManager( new LinearLayoutManager( this.getActivity() ) );

        mDatabase = FirebaseDatabase.getInstance().getReference().child( "Project" ).child( uId );

        mDatabase.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelList = new ArrayList<Project>();
                for (DataSnapshot dataSnapShot : dataSnapshot.getChildren()) {
                    Project prj = dataSnapShot.getValue( Project.class );

                    modelList.add( prj);



                }
                cardadapterp = new CardadapterP(ProjectFragment.this.getActivity(), modelList );
                recyclerView.setAdapter( cardadapterp );
                Log.i( "child updated", "success" );
//                Toast.makeText( TaskFragment.this.getActivity(), "child update", Toast.LENGTH_SHORT ).show();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( ProjectFragment.this.getActivity(), "no data", Toast.LENGTH_SHORT ).show();
            }

        } );


        return v;

    }



}
