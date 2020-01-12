package com.example.ikjoon.moldmanagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MoldAlertActivity extends Activity {

    private static String TAG = "결과 나오는 부분 : ";

    private static final String TAG_JSON = "mold";

    ArrayList<HashMap<String, String>> mArrayList;
    ListView needToChangeMoldListView;
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mold_alert);

        needToChangeMoldListView = findViewById(R.id.needToChangeMoldListView);

        mArrayList = new ArrayList<>();
        GetData getData = new GetData();
        getData.execute("http://smurf1213.cafe24.com/mold_have_to_change.php");

        Button changeNowButton = findViewById(R.id.changeNowButton);
        changeNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoldAlertActivity.this, UsageStatusActivity.class);
                MoldAlertActivity.this.startActivity(intent);
                finish();
            }
        });

        Button changeLaterButton = findViewById(R.id.changeLaterButton);
        changeLaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //바깥레이어 클릭시 안닫히게
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldAlertActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //resultText.setText(result);
            mJsonString = result;
            showResult();
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String moldCode = item.getString("MOLD_CODE");
                String moldNumber = item.getString("MOLD_NUMBER");
                String hittingTimes = item.getString("HITTING_ACCUMULATE_TIME");

                double amount = Double.parseDouble(hittingTimes);
                DecimalFormat formatter = new DecimalFormat("#,###");
                String formatted = formatter.format(amount);


                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put("MOLD_CODE", moldCode);
                hashMap.put("MOLD_NUMBER", moldNumber);
                hashMap.put("HITTING_ACCUMULATE_TIME", formatted);


                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    MoldAlertActivity.this, mArrayList, R.layout.list_item_need_to_change_mold,
                    new String[]{"MOLD_CODE", "MOLD_NUMBER", "HITTING_ACCUMULATE_TIME"},
                    new int[]{R.id.needToChangeMoldCodeListItem, R.id.needToChangeMoldNumberListItem, R.id.needToChangeMoldHittingTimesListItem}
            );

            needToChangeMoldListView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }
}
