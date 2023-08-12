package com.example.bietdoidoctruyen.adapter;

import static android.os.Build.VERSION_CODES.R;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bietdoidoctruyen.R;

import java.util.List;

public class CheckBoxCateAdapter extends RecyclerView.Adapter<CheckBoxCateAdapter.CheckBoxCateViewHolder> {
    private List<String> options;
    private boolean[] selectedOptions;

    public CheckBoxCateAdapter(List<String> options) {
        this.options = options;
        selectedOptions = new boolean[options.size()];
    }

    public CheckBoxCateAdapter(List<String> options, List<Integer> selectedIndices) {
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
    public CheckBoxCateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.bietdoidoctruyen.R.layout.item_check_box_cate, parent, false);
        return new CheckBoxCateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckBoxCateViewHolder holder, int position) {
        String option = options.get(position);
        holder.cbCate.setText(option);
        holder.cbCate.setChecked(selectedOptions[position]);
        if(holder.cbCate.isChecked()) {
            holder.cbCate.setEnabled(false);
        } else {
            holder.cbCate.setOnCheckedChangeListener((buttonView, isChecked) -> {
                selectedOptions[position] = isChecked;
            });
        }
    }
    public void updateSelectedOptions(List<Integer> selectedCategoryIds) {
        for (int i = 0; i < options.size(); i++) {
            selectedOptions[i] = selectedCategoryIds.contains(options.get(i));
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return options.size();
    }

    public class CheckBoxCateViewHolder extends RecyclerView.ViewHolder{

        CheckBox cbCate;
        public CheckBoxCateViewHolder(@NonNull View itemView) {
            super(itemView);
            cbCate = itemView.findViewById(com.example.bietdoidoctruyen.R.id.cb_cate);
        }

    }
}
