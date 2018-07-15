package com.example.android.hometask1.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.hometask1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
    }

    private void initButtons(){
        findViewById(R.id.ht1_button).setOnClickListener(this);
        findViewById(R.id.ht2_button).setOnClickListener(this);
        findViewById(R.id.ht3_button).setOnClickListener(this);
        findViewById(R.id.ht4_button).setOnClickListener(this);
        findViewById(R.id.ht5_button).setOnClickListener(this);
        findViewById(R.id.ht6_button).setOnClickListener(this);
        findViewById(R.id.ht7_button).setOnClickListener(this);
        findViewById(R.id.ht8_button).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ht1_button:
                HomeTask1Activity.start(this);  //ht1 - home task 1
                break;
            case R.id.ht2_button:
                HomeTask2Activity.start(this);
                break;
            case R.id.ht3_button:
                HomeTask3Activity.start(this);
                break;
            case R.id.ht4_button:
                HomeTask4Activity.start(this);
                break;
            case R.id.ht5_button:
                HomeTask5Activity.start(this);
                break;
            case R.id.ht6_button:
                HomeTask6Activity.start(this);
                break;
            case R.id.ht7_button:
                HomeTask7Activity.start(this);
                break;
            case R.id.ht8_button:
                HomeTask8Activity.start(this);
                break;
        }
    }
}
