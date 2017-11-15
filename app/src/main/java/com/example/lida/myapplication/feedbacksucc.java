package com.example.lida.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class feedbacksucc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacksucc);
        getSupportActionBar().hide();
        RelativeLayout ll = (RelativeLayout) findViewById(R.id.lltt);
        ll.setAlpha((float) 0.4);
    }
}
