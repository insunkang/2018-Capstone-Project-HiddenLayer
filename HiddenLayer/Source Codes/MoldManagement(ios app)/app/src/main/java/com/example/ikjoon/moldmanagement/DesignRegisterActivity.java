package com.example.ikjoon.moldmanagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DesignRegisterActivity extends Activity {

    private TextView mTextViewResult;

    private EditText designCodeEditText;
    private EditText a_sq_HittingTimeEditText;
    private EditText a_ro_HittingTimeEditText;
    private EditText a_ob_w_HittingTimeEditText;
    private EditText a_ob_h_HittingTimeEditText;
    private EditText a_cp_HittingTimeEditText;
    private EditText a_sp_HittingTimeEditText;
    private EditText a_re_w_HittingTimeEditText;
    private EditText a_re_h_HittingTimeEditText;
    private EditText b_ob_h_HittingTimeEditText;
    private EditText b_re_w_HittingTimeEditText;
    private EditText b_re_h_HittingTimeEditText;
    private EditText b_ro_HittingTimeEditText;
    private EditText b_sq_HittingTimeEditText;
    private EditText b_sq_d_HittingTimeEditText;
    private EditText b_cr_HittingTimeEditText;
    private EditText c_cr_HittingTimeEditText;
    private EditText c_ob_w_HittingTimeEditText;
    private EditText c_ob_h_HittingTimeEditText;
    private EditText c_sp_HittingTimeEditText;
    private EditText c_rr_HittingTimeEditText;
    private EditText d_re_w_HittingTimeEditText;
    private EditText d_re_h_HittingTimeEditText;
    private EditText e_re_w_HittingTimeEditText;
    private EditText e_re_h_HittingTimeEditText;
    private EditText g_cr_HittingTimeEditText;
    private EditText g_re_w_HittingTimeEditText;
    private EditText productTimeEditText;
    private static String TAG = "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_register);


        //뒤로 버튼 클릭 시 이벤트
        Button backToDesignListButton = findViewById(R.id.backToDesignListButton);
        backToDesignListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //라디오 버튼 변수 선언
        final RadioGroup metalSheetTypeRadioGroup = findViewById(R.id.metalSheetTypeRadioGroup);

        //설계도 정보 입력 변수 선언
        designCodeEditText = findViewById(R.id.designCodeEditText);
        a_sq_HittingTimeEditText = findViewById(R.id.a_sq_HittingTimeEditText);
        a_ro_HittingTimeEditText = findViewById(R.id.a_ro_HittingTimeEditText);
        a_ob_w_HittingTimeEditText = findViewById(R.id.a_ob_w_HittingTimeEditText);
        a_ob_h_HittingTimeEditText = findViewById(R.id.a_ob_h_HittingTimeEditText);
        a_cp_HittingTimeEditText = findViewById(R.id.a_cp_HittingTimeEditText);
        a_sp_HittingTimeEditText = findViewById(R.id.a_sp_HittingTimeEditText);
        a_re_w_HittingTimeEditText = findViewById(R.id.a_re_w_HittingTimeEditText);
        a_re_h_HittingTimeEditText = findViewById(R.id.a_re_h_HittingTimeEditText);
        b_ob_h_HittingTimeEditText = findViewById(R.id.b_ob_h_HittingTimeEditText);
        b_re_w_HittingTimeEditText = findViewById(R.id.b_re_w_HittingTimeEditText);
        b_re_h_HittingTimeEditText = findViewById(R.id.b_re_h_HittingTimeEditText);
        b_ro_HittingTimeEditText = findViewById(R.id.b_ro_HittingTimeEditText);
        b_sq_d_HittingTimeEditText = findViewById(R.id.b_sq_d_HittingTimeEditText);
        b_sq_HittingTimeEditText = findViewById(R.id.b_sq_HittingTimeEditText);
        b_cr_HittingTimeEditText = findViewById(R.id.b_cr_HittingTimeEditText);
        c_cr_HittingTimeEditText = findViewById(R.id.c_cr_HittingTimeEditText);
        c_ob_w_HittingTimeEditText = findViewById(R.id.c_ob_w_HittingTimeEditText);
        c_ob_h_HittingTimeEditText = findViewById(R.id.c_ob_h_HittingTimeEditText);
        c_sp_HittingTimeEditText = findViewById(R.id.c_sp_HittingTimeEditText);
        c_rr_HittingTimeEditText = findViewById(R.id.c_rr_HittingTimeEditText);
        d_re_w_HittingTimeEditText = findViewById(R.id.d_re_w_HittingTimeEditText);
        d_re_h_HittingTimeEditText = findViewById(R.id.d_re_h_HittingTimeEditText);
        e_re_w_HittingTimeEditText = findViewById(R.id.e_re_w_HittingTimeEditText);
        e_re_h_HittingTimeEditText = findViewById(R.id.e_re_h_HittingTimeEditText);
        g_cr_HittingTimeEditText = findViewById(R.id.g_cr_HittingTimeEditText);
        g_re_w_HittingTimeEditText = findViewById(R.id.g_re_w_HittingTimeEditText);
        productTimeEditText = findViewById(R.id.productTimeEditText);



        // 등록하기 버튼 클릭 시 이벤트(DESIGN_TABLE에 이 설계도 정보를 저장)
        Button designRegisterFinalButton = findViewById(R.id.designRegisterFinalButton);
        designRegisterFinalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //설계도 코드, 사용 금형, 금형 개수
                int isZero = 0;

                final String designcode = designCodeEditText.getText().toString();
                final String productTime = productTimeEditText.getText().toString();
                final String a_sqHittingTime = a_sq_HittingTimeEditText.getText().toString();
                final String a_roHittingTime = a_ro_HittingTimeEditText.getText().toString();
                final String a_ob_wHittingTime = a_ob_w_HittingTimeEditText.getText().toString();
                final String a_ob_hHittingTime = a_ob_h_HittingTimeEditText.getText().toString();
                final String a_cpHittingTime = a_cp_HittingTimeEditText.getText().toString();
                final String a_spHittingTime = a_sp_HittingTimeEditText.getText().toString();
                final String a_re_wHittingTime = a_re_w_HittingTimeEditText.getText().toString();
                final String a_re_hHittingTime = a_re_h_HittingTimeEditText.getText().toString();
                final String b_ob_hHittingTime = b_ob_h_HittingTimeEditText.getText().toString();
                final String b_re_wHittingTime = b_re_w_HittingTimeEditText.getText().toString();
                final String b_re_hHittingTime = b_re_h_HittingTimeEditText.getText().toString();
                final String b_roHittingTime = b_ro_HittingTimeEditText.getText().toString();
                final String b_sqHittingTime = b_sq_HittingTimeEditText.getText().toString();
                final String b_sq_dHittingTime = b_sq_d_HittingTimeEditText.getText().toString();
                final String b_crHittingTime = b_cr_HittingTimeEditText.getText().toString();
                final String c_crHittingTime = c_cr_HittingTimeEditText.getText().toString();
                final String c_ob_wHittingTime = c_ob_w_HittingTimeEditText.getText().toString();
                final String c_ob_hHittingTime = c_ob_h_HittingTimeEditText.getText().toString();
                final String c_spHittingTime = c_sp_HittingTimeEditText.getText().toString();
                final String c_rrHittingTime = c_rr_HittingTimeEditText.getText().toString();
                final String d_re_wHittingTime = d_re_w_HittingTimeEditText.getText().toString();
                final String d_re_hHittingTime = d_re_h_HittingTimeEditText.getText().toString();
                final String e_re_wHittingTime = e_re_w_HittingTimeEditText.getText().toString();
                final String e_re_hHittingTime = e_re_h_HittingTimeEditText.getText().toString();
                final String g_crHittingTime = g_cr_HittingTimeEditText.getText().toString();
                final String g_re_wHittingTime = g_re_w_HittingTimeEditText.getText().toString();
                final String totalHittingTimes = String.valueOf(Integer.parseInt(a_sqHittingTime) + Integer.parseInt(a_roHittingTime) + Integer.parseInt(a_ob_wHittingTime) + Integer.parseInt(a_ob_hHittingTime)
                        + Integer.parseInt(a_cpHittingTime) + Integer.parseInt(a_spHittingTime) + Integer.parseInt(a_re_wHittingTime) + Integer.parseInt(a_re_hHittingTime)
                        + Integer.parseInt(b_ob_hHittingTime) + Integer.parseInt(b_re_wHittingTime) + Integer.parseInt(b_re_hHittingTime) + Integer.parseInt(b_roHittingTime) + Integer.parseInt(b_sq_dHittingTime)
                        + Integer.parseInt(b_sqHittingTime) + Integer.parseInt(b_crHittingTime) + Integer.parseInt(c_crHittingTime) + Integer.parseInt(c_ob_wHittingTime) + Integer.parseInt(c_ob_hHittingTime)
                        + Integer.parseInt(c_spHittingTime) + Integer.parseInt(c_rrHittingTime) + Integer.parseInt(d_re_wHittingTime) + Integer.parseInt(d_re_hHittingTime) + Integer.parseInt(e_re_wHittingTime)
                        + Integer.parseInt(e_re_hHittingTime) + Integer.parseInt(g_crHittingTime) + Integer.parseInt(g_re_wHittingTime));

                // 사용하는 금형 개수 구하는 부분
                if (Integer.parseInt(a_sqHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(a_roHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(a_ob_wHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(a_ob_hHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(a_cpHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(a_spHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(a_re_wHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(a_re_hHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(b_ob_hHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(b_re_wHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(b_re_hHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(b_roHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(b_sqHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(b_sq_dHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(b_crHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(c_crHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(c_ob_wHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(c_ob_hHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(c_spHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(c_rrHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(d_re_wHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(d_re_hHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(e_re_wHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(e_re_hHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(g_crHittingTime) != 0)
                    isZero++;
                if (Integer.parseInt(g_re_wHittingTime) != 0)
                    isZero++;

                final String totalmoldnumber = String.valueOf(isZero);

                //선택된 라디오 버튼
                int checkedId = metalSheetTypeRadioGroup.getCheckedRadioButtonId();
                RadioButton typeButton = findViewById(checkedId);
                final String metalSheetType = typeButton.getText().toString();

                if (designcode.equals("") || productTime.equals("") || a_sqHittingTime.equals("") || a_roHittingTime.equals("") || a_ob_wHittingTime.equals("") || a_ob_hHittingTime.equals("")
                        || a_cpHittingTime.equals("") || a_spHittingTime.equals("") || a_re_wHittingTime.equals("") || a_re_hHittingTime.equals("") || b_ob_hHittingTime.equals("")
                        || b_re_wHittingTime.equals("") || b_re_hHittingTime.equals("") || b_roHittingTime.equals("") || b_sqHittingTime.equals("") || b_sq_dHittingTime.equals("")
                        || b_crHittingTime.equals("") || c_crHittingTime.equals("") || c_ob_wHittingTime.equals("") || c_ob_hHittingTime.equals("") || c_spHittingTime.equals("")
                        || c_rrHittingTime.equals("") || d_re_hHittingTime.equals("") || d_re_hHittingTime.equals("") || e_re_wHittingTime.equals("") || e_re_hHittingTime.equals("")
                        || g_crHittingTime.equals("") || g_re_wHittingTime.equals("")) {

                    Toast.makeText(getApplicationContext(), "등록할 수 없습니다. 빈칸이 있는지 확인해주세요.", Toast.LENGTH_LONG).show();

                } else {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(DesignRegisterActivity.this, R.style.MyAlertDialogStyle);
                    builder.setTitle("확인");
                    builder.setMessage("새로운 설계도로 등록하시겠습니까?");
                    builder.setPositiveButton("예",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        InsertData task = new InsertData();
                                        task.execute(designcode, metalSheetType, productTime, a_sqHittingTime, a_roHittingTime, a_ob_wHittingTime, a_ob_hHittingTime,
                                                a_cpHittingTime, a_spHittingTime, a_re_wHittingTime, a_re_hHittingTime, b_ob_hHittingTime, b_re_wHittingTime, b_re_hHittingTime,
                                                b_roHittingTime, b_sqHittingTime, b_sq_dHittingTime, b_crHittingTime, c_crHittingTime, c_ob_wHittingTime, c_ob_hHittingTime,
                                                c_spHittingTime, c_rrHittingTime, d_re_wHittingTime, d_re_hHittingTime, e_re_wHittingTime, e_re_hHittingTime, g_crHittingTime,
                                                g_re_wHittingTime, totalHittingTimes, totalmoldnumber);
                                        finish();
                                    } catch (Exception e) {

                                        Log.d(TAG, "error : ", e);

                                        Toast.makeText(getApplicationContext(), "등록할 수 없습니다. 다시 시도 해주세요.", Toast.LENGTH_LONG).show();

                                        // 입력 오류시 다시 입력하도록 리셋
                                        designCodeEditText.setText("");
                                        a_sq_HittingTimeEditText.setText("0");
                                        a_ro_HittingTimeEditText.setText("0");
                                        a_ob_w_HittingTimeEditText.setText("0");
                                        a_ob_h_HittingTimeEditText.setText("0");
                                        a_cp_HittingTimeEditText.setText("0");
                                        a_sp_HittingTimeEditText.setText("0");
                                        a_re_w_HittingTimeEditText.setText("0");
                                        a_re_h_HittingTimeEditText.setText("0");
                                        b_ob_h_HittingTimeEditText.setText("0");
                                        b_re_w_HittingTimeEditText.setText("0");
                                        b_re_h_HittingTimeEditText.setText("0");
                                        b_ro_HittingTimeEditText.setText("0");
                                        b_sq_HittingTimeEditText.setText("0");
                                        b_sq_d_HittingTimeEditText.setText("0");
                                        b_cr_HittingTimeEditText.setText("0");
                                        c_cr_HittingTimeEditText.setText("0");
                                        c_ob_w_HittingTimeEditText.setText("0");
                                        c_ob_h_HittingTimeEditText.setText("0");
                                        c_sp_HittingTimeEditText.setText("0");
                                        c_rr_HittingTimeEditText.setText("0");
                                        d_re_w_HittingTimeEditText.setText("0");
                                        d_re_h_HittingTimeEditText.setText("0");
                                        e_re_w_HittingTimeEditText.setText("0");
                                        e_re_h_HittingTimeEditText.setText("0");
                                        g_cr_HittingTimeEditText.setText("0");
                                        g_re_w_HittingTimeEditText.setText("0");
                                        productTimeEditText.setText("");


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

        //화면 터치 시 키보드 숨김
        LinearLayout registerDesignLinearLayout = findViewById(R.id.registerDesignLinearLayout);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        registerDesignLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert imm != null;
                imm.hideSoftInputFromWindow(designCodeEditText.getWindowToken(), 0);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(DesignRegisterActivity.this,
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

            String designCode = params[0];
            String metalSheetType = params[1];
            String productTime = params[2];
            String a_sqHittingTime = params[3];
            String a_roHittingTime = params[4];
            String a_ob_wHittingTime = params[5];
            String a_ob_hHittingTime = params[6];
            String a_cpHittingTime = params[7];
            String a_spHittingTime = params[8];
            String a_re_wHittingTime = params[9];
            String a_re_hHittingTime = params[10];
            String b_ob_hHittingTime = params[11];
            String b_re_wHittingTime = params[12];
            String b_re_hHittingTime = params[13];
            String b_roHittingTime = params[14];
            String b_sqHittingTime = params[15];
            String b_sq_dHittingTime = params[16];
            String b_crHittingTime = params[17];
            String c_crHittingTime = params[18];
            String c_ob_wHittingTime = params[19];
            String c_ob_hHittingTime = params[20];
            String c_spHittingTime = params[21];
            String c_rrHittingTime = params[22];
            String d_re_wHittingTime = params[23];
            String d_re_hHittingTime = params[24];
            String e_re_wHittingTime = params[25];
            String e_re_hHittingTime = params[26];
            String g_crHittingTime = params[27];
            String g_re_wHittingTime = params[28];
            String totalHittingTimes = params[29];
            String totalmoldnumber = params[30];

            String serverURL = "http://smurf1213.cafe24.com/insert_design.php";

            String postParameters = "DESIGN_CODE=" + designCode
                            + "&METAL_SHEET_TYPE=" + metalSheetType
                            + "&PRODUCT_TIME=" + productTime
                            + "&A_SQ=" + a_sqHittingTime
                            + "&A_RO=" + a_roHittingTime
                            + "&A_OB_W=" + a_ob_wHittingTime
                            + "&A_OB_H=" + a_ob_hHittingTime
                            + "&A_CP=" + a_cpHittingTime
                            + "&A_SP=" + a_spHittingTime
                            + "&A_RE_W=" + a_re_wHittingTime
                            + "&A_RE_H=" + a_re_hHittingTime
                            + "&B_OB_H=" + b_ob_hHittingTime
                            + "&B_RE_W=" + b_re_wHittingTime
                            + "&B_RE_H=" + b_re_hHittingTime
                            + "&B_RO=" + b_roHittingTime
                            + "&B_SQ=" + b_sqHittingTime
                            + "&B_SQ_D=" + b_sq_dHittingTime
                            + "&B_CR=" + b_crHittingTime
                            + "&C_CR=" + c_crHittingTime
                            + "&C_OB_W=" + c_ob_wHittingTime
                            + "&C_OB_H=" + c_ob_hHittingTime
                            + "&C_SP=" + c_spHittingTime
                            + "&C_RR=" + c_rrHittingTime
                            + "&D_RE_W=" + d_re_wHittingTime
                            + "&D_RE_H=" + d_re_hHittingTime
                            + "&E_RE_W=" + e_re_wHittingTime
                            + "&E_RE_H=" + e_re_hHittingTime
                            + "&G_CR=" + g_crHittingTime
                            + "&G_RE_W=" + g_re_wHittingTime
                            + "&TOTAL_HITTING_TIMES=" + totalHittingTimes
                            + "&TOTAL_MOLD_NUMBER=" + totalmoldnumber;


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