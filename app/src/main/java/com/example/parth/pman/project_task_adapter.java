package com.example.parth.pman;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class project_task_adapter extends Adapter<project_task_adapter.ViewHolder> {
    Context context;
    ArrayList<model_task> modelList;

    public project_task_adapter(Context context, ArrayList<model_task> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.project_detailed_task_list, parent, false );
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        model_task mdl = modelList.get( position );
        holder.textView.setText( mdl.getName() );

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       TextView textView;
        CardView cardView;

        public ViewHolder(View v) {

            super( v );
            textView = itemView.findViewById( R.id.txt2 );
            cardView = itemView.findViewById( R.id.cardview1 );
        }
    }
}
