package com.example.lida.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lida.myapplication.R;
import com.example.lida.myapplication.xtras.var;

/**
 * Created by Lida on 20-Oct-17.
 */

public class Registred extends Compreg {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.registred);
        TextView tvno = (TextView) findViewById(R.id.tvno);
        tvno.setText(var.id);
        getSupportActionBar().hide();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Registred.this, Comp.class);
        startActivity(i);
        finish();
    }
}
