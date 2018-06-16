package com.example.android.hometask1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ht4Activity extends AppCompatActivity {

    public static void start(Activity activity){
        Intent intent = new Intent(activity, ht4Activity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.alpha, R.anim.extension_with_alpha);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht4);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.alpha, R.anim.compression_with_apha);
    }
}
