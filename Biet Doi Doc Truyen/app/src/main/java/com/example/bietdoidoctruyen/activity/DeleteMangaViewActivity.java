package com.example.bietdoidoctruyen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.CheckBoxDelMangaByCateAdapter;
import com.example.bietdoidoctruyen.dao.CategoryMangaDAO;
import com.example.bietdoidoctruyen.dao.ListDataDAO;
import com.example.bietdoidoctruyen.model.Manga;

import java.util.ArrayList;
import java.util.List;

public class DeleteMangaViewActivity extends AppCompatActivity {

    private Button btnDeleteMangaDb;
    private Manga manga;
    private TextView tvMangaName;
    public static List<Integer> listCateIds = new ArrayList<>();
    CategoryMangaDAO categoryMangaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_manga_view);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        manga = (Manga) bundle.get("object_user");

        tvMangaName = findViewById(R.id.tv_manga_name);
        tvMangaName.setText(manga.getMangaName());


        RecyclerView recyclerView = findViewById(R.id.rcv_cb_cate);
        ListDataDAO listDataDAO = new ListDataDAO(this);
        List<String> options = new ArrayList<>();
        listDataDAO.getCategoryName().forEach(cate -> {
            options.add(cate.getCatagoryName());
        });

        categoryMangaDAO = new CategoryMangaDAO(this);

        listCateIds = categoryMangaDAO.getCategoryIdsByMangaId(manga.getIdManga());

        CheckBoxDelMangaByCateAdapter adapter = new CheckBoxDelMangaByCateAdapter(DeleteMangaViewActivity.this ,options, listCateIds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        btnDeleteMangaDb = findViewById(R.id.btn_delete_manga_db);
        btnDeleteMangaDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 0;
                do {
                    if (!adapter.getSelectedOptions()[i]) {
                        int checkIn = listCateIds.get(i);
                        categoryMangaDAO.removeMangaByCategoryIdAndMangaId(checkIn, manga.getIdManga());
                        Toast.makeText(DeleteMangaViewActivity.this, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                    i++;
                } while (i < adapter.getSelectedOptions().length);

            }
        });
    }

}