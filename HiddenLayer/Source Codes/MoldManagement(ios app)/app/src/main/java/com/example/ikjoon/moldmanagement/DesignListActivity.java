package com.example.ikjoon.moldmanagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


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


public class DesignListActivity extends Activity {

    private static String TAG = "phptest_DesignListActivity";

    private static final String TAG_JSON = "design";
    private static final String TAG_DESIGN_CODE = "DESIGN_CODE";
    private static final String TAG_METAL_SHEET_TYPE = "METAL_SHEET_TYPE";
    private static final String TAG_TOTAL_MOLD_NUMBER = "TOTAL_MOLD_NUMBER";
    private static final String TAG_TOTAL_HITTING_TIMES = "TOTAL_HITTING_TIMES";
    private static final String TAG_PRODUCT_TIME = "PRODUCT_TIME";

    ArrayList<HashMap<String, String>> mArrayList;
    ListView designListView;
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_list);

        designListView = findViewById(R.id.designListView);

        // 뒤로 버튼 클릭 시 이벤트(메인화면으로 돌아감)
        Button backToMain2 = findViewById(R.id.backToMain2);
        backToMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // 등록 버튼 클릭 시 이벤트(새로운 설계도 등록 할 때 클릭)
        TextView designRegisterButton = findViewById(R.id.designRegisterButton);
        designRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DesignListActivity.this, DesignRegisterActivity.class);
                DesignListActivity.this.startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        mArrayList = new ArrayList<>();
        GetData task = new GetData();
        task.execute("http://smurf1213.cafe24.com/select_design.php");

    }

    @SuppressLint("StaticFieldLeak")
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(DesignListActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);

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

                String designCode = item.getString(TAG_DESIGN_CODE);
                String metalSheetType = item.getString(TAG_METAL_SHEET_TYPE);
                String totalMoldNumber = item.getString(TAG_TOTAL_MOLD_NUMBER);
                String totalHittingTimes = item.getString(TAG_TOTAL_HITTING_TIMES);
                String productTime = item.getString(TAG_PRODUCT_TIME);

                double amount = Double.parseDouble(totalHittingTimes);
                DecimalFormat formatter = new DecimalFormat("#,###");
                String formatted = formatter.format(amount);


                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_DESIGN_CODE, designCode);
                hashMap.put(TAG_METAL_SHEET_TYPE, metalSheetType);
                hashMap.put(TAG_TOTAL_MOLD_NUMBER, totalMoldNumber);
                hashMap.put(TAG_TOTAL_HITTING_TIMES, formatted);
                hashMap.put(TAG_PRODUCT_TIME, productTime);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    DesignListActivity.this, mArrayList, R.layout.design_list_item,
                    new String[]{TAG_DESIGN_CODE, TAG_METAL_SHEET_TYPE, TAG_TOTAL_MOLD_NUMBER, TAG_TOTAL_HITTING_TIMES, TAG_PRODUCT_TIME},
                    new int[]{R.id.designCodeList, R.id.metalSheetTypeList, R.id.moldNumberList, R.id.hittingTimesList, R.id.productTimeList}
            );

            designListView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }
}


