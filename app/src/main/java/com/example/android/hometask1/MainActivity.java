package com.example.android.hometask1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int READY_TASKS = 1;
    private final int FIRST_TASK = 0;
    private Button[] buttons = new Button[READY_TASKS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons[FIRST_TASK] = findViewById(R.id.ht1_button);  //ht1 - home task 1
       /* buttons[1] = findViewById(R.id.ht2_button);
        buttons[2] = findViewById(R.id.ht3_button);*/
    }


    @Override
    public void onClick(View view) {
        switch (view){
            case buttons[FIRST_TASK]: ht1Activity.start(this);
                break;
        }
    }
}
