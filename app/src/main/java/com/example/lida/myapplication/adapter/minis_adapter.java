package com.example.lida.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lida.myapplication.R;
import com.example.lida.myapplication.models.mins;
import com.example.lida.myapplication.models.mla;

import java.util.List;

/**
 * Created by Ram on 11/9/2017.
 */

public class minis_adapter extends RecyclerView.Adapter<minis_adapter.MyViewHolder> {

    private List<mins> minis_list;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, department, constituency;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.mins_name);
            department = (TextView) view.findViewById(R.id.mins_dep);
            // constituency = (TextView) view.findViewById(R.id.constituency);
        }
    }


    public minis_adapter(List<mins> minis_list) {
        this.minis_list = minis_list;
    }

    @Override
    public minis_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mins_inflator, parent, false);

        return new minis_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(minis_adapter.MyViewHolder holder, int position) {
        mins ml = minis_list.get(position);
        holder.name.setText(ml.getName());
        try {
            holder.department.setText(ml.getDepartment());
        }
       catch (Exception e){

       }
        //holder.ml_constituency.setText(ml.getConstituency());
    }

    @Override
    public int getItemCount() {
        return minis_list.size();
    }
}