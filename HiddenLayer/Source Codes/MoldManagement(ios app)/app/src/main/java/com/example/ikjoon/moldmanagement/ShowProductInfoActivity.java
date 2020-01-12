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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ShowProductInfoActivity extends Activity {

    private static String TAG = "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";

    private static final String TAG_JSON = "design";
    private static final String TAG_REQUIRED_TIME = "PRODUCT_TIME";
    private static final String TAG_TOTAL_MOLD_NUMBER = "TOTAL_MOLD_NUMBER";
    private static final String TAG_MOLD_CODE = "MOLD_CODE";
    private static final String TAG_HITTING_TIMES = "HITTING_TIMES";


    ArrayList<HashMap<String, String>> mArrayList;
    TextView selectedDesignCodeText, moldToBeUsedText, outputToBeProductText, requiredTimeText, metalSheetTypeText;
    ListView selectedMoldsListView;
    String productAmount, designCode, dayOrNight;
    String mJsonString, mJsonString2, mJsonString3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product_info);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        productAmount = extras.getString("productAmount");
        designCode = extras.getString("designCode");
        dayOrNight = extras.getString("dayOrNight");

        selectedDesignCodeText = findViewById(R.id.selectedDesignCodeText);
        moldToBeUsedText = findViewById(R.id.moldToBeUsedText);
        selectedMoldsListView = findViewById(R.id.selectedMoldsListView);
        outputToBeProductText = findViewById(R.id.outputToBeProductText);
        requiredTimeText = findViewById(R.id.requiredTimeText);
        metalSheetTypeText = findViewById(R.id.selectedDesignMetalSheetTypeText);

        selectedDesignCodeText.setText(designCode);
        String amount = productAmount + "ea";
        outputToBeProductText.setText(amount);


        GetData task = new GetData();
        task.execute(designCode);

        mArrayList = new ArrayList<>();
        GetUsingMolds getUsingMolds = new GetUsingMolds();
        getUsingMolds.execute(designCode);

        Button checked = findViewById(R.id.exitRecommendationButton);
        checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowProductInfoActivity.this, MainActivity.class);
                ShowProductInfoActivity.this.startActivity(intent);
            }
        });

        GetNeedInfo getNeedInfo = new GetNeedInfo();
        getNeedInfo.execute(designCode);
    }

    //뒤로가기 기능 막기
    @Override
    public void onBackPressed() {
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ShowProductInfoActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //theResult.setText(result);
            Log.d(TAG, "onPostExecute 부분 - " + result);

            if (result == null) {

                //theResult.setText(errorString);
            } else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String designCode = params[0];

            String serverURL = "http://smurf1213.cafe24.com/design_select_qurey.php";
            String postParameters = "DESIGN_CODE=" + designCode;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


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

        StringBuilder stringBuffer1 = new StringBuilder();
        StringBuilder stringBuffer2 = new StringBuilder();
        StringBuilder stringBuffer3 = new StringBuilder();

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            JSONObject item = jsonArray.getJSONObject(0);

            String totalMoldNumber = item.getString(TAG_TOTAL_MOLD_NUMBER);
            String requiredTime = item.getString(TAG_REQUIRED_TIME);
            String metalSheetType = item.getString("METAL_SHEET_TYPE");

            stringBuffer1.append(requiredTime);
            stringBuffer2.append(totalMoldNumber);
            stringBuffer3.append(metalSheetType);

            int requiredTimeInt = Integer.parseInt(stringBuffer1.toString()) * Integer.parseInt(productAmount) / 60;
            String strRequired = String.valueOf(requiredTimeInt) + "분";
            String strMoldToBeUsed = "총 " + stringBuffer2.toString() + "개";
            String strMetalSheetType = String.valueOf(stringBuffer3);

            requiredTimeText.setText(strRequired);
            moldToBeUsedText.setText(strMoldToBeUsed);
            metalSheetTypeText.setText(strMetalSheetType);


        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

    @SuppressLint("StaticFieldLeak")
    private class GetUsingMolds extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ShowProductInfoActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //theResult.setText(result);
            Log.d(TAG, "onPostExecute 부분 - " + result);

            if (result == null) {

                //theResult.setText(errorString);
            } else {

                mJsonString2 = result;
                showUsingMolds();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String designCode = params[0];

            String serverURL = "http://smurf1213.cafe24.com/select_usingMold_from_design.php";
            String postParameters = "DESIGN_CODE=" + designCode;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


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


    private void showUsingMolds() {

        try {
            JSONObject jsonObject = new JSONObject(mJsonString2);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String moldCode = item.getString(TAG_MOLD_CODE);
                String hitting = item.getString(TAG_HITTING_TIMES);

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(TAG_MOLD_CODE, moldCode);
                hashMap.put(TAG_HITTING_TIMES, hitting);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    ShowProductInfoActivity.this, mArrayList, R.layout.mold_selected_list_item,
                    new String[]{TAG_MOLD_CODE, TAG_HITTING_TIMES},
                    new int[]{R.id.selectedMoldCodeListItem, R.id.selectedMoldNumberListItem}
            );

            selectedMoldsListView.setAdapter(adapter);


        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


    @SuppressLint("StaticFieldLeak")
    private class GetNeedInfo extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ShowProductInfoActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //theResult.setText(result);
            Log.d(TAG, "onPostExecute 부분 - " + result);

            if (result == null) {

                //theResult.setText(errorString);
            } else {

                mJsonString3 = result;
                showInfo();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String designCode = params[0];

            String serverURL = "http://smurf1213.cafe24.com/select_need_info.php";
            String postParameters = "DESIGN_CODE=" + designCode;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


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


    private void showInfo() {
        try {

            JSONObject jsonObject = new JSONObject(mJsonString3);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                String row = item.getString("MOLD_CODE");
                Log.d("인덱스 몇 개인지 보는 부분 : ", row);
            }

            calculate(jsonArray);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

    private void calculate(JSONArray jsonArray) {

        try {
            ArrayList<String> arrayList1 = new ArrayList<>();
            ArrayList<String> arrayList2 = new ArrayList<>();
            ArrayList<String> arrayList3 = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String moldCode = item.getString("MOLD_CODE");
                String number = item.getString("MOLD_NUMBER");
                String hittingTimes = item.getString("HITTING_TIMES");
                String accuHitting = item.getString("HITTING_ACCUMULATE_TIME");
                String dayOrNight = item.getString("METAL_SHEET_TYPE");

                if (dayOrNight.equals("1.6T")) {

                    int calResult = Integer.parseInt(hittingTimes) * Integer.parseInt(productAmount);
                    int accuRecult = Integer.parseInt(accuHitting) + calResult;

                    arrayList1.add(moldCode);
                    arrayList2.add(number);
                    arrayList3.add(String.valueOf(accuRecult));

                } else if (dayOrNight.equals("2T")) {

                    double calResult = Integer.parseInt(hittingTimes) * Integer.parseInt(productAmount) * 1.25;
                    double accuRecult = Integer.parseInt(accuHitting) + calResult;

                    arrayList1.add(moldCode);
                    arrayList2.add(number);
                    arrayList3.add(String.valueOf(accuRecult));
                } else {
                    Log.d(TAG, "1.6T 또는 2T가 아닌 경우");
                }
            }

            //String x = arrayList1.get(0) + ", " + arrayList2.get(0) + ", " + arrayList3.get(0);

            //Log.d("------------계산결과", x);

            try {
                String puncher = arrayList1.get(0);
                String number = arrayList2.get(0);
                String accuResult = arrayList3.get(0);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 0은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(1);
                String number = arrayList2.get(1);
                String accuResult = arrayList3.get(1);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 1은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(2);
                String number = arrayList2.get(2);
                String accuResult = arrayList3.get(2);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 2은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(3);
                String number = arrayList2.get(3);
                String accuResult = arrayList3.get(3);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 3은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(4);
                String number = arrayList2.get(4);
                String accuResult = arrayList3.get(4);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 4은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(5);
                String number = arrayList2.get(5);
                String accuResult = arrayList3.get(5);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 5은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(6);
                String number = arrayList2.get(6);
                String accuResult = arrayList3.get(6);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 6은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(7);
                String number = arrayList2.get(7);
                String accuResult = arrayList3.get(7);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 7은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(8);
                String number = arrayList2.get(8);
                String accuResult = arrayList3.get(8);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 8은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(9);
                String number = arrayList2.get(9);
                String accuResult = arrayList3.get(9);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 9은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(10);
                String number = arrayList2.get(10);
                String accuResult = arrayList3.get(10);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 10은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(11);
                String number = arrayList2.get(11);
                String accuResult = arrayList3.get(11);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 11은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(12);
                String number = arrayList2.get(12);
                String accuResult = arrayList3.get(12);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 12은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(13);
                String number = arrayList2.get(13);
                String accuResult = arrayList3.get(13);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 13은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(14);
                String number = arrayList2.get(14);
                String accuResult = arrayList3.get(14);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 14은(는) 존재하지 않습니다.", String.valueOf(e));
            }
            try {
                String puncher = arrayList1.get(15);
                String number = arrayList2.get(15);
                String accuResult = arrayList3.get(15);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 15은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(16);
                String number = arrayList2.get(16);
                String accuResult = arrayList3.get(16);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 16은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(17);
                String number = arrayList2.get(17);
                String accuResult = arrayList3.get(17);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 17은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(18);
                String number = arrayList2.get(18);
                String accuResult = arrayList3.get(18);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 18은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(19);
                String number = arrayList2.get(19);
                String accuResult = arrayList3.get(19);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 19은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(20);
                String number = arrayList2.get(20);
                String accuResult = arrayList3.get(20);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 20은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(21);
                String number = arrayList2.get(21);
                String accuResult = arrayList3.get(21);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 21은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(22);
                String number = arrayList2.get(22);
                String accuResult = arrayList3.get(22);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 22은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(23);
                String number = arrayList2.get(23);
                String accuResult = arrayList3.get(23);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 23은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(24);
                String number = arrayList2.get(24);
                String accuResult = arrayList3.get(24);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 24은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(25);
                String number = arrayList2.get(25);
                String accuResult = arrayList3.get(25);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 25은(는) 존재하지 않습니다.", String.valueOf(e));
            }

            try {
                String puncher = arrayList1.get(26);
                String number = arrayList2.get(26);
                String accuResult = arrayList3.get(26);

                UpdateData updateData = new UpdateData();
                updateData.execute(puncher, number, accuResult);

            } catch (Exception e) {
                Log.d("인덱스 26은(는) 존재하지 않습니다.", String.valueOf(e));
            }


        } catch (JSONException e) {
            Log.d(TAG, "계산 함수 틀렸을 때 : ", e);
        }


    }

    @SuppressLint("StaticFieldLeak")
    class UpdateData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ShowProductInfoActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {


            String puncher = params[0];
            String number = params[1];
            String accuTimes = params[2];

            String serverURL = "http://smurf1213.cafe24.com/update_hitting_number.php";

            String postParameters = "MOLD_CODE=" + puncher
                    + "&MOLD_NUMBER=" + number
                    + "&HITTING_ACCUMULATE_TIME=" + accuTimes;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString();

            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return "Error: " + e.getMessage();
            }

        }
    }

}
