package com.example.android.hometask1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.android.hometask1.CircularTransformation;
import com.example.android.hometask1.R;
import com.squareup.picasso.Picasso;

public class HomeTask3Activity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private ImageView imageView;
    private String imageURL = "http://bm.img.com.ua/nxs/img/prikol/images/large/1/2/308321_879389.jpg";

    public static void start(Activity activity){
        Intent intent = new Intent(activity, HomeTask3Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht3);

        editText = findViewById(R.id.ht3_editText);
        button = findViewById(R.id.ht3_DownloadButton);
        imageView = findViewById(R.id.ht3_imageView);

        editText.setText(imageURL);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get().load(editText.getText().toString()).transform(new CircularTransformation()).into(imageView);
            }
        });

    }
}
