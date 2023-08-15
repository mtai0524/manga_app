package com.example.bietdoidoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bietdoidoctruyen.activity.ManagementCategoryActivity;
import com.example.bietdoidoctruyen.dao.ListDataDAO;

import java.util.ArrayList;
import java.util.List;

public class AddCateViewActivity extends AppCompatActivity {
    private EditText etAddCate;
    private Button btnAddCate;
    private String addCateName = "";
    private Spinner spinnerType;
    int selectedOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cate_view);
        etAddCate = findViewById(R.id.et_add_cate);
        spinnerType = findViewById(R.id.spinner_type);
        btnAddCate = findViewById(R.id.btn_add_cate);

        List<Integer> options = new ArrayList<>();
        options.add(1);
        options.add(4);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOption = (int) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAddCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCateName = etAddCate.getText().toString();
                if(!addCateName.equals("")){
                    ListDataDAO listDataDAO = new ListDataDAO(AddCateViewActivity.this);
                    listDataDAO.insertCategory(addCateName, selectedOption);
                    Toast.makeText(AddCateViewActivity.this, "thêm category thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddCateViewActivity.this, "chưa nhập tên danh mục", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}