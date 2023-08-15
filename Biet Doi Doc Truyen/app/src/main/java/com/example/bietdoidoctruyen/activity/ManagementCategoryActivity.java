package com.example.bietdoidoctruyen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.ManagementCateAdapter;
import com.example.bietdoidoctruyen.dao.ListDataDAO;
import com.example.bietdoidoctruyen.model.ListData;

import java.util.ArrayList;
import java.util.List;

public class ManagementCategoryActivity extends AppCompatActivity {
    private ListView lvCate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managament_category);



        lvCate = findViewById(R.id.lv_cate);
        List<ListData> list = new ArrayList<>();
        ListDataDAO listDataDAO = new ListDataDAO(ManagementCategoryActivity.this);

        list = listDataDAO.getAllCategories();



        ManagementCateAdapter managementCateAdapter = new ManagementCateAdapter(ManagementCategoryActivity.this, list);
        lvCate.setAdapter(managementCateAdapter);
    }
}