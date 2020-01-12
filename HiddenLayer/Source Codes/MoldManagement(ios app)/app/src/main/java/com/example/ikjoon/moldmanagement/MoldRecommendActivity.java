package com.example.ikjoon.moldmanagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.text.DecimalFormat;


public class MoldRecommendActivity extends Activity {

    private static String TAG = "각 결과 나오는 부분 : ";

    private static final String TAG_JSON = "mold";
    private static final String TAG_MOLD_CODE = "MOLD_CODE";
    private static final String TAG_MOLD_NUMBER = "MOLD_NUMBER";
    private static final String TAG_HITTING_ACCUMULATE_TIME = "HITTING_ACCUMULATE_TIME";

    String mJsonString1, mJsonString2, mJsonString3, mJsonString4, mJsonString5, mJsonString6, mJsonString7, mJsonString8, mJsonString9, mJsonString10, mJsonString11, mJsonString12, mJsonString13, mJsonString14, mJsonString15, mJsonString16, mJsonString17, mJsonString18, mJsonString19, mJsonString20, mJsonString21, mJsonString22, mJsonString23, mJsonString24, mJsonString25, mJsonString26;
    TextView mTemporaryResultText, ASQ1, ASQ2, ASQ3, ARO1, ARO2, ARO3, AOBW1, AOBW2, AOBW3, AOBH1, AOBH2, AOBH3, ACP1, ACP2, ACP3, ASP1, ASP2, ASP3, AREW1, AREW2, AREW3, AREH1, AREH2, AREH3, BOBH1, BOBH2, BOBH3, BREW1, BREW2, BREW3, BREH1, BREH2, BREH3, BRO1, BRO2, BRO3, BSQ1, BSQ2, BSQ3, BSQD1, BSQD2, BSQD3, BCR1, BCR2, BCR3, CCR1, CCR2, CCR3, COBW1, COBW2, COBW3, COBH1, COBH2, COBH3, CSP1, CSP2, CSP3, CRR1, CRR2, CRR3, DREW1, DREW2, DREW3, DREH1, DREH2, DREH3, EREW1, EREW2, EREW3, EREH1, EREH2, EREH3, GCR1, GCR2, GCR3, GREW1, GREW2, GREW3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mold_recommend);

        ASQ1 = findViewById(R.id.ASQ1);
        ASQ2 = findViewById(R.id.ASQ2);
        ASQ3 = findViewById(R.id.ASQ3);
        ARO1 = findViewById(R.id.ARO1);
        ARO2 = findViewById(R.id.ARO2);
        ARO3 = findViewById(R.id.ARO3);
        AOBW1 = findViewById(R.id.AOBW1);
        AOBW2 = findViewById(R.id.AOBW2);
        AOBW3 = findViewById(R.id.AOBW3);
        AOBH1 = findViewById(R.id.AOBH1);
        AOBH2 = findViewById(R.id.AOBH2);
        AOBH3 = findViewById(R.id.AOBH3);
        ACP1 = findViewById(R.id.ACP1);
        ACP2 = findViewById(R.id.ACP2);
        ACP3 = findViewById(R.id.ACP3);
        ASP1 = findViewById(R.id.ASP1);
        ASP2 = findViewById(R.id.ASP2);
        ASP3 = findViewById(R.id.ASP3);
        AREW1 = findViewById(R.id.AREW1);
        AREW2 = findViewById(R.id.AREW2);
        AREW3 = findViewById(R.id.AREW3);
        AREH1 = findViewById(R.id.AREH1);
        AREH2 = findViewById(R.id.AREH2);
        AREH3 = findViewById(R.id.AREH3);
        BOBH1 = findViewById(R.id.BOBH1);
        BOBH2 = findViewById(R.id.BOBH2);
        BOBH3 = findViewById(R.id.BOBH3);
        BREW1 = findViewById(R.id.BREW1);
        BREW2 = findViewById(R.id.BREW2);
        BREW3 = findViewById(R.id.BREW3);
        BREH1 = findViewById(R.id.BREH1);
        BREH2 = findViewById(R.id.BREH2);
        BREH3 = findViewById(R.id.BREH3);
        BRO1 = findViewById(R.id.BRO1);
        BRO2 = findViewById(R.id.BRO2);
        BRO3 = findViewById(R.id.BRO3);
        BSQ1 = findViewById(R.id.BSQ1);
        BSQ2 = findViewById(R.id.BSQ2);
        BSQ3 = findViewById(R.id.BSQ3);
        BSQD1 = findViewById(R.id.BSQD1);
        BSQD2 = findViewById(R.id.BSQD2);
        BSQD3 = findViewById(R.id.BSQD3);
        BCR1 = findViewById(R.id.BCR1);
        BCR2 = findViewById(R.id.BCR2);
        BCR3 = findViewById(R.id.BCR3);
        CCR1 = findViewById(R.id.CCR1);
        CCR2 = findViewById(R.id.CCR2);
        CCR3 = findViewById(R.id.CCR3);
        COBW1 = findViewById(R.id.COBW1);
        COBW2 = findViewById(R.id.COBW2);
        COBW3 = findViewById(R.id.COBW3);
        COBH1 = findViewById(R.id.COBH1);
        COBH2 = findViewById(R.id.COBH2);
        COBH3 = findViewById(R.id.COBH3);
        CSP1 = findViewById(R.id.CSP1);
        CSP2 = findViewById(R.id.CSP2);
        CSP3 = findViewById(R.id.CSP3);
        CRR1 = findViewById(R.id.CRR1);
        CRR2 = findViewById(R.id.CRR2);
        CRR3 = findViewById(R.id.CRR3);
        DREW1 = findViewById(R.id.DREW1);
        DREW2 = findViewById(R.id.DREW2);
        DREW3 = findViewById(R.id.DREW3);
        DREH1 = findViewById(R.id.DREH1);
        DREH2 = findViewById(R.id.DREH2);
        DREH3 = findViewById(R.id.DREH3);
        EREW1 = findViewById(R.id.EREW1);
        EREW2 = findViewById(R.id.EREW2);
        EREW3 = findViewById(R.id.EREW3);
        EREH1 = findViewById(R.id.EREH1);
        EREH2 = findViewById(R.id.EREH2);
        EREH3 = findViewById(R.id.EREH3);
        GCR1 = findViewById(R.id.GCR1);
        GCR2 = findViewById(R.id.GCR2);
        GCR3 = findViewById(R.id.GCR3);
        GREW1 = findViewById(R.id.GREW1);
        GREW2 = findViewById(R.id.GREW2);
        GREW3 = findViewById(R.id.GREW3);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        String dayOrNight = extras.getString("dayOrNight");

        mTemporaryResultText = findViewById(R.id.temporaryResultText);

        assert dayOrNight != null;
        if (dayOrNight.equals("day")) {
            mTemporaryResultText.setText("< 주간 작업 >");

            GetData getData = new GetData();
            getData.execute("http://smurf1213.cafe24.com/recommend_mold(ASQ_day).php");

            GetData2 getData2 = new GetData2();
            getData2.execute("http://smurf1213.cafe24.com/recommend_mold(ARO_day).php");

            GetData3 getData3 = new GetData3();
            getData3.execute("http://smurf1213.cafe24.com/recommend_mold(AOBW_day).php");

            GetData4 getData4 = new GetData4();
            getData4.execute("http://smurf1213.cafe24.com/recommend_mold(AOBH_day).php");

            GetData5 getData5 = new GetData5();
            getData5.execute("http://smurf1213.cafe24.com/recommend_mold(ACP_day).php");

            GetData6 getData6 = new GetData6();
            getData6.execute("http://smurf1213.cafe24.com/recommend_mold(ASP_day).php");

            GetData7 getData7 = new GetData7();
            getData7.execute("http://smurf1213.cafe24.com/recommend_mold(AREW_day).php");

            GetData8 getData8 = new GetData8();
            getData8.execute("http://smurf1213.cafe24.com/recommend_mold(AREH_day).php");

            GetData9 getData9 = new GetData9();
            getData9.execute("http://smurf1213.cafe24.com/recommend_mold(BOBH_day).php");

            GetData10 getData10 = new GetData10();
            getData10.execute("http://smurf1213.cafe24.com/recommend_mold(BREW_day).php");

            GetData11 getData11 = new GetData11();
            getData11.execute("http://smurf1213.cafe24.com/recommend_mold(BREH_day).php");

            GetData12 getData12 = new GetData12();
            getData12.execute("http://smurf1213.cafe24.com/recommend_mold(BRO_day).php");

            GetData13 getData13 = new GetData13();
            getData13.execute("http://smurf1213.cafe24.com/recommend_mold(BSQ_day).php");

            GetData14 getData14 = new GetData14();
            getData14.execute("http://smurf1213.cafe24.com/recommend_mold(BSQD_day).php");

            GetData15 getData15 = new GetData15();
            getData15.execute("http://smurf1213.cafe24.com/recommend_mold(BCR_day).php");

            GetData16 getData16 = new GetData16();
            getData16.execute("http://smurf1213.cafe24.com/recommend_mold(CCR_day).php");

            GetData17 getData17 = new GetData17();
            getData17.execute("http://smurf1213.cafe24.com/recommend_mold(COBW_day).php");

            GetData18 getData18 = new GetData18();
            getData18.execute("http://smurf1213.cafe24.com/recommend_mold(COBH_day).php");

            GetData19 getData19 = new GetData19();
            getData19.execute("http://smurf1213.cafe24.com/recommend_mold(CSP_day).php");

            GetData20 getData20 = new GetData20();
            getData20.execute("http://smurf1213.cafe24.com/recommend_mold(CRR_day).php");

            GetData21 getData21 = new GetData21();
            getData21.execute("http://smurf1213.cafe24.com/recommend_mold(DREW_day).php");

            GetData22 getData22 = new GetData22();
            getData22.execute("http://smurf1213.cafe24.com/recommend_mold(DREH_day).php");

            GetData23 getData23 = new GetData23();
            getData23.execute("http://smurf1213.cafe24.com/recommend_mold(EREW_day).php");

            GetData24 getData24 = new GetData24();
            getData24.execute("http://smurf1213.cafe24.com/recommend_mold(EREH_day).php");

            GetData25 getData25 = new GetData25();
            getData25.execute("http://smurf1213.cafe24.com/recommend_mold(GCR_day).php");

            GetData26 getData26 = new GetData26();
            getData26.execute("http://smurf1213.cafe24.com/recommend_mold(GREW_day).php");

        } else if (dayOrNight.equals("night")) {
            mTemporaryResultText.setText("< 야간 작업 >");

            GetData getData = new GetData();
            getData.execute("http://smurf1213.cafe24.com/recommend_mold(ASQ_night).php");

            GetData2 getData2 = new GetData2();
            getData2.execute("http://smurf1213.cafe24.com/recommend_mold(ARO_night).php");

            GetData3 getData3 = new GetData3();
            getData3.execute("http://smurf1213.cafe24.com/recommend_mold(AOBW_night).php");

            GetData4 getData4 = new GetData4();
            getData4.execute("http://smurf1213.cafe24.com/recommend_mold(AOBH_night).php");

            GetData5 getData5 = new GetData5();
            getData5.execute("http://smurf1213.cafe24.com/recommend_mold(ACP_night).php");

            GetData6 getData6 = new GetData6();
            getData6.execute("http://smurf1213.cafe24.com/recommend_mold(ASP_night).php");

            GetData7 getData7 = new GetData7();
            getData7.execute("http://smurf1213.cafe24.com/recommend_mold(AREW_night).php");

            GetData8 getData8 = new GetData8();
            getData8.execute("http://smurf1213.cafe24.com/recommend_mold(AREH_night).php");

            GetData9 getData9 = new GetData9();
            getData9.execute("http://smurf1213.cafe24.com/recommend_mold(BOBH_night).php");

            GetData10 getData10 = new GetData10();
            getData10.execute("http://smurf1213.cafe24.com/recommend_mold(BREW_night).php");

            GetData11 getData11 = new GetData11();
            getData11.execute("http://smurf1213.cafe24.com/recommend_mold(BREH_night).php");

            GetData12 getData12 = new GetData12();
            getData12.execute("http://smurf1213.cafe24.com/recommend_mold(BRO_night).php");

            GetData13 getData13 = new GetData13();
            getData13.execute("http://smurf1213.cafe24.com/recommend_mold(BSQ_night).php");

            GetData14 getData14 = new GetData14();
            getData14.execute("http://smurf1213.cafe24.com/recommend_mold(BSQD_night).php");

            GetData15 getData15 = new GetData15();
            getData15.execute("http://smurf1213.cafe24.com/recommend_mold(BCR_night).php");

            GetData16 getData16 = new GetData16();
            getData16.execute("http://smurf1213.cafe24.com/recommend_mold(CCR_night).php");

            GetData17 getData17 = new GetData17();
            getData17.execute("http://smurf1213.cafe24.com/recommend_mold(COBW_night).php");

            GetData18 getData18 = new GetData18();
            getData18.execute("http://smurf1213.cafe24.com/recommend_mold(COBH_night).php");

            GetData19 getData19 = new GetData19();
            getData19.execute("http://smurf1213.cafe24.com/recommend_mold(CSP_night).php");

            GetData20 getData20 = new GetData20();
            getData20.execute("http://smurf1213.cafe24.com/recommend_mold(CRR_night).php");

            GetData21 getData21 = new GetData21();
            getData21.execute("http://smurf1213.cafe24.com/recommend_mold(DREW_night).php");

            GetData22 getData22 = new GetData22();
            getData22.execute("http://smurf1213.cafe24.com/recommend_mold(DREH_night).php");

            GetData23 getData23 = new GetData23();
            getData23.execute("http://smurf1213.cafe24.com/recommend_mold(EREW_night).php");

            GetData24 getData24 = new GetData24();
            getData24.execute("http://smurf1213.cafe24.com/recommend_mold(EREH_night).php");

            GetData25 getData25 = new GetData25();
            getData25.execute("http://smurf1213.cafe24.com/recommend_mold(GCR_night).php");

            GetData26 getData26 = new GetData26();
            getData26.execute("http://smurf1213.cafe24.com/recommend_mold(GREW_night).php");
        }


        // 사용하기, 사용하지 않기 버튼
        Button useButton = findViewById(R.id.useRecommendedMoldsButton);
        useButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MoldRecommendActivity.this, R.style.MyAlertDialogStyle);
                builder.setTitle("확인");
                builder.setMessage("추천된 금형을 사용하시겠습니까?");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    updateTodayMold();
                                    finish();
                                    onBackPressed();
                                } catch (Exception e) {
                                    Log.d(TAG, "error : ", e);
                                }
                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.show();
            }
        });
        Button noUseButton = findViewById(R.id.notUseRecommendedMoldsButton);
        noUseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() { }

    @SuppressLint("StaticFieldLeak")
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString1 = result;
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
            JSONObject jsonObject = new JSONObject(mJsonString1);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            ASQ1.setText(moldCode);
            ASQ2.setText(moldNumber);
            ASQ3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }


    @SuppressLint("StaticFieldLeak")
    private class GetData2 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString2 = result;
            showResult2();
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

    private void showResult2() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString2);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            ARO1.setText(moldCode);
            ARO2.setText(moldNumber);
            ARO3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }


    @SuppressLint("StaticFieldLeak")
    private class GetData3 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString3 = result;
            showResult3();
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

    private void showResult3() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString3);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            AOBW1.setText(moldCode);
            AOBW2.setText(moldNumber);
            AOBW3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData4 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString4 = result;
            showResult4();
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

    private void showResult4() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString4);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            AOBH1.setText(moldCode);
            AOBH2.setText(moldNumber);
            AOBH3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData5 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString5 = result;
            showResult5();
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

    private void showResult5() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString5);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            ACP1.setText(moldCode);
            ACP2.setText(moldNumber);
            ACP3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData6 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);

            mJsonString6 = result;
            showResult6();
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

    private void showResult6() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString6);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            ASP1.setText(moldCode);
            ASP2.setText(moldNumber);
            ASP3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData7 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString7 = result;
            showResult7();
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

    private void showResult7() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString7);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            AREW1.setText(moldCode);
            AREW2.setText(moldNumber);
            AREW3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData8 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString8 = result;
            showResult8();
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

    private void showResult8() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString8);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            AREH1.setText(moldCode);
            AREH2.setText(moldNumber);
            AREH3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }


    @SuppressLint("StaticFieldLeak")
    private class GetData9 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString9 = result;
            showResult9();
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

    private void showResult9() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString9);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            BOBH1.setText(moldCode);
            BOBH2.setText(moldNumber);
            BOBH3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData10 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString10 = result;
            showResult10();
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

    private void showResult10() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString10);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            BREW1.setText(moldCode);
            BREW2.setText(moldNumber);
            BREW3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }


    @SuppressLint("StaticFieldLeak")
    private class GetData11 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString11 = result;
            showResult11();
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

    private void showResult11() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString11);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            BREH1.setText(moldCode);
            BREH2.setText(moldNumber);
            BREH3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData12 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString12 = result;
            showResult12();
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

    private void showResult12() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString12);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            BRO1.setText(moldCode);
            BRO2.setText(moldNumber);
            BRO3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData13 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString13 = result;
            showResult13();
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

    private void showResult13() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString13);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            BSQ1.setText(moldCode);
            BSQ2.setText(moldNumber);
            BSQ3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData14 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString14 = result;
            showResult14();
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

    private void showResult14() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString14);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            BSQD1.setText(moldCode);
            BSQD2.setText(moldNumber);
            BSQD3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData15 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString15 = result;
            showResult15();
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

    private void showResult15() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString15);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            BCR1.setText(moldCode);
            BCR2.setText(moldNumber);
            BCR3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData16 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString16 = result;
            showResult16();
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

    private void showResult16() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString16);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            CCR1.setText(moldCode);
            CCR2.setText(moldNumber);
            CCR3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData17 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString17 = result;
            showResult17();
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

    private void showResult17() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString17);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            COBW1.setText(moldCode);
            COBW2.setText(moldNumber);
            COBW3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData18 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString18 = result;
            showResult18();
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

    private void showResult18() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString18);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            COBH1.setText(moldCode);
            COBH2.setText(moldNumber);
            COBH3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData19 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString19 = result;
            showResult19();
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

    private void showResult19() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString19);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            CSP1.setText(moldCode);
            CSP2.setText(moldNumber);
            CSP3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData20 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString20 = result;
            showResult20();
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

    private void showResult20() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString20);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            CRR1.setText(moldCode);
            CRR2.setText(moldNumber);
            CRR3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData21 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString21 = result;
            showResult21();
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

    private void showResult21() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString21);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            DREW1.setText(moldCode);
            DREW2.setText(moldNumber);
            DREW3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData22 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString22 = result;
            showResult22();
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

    private void showResult22() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString22);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            DREH1.setText(moldCode);
            DREH2.setText(moldNumber);
            DREH3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData23 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString23 = result;
            showResult23();
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

    private void showResult23() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString23);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            EREW1.setText(moldCode);
            EREW2.setText(moldNumber);
            EREW3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData24 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString24 = result;
            showResult24();
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

    private void showResult24() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString24);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            EREH1.setText(moldCode);
            EREH2.setText(moldNumber);
            EREH3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData25 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString25 = result;
            showResult25();
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

    private void showResult25() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString25);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            GCR1.setText(moldCode);
            GCR2.setText(moldNumber);
            GCR3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData26 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //mTemporaryResultText.setText(result);
            mJsonString26 = result;
            showResult26();
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

    private void showResult26() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString26);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.getString(TAG_MOLD_CODE);
            String moldNumber = item.getString(TAG_MOLD_NUMBER);
            String hittingTimes = item.getString(TAG_HITTING_ACCUMULATE_TIME);

            double amount = Double.parseDouble(hittingTimes);
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatted = formatter.format(amount);

            GREW1.setText(moldCode);
            GREW2.setText(moldNumber);
            GREW3.setText(formatted);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }
    }

    private void updateTodayMold() {

        try {
            JSONObject jsonObjectASQ = new JSONObject(mJsonString1);
            JSONArray jsonArrayASQ = jsonObjectASQ.getJSONArray(TAG_JSON);
            JSONObject itemASQ = jsonArrayASQ.getJSONObject(0);
            String ASQNumber = itemASQ.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectARO = new JSONObject(mJsonString2);
            JSONArray jsonArrayARO = jsonObjectARO.getJSONArray(TAG_JSON);
            JSONObject itemARO = jsonArrayARO.getJSONObject(0);
            String ARONumber = itemARO.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectAOBW = new JSONObject(mJsonString3);
            JSONArray jsonArrayAOBW = jsonObjectAOBW.getJSONArray(TAG_JSON);
            JSONObject itemAOBW = jsonArrayAOBW.getJSONObject(0);
            String AOBWNumber = itemAOBW.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectAOBH = new JSONObject(mJsonString4);
            JSONArray jsonArrayAOBH = jsonObjectAOBH.getJSONArray(TAG_JSON);
            JSONObject itemAOBH = jsonArrayAOBH.getJSONObject(0);
            String AOBHNumber = itemAOBH.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectACP = new JSONObject(mJsonString5);
            JSONArray jsonArrayACP = jsonObjectACP.getJSONArray(TAG_JSON);
            JSONObject itemACP = jsonArrayACP.getJSONObject(0);
            String ACPNumber = itemACP.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectASP = new JSONObject(mJsonString6);
            JSONArray jsonArrayASP = jsonObjectASP.getJSONArray(TAG_JSON);
            JSONObject itemASP = jsonArrayASP.getJSONObject(0);
            String ASPNumber = itemASP.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectAREW = new JSONObject(mJsonString7);
            JSONArray jsonArrayAREW = jsonObjectAREW.getJSONArray(TAG_JSON);
            JSONObject itemAREW = jsonArrayAREW.getJSONObject(0);
            String AREWNumber = itemAREW.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectAREH = new JSONObject(mJsonString8);
            JSONArray jsonArrayAREH = jsonObjectAREH.getJSONArray(TAG_JSON);
            JSONObject itemAREH = jsonArrayAREH.getJSONObject(0);
            String AREHNumber = itemAREH.getString(TAG_MOLD_NUMBER);


            JSONObject jsonObjectBOBH = new JSONObject(mJsonString9);
            JSONArray jsonArrayBOBH = jsonObjectBOBH.getJSONArray(TAG_JSON);
            JSONObject itemBOBH = jsonArrayBOBH.getJSONObject(0);
            String BOBHNumber = itemBOBH.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectBREW = new JSONObject(mJsonString10);
            JSONArray jsonArrayBREW = jsonObjectBREW.getJSONArray(TAG_JSON);
            JSONObject itemBREW = jsonArrayBREW.getJSONObject(0);
            String BREWNumber = itemBREW.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectBREH = new JSONObject(mJsonString11);
            JSONArray jsonArrayBREH = jsonObjectBREH.getJSONArray(TAG_JSON);
            JSONObject itemBREH = jsonArrayBREH.getJSONObject(0);
            String BREHNumber = itemBREH.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectBRO = new JSONObject(mJsonString12);
            JSONArray jsonArrayBRO = jsonObjectBRO.getJSONArray(TAG_JSON);
            JSONObject itemBRO = jsonArrayBRO.getJSONObject(0);
            String BRONumber = itemBRO.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectBSQ = new JSONObject(mJsonString13);
            JSONArray jsonArrayBSQ = jsonObjectBSQ.getJSONArray(TAG_JSON);
            JSONObject itemBSQ = jsonArrayBSQ.getJSONObject(0);
            String BSQNumber = itemBSQ.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectBSQD = new JSONObject(mJsonString14);
            JSONArray jsonArrayBSQD = jsonObjectBSQD.getJSONArray(TAG_JSON);
            JSONObject itemBSQD = jsonArrayBSQD.getJSONObject(0);
            String BSQDNumber = itemBSQD.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectBCR = new JSONObject(mJsonString15);
            JSONArray jsonArrayBCR = jsonObjectBCR.getJSONArray(TAG_JSON);
            JSONObject itemBCR = jsonArrayBCR.getJSONObject(0);
            String BCRNumber = itemBCR.getString(TAG_MOLD_NUMBER);


            JSONObject jsonObjectCCR = new JSONObject(mJsonString16);
            JSONArray jsonArrayCCR = jsonObjectCCR.getJSONArray(TAG_JSON);
            JSONObject itemCCR = jsonArrayCCR.getJSONObject(0);
            String CCRNumber = itemCCR.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectCOBW = new JSONObject(mJsonString17);
            JSONArray jsonArrayCOBW = jsonObjectCOBW.getJSONArray(TAG_JSON);
            JSONObject itemCOBW = jsonArrayCOBW.getJSONObject(0);
            String COBWNumber = itemCOBW.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectCOBH = new JSONObject(mJsonString18);
            JSONArray jsonArrayCOBH = jsonObjectCOBH.getJSONArray(TAG_JSON);
            JSONObject itemCOBH = jsonArrayCOBH.getJSONObject(0);
            String COBHNumber = itemCOBH.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectCSP = new JSONObject(mJsonString19);
            JSONArray jsonArrayCSP = jsonObjectCSP.getJSONArray(TAG_JSON);
            JSONObject itemCSP = jsonArrayCSP.getJSONObject(0);
            String CSPNumber = itemCSP.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectCRR = new JSONObject(mJsonString20);
            JSONArray jsonArrayCRR = jsonObjectCRR.getJSONArray(TAG_JSON);
            JSONObject itemCRR = jsonArrayCRR.getJSONObject(0);
            String CRRNumber = itemCRR.getString(TAG_MOLD_NUMBER);


            JSONObject jsonObjectDREW = new JSONObject(mJsonString21);
            JSONArray jsonArrayDREW = jsonObjectDREW.getJSONArray(TAG_JSON);
            JSONObject itemDREW = jsonArrayDREW.getJSONObject(0);
            String DREWNumber = itemDREW.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectDREH = new JSONObject(mJsonString22);
            JSONArray jsonArrayDREH = jsonObjectDREH.getJSONArray(TAG_JSON);
            JSONObject itemDREH = jsonArrayDREH.getJSONObject(0);
            String DREHNumber = itemDREH.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectEREW = new JSONObject(mJsonString23);
            JSONArray jsonArrayEREW = jsonObjectEREW.getJSONArray(TAG_JSON);
            JSONObject itemEREW = jsonArrayEREW.getJSONObject(0);
            String EREWNumber = itemEREW.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectEREH = new JSONObject(mJsonString24);
            JSONArray jsonArrayEREH = jsonObjectEREH.getJSONArray(TAG_JSON);
            JSONObject itemEREH = jsonArrayEREH.getJSONObject(0);
            String EREHNumber = itemEREH.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectGCR = new JSONObject(mJsonString25);
            JSONArray jsonArrayGCR = jsonObjectGCR.getJSONArray(TAG_JSON);
            JSONObject itemGCR = jsonArrayGCR.getJSONObject(0);
            String GCRNumber = itemGCR.getString(TAG_MOLD_NUMBER);

            JSONObject jsonObjectGREW = new JSONObject(mJsonString26);
            JSONArray jsonArrayGREW = jsonObjectGREW.getJSONArray(TAG_JSON);
            JSONObject itemGREW = jsonArrayGREW.getJSONObject(0);
            String GREWNumber = itemGREW.getString(TAG_MOLD_NUMBER);

            InsertData insertData = new InsertData();
            insertData.execute(ASQNumber,ARONumber,AOBWNumber,AOBHNumber,ACPNumber,ASPNumber,AREWNumber,AREHNumber,
                    BOBHNumber,BREWNumber,BREHNumber,BRONumber,BSQNumber,BSQDNumber,BCRNumber,CCRNumber,COBWNumber,COBHNumber,
                    CSPNumber,CRRNumber,DREWNumber,DREHNumber,EREWNumber,EREHNumber,GCRNumber,GREWNumber);

        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);
        }

    }

    @SuppressLint("StaticFieldLeak")
    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MoldRecommendActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {


            String asq = params[0];
            String aro = params[1];
            String aobw = params[2];
            String aobh = params[3];
            String a_cp = params[4];
            String a_sp = params[5];
            String a_re_w = params[6];
            String a_re_h = params[7];
            String b_ob_h = params[8];
            String b_re_w = params[9];
            String b_re_h = params[10];
            String b_ro = params[11];
            String b_sq = params[12];
            String b_sq_d = params[13];
            String b_cr = params[14];
            String c_cr = params[15];
            String c_ob_w = params[16];
            String c_ob_h = params[17];
            String c_sp = params[18];
            String c_rr = params[19];
            String d_re_w = params[20];
            String d_re_h = params[21];
            String e_re_w = params[22];
            String e_re_h = params[23];
            String g_cr = params[24];
            String g_re_w = params[25];


            String serverURL = "http://smurf1213.cafe24.com/update_today_mold.php";

            String postParameters = "A_SQ=" + asq
                    + "&A_RO=" + aro
                    + "&A_OB_W=" + aobw
                    + "&A_OB_H=" + aobh
                    + "&A_CP=" + a_cp
                    + "&A_SP=" + a_sp
                    + "&A_RE_W=" + a_re_w
                    + "&A_RE_H=" + a_re_h
                    + "&B_OB_H=" + b_ob_h
                    + "&B_RE_W=" + b_re_w
                    + "&B_RE_H=" + b_re_h
                    + "&B_RO=" + b_ro
                    + "&B_SQ=" + b_sq
                    + "&B_SQ_D=" + b_sq_d
                    + "&B_CR=" + b_cr
                    + "&C_CR=" + c_cr
                    + "&C_OB_W=" + c_ob_w
                    + "&C_OB_H=" + c_ob_h
                    + "&C_SP=" + c_sp
                    + "&C_RR=" + c_rr
                    + "&D_RE_W=" + d_re_w
                    + "&D_RE_H=" + d_re_h
                    + "&E_RE_W=" + e_re_w
                    + "&E_RE_H=" + e_re_h
                    + "&G_CR=" + g_cr
                    + "&G_RE_W=" + g_re_w;


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
