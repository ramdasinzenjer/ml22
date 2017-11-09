package com.example.lida.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {
    String status = "stata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        SharedPreferences sh = getSharedPreferences("mlaid",MODE_PRIVATE);
        final String name = sh.getString("name",null);
        final String id = sh.getString("id",null);

        Button sub = (Button) findViewById(R.id.feedback_bt);
        final EditText edt = (EditText) findViewById(R.id.feedback_et);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String des = edt.getText().toString().trim();
                RequestQueue queue = Volley.newRequestQueue(Feedback.this);
                String response = "";
                final String finalResponse = response;
                //TODO url
                String S_URL = "http://192.168.1.9/mla/feedback.php";
                StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray result = jsonObject.getJSONArray("c");
                                    JSONObject employee = result.getJSONObject(1);
                                    status = employee.getString("status");
                                    Toast.makeText(Feedback.this, status, Toast.LENGTH_SHORT).show();
                                    if (status.contains("success")) {
                                        Toast.makeText(Feedback.this, "done", Toast.LENGTH_SHORT).show();


                                    }
                                    if (!(status.contains("success"))) {

                                        Toast.makeText(Feedback.this, "Error try again ", Toast.LENGTH_SHORT).show();

                                    }



                                } catch (JSONException e) {
                                    Toast.makeText(Feedback.this, e.toString(), Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.e("ErrorResponse", finalResponse);
                                Toast.makeText(Feedback.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();


                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();


                        //params.put("FirstName", tusername.getText().toString());
                        //params.put("Password", tPassword.getText().toString());
                        params.put("name", name);
                        params.put("title", id);
                        params.put("id", id);
                        params.put("des", id);

                        return params;
                    }
                };
                postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(postRequest);
            }
        });


    }

}
