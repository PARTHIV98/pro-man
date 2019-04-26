package com.example.parth.pman;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.parth.pman.BottomNavigationViewHelper;
import com.example.parth.pman.HomeFragment;
import com.example.parth.pman.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById( R.id.navigation );
        BottomNavigationViewHelper.removeShiftMode( bottomNavigationView );
        BottomNavigationView navigation =  findViewById( R.id.navigation );
        loadFragment( new HomeFragment() );
        navigation.setOnNavigationItemSelectedListener(this);



    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace( R.id.frame , fragment).commit();
            return true;
        }
        return false;
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId()){
            case R.id.home:
                fragment=new HomeFragment();
                break;
            case R.id.task:
                fragment=new TaskFragment();
                break;
            case R.id.project:
                fragment=new ProjectFragment();
                break;
            case R.id.meeting:
                fragment=new MeetingFragment();
                break;
            case R.id.profile:
                fragment=new ProfileFragment();
                break;

        }
        return loadFragment( fragment );
    }



}
