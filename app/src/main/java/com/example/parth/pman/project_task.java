package com.example.parth.pman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class project_task extends AppCompatActivity {
    private RecyclerView listView;
    private project_task_adapter cardadapter;
    ArrayList<model_task> modelList;
    String[] name;
    Button btnadd;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detailed_task_rcyclevew );
        listView = findViewById( R.id.listview);
        btnadd=findViewById( R.id.addtasktoprjbtn);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager( project_task.this, LinearLayoutManager.VERTICAL, false );

        name = new String[]{"Abc", "Def", "Nothing"};
        listView.setLayoutManager( verticalLayoutManager );

        modelList = new ArrayList<model_task>();
        for (int i = 0; i < name.length; i++) {
            String name1 = name[i];
            model_task model = new model_task( name1 );
            modelList.add( model );


        }
        cardadapter = new project_task_adapter( this,modelList );
        listView.setAdapter( cardadapter );


          btnadd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intents = getIntent();

                Intent intent=new Intent( getApplicationContext(),CreateTask.class );

                startActivity( intent );
            }
        } );

    }
}
