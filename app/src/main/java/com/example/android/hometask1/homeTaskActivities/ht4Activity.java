package com.example.android.hometask1.homeTaskActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.android.hometask1.ClockView;
import com.example.android.hometask1.R;

public class ht4Activity extends AppCompatActivity {

    private AnimationDrawable owlAnimation;
    private ImageView owlImageView;
    private ClockView clockView;
    private ClockTread clockTread = new ClockTread();

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ht4Activity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.alpha, R.anim.extension_with_alpha);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht4);
        owlImageView = findViewById(R.id.owlImageView);
        owlImageView.setBackgroundResource(R.drawable.owl_animation_list);
        owlAnimation = (AnimationDrawable) owlImageView.getBackground();
        owlAnimation.start();
        clockView = findViewById(R.id.clockView);
        clockTread.start();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.alpha, R.anim.compression_with_apha);
        clockTread.interrupt();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clockTread.interrupt();
    }

    private class ClockTread extends Thread {
        Runnable clockRunnable = new Runnable() {
            @Override
            public void run() {
                clockView.invalidate();
            }
        };

        @Override
        public void run() {
            try {
                while (true) {
                        Thread.sleep(1000);
                        ht4Activity.this.runOnUiThread(clockRunnable);
                    }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
