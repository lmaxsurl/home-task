package com.example.android.hometask1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.example.android.hometask1.Fragments.InfoFragment;
import com.example.android.hometask1.Interfaces.IChangeList;
import com.example.android.hometask1.R;
import com.example.android.hometask1.Singleton;
import com.example.android.hometask1.Student;

public class StudentActivity extends AppCompatActivity implements IChangeList {

    private InfoFragment infoFragment;

    public static void start(Activity activity, int position) {
        Intent intent = new Intent(activity, StudentActivity.class);
        intent.putExtra("position", position);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        infoFragment = InfoFragment.getInstance(getIntent().getIntExtra("position", RecyclerView.NO_POSITION));
        showFragment(infoFragment, R.id.student_container);
    }

    private void showFragment(Fragment fragment, int containerId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
    }


    @Override
    public void onDeleteListener(int position) {
        Student removingStudent = Singleton.INSTANCE.getStudents().get(position);
        int index = Singleton.INSTANCE.getOriginalList().indexOf(removingStudent);
        Singleton.INSTANCE.getOriginalList().remove(index);
        finish();
    }

    @Override
    public void onAddListener() {

    }

    @Override
    public void onEditListener(int position) {
        Student student = Singleton.INSTANCE.getStudents().get(position);
        EditText nameEditText = infoFragment.getNameEditText(),
                surnameEditText = infoFragment.getSurnameEditText(),
                ageEditText = infoFragment.getAgeEditText();

        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        int age = Integer.parseInt(ageEditText.getText().toString());

        student.setName(name);
        student.setSurname(surname);
        student.setAge(age);
        finish();
    }
}
