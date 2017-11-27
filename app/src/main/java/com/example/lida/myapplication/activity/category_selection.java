package com.example.lida.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lida.myapplication.R;

public class category_selection extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);
        getSupportActionBar().setTitle("Category");

    }

    public void ministers(View v) {
        Intent i = new Intent(category_selection.this, minis_list.class);
        startActivity(i);

    }

    public void mps(View v) {
        Intent i = new Intent(category_selection.this, mps.class);
        startActivity(i);

    }

    public void mlas(View v) {
        Intent i = new Intent(category_selection.this, mla_list.class);
        startActivity(i);
    }
    public void cmo(View v){
        Intent i=new Intent(category_selection.this,mla_list.class);
        startActivity(i);
    }
}
