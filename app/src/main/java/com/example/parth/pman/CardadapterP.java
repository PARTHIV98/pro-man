package com.example.parth.pman;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class CardadapterP extends RecyclerView.Adapter<CardadapterP.ViewHolder> {
    Context context;
    ArrayList<Project> modelList;
    private DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    private String uId;



    public CardadapterP(Context context, ArrayList<Project> modelList) {
        this.context = context;
        this.modelList= modelList;
    }

    @NonNull
    @Override
    public CardadapterP.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardadapterP.ViewHolder( LayoutInflater.from( context ).inflate(R.layout.project_activity_list,parent,false));

    }


    @Override
    public void onBindViewHolder(@NonNull CardadapterP.ViewHolder holder, final int position) {

        holder.textView.setText( modelList.get( position ).getProjectname());


       holder.cardView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText( context, "cilcked"+getItemId( position ), Toast.LENGTH_SHORT ).show();
                    try{
                        String projectid=modelList.get( position ).getId();
                        Intent intent = new Intent( context,project_task.class );
                    intent.putExtra("projectid",projectid );
                    context.startActivity( intent );
                }

                catch (Exception e){
                    Toast.makeText( context, "Error"+ e.getMessage(), Toast.LENGTH_SHORT ).show();
                }}
            } );
holder.btn.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser mUser = mAuth.getCurrentUser();
                String uId= mUser.getUid();
String id = modelList.get( position ).getId();
        mAuth = FirebaseAuth.getInstance();
                mDatabase = FirebaseDatabase.getInstance().getReference("Project").child( uId ).child( id );
                mDatabase.removeValue();
                Toast.makeText( context, "Deleted", Toast.LENGTH_SHORT ).show();

    }
} );


        }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        Button btn;
        String iid;


        public ViewHolder(View itemView) {
            super( itemView );

            textView = itemView.findViewById( R.id.projectTitle );
            btn = itemView.findViewById(R.id.dltprj);
            cardView = itemView.findViewById( R.id.cardviewproject);



        }
    }
}


