package com.example.lida.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lida.myapplication.adapter.RecyclerTouchListener;
import com.example.lida.myapplication.adapter.mla_adapter;
import com.example.lida.myapplication.decorators.DividerItemDecoration;
import com.example.lida.myapplication.models.mla;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class mla_list extends AppCompatActivity {
    String sh;
    private List<mla> mla_list = new ArrayList<>();
    private RecyclerView mla_View;
    private mla_adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mla_list);
        mla_View = (RecyclerView) findViewById(R.id.mla_recyclervview);

        mAdapter = new mla_adapter(mla_list);

        mla_View.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mla_View.setLayoutManager(mLayoutManager);
        mla_View.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mla_View.setItemAnimator(new DefaultItemAnimator());
        mla_View.setAdapter(mAdapter);
        final NewClass n = new NewClass();
        n.execute();
        mla_View.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mla_View, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mla ml = mla_list.get(position);
                Toast.makeText(getApplicationContext(), ml.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                SharedPreferences sh = getSharedPreferences("mlaid", MODE_PRIVATE);
                SharedPreferences.Editor edsh = sh.edit();
                edsh.putString("id", ml.getId());
                edsh.putString("name", ml.getName());
                edsh.commit();

                SharedPreferences md = getSharedPreferences("abc", MODE_PRIVATE);
                String s = md.getString("type", null);
                if (s.equalsIgnoreCase("complaint")) {
                    Intent i = new Intent(mla_list.this, Compreg.class);
                    startActivity(i);
                }
                if (s.equalsIgnoreCase("feedback")) {
                    Intent i = new Intent(mla_list.this, Feedback.class);
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


            sh = Connectivity.excutePost(Constants.MLA_URL,
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
                String mlaid = data1.getString("mlaid");

                mla ml = new mla(name, mlaid, "con");


                mla_list.add(ml);

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("mException", "qqqqqq" + e);
        }
    }

}
