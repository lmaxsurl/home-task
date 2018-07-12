package com.example.android.hometask1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.android.hometask1.Fragments.AddFragment;
import com.example.android.hometask1.Interfaces.IChangeList;
import com.example.android.hometask1.Interfaces.IOnAddClickListener;
import com.example.android.hometask1.Interfaces.IOnItemClickListener;
import com.example.android.hometask1.Fragments.InfoFragment;
import com.example.android.hometask1.Fragments.ListFragment;
import com.example.android.hometask1.R;
import com.example.android.hometask1.Singleton;
import com.example.android.hometask1.Student;

import java.util.ArrayList;

public class HomeTask7Activity extends AppCompatActivity implements IOnItemClickListener, IChangeList, IOnAddClickListener {

    private boolean twoPane = true;
    private ListFragment listFragment;
    private InfoFragment infoFragment;
    private AddFragment addFragment;
    public static void start(Activity activity) {
        Intent intent = new Intent(activity, HomeTask7Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht7);
        listFragment = new ListFragment();
        showFragment(listFragment, R.id.student_list_container);
        if (findViewById(R.id.student_container) == null)
            twoPane = false;
    }

    private void showFragment(Fragment fragment, int containerId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(fragment instanceof InfoFragment || fragment instanceof AddFragment){
            fragmentManager.popBackStack();
            transaction.addToBackStack(null);
        }
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    @Override
    public void OnItemClick(int position) {
        if (twoPane) {
            infoFragment = InfoFragment.getInstance(position);
            showFragment(infoFragment, R.id.student_container);
        } else
            StudentActivity.start(this, position);
    }

    @Override
    public void onDeleteListener(int position) {
        Student removingStudent = Singleton.INSTANCE.getStudents().get(position);
        int index = Singleton.INSTANCE.getOriginalList().indexOf(removingStudent);
        Singleton.INSTANCE.getOriginalList().remove(index);
        this.onBackPressed();
        infoFragment = null;
        listFragment = new ListFragment();
        showFragment(listFragment, R.id.student_list_container);
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
        this.onBackPressed();
        listFragment = new ListFragment();
        showFragment(listFragment, R.id.student_list_container);
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
        this.onBackPressed();
        infoFragment = null;
        listFragment = new ListFragment();
        showFragment(listFragment, R.id.student_list_container);
    }

    @Override
    public void onAddClick() {
        if(twoPane) {
            addFragment = new AddFragment();
            showFragment(addFragment, R.id.student_container);
        } else
            AddStudentActivity.start(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listFragment = new ListFragment();
        showFragment(listFragment, R.id.student_list_container);
    }
}
