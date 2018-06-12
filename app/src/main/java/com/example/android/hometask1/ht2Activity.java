package com.example.android.hometask1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ht2Activity extends AppCompatActivity {

    public static void start(Activity activity){
        Intent intent = new Intent(activity, ht1Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht2);
    }
}
