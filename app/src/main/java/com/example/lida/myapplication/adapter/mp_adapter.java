package com.example.lida.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lida.myapplication.R;
import com.example.lida.myapplication.models.mla;
import com.example.lida.myapplication.models.mp;

import java.util.List;

/**
 * Created by SUDHEESH on 11/11/2017.
 */

    public class mp_adapter extends RecyclerView.Adapter<com.example.lida.myapplication.adapter.mp_adapter.MyViewHolder> {

        private List<mp> mplist;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView name, id, constituency;

            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.mp_name);
                constituency = (TextView) view.findViewById(R.id.mp_constituency);
            }
        }


        public mp_adapter(List<mp> mplist) {
            this.mplist = mplist;
        }

        @Override
        public com.example.lida.myapplication.adapter.mp_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.mp_inflator, parent, false);

            return new com.example.lida.myapplication.adapter.mp_adapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(com.example.lida.myapplication.adapter.mp_adapter.MyViewHolder holder, int position) {
            mp ml = mplist.get(position);
            holder.name.setText(ml.getName());
            holder.constituency.setText(ml.getConstituency());
        }

        @Override
        public int getItemCount() {
            return mplist.size();
        }
    }


