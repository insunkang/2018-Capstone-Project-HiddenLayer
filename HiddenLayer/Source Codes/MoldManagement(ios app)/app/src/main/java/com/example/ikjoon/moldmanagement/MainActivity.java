package com.example.ikjoon.moldmanagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.viewbadger.BadgeView;

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


public class MainActivity extends Activity {

    private static String TAG = "결과 나오는 부분 : ";

    private static final String TAG_JSON = "mold";

    ArrayList<String> mArrayList;
    ListView needToChangeMoldListView;
    ImageButton warningAlertImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        warningAlertImageButton = findViewById(R.id.warningAlertImageButton);


        // 생산량 입력 버튼 클릭 시 이벤트
        Button inputProductionQuantityButton = findViewById(R.id.inputProductionQuantityButton);
        inputProductionQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputProductionInfoActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


        // 설계도 버튼 클릭 시 이벤트
        Button designButton = findViewById(R.id.designButton);
        designButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DesignListActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


        // 생산히스토리 버튼 클릭 시 이벤트
        Button productionHistoryButton = findViewById(R.id.productionHistoryButton);
        productionHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductionHistoryActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


        // 금형 사용 현황 버튼 클릭 시 이벤트
        Button usageStatusButton = findViewById(R.id.usageStatusButton);
        usageStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsageStatusActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


        // 금형 추천 버튼
        Button moldRecommendButton = findViewById(R.id.moldRecommendButton);
        moldRecommendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRecommendDialog();
            }
        });

        // 금형 교체 경고 등 버튼
        Button brokenMoldButton = findViewById(R.id.brokenMoldButton);
        brokenMoldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoldBreakageHistoryActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        // 현재 사용 금형 버튼
        TextView nowMoldButton = findViewById(R.id.nowMoldButton);
        nowMoldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowNowMoldActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }


    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        mArrayList = new ArrayList<>();
        GetData getData = new GetData();
        getData.execute("http://smurf1213.cafe24.com/mold_have_to_change.php");

    }

    // 뒤로 버튼 먹통
    @Override
    public void onBackPressed() {
    }


    private void ShowRecommendDialog() {
        LayoutInflater dialog = LayoutInflater.from(this);
        @SuppressLint("InflateParams") final View dialogLayout = dialog.inflate(R.layout.dialog_day_or_night, null);
        final Dialog myDialog = new Dialog(this);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = myDialog.getWindow();
        assert window != null;
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);

        myDialog.setContentView(dialogLayout);
        myDialog.show();

        Button btn_day = dialogLayout.findViewById(R.id.dialogDayButton);
        Button btn_night = dialogLayout.findViewById(R.id.dialogNightButton);


        btn_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoldRecommendActivity.class);
                intent.putExtra("dayOrNight", "day");
                intent.putExtra("title", "주간");
                startActivityForResult(intent, 100);
                myDialog.dismiss();
            }
        });

        btn_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoldRecommendActivity.class);
                intent.putExtra("dayOrNight", "night");
                intent.putExtra("title", "야간");
                startActivityForResult(intent, 100);
                myDialog.dismiss();
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

            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            //resultText.setText(result);

            showResult(result);
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


    private void showResult(String string) {
        try {

            JSONObject jsonObject = new JSONObject(string);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            JSONObject item = jsonArray.getJSONObject(0);

            String moldCode = item.optString("MOLD_CODE", "No Value");
            mArrayList.add(moldCode);

            warningAlertImageButton.setImageResource(R.drawable.image_notification_active);
            warningAlertImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MoldAlertActivity.class);
                    MainActivity.this.startActivity(intent);
                }
            });


        } catch (JSONException e) {

            warningAlertImageButton.setImageResource(R.drawable.image_notification);
            warningAlertImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "아직 교체해야 할 금형이 없습니다", Toast.LENGTH_LONG).show();
                }
            });

            Log.d(TAG, "showResult : ", e);
        }
    }
}
