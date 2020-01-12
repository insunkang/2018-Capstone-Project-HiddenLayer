package com.example.ikjoon.moldmanagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MoldBreakageHistoryActivity extends Activity {

    private static String TAG = "결과 출력  ";

    private static final String TAG_JSON = "brokenMold";
    private static final String TAG_BROKEN_DATE = "BROKEN_DATE";
    private static final String TAG_MOLD_CODE = "MOLD_CODE";
    private static final String TAG_FINAL_HITTING_TIMES = "FINAL_HITTING_TIMES";

    ArrayList<HashMap<String, String>> mArrayList;
    ListView brokenMoldListView;
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mold_breakage_history);

        brokenMoldListView = findViewById(R.id.brokenMoldListView);

        //뒤로 버튼
        Button backToMain5 = findViewById(R.id.backToMain5);
        backToMain5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button registerNewBrokenMoldBrokenButton = findViewById(R.id.registerNewBrokenMoldBrokenButton);
        registerNewBrokenMoldBrokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoldBreakageHistoryActivity.this, RegisterBrokenMoldActivity.class);
                MoldBreakageHistoryActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mArrayList = new ArrayList<>();

        GetData task = new GetData();
        task.execute("http://smurf1213.cafe24.com/select_broken_mold.php");

    }

    @SuppressLint("StaticFieldLeak")
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldBreakageHistoryActivity.this,
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

                return "";
            }

        }
    }


    private void showResult() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String brokenDate = item.getString(TAG_BROKEN_DATE);
                String moldCode = item.getString(TAG_MOLD_CODE);
                String finalHitting = item.getString(TAG_FINAL_HITTING_TIMES);
                double amount = Double.parseDouble(finalHitting);
                DecimalFormat formatter = new DecimalFormat("#,###");
                String formatted = formatter.format(amount);


                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_BROKEN_DATE, brokenDate);
                hashMap.put(TAG_MOLD_CODE, moldCode);
                hashMap.put(TAG_FINAL_HITTING_TIMES, formatted);


                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    MoldBreakageHistoryActivity.this, mArrayList, R.layout.list_item_broken_mold,
                    new String[]{TAG_BROKEN_DATE, TAG_MOLD_CODE, TAG_FINAL_HITTING_TIMES},
                    new int[]{R.id.brokenDateListItem, R.id.brokenMoldListItem, R.id.finalHittingTimesListItem}
            );

            brokenMoldListView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }
}

