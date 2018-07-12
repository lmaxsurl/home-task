package com.example.android.hometask1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.android.hometask1.Fragments.AddFragment;
import com.example.android.hometask1.Interfaces.IChangeList;
import com.example.android.hometask1.R;
import com.example.android.hometask1.Singleton;
import com.example.android.hometask1.Student;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity implements IChangeList {

    private AddFragment addFragment;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, AddStudentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        addFragment = new AddFragment();
        showFragment(addFragment, R.id.student_container);
    }

    private void showFragment(Fragment fragment, int containerId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
    }


    @Override
    public void onDeleteListener(int position) {

    }

    @Override
    public void onAddListener() {
        EditText nameEditText = addFragment.getNameEditText(),
                surnameEditText = addFragment.getSurnameEditText(),
                ageEditText = addFragment.getAgeEditText(),
                urlEditText = addFragment.getUrlEditText();
        String url = urlEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        int age = Integer.parseInt(ageEditText.getText().toString());
        ArrayList<Student> list = Singleton.INSTANCE.getOriginalList();
        if(list != null){
            list.add(new Student(name, surname, age, url));
        } else{
            list = new ArrayList<>();
            list.add(new Student(name, surname, age, url));
        }
        finish();
    }

    @Override
    public void onEditListener(int position) {

    }
}
