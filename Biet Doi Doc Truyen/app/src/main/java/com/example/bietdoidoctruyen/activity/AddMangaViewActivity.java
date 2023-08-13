package com.example.bietdoidoctruyen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.CheckBoxCateAdapter;
import com.example.bietdoidoctruyen.dao.CategoryMangaDAO;
import com.example.bietdoidoctruyen.dao.ListDataDAO;
import com.example.bietdoidoctruyen.dao.MangaDAO;

import java.util.ArrayList;
import java.util.List;

public class AddMangaViewActivity extends AppCompatActivity {

    private EditText insertMangaName;
    private EditText insertMangaImage;
    private EditText insertMangaDescription;
    private Button btnAddManga;

    String name = "";
    String image = "";
    String cate = "";
    String desc = "";
    MangaDAO mangaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manga_view);
        insertMangaName = findViewById(R.id.et_mangaName);
        insertMangaImage = findViewById(R.id.et_mangaCover);
        insertMangaDescription = findViewById(R.id.et_mangaDescription);
        btnAddManga = findViewById(R.id.addBtn);
        mangaDAO = new MangaDAO(AddMangaViewActivity.this);


//        btnAddManga.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        CategoryMangaDAO categoryMangaDAO = new CategoryMangaDAO(this);
        RecyclerView recyclerView = findViewById(R.id.rcv_cb_cate);
        ListDataDAO listDataDAO = new ListDataDAO(this);
        List<String> options = new ArrayList<>();
        List<Integer> listIdCategoryManga = new ArrayList<>();
        listDataDAO.getCategoryName().forEach(cate -> {
            options.add(cate.getCatagoryName());
//            listIdCategoryManga.add(cate.getCategoryId());
        });

        CheckBoxCateAdapter adapter = new CheckBoxCateAdapter(options);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        btnAddManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = insertMangaName.getText().toString();
                image = insertMangaImage.getText().toString();
                desc = insertMangaDescription.getText().toString();
                if(name.equals("") || image.equals("") || desc.equals("")){
                    Toast.makeText(AddMangaViewActivity.this, "nhap thieu kia m", Toast.LENGTH_SHORT).show();
                    return;
                }
                long idMangaNew =  mangaDAO.insertManga(name, image, desc); // tạo manga lưu vào sqlite , sau đó trả id vừa được tạo
                Log.i("data", String.format("Name: %s, Image: %s, Desc: %s", name, image, desc));


                boolean[] selectedOptions = adapter.getSelectedOptions();

                for (int i = 0; i < selectedOptions.length; i++) {
                    if (selectedOptions[i]) {
                        String selectedOption = options.get(i); // phieu luu
                        ///
                        int id = listDataDAO.getCategoryIdByName(selectedOption);
                        categoryMangaDAO.insertCategoryManga(id, Integer.parseInt(String.valueOf(idMangaNew)));
//
                        Log.d("Selected Category", selectedOption);
                        Log.d("id manga new cate", String.valueOf(id));
                    }
                }
            }
        });
    }
}