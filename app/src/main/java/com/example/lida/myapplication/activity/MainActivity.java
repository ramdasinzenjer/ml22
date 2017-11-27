package com.example.lida.myapplication.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lida.myapplication.xtras.Connectivity;
import com.example.lida.myapplication.xtras.Constants;
import com.example.lida.myapplication.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {


    EditText et1, et2, et3, et4, et5, et6;
    Button bt1;
    static String response;
    static String uname;
    static String phno;
    static String email;
    static String aadar;
    static String paasword;
    static String confirmpas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et5 = (EditText) findViewById(R.id.et5);
        et6 = (EditText) findViewById(R.id.et6);
        bt1 = (Button) findViewById(R.id.bt1);


        bt1.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       uname = et1.getText().toString();
                                       phno = et2.getText().toString();
                                       email = et3.getText().toString();
                                       aadar = et4.getText().toString();
                                       paasword = et5.getText().toString();
                                       confirmpas = et6.getText().toString();
                                       String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                                       int Counter = 0;

/*
                if (!et3.getText().toString().matches(emailPattern)) {

                    Toast.makeText(getApplicationContext(), "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                    Counter = 1;
                }
                if (!(et5.getText().toString().equals(et6.getText().toString()))) {

                    Toast.makeText(MainActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
                }
                if (Counter == 0) {
                    RegisterApiTask b = new RegisterApiTask();
                    b.execute();

                }*/
                                       RegisterApiTask b = new RegisterApiTask();
                                       b.execute();
                                   }
                               }
        );
    }





    public class RegisterApiTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {


            String urlParameters = null;
            try {

                urlParameters = "Username=" + URLEncoder.encode(uname, "UTF-8") + "&&"
                        + "Phoneno=" + URLEncoder.encode(phno, "UTF-8") + "&&"
                        + "Email=" + URLEncoder.encode(email, "UTF-8") + "&&"
                        + "Aadarno=" + URLEncoder.encode(aadar, "UTF-8") + "&&"
                        + "Password=" + URLEncoder.encode(paasword, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                Log.e("eroor from ur encoding", e.toString());
            }

            response = Connectivity.excutePost(Constants.REGISTRATION_URL,
                    urlParameters);
            Log.e("You are at", "" + response);

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (response.contains("success")) {

                Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                /*Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();*/
                Intent i=new Intent(getApplicationContext(),Comp.class);
                startActivity(i);

            } else {
                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
            }

        }

    }
    // Toast.makeText(MainActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
}






