package com.example.bietdoidoctruyen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.EditDataAdapter;
import com.example.bietdoidoctruyen.adapter.ListDataAdapter;
import com.example.bietdoidoctruyen.dao.MangaDAO;
import com.example.bietdoidoctruyen.model.ListData;
import com.example.bietdoidoctruyen.model.Manga;

import java.util.List;

public class ManagementMangaActivity extends AppCompatActivity {
    private RecyclerView recvEditManga;
    EditDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_manga);

        recvEditManga = findViewById(R.id.recv_edit_manga);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recvEditManga.setLayoutManager(linearLayoutManager);

        ListData listDataGird = new ListData();
        MangaDAO mangaDAO = new MangaDAO(this);
        List<Manga> mangaList = mangaDAO.getAll();
        listDataAdapter = new EditDataAdapter(this);

        listDataGird.setListCategory(mangaList);
        listDataGird.setType(EditDataAdapter.TYPE_CATEGORY);
        listDataAdapter.addData(listDataGird);
        listDataAdapter.notifyDataSetChanged();
        recvEditManga.setAdapter(listDataAdapter);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}