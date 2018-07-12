package com.example.android.hometask1.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.hometask1.Interfaces.IChangeList;
import com.example.android.hometask1.R;

public class AddFragment extends Fragment {

    private EditText urlEditText,
            nameEditText,
            surnameEditText,
            ageEditText;
    private Button addButton;
    private IChangeList listener;

    public EditText getSurnameEditText() {
        return surnameEditText;
    }

    public EditText getNameEditText() {
        return nameEditText;
    }

    public EditText getAgeEditText() {
        return ageEditText;
    }

    public EditText getUrlEditText() {
        return urlEditText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        urlEditText = view.findViewById(R.id.student_url_et);
        nameEditText = view.findViewById(R.id.student_name_et);
        surnameEditText = view.findViewById(R.id.student_surname_et);
        ageEditText = view.findViewById(R.id.student_age_et);
        addButton = view.findViewById(R.id.add_student_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFilled() && listener != null)
                    listener.onAddListener();
                else
                    Toast.makeText(getContext(), "Check the entered data!", Toast.LENGTH_SHORT)
                            .show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IChangeList)
            listener = (IChangeList) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private boolean isFilled() {
        return nameEditText.getText().toString().length() > 0
                && surnameEditText.getText().toString().length() > 0
                && ageEditText.getText().toString().length() > 0
                && urlEditText.getText().toString().length() > 0;
    }
}
