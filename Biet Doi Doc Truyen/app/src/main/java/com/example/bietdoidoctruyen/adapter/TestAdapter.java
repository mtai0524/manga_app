package com.example.bietdoidoctruyen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.model.Test;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {
    List<Test> testList;
    public void setData(List<Test> list){
        this.testList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_test, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        Test test = testList.get(position);
        if(test == null){
            return;
        }
        holder.tv_test.setText(test.getText());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TestViewHolder extends RecyclerView.ViewHolder{
        TextView tv_test;
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_test = itemView.findViewById(R.id.tv_test);
        }
    }
}
