package com.example.android.hometask1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.hometask1.CircularTransformation;
import com.example.android.hometask1.R;
import com.example.android.hometask1.Singleton;
import com.example.android.hometask1.Student;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText ageEditText;
    private Button editButton;
    private Button deleteButton;
    private ImageView image;

    public static void start(Activity activity, int position) {
        Intent intent = new Intent(activity, StudentActivity.class);
        intent.putExtra("position", position);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        init();
    }

    private void init() {
        image = findViewById(R.id.student_image);
        nameEditText = findViewById(R.id.student_name);
        surnameEditText = findViewById(R.id.student_surname);
        ageEditText = findViewById(R.id.student_age);
        editButton = findViewById(R.id.edit_student_button);
        deleteButton = findViewById(R.id.delete_student_button);

        final int position = getIntent().getIntExtra("position", -1);
        if (position == -1)
            this.finish();
        final Student student = Singleton.INSTANCE.getStudents().get(position);

        Picasso.get()
                .load(student.getURL())
                .resize(512, 512)
                .centerCrop()
                .transform(new CircularTransformation())
                .into(image);
        nameEditText.setText(student.getName());
        surnameEditText.setText(student.getSurname());
        ageEditText.setText("" + student.getAge());
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Student> list = Singleton.INSTANCE.getStudents();
                if (isFilled()) {
                    String name = nameEditText.getText().toString();
                    String surname = surnameEditText.getText().toString();
                    int age = Integer.parseInt(ageEditText.getText().toString());
                    student.setName(name);
                    student.setSurname(surname);
                    student.setAge(age);
                    StudentActivity.this.finish();
                } else
                    Toast.makeText(StudentActivity.this, "Check the entered data!", Toast.LENGTH_SHORT)
                            .show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.INSTANCE.getStudents().remove(position);
                StudentActivity.this.finish();

            }
        });
    }

    private boolean isFilled() {
        return nameEditText.getText().toString().length() > 0
                && surnameEditText.getText().toString().length() > 0
                && ageEditText.getText().toString().length() > 0;
    }
}
