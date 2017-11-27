package com.example.lida.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lida.myapplication.xtras.Connectivity;
import com.example.lida.myapplication.xtras.Constants;
import com.example.lida.myapplication.R;
import com.example.lida.myapplication.adapter.RecyclerTouchListener;
import com.example.lida.myapplication.adapter.minis_adapter;
import com.example.lida.myapplication.decorators.DividerItemDecoration;
import com.example.lida.myapplication.models.mins;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class minis_list extends AppCompatActivity {
    RecyclerView mins_view;
    private minis_adapter mAdapter;
    private List<mins> minll = new ArrayList<>();
    String sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minis_list);
        SharedPreferences md = getSharedPreferences("abc", MODE_PRIVATE);
        String s = md.getString("type", null);
        if (s.equalsIgnoreCase("complaint")) {
            getSupportActionBar().setTitle("Complaint Registration");
        }
        if (s.equalsIgnoreCase("feedback")) {
            getSupportActionBar().setTitle("Feedback Or suggetion");
        }

        mins_view = (RecyclerView) findViewById(R.id.mins_id);

        mAdapter = new minis_adapter(minll);

        mins_view.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mins_view.setLayoutManager(mLayoutManager);
        mins_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mins_view.setItemAnimator(new DefaultItemAnimator());
        mins_view.setAdapter(mAdapter);
        NewClass n = new NewClass();
        n.execute();
        mins_view.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mins_view, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mins ml = minll.get(position);
                Toast.makeText(getApplicationContext(), ml.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                SharedPreferences sh = getSharedPreferences("minsid", MODE_PRIVATE);
                SharedPreferences.Editor edsh = sh.edit();
                edsh.putString("id", ml.getId());
                edsh.putString("name", ml.getName());
                edsh.commit();

                SharedPreferences md = getSharedPreferences("abc", MODE_PRIVATE);
                String s = md.getString("type", null);
                if (s.equalsIgnoreCase("complaint")) {
                    Intent i = new Intent(minis_list.this, Compreg.class);
                    startActivity(i);
                }
                if (s.equalsIgnoreCase("feedback")) {
                    Intent i = new Intent(minis_list.this, Feedback.class);
                    startActivity(i);
                }


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public class NewClass extends AsyncTask<String, String, String> {
        String urlParameters = "";

        @Override
        protected String doInBackground(String... strings) {


            sh = Connectivity.excutePost(Constants.Minis_list,
                    "");
            Log.e("You are at", "" + sh);

            return sh;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //   if (sh.contains("SUCCESS")) {
            //   Toast.makeText(getApplicationContext(), sh, Toast.LENGTH_SHORT).show();
            parsingmethod(s);
            mAdapter.notifyDataSetChanged();
            //   }
        }


    }

    public void parsingmethod(String resp) {
        try {
            JSONObject object0 = new JSONObject(resp);
            JSONObject jobject1 = object0.getJSONObject("Event");
            JSONArray ja = jobject1.getJSONArray("Details");
            int length = ja.length();
            List<String> label = new ArrayList<String>();

            for (int i = 0; i < length; i++) {
                JSONObject data1 = ja.getJSONObject(i);
                final String[] id = {data1.getString("id")};
                String name = data1.getString("name");
                String mlaid = data1.getString("ministral_id");
                String Department = data1.getString("Department");
                String constituency = data1.getString("constituency");

                mins ml = new mins(name,mlaid,Department,constituency);


                minll.add(ml);

            }

        } catch (JSONException e )
        {
          Log.e("fv",e.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("mException", "qqqqqq" + e);
        }
    }
    }
