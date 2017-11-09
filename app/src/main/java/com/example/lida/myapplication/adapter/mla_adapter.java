package com.example.lida.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lida.myapplication.R;
import com.example.lida.myapplication.models.mla;

import java.util.List;

/**
 * Created by SUDHEESH on 11/8/2017.
 */

public class mla_adapter extends RecyclerView.Adapter<mla_adapter.MyViewHolder> {

private List<mla> mla_list;



public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView name, id, constituency;

    public MyViewHolder(View view) {
        super(view);
        name = (TextView) view.findViewById(R.id.ml_name);
        id = (TextView) view.findViewById(R.id.ml_id);
       // constituency = (TextView) view.findViewById(R.id.constituency);
    }
}


    public mla_adapter(List<mla> mla_list) {
        this.mla_list = mla_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mla_inflator, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mla ml = mla_list.get(position);
        holder.name.setText(ml.getName());
        holder.id.setText(ml.getId());
        //holder.ml_constituency.setText(ml.getConstituency());
    }

    @Override
    public int getItemCount() {
        return mla_list.size();
    }
}
