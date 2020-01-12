package com.example.ikjoon.moldmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class UsageStatusActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_status);

        //뒤로버튼
        Button backToMain4 = findViewById(R.id.backToMain4);
        backToMain4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // 각 금형 버튼 클릭 시
        Button.OnClickListener listener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsageStatusActivity.this, UsageStatusDetailActivity.class);
                switch (v.getId()) {
                    case R.id.A_SQ_button:
                        intent.putExtra("code", "A_SQ");
                        intent.putExtra("firstMoldID", "1");
                        intent.putExtra("secondMoldID", "2");
                        intent.putExtra("thirdMoldID", "3");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.A_RO_button:
                        intent.putExtra("code", "A_RO");
                        intent.putExtra("firstMoldID", "4");
                        intent.putExtra("secondMoldID", "5");
                        intent.putExtra("thirdMoldID", "6");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.A_OB_W_button:
                        intent.putExtra("code", "A_OB_W");
                        intent.putExtra("firstMoldID", "7");
                        intent.putExtra("secondMoldID", "8");
                        intent.putExtra("thirdMoldID", "9");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.A_OB_H_button:
                        intent.putExtra("code", "A_OB_H");
                        intent.putExtra("firstMoldID", "10");
                        intent.putExtra("secondMoldID", "11");
                        intent.putExtra("thirdMoldID", "12");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.A_CP_button:
                        intent.putExtra("code", "A_CP");
                        intent.putExtra("firstMoldID", "13");
                        intent.putExtra("secondMoldID", "14");
                        intent.putExtra("thirdMoldID", "15");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.A_SP_button:
                        intent.putExtra("code", "A_SP");
                        intent.putExtra("firstMoldID", "16");
                        intent.putExtra("secondMoldID", "17");
                        intent.putExtra("thirdMoldID", "18");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.A_RE_W_button:
                        intent.putExtra("code", "A_RE_W");
                        intent.putExtra("firstMoldID", "19");
                        intent.putExtra("secondMoldID", "20");
                        intent.putExtra("thirdMoldID", "21");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.A_RE_H_button:
                        intent.putExtra("code", "A_RE_H");
                        intent.putExtra("firstMoldID", "22");
                        intent.putExtra("secondMoldID", "23");
                        intent.putExtra("thirdMoldID", "24");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.B_OB_H_button:
                        intent.putExtra("code", "B_OB_H");
                        intent.putExtra("firstMoldID", "25");
                        intent.putExtra("secondMoldID", "26");
                        intent.putExtra("thirdMoldID", "27");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.B_RE_W_button:
                        intent.putExtra("code", "B_RE_W");
                        intent.putExtra("firstMoldID", "28");
                        intent.putExtra("secondMoldID", "29");
                        intent.putExtra("thirdMoldID", "30");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.B_RE_H_button:
                        intent.putExtra("code", "B_RE_H");
                        intent.putExtra("firstMoldID", "31");
                        intent.putExtra("secondMoldID", "32");
                        intent.putExtra("thirdMoldID", "33");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.B_RO_button:
                        intent.putExtra("code", "B_RO");
                        intent.putExtra("firstMoldID", "34");
                        intent.putExtra("secondMoldID", "35");
                        intent.putExtra("thirdMoldID", "36");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.B_SQ_button:
                        intent.putExtra("code", "B_SQ");
                        intent.putExtra("firstMoldID", "37");
                        intent.putExtra("secondMoldID", "38");
                        intent.putExtra("thirdMoldID", "39");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.B_SQ_D_button:
                        intent.putExtra("code", "B_SQ_D");
                        intent.putExtra("firstMoldID", "40");
                        intent.putExtra("secondMoldID", "41");
                        intent.putExtra("thirdMoldID", "42");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.B_CR_button:
                        intent.putExtra("code", "B_CR");
                        intent.putExtra("firstMoldID", "43");
                        intent.putExtra("secondMoldID", "44");
                        intent.putExtra("thirdMoldID", "45");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.C_CR_button:
                        intent.putExtra("code", "C_CR");
                        intent.putExtra("firstMoldID", "46");
                        intent.putExtra("secondMoldID", "47");
                        intent.putExtra("thirdMoldID", "48");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.C_OB_W_button:
                        intent.putExtra("code", "C_OB_W");
                        intent.putExtra("firstMoldID", "49");
                        intent.putExtra("secondMoldID", "50");
                        intent.putExtra("thirdMoldID", "51");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.C_OB_H_button:
                        intent.putExtra("code", "C_OB_H");
                        intent.putExtra("firstMoldID", "52");
                        intent.putExtra("secondMoldID", "53");
                        intent.putExtra("thirdMoldID", "54");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.C_SP_button:
                        intent.putExtra("code", "C_SP");
                        intent.putExtra("firstMoldID", "55");
                        intent.putExtra("secondMoldID", "56");
                        intent.putExtra("thirdMoldID", "57");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.C_RR_button:
                        intent.putExtra("code", "C_RR");
                        intent.putExtra("firstMoldID", "58");
                        intent.putExtra("secondMoldID", "59");
                        intent.putExtra("thirdMoldID", "60");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.D_RE_W_button:
                        intent.putExtra("code", "D_RE_W");
                        intent.putExtra("firstMoldID", "61");
                        intent.putExtra("secondMoldID", "62");
                        intent.putExtra("thirdMoldID", "63");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.D_RE_H_button:
                        intent.putExtra("code", "D_RE_H");
                        intent.putExtra("firstMoldID", "64");
                        intent.putExtra("secondMoldID", "65");
                        intent.putExtra("thirdMoldID", "66");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.E_RE_W_button:
                        intent.putExtra("code", "E_RE_W");
                        intent.putExtra("firstMoldID", "67");
                        intent.putExtra("secondMoldID", "68");
                        intent.putExtra("thirdMoldID", "69");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.E_RE_H_button:
                        intent.putExtra("code", "E_RE_H");
                        intent.putExtra("firstMoldID", "70");
                        intent.putExtra("secondMoldID", "71");
                        intent.putExtra("thirdMoldID", "72");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.G_CR_button:
                        intent.putExtra("code", "G_CR");
                        intent.putExtra("firstMoldID", "73");
                        intent.putExtra("secondMoldID", "74");
                        intent.putExtra("thirdMoldID", "75");
                        startActivityForResult(intent, 100);
                        break;
                    case R.id.G_RE_W_button:
                        intent.putExtra("code", "G_RE_W");
                        intent.putExtra("firstMoldID", "76");
                        intent.putExtra("secondMoldID", "77");
                        intent.putExtra("thirdMoldID", "78");
                        startActivityForResult(intent, 100);
                        break;
                }

            }
        };


        ImageButton asqButton = findViewById(R.id.A_SQ_button);
        asqButton.setOnClickListener(listener);
        ImageButton aroButton = findViewById(R.id.A_RO_button);
        aroButton.setOnClickListener(listener);
        ImageButton aobwButton = findViewById(R.id.A_OB_W_button);
        aobwButton.setOnClickListener(listener);
        ImageButton aobhButton = findViewById(R.id.A_OB_H_button);
        aobhButton.setOnClickListener(listener);
        ImageButton acpButton = findViewById(R.id.A_CP_button);
        acpButton.setOnClickListener(listener);
        ImageButton aspButton = findViewById(R.id.A_SP_button);
        aspButton.setOnClickListener(listener);
        ImageButton arewButton = findViewById(R.id.A_RE_W_button);
        arewButton.setOnClickListener(listener);
        ImageButton arehButton = findViewById(R.id.A_RE_H_button);
        arehButton.setOnClickListener(listener);

        ImageButton bobhButton = findViewById(R.id.B_OB_H_button);
        bobhButton.setOnClickListener(listener);
        ImageButton brewButton = findViewById(R.id.B_RE_W_button);
        brewButton.setOnClickListener(listener);
        ImageButton brehButton = findViewById(R.id.B_RE_H_button);
        brehButton.setOnClickListener(listener);
        ImageButton broButton = findViewById(R.id.B_RO_button);
        broButton.setOnClickListener(listener);
        ImageButton bsqButton = findViewById(R.id.B_SQ_button);
        bsqButton.setOnClickListener(listener);
        ImageButton bsqdButton = findViewById(R.id.B_SQ_D_button);
        bsqdButton.setOnClickListener(listener);
        ImageButton bcrButton = findViewById(R.id.B_CR_button);
        bcrButton.setOnClickListener(listener);

        ImageButton ccrButton = findViewById(R.id.C_CR_button);
        ccrButton.setOnClickListener(listener);
        ImageButton cobwButton = findViewById(R.id.C_OB_W_button);
        cobwButton.setOnClickListener(listener);
        ImageButton cobhButton = findViewById(R.id.C_OB_H_button);
        cobhButton.setOnClickListener(listener);
        ImageButton cspButton = findViewById(R.id.C_SP_button);
        cspButton.setOnClickListener(listener);
        ImageButton crrButton = findViewById(R.id.C_RR_button);
        crrButton.setOnClickListener(listener);

        ImageButton drewButton = findViewById(R.id.D_RE_W_button);
        drewButton.setOnClickListener(listener);
        ImageButton drehButton =findViewById(R.id.D_RE_H_button);
        drehButton.setOnClickListener(listener);

        ImageButton erewButton = findViewById(R.id.E_RE_W_button);
        erewButton.setOnClickListener(listener);
        ImageButton erehButton = findViewById(R.id.E_RE_H_button);
        erehButton.setOnClickListener(listener);

        ImageButton gcrButton = findViewById(R.id.G_CR_button);
        gcrButton.setOnClickListener(listener);
        ImageButton grewButton = findViewById(R.id.G_RE_W_button);
        grewButton.setOnClickListener(listener);



    }
}
