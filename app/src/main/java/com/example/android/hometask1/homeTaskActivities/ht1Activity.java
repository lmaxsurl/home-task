package com.example.android.hometask1.homeTaskActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.hometask1.R;

public class ht1Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1;
    private TextView textView2;
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            swap();
        }
    };

    public static void start(Activity activity){
        Intent intent = new Intent(activity, ht1Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht1);
        Button button = findViewById(R.id.button);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView1.setOnClickListener(clickListener);
        textView2.setOnClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swap();
            }
        });
    }

    @Override
    public void onClick(View view) {
        swap();
    }

    private void swap(){
        CharSequence tmp = textView1.getText();
        textView1.setText(textView2.getText());
        textView2.setText(tmp);

        ColorDrawable cd1 = (ColorDrawable) textView1.getBackground();
        ColorDrawable cd2 = (ColorDrawable) textView2.getBackground();
        int firstColor = cd1.getColor();
        int secondColor = cd2.getColor();
        textView1.setBackgroundColor(secondColor);
        textView2.setBackgroundColor(firstColor);
    }
}
