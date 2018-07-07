package com.example.android.hometask1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.hometask1.R;
import com.example.android.hometask1.Singleton;
import com.example.android.hometask1.Student;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {

    private EditText urlEditText, nameEditText, surnameEditText, ageEditText;
    private Button addButton;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, AddStudentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        init();
    }

    private void init() {
        urlEditText = findViewById(R.id.student_url);
        nameEditText = findViewById(R.id.student_name);
        surnameEditText = findViewById(R.id.student_surname);
        ageEditText = findViewById(R.id.student_age);
        addButton = findViewById(R.id.add_student_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Student> list = Singleton.INSTANCE.getStudents();
                if(isFilled()){
                    String name = nameEditText.getText().toString();
                    String surname = surnameEditText.getText().toString();
                    String url = urlEditText.getText().toString();
                    int age = Integer.parseInt(ageEditText.getText().toString());
                    if(list != null){
                        list.add(new Student(name, surname, age, url));
                    } else{
                        list = new ArrayList<>();
                        list.add(new Student(name, surname, age, url));
                    }
                    AddStudentActivity.this.finish();
                }
                else
                    Toast.makeText(AddStudentActivity.this, "Check the entered data!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isFilled() {
        return nameEditText.getText().toString().length() > 0
                && surnameEditText.getText().toString().length() > 0
                && ageEditText.getText().toString().length() > 0
                && urlEditText.getText().toString().length() > 0;
    }
}
