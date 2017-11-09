package com.example.lida.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class category_selection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);

    }
   public   void  ministers(View v)
   {


   }
   public void  mps(View v)
   {

   }
   public void mlas(View v)
   {
       Intent i = new Intent(category_selection.this , mla_list.class);
       startActivity(i);
   }
}
