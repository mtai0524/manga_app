package com.example.bietdoidoctruyen.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.dao.ListDataDAO;
import com.example.bietdoidoctruyen.model.ListData;

import java.util.ArrayList;
import java.util.List;

public class ManagementCateAdapter extends BaseAdapter {
    Context context;
    List<ListData> listData;
    public ManagementCateAdapter(Context context, List<ListData> listData){
        this.context = context;
        this.listData = listData;
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ManagementCateViewHolder holder;
        if(view == null){
            holder = new ManagementCateViewHolder();
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.item_cate_edit_del, null);
            holder.et_name_cate = view.findViewById(R.id.tv_name_cate);
            holder.btn_edit_cate = view.findViewById(R.id.btn_edit_cate);
            holder.btn_del_cate = view.findViewById(R.id.btn_del_cate);
            view.setTag(holder);
        }
        else{
            holder = (ManagementCateViewHolder)view.getTag();
        }
        holder.et_name_cate.setText(listData.get(i).getCatagoryName());
        holder.btn_edit_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editCate = holder.et_name_cate.getText().toString();
                ListDataDAO listDataDAO = new ListDataDAO(context);
                listDataDAO.updateCategoryName(listData.get(i).getCategoryId(), editCate);

                // Cập nhật dữ liệu trong danh sách
                listData.get(i).setCatagoryName(editCate);

                // Thông báo cho Adapter cập nhật giao diện
                notifyDataSetChanged();

                Toast.makeText(context, "sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btn_del_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListDataDAO listDataDAO = new ListDataDAO(context);
                listDataDAO.deleteCategoryById(listData.get(i).getCategoryId());
                listData.remove(i);
                notifyDataSetChanged();
                Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
class ManagementCateViewHolder{
    EditText et_name_cate;
    Button btn_edit_cate;
    Button btn_del_cate;
}
