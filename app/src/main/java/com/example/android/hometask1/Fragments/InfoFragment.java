package com.example.android.hometask1.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.hometask1.CircularTransformation;
import com.example.android.hometask1.Interfaces.IChangeList;
import com.example.android.hometask1.R;
import com.example.android.hometask1.Singleton;
import com.example.android.hometask1.Student;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InfoFragment extends Fragment {

    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText ageEditText;
    private Button editButton;
    private Button deleteButton;
    private ImageView image;
    private IChangeList listener = null;
    private static String KEY_POSITION = "position";

    public EditText getAgeEditText() {
        return ageEditText;
    }

    public EditText getNameEditText() {
        return nameEditText;
    }

    public EditText getSurnameEditText() {
        return surnameEditText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.info_fragment, container, false);
    }

    public static InfoFragment getInstance(int position) {
        Bundle arguments = new Bundle();
        arguments.putInt(KEY_POSITION, position);
        InfoFragment infoFragment = new InfoFragment();
        infoFragment.setArguments(arguments);
        return infoFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameEditText = view.findViewById(R.id.student_name_et);
        surnameEditText = view.findViewById(R.id.student_surname_et);
        ageEditText = view.findViewById(R.id.student_age_et);
        image = view.findViewById(R.id.student_image);
        editButton = view.findViewById(R.id.edit_student_button);
        deleteButton = view.findViewById(R.id.delete_student_button);
        init();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IChangeList) {
            listener = (IChangeList) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void init() {
        final int position;
        if (getArguments() != null)
            position = getArguments().getInt(KEY_POSITION, RecyclerView.NO_POSITION);
        else
            return;
        final ArrayList<Student> list = Singleton.INSTANCE.getStudents();
        if (position == RecyclerView.NO_POSITION || position >= list.size())
            return;
        final Student student = list.get(position);

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
                if (isFilled() && listener != null) {
                    listener.onEditListener(position);

                } else
                    Toast.makeText(getContext(), "Check the entered data!", Toast.LENGTH_SHORT)
                            .show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onDeleteListener(position);
            }
        });
    }

    private boolean isFilled() {
        return nameEditText.getText().toString().length() > 0
                && surnameEditText.getText().toString().length() > 0
                && ageEditText.getText().toString().length() > 0;
    }
}
