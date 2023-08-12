package com.example.bietdoidoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bietdoidoctruyen.adapter.CheckBoxCateAdapter;
import com.example.bietdoidoctruyen.dao.CategoryMangaDAO;
import com.example.bietdoidoctruyen.dao.ListDataDAO;
import com.example.bietdoidoctruyen.dao.MangaDAO;
import com.example.bietdoidoctruyen.model.Manga;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    private Manga manga;
    private EditText insertMangaName;
    private EditText insertMangaImage;
    private EditText insertMangaDescription;
    private Button btnEditDb;
    List<Integer> options = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        manga = (Manga) bundle.get("object_user");


        insertMangaName = findViewById(R.id.et_mangaName);
        insertMangaImage = findViewById(R.id.et_mangaCover);
        insertMangaDescription = findViewById(R.id.et_mangaDescription);
        btnEditDb = findViewById(R.id.btn_edit_db);

        insertMangaName.setText(manga.getMangaName());
        insertMangaImage.setText(manga.getImage());
        insertMangaDescription.setText(manga.getDescription());


        RecyclerView recyclerView = findViewById(R.id.rcv_cb_cate);
        ListDataDAO listDataDAO = new ListDataDAO(this);
        List<String> options = new ArrayList<>();
        listDataDAO.getCategoryName().forEach(cate -> {
            options.add(cate.getCatagoryName());
//            listIdCategoryManga.add(cate.getCategoryId());
        });

        CategoryMangaDAO categoryMangaDAO = new CategoryMangaDAO(this);
        List<Integer> listCateIds = categoryMangaDAO.getCategoryIdsByMangaId(manga.getIdManga());

        CheckBoxCateAdapter adapter = new CheckBoxCateAdapter(options, listCateIds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Log.i("cate id", listCateIds.toString());

//        adapter.updateSelectedOptions(listCateIds);


        btnEditDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = insertMangaName.getText().toString();
                String newAvatar = insertMangaImage.getText().toString();
                String newDesc = insertMangaDescription.getText().toString();
                MangaDAO mangaDAO = new MangaDAO(EditActivity.this);
                mangaDAO.updateManga(manga.getIdManga(),newName, newAvatar, newDesc);

                boolean[] selectedOptions = adapter.getSelectedOptions();

                for (int i = 0; i < selectedOptions.length; i++) {
                    if (selectedOptions[i]) {
                        String selectedOption = options.get(i); // phieu luu
                        ///
                        int id = listDataDAO.getCategoryIdByName(selectedOption);
                        categoryMangaDAO.insertCategoryManga(id, Integer.parseInt(String.valueOf(manga.getIdManga())));
//
                        Log.d("Selected Category", selectedOption);
                        Log.d("id manga new cate", String.valueOf(id));
                    }
                }

                startActivity(new Intent(EditActivity.this, EditMangaActivity.class));
            }
        });
    }

}