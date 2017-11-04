package com.example.lida.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by Lida on 20-Oct-17.
 */

public class Registred extends Compreg
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.registred);
        TextView tvno = (TextView) findViewById(R.id.tvno);
        tvno.setText(var.id);



    }
}
