package com.example.android.hometask1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.android.hometask1.R;
import com.example.android.hometask1.Singleton;
import com.example.android.hometask1.Student;
import com.example.android.hometask1.StudentAdapter;

import java.util.ArrayList;

public class ht6Activity extends AppCompatActivity implements StudentAdapter.OnItemClickListener {

    private StudentAdapter studentAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ht6Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht6);
        createDataList();
        initRecycleView();
        fab = findViewById(R.id.ht6_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStudentActivity.start(ht6Activity.this);
            }
        });
    }

    private void initRecycleView() {
        studentAdapter = new StudentAdapter();
        recyclerView = findViewById(R.id.ht6_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.setOnClickListener(ht6Activity.this);
        studentAdapter.setDataList();
    }

    private void createDataList() {
        ArrayList<Student> studentsList = new ArrayList<>();
        studentsList.add(new Student("Max", "Ivanov", 25,
                "https://mirpozitiva.ru/uploads/posts/2016-09/1474011210_15.jpg"));
        studentsList.add(new Student("Jill", "Hummerson", 18,
                "http://bipbap.ru/wp-content/uploads/2017/05/VOLKI-krasivye-i-ochen-umnye-zhivotnye.jpg"));
        studentsList.add(new Student("Grew", "Martison", 49,
                "http://bipbap.ru/wp-content/uploads/2017/10/0_8eb56_842bba74_XL-640x400.jpg"));
        studentsList.add(new Student("Pavel", "Stepanov", 15,
                "http://s1.1zoom.me/big0/930/Coast_Sunrises_and_sunsets_Waves_USA_Ocean_Kaneohe_521540_1280x775.jpg"));
        studentsList.add(new Student("Igor", "Trenkov", 19,
                "https://pp.userapi.com/c9410/g20099/a_a0f275d0.jpg?ava=1"));
        studentsList.add(new Student("Sasha", "Stepanov", 30,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScFORdDaLnx9tMS04LOH-o5YDW9996UMKRaLNYzMkvvDf_drbq"));
        Singleton.INSTANCE.setStudentsData(studentsList);
    }

    @Override
    public void OnItemClick(int position) {
        StudentActivity.start(this, position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentAdapter.setDataList();
    }
}
