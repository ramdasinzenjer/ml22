package com.example.lida.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.lida.myapplication.R;

public class feedbacksucc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacksucc);
        getSupportActionBar().hide();
        RelativeLayout ll = (RelativeLayout) findViewById(R.id.lltt);
        ll.setAlpha((float) 0.4);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(feedbacksucc.this,Comp.class);
        startActivity(i);
        finish();

    }
}
