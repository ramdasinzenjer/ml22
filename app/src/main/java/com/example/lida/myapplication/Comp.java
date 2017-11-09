package com.example.lida.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Comp extends AppCompatActivity {
    Button btl,bts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.comp);
        btl=(Button)findViewById(R.id.btl);
        bts=(Button)findViewById(R.id.bts);

        btl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s=new Intent(Comp.this,category_selection.class);
                startActivity(s);
            }
        });
    }
}
