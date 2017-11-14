package com.example.lida.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lida.myapplication.xtras.classFileWrite;
import com.example.lida.myapplication.xtras.timeStampName;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static com.example.lida.myapplication.Filepath.getPath;


public class Compreg extends AppCompatActivity {
    EditText etc, etcomp;
    Button btupload, bts;
    TextView tvselect, mText;
    String title, des;
    String sh;
    Spinner mspinner;
    ArrayAdapter<String> adapter;
    String s;
    String idk;
    String response;
    String name1;
    Button fileupload;
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String selectedFilePath;
    ProgressDialog dialog;
    String filename;
    ImageButton btcam;
    int isproof = 0;


    ArrayList<HashMap<String, String>> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compreg);

        mText = (TextView) findViewById(R.id.messageText);
        etc = (EditText) findViewById(R.id.etc);
        etcomp = (EditText) findViewById(R.id.etcomp);
        btupload = (Button) findViewById(R.id.btupload);
        fileupload = (Button) findViewById(R.id.uploadfilee);
        bts = (Button) findViewById(R.id.bts);
        SharedPreferences share = getSharedPreferences("mlaid", MODE_PRIVATE);
        /*name1 = share.getString("name", "  ");*/
        idk = share.getString("id", "");
        Toast.makeText(this, name1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, idk, Toast.LENGTH_SHORT).show();
        SharedPreferences dd = getSharedPreferences("abc", MODE_PRIVATE);
        name1 = dd.getString("name", null);
        Toast.makeText(this, name1, Toast.LENGTH_SHORT).show();
        btcam = (ImageButton) findViewById(R.id.bt_cam);

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
                int isempty_test = 0;

                title = etc.getText().toString();
                des = etcomp.getText().toString();

                if (title.isEmpty()) {
                    isempty_test = 1;
                    Toast.makeText(Compreg.this, "Title is emplty", Toast.LENGTH_SHORT).show();

                }
                if (des.isEmpty()) {
                    isempty_test = 1;
                    Toast.makeText(Compreg.this, "Discription is emplty", Toast.LENGTH_SHORT).show();

                }
                if (isempty_test == 0) {
                    if (isproof == 0) {
                        new NewTask().execute();
                    }
                    if (isproof == 1) {
                        new NewTask2().execute();
                    }

                }


            }
        });
        btcam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btupload.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 123);


            }
        });

        fileupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new upp().execute();
                Toast.makeText(Compreg.this, "upload", Toast.LENGTH_SHORT).show();
            }
        });


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

    public class NewTask2 extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String urlParameters = null;
            try {
                urlParameters = "title=" + URLEncoder.encode(title, "UTF-8") + "&&"
                        + "des=" + URLEncoder.encode(des, "UTF-8") + "&&" + "id=" + URLEncoder.encode(idk, "UTF-8") + "&&" + "name=" + URLEncoder.encode(name1, "UTF-8") + "&&" + "file_name=" + URLEncoder.encode(filename, "UTF-8");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response = Connectivity.excutePost(Constants.COMPLAINT_REG_URL2,
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
                selectedFilePath = getPath(this, selectedFileUri);
                Log.i(TAG, "Selected File Path:" + selectedFilePath);

                if (selectedFilePath != null && !selectedFilePath.equals("")) {


                } else {
                    Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                }
            }
            if (requestCode == 123) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                ImageView img = (ImageView) findViewById(R.id.testimgview);
                selectedFilePath = classFileWrite.createDirectoryAndSaveFile(photo, timeStampName.time());
                fileupload.setVisibility(View.VISIBLE);
                btcam.setVisibility(View.INVISIBLE);


            }
        }

    }


    class upp extends AsyncTask<String, String, Void> implements SingleUploadBroadcastReceiver.Delegate {

        @Override
        protected Void doInBackground(String... strings) {
            final String TAG = "AndroidUploadService";

            final SingleUploadBroadcastReceiver uploadReceiver =
                    new SingleUploadBroadcastReceiver();

            //getting name for the image
            //String name = Todo;

            //getting the actual path of the image
            //path

            //Uploading code

            try {
                isproof = 1;
                String uploadId = UUID.randomUUID().toString();
                uploadReceiver.setDelegate(this);
                uploadReceiver.setUploadID(uploadId);
                filename = selectedFilePath.substring((selectedFilePath.lastIndexOf("/") + 1));
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(Compreg.this, filename, Toast.LENGTH_SHORT).show();
                    }
                });
                //Creating a multi part request
                new MultipartUploadRequest(Compreg.this, uploadId, "http://192.168.1.9/mla/uploadfile.php")
                        .addFileToUpload(selectedFilePath, "uploaded_file") //Adding file
                        .setNotificationConfig(new UploadNotificationConfig())
                        .addParameter("name", name1) //Adding text parameter to the request
                        .addParameter("id", idk) //Adding text parameter to the request
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload

            } catch (final Exception exc) {

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(Compreg.this, exc.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


            }

            return null;
        }


        @Override
        public void onProgress(int progress) {

        }

        @Override
        public void onProgress(long uploadedBytes, long totalBytes) {

        }

        @Override
        public void onError(Exception exception) {

        }

        @Override
        public void onCompleted(int serverResponseCode, byte[] serverResponseBody) {

            final String mn = serverResponseBody.toString();
            Log.e("sdcbvjjjjjjjsdkhjvbc", mn);


        }

        @Override
        public void onCancelled() {

        }
    }

}


