package com.example.ikjoon.moldmanagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
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
import java.util.ArrayList;
import java.util.HashMap;

public class ProductionHistoryActivity extends Activity {

    private static String TAG = "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";

    private static final String TAG_JSON = "product";
    private static final String TAG_PRODUCT_DATE = "PRODUCT_DATE";
    private static final String TAG_PRODUCT_TIME = "PRODUCT_TIME";
    private static final String TAG_DAY_NIGHT = "DAY_NIGHT";
    private static final String TAG_DESIGN_CODE = "DESIGN_CODE";
    private static final String TAG_PRODUCT_OUTPUT = "PRODUCT_OUTPUT";

    ArrayList<HashMap<String, String>> mArrayList;
    ListView productListView;
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_history);

        productListView = findViewById(R.id.productListView);

        mArrayList = new ArrayList<>();
        GetData getData = new GetData();
        getData.execute("http://smurf1213.cafe24.com/product_select.php");

        Button backToMain3 = findViewById(R.id.backToMain3);
        backToMain3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ProductionHistoryActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //resultText.setText(result);
            if (result == null) {
                //resultText.setText(errorString);
            } else {

                mJsonString = result;
                showResult();
            }
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

                String productDate = item.getString(TAG_PRODUCT_DATE);
                String productTime = item.getString(TAG_PRODUCT_TIME);
                String productDayNight = item.getString(TAG_DAY_NIGHT);
                String designCode = item.getString(TAG_DESIGN_CODE);
                String productOutput = item.getString(TAG_PRODUCT_OUTPUT);


                HashMap<String, String> hashMap = new HashMap<>();


                hashMap.put(TAG_PRODUCT_DATE, productDate);
                hashMap.put(TAG_PRODUCT_TIME, productTime);
                hashMap.put(TAG_DAY_NIGHT, productDayNight);
                hashMap.put(TAG_DESIGN_CODE, designCode);
                hashMap.put(TAG_PRODUCT_OUTPUT, productOutput);


                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    ProductionHistoryActivity.this, mArrayList, R.layout.product_list_item,
                    new String[]{TAG_PRODUCT_DATE, TAG_PRODUCT_TIME, TAG_DAY_NIGHT, TAG_DESIGN_CODE, TAG_PRODUCT_OUTPUT},
                    new int[]{R.id.productDateListItem, R.id.productTimeListItem, R.id.productDayOrNightListItem, R.id.productDesignCodeListItem, R.id.productAmountListItem}
            );

            productListView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}
