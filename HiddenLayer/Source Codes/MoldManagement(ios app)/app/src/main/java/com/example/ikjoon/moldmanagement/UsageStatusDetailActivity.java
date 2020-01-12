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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

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
import java.util.ArrayList;
import java.util.HashMap;

public class UsageStatusDetailActivity extends Activity {

    private static String TAG = "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";

    private static final String TAG_JSON = "mold";
    private static final String TAG_NUMBER = "MOLD_NUMBER";
    private static final String TAG_ACCU = "HITTING_ACCUMULATE_TIME";

    //private TextView theResult;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView moldDetailListView;
    String mJsonString;

    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_status_detail);

        // 뒤로 버튼
        Button backBtn = findViewById(R.id.backToUsageStatusFromDetail);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // 넘겨받은 금형 코드로 데이터 출력
        moldDetailListView = findViewById(R.id.moldDetailListView);

        // 데이터 넘겨받는 부분
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        code = extras.getString("code");
        final String firstMoldID = extras.getString("firstMoldID");
        final String secondMoldID = extras.getString("secondMoldID");
        final String thirdMoldID = extras.getString("thirdMoldID");

        // 페이지 제목 설정
        TextView title = findViewById(R.id.titleMoldDetailText);
        String titleContent = code + " 금형 세부 사항";
        title.setText(titleContent);



        final UpdateData updateData = new UpdateData();
        final AlertDialog.Builder builder = new AlertDialog.Builder(UsageStatusDetailActivity.this, R.style.MyAlertDialogStyle);
        Button.OnClickListener listener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.changeFirstMoldButton:
                        builder.setTitle("금형을 교체하시겠습니까?");
                        builder.setMessage("해당 금형의 히팅 횟수가 0으로 초기화 됩니다");
                        builder.setPositiveButton("예",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            updateData.execute(firstMoldID);
                                            onResume();
                                            Toast.makeText(getApplicationContext(), "해당 금형의 히팅 횟수가 0으로 초기화 되었습니다", Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            Log.d(TAG, "error : ", e);
                                            Toast.makeText(getApplicationContext(), "반영되지 않았습니다. 다시 시도 해주세요.", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                        builder.setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        builder.show();
                        break;
                    case R.id.changeSecondMoldButton:
                        builder.setTitle("금형을 교체하시겠습니까?");
                        builder.setMessage("해당 금형의 히팅 횟수가 0으로 초기화 됩니다");
                        builder.setPositiveButton("예",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            updateData.execute(secondMoldID);
                                            onResume();
                                            Toast.makeText(getApplicationContext(), "해당 금형의 히팅 횟수가 0으로 초기화 되었습니다", Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            Log.d(TAG, "error : ", e);
                                            Toast.makeText(getApplicationContext(), "반영되지 않았습니다. 다시 시도 해주세요.", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                        builder.setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        builder.show();
                        break;
                    case R.id.changeThirdMoldButton:

                        builder.setTitle("금형을 교체하시겠습니까?");
                        builder.setMessage("해당 금형의 히팅 횟수가 0으로 초기화 됩니다");
                        builder.setPositiveButton("예",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            updateData.execute(thirdMoldID);
                                            onResume();
                                            Toast.makeText(getApplicationContext(), "해당 금형의 히팅 횟수가 0으로 초기화 되었습니다", Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            Log.d(TAG, "error : ", e);
                                            Toast.makeText(getApplicationContext(), "반영되지 않았습니다. 다시 시도 해주세요.", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                        builder.setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        builder.show();
                        break;
                }
            }
        };

        Button firstMoldchangeButton = findViewById(R.id.changeFirstMoldButton);
        firstMoldchangeButton.setOnClickListener(listener);
        Button secondMoldchangeButton = findViewById(R.id.changeSecondMoldButton);
        secondMoldchangeButton.setOnClickListener(listener);
        Button thirdMoldchangeButton = findViewById(R.id.changeThirdMoldButton);
        thirdMoldchangeButton.setOnClickListener(listener);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // 리스트뷰, 그래프 설정
        GetData task = new GetData();
        task.execute(code);
        mArrayList = new ArrayList<>();
    }

    @SuppressLint("StaticFieldLeak")
    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(UsageStatusDetailActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //theResult.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null) {

                //theResult.setText(errorString);
            } else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String mold_code = params[0];

            String serverURL = "http://smurf1213.cafe24.com/mold_select_query.php";
            String postParameters = "MOLD_CODE=" + mold_code;

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
        try {

            ArrayList<String> arrayList = new ArrayList<>();

            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);
                String number = item.getString(TAG_NUMBER);
                String accu = item.getString(TAG_ACCU);

                double amount = Double.parseDouble(accu);
                DecimalFormat formatter = new DecimalFormat("#,###");
                String formatted = formatter.format(amount);

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_NUMBER, number);
                hashMap.put(TAG_ACCU, formatted);

                mArrayList.add(hashMap);

                // 막대그래프에 넣을 데이터 얻는 부분
                String data = item.optString(TAG_ACCU, "No Value");
                arrayList.add(data);
            }

            ListAdapter adapter = new SimpleAdapter(
                    UsageStatusDetailActivity.this, mArrayList, R.layout.mold_list_item,
                    new String[]{TAG_NUMBER, TAG_ACCU},
                    new int[]{R.id.moldDetailNumberList, R.id.hittingAccumulateDetailList}
            );

            moldDetailListView.setAdapter(adapter);

            String data1 = arrayList.get(0);
            String data2 = arrayList.get(1);
            String data3 = arrayList.get(2);
            makeChart(data1, data2, data3);


        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

    private void makeChart(String x, String y, String z) {
        // 막대그래프 구현
        BarChart barChart = findViewById(R.id.barChart);

        // 변수 가져오기
        float x1 = Float.parseFloat(x);
        float x2 = Float.parseFloat(y);
        float x3 = Float.parseFloat(z);

        //  데이터셋 설정
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(x1, 0));
        entries.add(new BarEntry(x2, 1));
        entries.add(new BarEntry(x3, 2));

        BarDataSet bardataset = new BarDataSet(entries, "각 금형");

        //  x축 레이블
        ArrayList<String> labels = new ArrayList<>();
        labels.add(code + " 금형 1번");
        labels.add(code + " 금형 2번");
        labels.add(code + " 금형 3번");

        //  데이터셋을 그래프에 할당
        BarData data = new BarData(labels, bardataset);
        barChart.setData(data);

        //  그래프 색, 이름 설정
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setDescription(code + " 금형 사용 현황");
    }

    // 디비 업데이트 함수
    @SuppressLint("StaticFieldLeak")
    class UpdateData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(UsageStatusDetailActivity.this,
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

            String id = params[0];
            
            String serverURL = "http://smurf1213.cafe24.com/mold_update.php";

            String postParameters = "ID=" + id;


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
