package com.example.lida.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.example.lida.myapplication.Constants.knowstatus;

public class KnowYourStatus extends AppCompatActivity {
    EditText StatusId;
    ImageButton Search;
    TextView status, details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowstatus);


        StatusId = (EditText) findViewById(R.id.statusId);
        Search = (ImageButton) findViewById(R.id.Search);
        status = (TextView) findViewById(R.id.Statussss);
        details = (TextView) findViewById(R.id.details);
        ProgressDialog show;

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((StatusId.getText().toString().isEmpty())) {
                    Toast.makeText(KnowYourStatus.this, "Enter the Status id to search", Toast.LENGTH_SHORT).show();

                } else {
                    KYS n = new KYS();
                    n.execute();
                }
            }
        });
    }

    class KYS extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String urlParameters = null;
            try {

                urlParameters = "id=" + URLEncoder.encode(StatusId.getText().toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                Log.e("eroor from ur encoding", e.toString());
            }
            String mn = Connectivity.excutePost(knowstatus, urlParameters);
            return mn;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            parsingmethod(s);
        }
    }

    public void parsingmethod(String resp) {
        TextView t = (TextView) findViewById(R.id.dtvisibility);
        t.setVisibility(View.VISIBLE);
        TextView m = (TextView) findViewById(R.id.stvisibility);
        m.setVisibility(View.VISIBLE);

        try {
            JSONObject object0 = new JSONObject(resp);
            JSONArray jobject1 = object0.getJSONArray("result");

            JSONObject data1 = jobject1.getJSONObject(0);

            status.setText(data1.getString("Status"));
            details.setText(data1.getString("Details"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
