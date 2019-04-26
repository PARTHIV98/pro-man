package com.example.parth.pman;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.*;

public class Cardadapter extends RecyclerView.Adapter<Cardadapter.ViewHolder> {
    public Context context;
    String tsk,desc,sts,prt,dt;
    ArrayList<Task> modelList;


    public Cardadapter(Context context, ArrayList<Task> modelList) {
        this.context = context;

        this.modelList= modelList;
    }

    public Cardadapter(project_task context, ArrayList<model_task> modelList) {

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( context ).inflate(R.layout.task_activity,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        tsk = modelList.get(position).getTaskname();
        desc = modelList.get(position).getDescrptn();
        sts = modelList.get(position).getStats();
        dt =modelList.get(position).getDte();
        prt =modelList.get(position).getPrrity();


        holder.title.setText( modelList.get( position ).getTaskname() );
        holder.status.setText( modelList.get( position ).getStats());
        holder.duedttext.setText( modelList.get( position ).getDte());


        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText( context, "name ", Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent( context,DetailedTaskView.class );
                intent.putExtra( "text1", tsk );
                intent.putExtra( "text2", desc );
                intent.putExtra( "text3", sts);
                intent.putExtra( "text4", dt );
                intent.putExtra( "text5", prt );
                intent.putExtra( "id",modelList.get( position ).getId() );
                context.startActivity( intent );
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
        TextView title,status,duedttext;
        CardView cardView;

        View mv;
        public ViewHolder(View itemView) {
            super( itemView );
            mv = itemView;
            title = itemView.findViewById( R.id.tasktitle );
            status = itemView.findViewById( R.id.taskstatus );
            duedttext = itemView.findViewById( R.id.duedte);
            cardView = itemView.findViewById( R.id.cardview );


        }

        public  void onClick(final int position){
            cardView.setOnClickListener( new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText( context, position+"clicked", Toast.LENGTH_SHORT ).show();
                }
            } );
        }



    }

}
