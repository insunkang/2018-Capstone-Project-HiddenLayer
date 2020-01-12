package com.example.ikjoon.moldmanagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
import java.util.Calendar;

public class InputProductionInfoActivity extends Activity {

    TextView dateInputButton, timeInputButton;
    Spinner spinner;
    EditText productAmountEditText;

    String mJsonString;
    String productDate, productTime;

    private static String TAG = "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";

    private static final String TAG_JSON = "design";
    private static final String TAG_DESIGN_CODE = "DESIGN_CODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_production_info);

        spinner = findViewById(R.id.designSpinner);
        dateInputButton = findViewById(R.id.dateInputButton);
        timeInputButton = findViewById(R.id.timeInputButton);


        // 스피너에 나타낼 설계도 정보 불러오기
        InputProductionInfoActivity.GetData task = new InputProductionInfoActivity.GetData();
        task.execute("http://smurf1213.cafe24.com/select_design.php");

        // 뒤로가기 버튼 클릭 시 이벤트(메인화면으로 돌아감)
        Button backToMain1 = findViewById(R.id.backToMain1);
        backToMain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // 빈 화면 클릭 시 키보드 숨김
        final EditText productAmountEditText = findViewById(R.id.productAmountEditText);
        RelativeLayout inputProductionInfoRL = findViewById(R.id.inputProductionInfoRL);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputProductionInfoRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert imm != null;
                imm.hideSoftInputFromWindow(productAmountEditText.getWindowToken(), 0);
            }
        });


        // 날짜 및 시간 설정
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateInputButton.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
                        productDate = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
                    }
                }, mYear, mMonth, mDay);
        dateInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        final int mHour = c.get(Calendar.HOUR_OF_DAY);
        final int mMin = c.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeInputButton.setText(hourOfDay + "시 " + minute + "분");
                        productTime = hourOfDay + ":" + minute + ":00";
                    }
                }, mHour, mMin, true);
        timeInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });


        // 등록하기 버튼
        Button enterProductInformation = findViewById(R.id.enterProductInformation);
        enterProductInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String spinnerItem = spinner.getSelectedItem().toString();
                final String productAmount = productAmountEditText.getText().toString();
                String date = dateInputButton.getText().toString();
                String time = timeInputButton.getText().toString();

                if (productAmount.equals("") || date.equals("날짜") || time.equals("시간")) {
                    Toast.makeText(getApplicationContext(), "등록 할 수 없습니다. 생산량, 날짜, 시간을 제대로 입력해주세요", Toast.LENGTH_LONG).show();
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(InputProductionInfoActivity.this, R.style.MyAlertDialogStyle);
                    builder.setTitle("확인");
                    builder.setMessage("생산 정보를 입력하시겠습니까?");
                    builder.setPositiveButton("예",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    // 라디오 버튼 변수 설정
                                    final RadioGroup dayOrNightRadioGroup = findViewById(R.id.dayOrNightRadioGroup);
                                    int checkedId = dayOrNightRadioGroup.getCheckedRadioButtonId();
                                    RadioButton typeButton = findViewById(checkedId);
                                    String dayOrNight = typeButton.getText().toString();

                                    try {
                                        InsertData task = new InsertData();
                                        task.execute(productDate, productTime, dayOrNight, spinnerItem, productAmount);

                                        Intent intent = new Intent(InputProductionInfoActivity.this, ShowProductInfoActivity.class);
                                        intent.putExtra("designCode", spinnerItem);
                                        intent.putExtra("productAmount", productAmount);
                                        intent.putExtra("dayOrNight", dayOrNight);
                                        startActivityForResult(intent, 100);

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

            progressDialog = ProgressDialog.show(InputProductionInfoActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);

            if (result == null) {

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

                return null;
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
                String designCode = item.optString(TAG_DESIGN_CODE, "No Value");
                arrayList.add(designCode);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    InputProductionInfoActivity.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    arrayList);

            if (spinner != null) {
                spinner.setAdapter(adapter);
            }

        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);
        }
    }


    // 데이터 입력 함수
    @SuppressLint("StaticFieldLeak")
    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(InputProductionInfoActivity.this,
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

            String productDate = params[0];
            String productTime = params[1];
            String dayOrNight = params[2];
            String spinnerItem = params[3];
            String productAmount = params[4];

            String serverURL = "http://smurf1213.cafe24.com/insert_product.php";

            String postParameters = "PRODUCT_DATE=" + productDate
                    + "&PRODUCT_TIME=" + productTime
                    + "&DAY_NIGHT=" + dayOrNight
                    + "&DESIGN_CODE=" + spinnerItem
                    + "&PRODUCT_OUTPUT=" + productAmount;


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

