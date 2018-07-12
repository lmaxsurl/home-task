package com.example.android.hometask1.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.hometask1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ht1Button;
    private Button ht2Button;
    private Button ht3Button;
    private Button ht4Button;
    private Button ht5Button;
    private Button ht6Button;
    private Button ht7Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
    }

    private void initButtons(){
        ht1Button = findViewById(R.id.ht1_button);
        ht1Button.setOnClickListener(this);

        ht2Button = findViewById(R.id.ht2_button);
        ht2Button.setOnClickListener(this);

        ht3Button = findViewById(R.id.ht3_button);
        ht3Button.setOnClickListener(this);

        ht4Button = findViewById(R.id.ht4_button);
        ht4Button.setOnClickListener(this);

        ht5Button = findViewById(R.id.ht5_button);
        ht5Button.setOnClickListener(this);

        ht6Button = findViewById(R.id.ht6_button);
        ht6Button.setOnClickListener(this);

        ht7Button = findViewById(R.id.ht7_button);
        ht7Button.setOnClickListener(this);
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
        }
    }
}
