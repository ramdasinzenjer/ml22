package com.example.lida.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Compreg extends AppCompatActivity {
    EditText etc, etcomp;
    Button btupload, bts;
    TextView tvselect;
    String title, des;
    String sh;
    Spinner mspinner;
    ArrayAdapter<String> adapter;
    String s;
    String idk;
    String response;
    String name1;
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String selectedFilePath;
    ProgressDialog dialog;


    ArrayList<HashMap<String, String>> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compreg);
        etc = (EditText) findViewById(R.id.etc);
        etcomp = (EditText) findViewById(R.id.etcomp);
        btupload = (Button) findViewById(R.id.btupload);
        final Button fileupload = (Button) findViewById(R.id.uploadfilee);
        bts = (Button) findViewById(R.id.bts);
        tvselect = (TextView) findViewById(R.id.tvselect);
        mspinner = (Spinner) findViewById(R.id.spinner);
        SharedPreferences share = getSharedPreferences("abc", MODE_APPEND);
        name1 = share.getString("name", "");

        btupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
                btupload.setVisibility(View.INVISIBLE);
                fileupload.setVisibility(View.VISIBLE);

            }
        });
        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                title = etc.getText().toString();
                des = etcomp.getText().toString();
                new NewTask().execute();


            }
        });
        fileupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new upp().execute();
                Toast.makeText(Compreg.this, "upload", Toast.LENGTH_SHORT).show();
            }
        });
        new NewClass().execute();


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
            parsingmethod(sh);

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

                HashMap<String, String> map = new HashMap<>();
                map.put("id", id[0]);
                map.put("name", name);
                map.put("mlaid", mlaid);

                list.add(map);
                label.add(list.get(i).get("name"));

                //   Toast.makeText(getApplicationContext(),"-------"+label,Toast.LENGTH_SHORT).show();

                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, label);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mspinner.setAdapter(adapter);

                mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        s = adapterView.getItemAtPosition(i).toString();
                        Toast.makeText(getApplicationContext(), "-------" + s, Toast.LENGTH_SHORT).show();
                        idk = list.get(i).get("mlaid").toString();
                        Toast.makeText(Compreg.this, idk, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                Log.e("mException", "qqqqq" + label.get(i).toString());

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("mException", "qqqqqq" + e);
        }
    }

    public class NewTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String urlParameters = null;
            try {
                urlParameters = "title=" + URLEncoder.encode(title, "UTF-8") + "&&"
                        + "des=" + URLEncoder.encode(des, "UTF-8") + "&&" + "id=" + URLEncoder.encode(idk, "UTF-8") + "&&" + "name=" + URLEncoder.encode(name1, "UTF-8");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response = Connectivity.excutePost(Constants.COMPLAINT_REG_URL,
                    urlParameters);
            Log.e("You are at", "" + response);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Compreg.this, "" + response, Toast.LENGTH_SHORT).show();

            try {
                JSONObject lid = new JSONObject(response);
                JSONArray result = lid.getJSONArray("ram");
                JSONObject em = result.getJSONObject(1);
                var.id = em.getString("id");
                String ng = em.getString("status");
                Toast.makeText(Compreg.this, "" + response, Toast.LENGTH_SHORT).show();

                if (ng.contains("success")) {
                    Toast.makeText(Compreg.this, "" + response, Toast.LENGTH_SHORT).show();
                    Intent n = new Intent(Compreg.this, Registred.class);
                    startActivity(n);
                } else {
                    Toast.makeText(Compreg.this, "" + response, Toast.LENGTH_SHORT).show();

                }


            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("ASDsc", e.toString());
                Toast.makeText(Compreg.this, e.toString(), Toast.LENGTH_SHORT).show();


            }


        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST) {
                if (data == null) {
                    //no data present
                    return;
                }


                Uri selectedFileUri = data.getData();
                selectedFilePath = Filepath.getPath(this, selectedFileUri);
                Log.i(TAG, "Selected File Path:" + selectedFilePath);

                if (selectedFilePath != null && !selectedFilePath.equals("")) {

                } else {
                    Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    class upp extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {


            int serverResponseCode = 0;

            HttpURLConnection connection;
            DataOutputStream dataOutputStream;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";


            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;
            File selectedFile = new File(selectedFilePath);


            String[] parts = selectedFilePath.split("/");
            final String fileName = parts[parts.length - 1];

            if (!selectedFile.isFile()) {
                dialog.dismiss();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                return String.valueOf(0);
            } else {
                try {
                    FileInputStream fileInputStream = new FileInputStream(selectedFile);
                    URL url = new URL("http://192.168.1.9/mla/uploadfile.php");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);//Allow Inputs
                    connection.setDoOutput(true);//Allow Outputs
                    connection.setUseCaches(false);//Don't use a cached Copy
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Connection", "Keep-Alive");
                    connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                    connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    connection.setRequestProperty("uploaded_file", selectedFilePath);
                    connection.setRequestProperty("name",name1 );

                    //creating new dataoutputstream
                    dataOutputStream = new DataOutputStream(connection.getOutputStream());

                    //writing bytes to data outputstream
                    dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                    dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                            + selectedFilePath + "\"" + lineEnd);

                    dataOutputStream.writeBytes(lineEnd);

                    //returns no. of bytes present in fileInputStream
                    bytesAvailable = fileInputStream.available();
                    //selecting the buffer size as minimum of available bytes or 1 MB
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    //setting the buffer as byte array of size of bufferSize
                    buffer = new byte[bufferSize];

                    //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                    while (bytesRead > 0) {
                        //write the bytes read from inputstream
                        dataOutputStream.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }

                    dataOutputStream.writeBytes(lineEnd);
                    dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    serverResponseCode = connection.getResponseCode();
                    String serverResponseMessage = connection.getResponseMessage();

                    Log.e(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                    //response code of 200 indicates the server status OK
                    if (serverResponseCode == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }

                    //closing the input and output streams
                    fileInputStream.close();
                    dataOutputStream.flush();
                    dataOutputStream.close();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Compreg.this, "File Not Found", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                   runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Compreg.this, "URL error!", Toast.LENGTH_SHORT).show();
                        }
                    });


                } catch (IOException e) {
                    final String f = e.toString();

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Compreg.this, "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(Compreg.this, f, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                return String.valueOf(serverResponseCode);
            }

        }

    }
}