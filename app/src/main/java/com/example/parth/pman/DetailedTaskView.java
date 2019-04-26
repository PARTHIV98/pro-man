package com.example.parth.pman;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailedTaskView extends AppCompatActivity {
    TextView task_name, description, status, duedate, priority;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ImageView img;
    String Tname, desc, stts, ddate, prrt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detailed_task_view );

        //GET VIEW USING ID's
        task_name = findViewById( R.id.Dtaskname );
        description = findViewById( R.id.Ddescription );
        status = findViewById( R.id.Dstatus );
        duedate = findViewById( R.id.Dduedate );
        priority = findViewById( R.id.Dpriority );
        img = findViewById( R.id.pinimg );


        Intent intent = getIntent();
        String id = intent.getExtras().getString( "id" );
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child( "Task" ).child( uId );
        String kid = mDatabase.push().getKey();


        mDatabase.child( id ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // GET THE VALUES FROM FIREBASE
                Tname = (String) dataSnapshot.child( "taskname" ).getValue();
                desc = (String) dataSnapshot.child( "descrptn" ).getValue();
                stts = (String) dataSnapshot.child( "stats" ).getValue();
                ddate = (String) dataSnapshot.child( "dte" ).getValue();
                prrt = (String) dataSnapshot.child( "prrity" ).getValue();


                //SET VALUES TO ACTIVITY
                task_name.setText( Tname );
                description.setText( desc );
                status.setText( stts );
                duedate.setText( ddate );
                priority.setText( prrt );
try {
    if (stts.equals( "Completed" )) {
        Toast.makeText( DetailedTaskView.this, "Not started", Toast.LENGTH_SHORT ).show();
        int myColor = getResources().getColor( R.color.colorsuccess );
        status.setTextColor( myColor );

    } else if (stts.equals( "Not started" )) {
        Toast.makeText( DetailedTaskView.this, "Not started", Toast.LENGTH_SHORT ).show();
        int myColor = getResources().getColor( R.color.colorsuccess );
        status.setTextColor( myColor );
    }

}
catch (Exception e){
//    Toast.makeText( DetailedTaskView.this, "Error"+""+e.getMessage(), Toast.LENGTH_LONG ).show();
}


            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


        img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Intent.ACTION_GET_CONTENT );
                intent.setType( "*/*" );
                startActivityForResult( intent, 7 );
            }
        } );
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        switch (requestCode) {
            case 7:
                if (resultCode == RESULT_OK) {
                    String PathHolder = data.getData().getPath();
                    Toast.makeText( DetailedTaskView.this, PathHolder, Toast.LENGTH_LONG ).show();
                }
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.task_menu, menu );
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Toast.makeText( getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG ).show();
                return true;
            case R.id.item2:
                DeleteTask();

                return true;
            default:
                return super.onOptionsItemSelected( item );
        }
    }

    private void DeleteTask() {

        Bundle bundle = getIntent().getExtras();
        String id = null;
        if (bundle != null) {
            id = bundle.getString( "id" );
        }
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId= mUser.getUid();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Task").child( uId ).child( id );
        mDatabase.removeValue();
        Toast.makeText( this, "DELETED", Toast.LENGTH_SHORT ).show();
        finish();
        // consider using Java coding conventions (upper first char class names!!!)


    }









}