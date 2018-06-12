package com.example.android.hometask1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.ht1_button);
        button1.setOnClickListener(this);

        button2 = findViewById(R.id.ht2_button);
        button2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ht1_button: ht1Activity.start(this);  //ht1 - home task 1
                break;
            case R.id.ht2_button: ht2Activity.start(this);
                break;
        }
    }
}
