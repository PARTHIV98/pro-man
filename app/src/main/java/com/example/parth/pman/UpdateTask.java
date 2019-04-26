package com.example.parth.pman;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateTask extends AppCompatActivity {
    TextView ptu,u_date,ustatustxt ;
    String E_T1,E_T2,projid;
    EditText u_ET1,u_ET2;
    EditText tskname,dscrptn;
    TextView prrt,stts,dte;
    Button save;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update_task );
        u_ET1 = findViewById( R.id.uET1 );
        u_ET2 = findViewById( R.id.uET2 );

        u_date =findViewById( R.id.utxtdate );
        save = findViewById( R.id.mybutton );
        ptu = findViewById( R.id.uprioritytxt );
        ustatustxt= findViewById( R.id.ustatustxt);

        Bundle bundle = getIntent().getExtras();
        String E_T_1 = bundle.getString("text1");
        String E_T_2 = bundle.getString("text2");
        String date = bundle.getString("date");
        u_ET1.setText( E_T_1);
        u_ET2.setText( E_T_2);
        u_date.setText(date);





//        tskname=(EditText)findViewById(R.id.uET1);          /..u_ET1../
//        dscrptn=(EditText)findViewById(R.id.uET2);          /..u_ET2../
//        prrt=(TextView)findViewById(R.id.uprioritytxt);    /..ptu../
//       stts=findViewById( R.id.ustatustxt );                /..ustatustxt../

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//                if(validateForm())
//                {
//                    String tsk=u_ET1.getText().toString().trim();
//                    String dsc=u_ET2.getText().toString().trim();
//                    String prt=ptu.getText().toString().trim();
//                    String dt=u_date.getText().toString().trim();
//                    String sts = ustatustxt.getText().toString().trim();
//                    // create user object and set all the properties
//                   Task task=new Task();
//                    task.setTaskname(tsk);
//                    task.setDescription(dsc);
//                    task.setPriority(prt);
//                    task.setDate(dt);
//                    task.setStatus(sts);
//                    if(mAuth.getCurrentUser()!=null)
//                    {
//                        // save the user at UserNode under user UID
//                        mDatabase.child("UserNode").child(mAuth.getCurrentUser().getUid()).setValue(task, new DatabaseReference.CompletionListener() {
//                            @Override
//                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                                if(databaseError==null)
//                                {
//                                    Toast.makeText(UpdateTask.this, "Data is saved successfully",
//                                            Toast.LENGTH_SHORT).show();
//                                    finish();
//                                }
//                            }
//                        });
//                    }
//                }
//            }
//        });

        ptu.setBackgroundResource(R.drawable.priority_bg);

        final GradientDrawable gd = (GradientDrawable) ptu.getBackground().getCurrent();

        gd.setStroke(2, Color.parseColor("#6200EE"), 10, 2);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu( UpdateTask.this, view );
                popupMenu.getMenuInflater().inflate( R.menu.priority_menu, popupMenu.getMenu() );
                popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        String txt = menuItem.getTitle().toString();
                        ptu.setText( txt );
                        switch (menuItem.getItemId()) {

                            case R.id.Critical:
                                gd.setStroke( 1,Color.parseColor( "#bc1900" ),10, 10 );
                                ptu.setTextColor( getResources().getColor( R.color.criticalprio ));


                                break;


                            case R.id.High:
                                ptu.setTextColor( getResources().getColor( R.color.colorored ));
                                gd.setStroke( 1,Color.parseColor( "#f0634d" ),10, 10 );
                                break;

                            case R.id.Low:
                                ptu.setTextColor( getResources().getColor( R.color.colorlightgreen));
                                gd.setStroke( 1,Color.parseColor( "#99e365" ),10, 10 );
                                break;
                            case R.id.Medium:
                                ptu.setTextColor( getResources().getColor( R.color.mediumprio));
                                gd.setStroke( 1,Color.parseColor( "#f6ebe42e" ),10, 10 );
                                break;

                            default:

                                ptu.setTextColor( getResources().getColor( R.color.Defalutitem ) );
                                gd.setStroke( 1,Color.parseColor( "#FFAAEE11" ),10, 10 );
                                break;
                        }
                        return true;
                    }

                } );
                popupMenu.show();
            }
        };


        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu( UpdateTask.this, view );
                popupMenu.getMenuInflater().inflate( R.menu.status_item, popupMenu.getMenu() );
                popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        String txt = menuItem.getTitle().toString();
                       ustatustxt.setText( txt );
                        switch (menuItem.getItemId()) {

                            case R.id.Not_started:
                               ustatustxt.setTextColor( getResources().getColor( R.color.colorored ));
                                break;
                            case R.id.In_progress:
                                ustatustxt.setTextColor( getResources().getColor( R.color.colorlightgreen ));
                                break;

                            case R.id.Completed:
                                ustatustxt.setTextColor( getResources().getColor( R.color.colorsuccess));
                                break;
                            case R.id.Cancelled:
                                ustatustxt.setTextColor( getResources().getColor( R.color.criticalprio));
                                break;

                            default:
                                ustatustxt.setTextColor( getResources().getColor( R.color.Defalutitem ) );
                                break;
                        }
                        return true;
                    }

                } );
                popupMenu.show();
            }
        };
        ptu.setOnClickListener( listener );
        ustatustxt.setOnClickListener( listener1 );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.donebutton, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.mybutton) {
            // do something here


                Intent intents = getIntent();
                String projid = intents.getExtras().getString( "projid" );
               newdata();
//            Toast.makeText( this, "Task Details are updated", Toast.LENGTH_SHORT ).show();


            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    Task task=new Task();
//    private void getValues() {
//       task.setTaskname(u_ET1.getText().toString().trim());
//        task.setDescrptn(u_ET2.getText().toString().trim());
//        task.setPriority(ptu.getText().toString().trim());
//        task.setDte(u_date.getText().toString().trim());
//        task.setStats(ustatustxt.getText().toString().trim());
//    }

    private void newdata() {
        if(validateForm())
        {
          String tsn = u_ET1.getText().toString().trim();
            String des = u_ET2.getText().toString().trim();
            String sts =ustatustxt.getText().toString().trim();
//
            String dte = u_date.getText().toString().trim();
            String prrit = ptu.getText().toString().trim();
            // create user object and set all the properties

            mAuth = FirebaseAuth.getInstance();
            FirebaseUser mUser = mAuth.getCurrentUser();
            String uId= mUser.getUid();
            mDatabase =FirebaseDatabase.getInstance().getReference().child( "Task" ).child( uId );
           String id = mDatabase.push().getKey();

           Task task = new Task(id,tsn,sts,dte,des,prrit);
           mDatabase.child( id ).setValue( task );
            Toast.makeText(getApplicationContext(), "data inserted", Toast.LENGTH_SHORT ).show();
            finish();


        }
    }

    public boolean validateForm()
    {
        boolean alldone=true;
        String tsk=u_ET1.getText().toString().trim();
        String dsc=u_ET2.getText().toString().trim();
        String dt=u_date.getText().toString().trim();
        String prt=ptu.getText().toString().trim();
        String sts = ustatustxt.getText().toString().trim();
        if(TextUtils.isEmpty(tsk))
        {
            u_ET1.setError("Enter your first name");
            return false;
        }else
        {
            alldone=true;
            u_ET1.setError(null);
        }
        if(TextUtils.isEmpty(dsc))
        {
            u_ET2.setError("Enter your last name");
            return false;
        }else
        {
            alldone=true;
            u_ET2.setError(null);
        }
        if(TextUtils.isEmpty(prt))
        {
            ptu.setError("Enter your Age");
            return false;
        }else
        {
            alldone=true;
            ptu.setError(null);
        }
        if(TextUtils.isEmpty(dt))
        {
            u_date.setError("Enter your Email");
            return false;
        }else
        {
            alldone=true;
            u_date.setError(null);
        }
        if(TextUtils.isEmpty(sts))
        {
            ustatustxt.setError("Enter your Email");
            return false;
        }else
        {
            alldone=true;
            ustatustxt.setError(null);
        }
        return alldone;
    }
}





