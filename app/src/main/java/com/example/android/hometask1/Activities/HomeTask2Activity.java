package com.example.android.hometask1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.hometask1.R;

public class HomeTask2Activity extends AppCompatActivity {

    public static void start(Activity activity){
        Intent intent = new Intent(activity, HomeTask2Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht2);
    }
}
