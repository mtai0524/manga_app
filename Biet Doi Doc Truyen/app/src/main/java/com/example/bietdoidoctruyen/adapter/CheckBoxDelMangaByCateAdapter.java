package com.example.bietdoidoctruyen.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckBoxDelMangaByCateAdapter extends RecyclerView.Adapter<CheckBoxDelMangaByCateAdapter.CheckBoxDelMangaByCateViewHolder> {

    private List<String> options;
    private boolean[] selectedOptions;
    Context context;

    public CheckBoxDelMangaByCateAdapter(List<String> options) {
        this.options = options;
        selectedOptions = new boolean[options.size()];
    }

    public CheckBoxDelMangaByCateAdapter(Context context,List<String> options, List<Integer> selectedIndices) {
        this.options = options;
        selectedOptions = new boolean[options.size()];

        for (int index : selectedIndices) {
            Log.i("INDEX CMM" , String.valueOf(index));
            if (index >= 0 && index <= selectedOptions.length) {
                selectedOptions[--index] = true;
                Log.i("INDEX CMM O TRONG" , String.valueOf(index));
            }
        }
    }
    public boolean[] getSelectedOptions() {
        return selectedOptions;
    }

    @NonNull
    @Override
    public CheckBoxDelMangaByCateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.bietdoidoctruyen.R.layout.item_check_box_cate, parent, false);
        return new CheckBoxDelMangaByCateAdapter.CheckBoxDelMangaByCateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckBoxDelMangaByCateViewHolder holder, int position) {
        String option = options.get(position);
        holder.cbCate.setText(option);
        holder.cbCate.setChecked(selectedOptions[position]);


        if(!holder.cbCate.isChecked()) {
            holder.cbCate.setEnabled(false);
        } else {
            holder.cbCate.setOnCheckedChangeListener((buttonView, isChecked) -> {
                selectedOptions[position] = isChecked;
            });
        }
    }

    @Override
    public int getItemCount() {
            return options.size();
        }

    protected class CheckBoxDelMangaByCateViewHolder extends RecyclerView.ViewHolder{
        CheckBox cbCate;
        public CheckBoxDelMangaByCateViewHolder(@NonNull View itemView) {
            super(itemView);
            cbCate = itemView.findViewById(com.example.bietdoidoctruyen.R.id.cb_cate);
        }
    }
}
