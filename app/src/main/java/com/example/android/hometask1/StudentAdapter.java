package com.example.android.hometask1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter {

    private ArrayList<Student> dataList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Student student = dataList.get(position);
        Holder itemHolder = (Holder) holder;
        itemHolder.nameTextView.setText(student.getName() + "\n" + student.getSurname());
        Picasso.get()
                .load(student.getURL())
                .resize(512,512)
                .centerCrop()
                .transform(new CircularTransformation())
                .into(itemHolder.imageView);
    }

    public void setDataList() {
        dataList = Singleton.INSTANCE.getStudents();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList == null? 0 : dataList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public ImageView imageView;

        private Holder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.item_tv);
            imageView = itemView.findViewById(R.id.item_iv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
