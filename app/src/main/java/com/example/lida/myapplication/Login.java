package com.example.lida.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

public class Login extends AppCompatActivity {
    EditText etu,etp;
    Button bts,btr;
    String sh;
    String uname;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etu=(EditText)findViewById(R.id.etu);
        etp=(EditText)findViewById(R.id.etp);
        bts=(Button)findViewById(R.id.bts);
        btr=(Button)findViewById(R.id.btr);

        bts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             uname=etu.getText().toString();
             pass=etp.getText().toString();

                SharedPreferences share = getSharedPreferences("abc" , MODE_APPEND);
                SharedPreferences.Editor ed = share.edit();
                ed.putString("name", uname);
                ed.commit();
                Toast.makeText(Login.this, "clicked", Toast.LENGTH_SHORT).show();
                NewClass n=new NewClass();
                n.execute();


            }
        });
        btr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent r=new Intent(Login.this,MainActivity.class);
                startActivity(r);
            }
        });
    }
    public class NewClass extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub

            String urlParameters = null;
            try {
                urlParameters ="Username=" + URLEncoder.encode(uname, "UTF-8") + "&&"
                        + "Password=" + URLEncoder.encode(pass, "UTF-8");
            }
            catch (Exception e) {
                // TODO: handle exception
                System.out.println("Error:"+e);
            }
            sh = Connectivity.excutePost(Constants.LOGIN_URL,
                    urlParameters);
Log.e("ggg",sh);
            return sh;

        }
        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub

            if(sh.contains("success"))
            {
                Toast.makeText(getApplicationContext(), sh, Toast.LENGTH_LONG).show();
                Intent i = new Intent(Login.this,Comp.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), sh, Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(result);

        }

    }



}
