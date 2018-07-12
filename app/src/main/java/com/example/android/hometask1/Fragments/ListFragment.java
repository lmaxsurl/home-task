package com.example.android.hometask1.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android.hometask1.Interfaces.IChangeList;
import com.example.android.hometask1.Interfaces.IOnAddClickListener;
import com.example.android.hometask1.Interfaces.IOnItemClickListener;
import com.example.android.hometask1.R;
import com.example.android.hometask1.Singleton;
import com.example.android.hometask1.Student;
import com.example.android.hometask1.StudentAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class ListFragment extends Fragment implements IOnItemClickListener {

    private StudentAdapter studentAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private EditText findEditText;
    private IOnItemClickListener listener;
    private IOnAddClickListener addListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fragment_fab);
        findEditText = view.findViewById(R.id.fragment_et);
        recyclerView = view.findViewById(R.id.fragment_rv);
        init();
    }

    private void init() {
        initRecycleView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addListener != null)
                    addListener.onAddClick();
            }
        });
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
                while (Singleton.INSTANCE.getStudents() == null)
                    Log.d("thread", "wait");
                if(getActivity() != null)
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        studentAdapter.setDataList(Singleton.INSTANCE.getStudents());
                        Log.d("thread", "add");
                    }
                });
            }
        }).start();
    }

    private void findData() {
        ArrayList<Student> list = new ArrayList<>();
        String key = findEditText.getText().toString().toLowerCase();
        if (key.equals("")) {
            studentAdapter.setDataList(Singleton.INSTANCE.getOriginalList());
            return;
        }
        for (Student student : Singleton.INSTANCE.getOriginalList()) {
            if (student.getName().toLowerCase().contains(key) || student.getSurname().toLowerCase().contains(key)) {
                list.add(student);
            }
        }
        Collections.sort(list);
        studentAdapter.setDataList(list);
        Singleton.INSTANCE.setStudents(list);
    }

    private void initRecycleView() {
        studentAdapter = new StudentAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.setOnClickListener(this);
        studentAdapter.setDataList(Singleton.INSTANCE.getStudents());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IOnItemClickListener)
            listener = (IOnItemClickListener) context;
        if(context instanceof IOnAddClickListener)
            addListener = (IOnAddClickListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        addListener = null;
    }

    @Override
    public void OnItemClick(int position) {
        listener.OnItemClick(position);
    }
}
