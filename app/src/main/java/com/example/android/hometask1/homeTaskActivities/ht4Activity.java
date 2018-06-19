package com.example.android.hometask1.homeTaskActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.hometask1.ClockView;
import com.example.android.hometask1.R;
import com.example.android.hometask1.RoundDiagram;

public class ht4Activity extends AppCompatActivity {

    private AnimationDrawable owlAnimation;
    private ImageView owlImageView;
    private ClockView clockView;
    private Thread clockTread;
    private float[] numbers;
    private Button addButton;
    private EditText ht4EditText;
    private RoundDiagram roundDiagram;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ht4Activity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.alpha, R.anim.extension_with_alpha);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht4);
        initViews();
        initThread();
    }

    private void initThread() {
        final Runnable clockRunnable = new Runnable() {
            @Override
            public void run() {
                clockView.invalidate();
            }
        };

        clockTread = new Thread(new Runnable() {
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
        });
        clockTread.start();
    }

    private void initViews() {
        owlImageView = findViewById(R.id.owlImageView);
        owlImageView.setBackgroundResource(R.drawable.owl_animation_list);
        owlAnimation = (AnimationDrawable) owlImageView.getBackground();
        owlAnimation.start();

        clockView = findViewById(R.id.clockView);
        addButton = findViewById(R.id.ht4_add_btn);
        roundDiagram = findViewById(R.id.ht4_diagram);
        ht4EditText = findViewById(R.id.ht4_editText);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeAndSendArr(ht4EditText.getText().toString());
            }
        });
    }


    private void makeAndSendArr(String s) {
        String numArr[];
        if (s.length() > 0) {
            numArr = s.split(",");
            numbers = new float[numArr.length];
            for (int i = 0; i < numArr.length; i++) {
                numbers[i] = Integer.parseInt(numArr[i]);
            }
            roundDiagram.setNumbers(numbers);
            roundDiagram.invalidate();
        } else {
            Toast.makeText(this, "Check the entered data", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.alpha, R.anim.compression_with_apha);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clockTread.interrupt();
    }
}
