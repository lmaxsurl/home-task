package com.example.android.hometask1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.android.hometask1.R;
import com.example.android.hometask1.Singleton;
import com.example.android.hometask1.Student;
import com.example.android.hometask1.StudentAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class HomeTask6Activity extends AppCompatActivity implements StudentAdapter.OnItemClickListener {

    private StudentAdapter studentAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private EditText findEditText;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, HomeTask6Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht6);
        init();
    }

    private void init() {
        initRecycleView();
        fab = findViewById(R.id.ht6_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStudentActivity.start(HomeTask6Activity.this);
            }
        });
        findEditText = findViewById(R.id.ht6_et);
        findEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                findData();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                HomeTask6Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        while(Singleton.INSTANCE.getStudents() == null);
                        HomeTask6Activity.this.studentAdapter.setDataList(Singleton.INSTANCE.getStudents());
                    }
                });
            }
        }).start();
    }

    private void findData() {
        ArrayList<Student> list = new ArrayList<>();
        String key = findEditText.getText().toString().toLowerCase();
        if(key.equals("")){
            studentAdapter.setDataList(Singleton.INSTANCE.getStudents());
            return;
        }
        for(Student student : Singleton.INSTANCE.getStudents()){
            if (student.getName().toLowerCase().contains(key) || student.getSurname().toLowerCase().contains(key)) {
                list.add(student);
            }
        }
        Collections.sort(list);
        studentAdapter.setDataList(list);
    }

    private void initRecycleView() {
        studentAdapter = new StudentAdapter();
        recyclerView = findViewById(R.id.ht6_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.setOnClickListener(HomeTask6Activity.this);
        studentAdapter.setDataList(Singleton.INSTANCE.getStudents());
    }


    @Override
    public void OnItemClick(int position) {
        StudentActivity.start(this, position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentAdapter.setDataList(Singleton.INSTANCE.getStudents());
    }
}
