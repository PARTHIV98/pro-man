package com.example.parth.pman;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CardadapterM extends RecyclerView.Adapter<CardadapterM.ViewHolder> {
        Context context;
        ArrayList<Meeting> modelList;
    private DatabaseReference mDatabase;
        FirebaseAuth mAuth;
    private String uId;
    String status;


    public CardadapterM(Context context, ArrayList<Meeting> modelList) {
            this.context = context;
            this.modelList= modelList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CardadapterM.ViewHolder( LayoutInflater.from( context ).inflate(R.layout.activity_meeting_activity,parent,false));

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {

       holder.textView.setText( modelList.get( position ).getMname() );
            holder.text2.setText( modelList.get( position ).getMds() );
            holder.text3.setText( modelList.get( position ).getMdte() );

//       holder.cardView.setOnClickListener( new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText( context, "name added", Toast.LENGTH_SHORT ).show();
//
////                    Intent intent = new Intent( context, project_task.class );
////                   intent.putExtra( "lab_name", mdl.getPname() );
////                    context.startActivity( intent );
//                }
//            } );
//holder.btn.setOnClickListener( new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        delete();
//    }
//} );
//
//        }
//
//    private void delete() {
//
//
//
//            uId =FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//        mAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//                mDatabase.child("Meeting").orderByKey().equalTo(uId).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
//
//                            String key = postsnapshot.getKey();
//                            dataSnapshot.getRef().removeValue();
//
//                        }
//                    }
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                } );
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
            TextView textView,text2,text3;
            CardView cardView;
            Button btn;


            public ViewHolder(View itemView) {
                super( itemView );

                textView = itemView.findViewById( R.id.meetingtitle );
                cardView = itemView.findViewById( R.id.cardviewM);
                text2 = itemView.findViewById( R.id.noc );
                text3 =itemView.findViewById( R.id.duedteM );
            }
        }
    }


