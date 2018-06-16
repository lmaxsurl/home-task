package com.example.android.hometask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.hometask1.homeTaskActivities.ht1Activity;
import com.example.android.hometask1.homeTaskActivities.ht2Activity;
import com.example.android.hometask1.homeTaskActivities.ht3Activity;
import com.example.android.hometask1.homeTaskActivities.ht4Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ht1Button;
    private Button ht2Button;
    private Button ht3Button;
    private Button ht4Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ht1Button = findViewById(R.id.ht1_button);
        ht1Button.setOnClickListener(this);

        ht2Button = findViewById(R.id.ht2_button);
        ht2Button.setOnClickListener(this);

        ht3Button = findViewById(R.id.ht3_button);
        ht3Button.setOnClickListener(this);

        ht4Button = findViewById(R.id.ht4_button);
        ht4Button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ht1_button: ht1Activity.start(this);  //ht1 - home task 1
                break;
            case R.id.ht2_button: ht2Activity.start(this);
                break;
            case R.id.ht3_button: ht3Activity.start(this);
                break;
            case R.id.ht4_button: ht4Activity.start(this);
        }
    }
}
