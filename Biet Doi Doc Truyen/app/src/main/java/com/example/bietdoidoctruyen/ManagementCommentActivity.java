package com.example.bietdoidoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.bietdoidoctruyen.adapter.ListViewMangaComment;
import com.example.bietdoidoctruyen.adapter.ManagementCommentAdapter;
import com.example.bietdoidoctruyen.adapter.ManagementUserAdapter;
import com.example.bietdoidoctruyen.dao.CommentDAO;
import com.example.bietdoidoctruyen.dao.MangaDAO;
import com.example.bietdoidoctruyen.dao.RegisterDAO;
import com.example.bietdoidoctruyen.model.CommentWithUserInfo;
import com.example.bietdoidoctruyen.model.Manga;
import com.example.bietdoidoctruyen.model.Register;

import java.util.List;

public class ManagementCommentActivity extends AppCompatActivity {
    private ListView lvCmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_comment);

        MangaDAO mangaDAO = new MangaDAO(ManagementCommentActivity.this);
        List<Manga> list = mangaDAO.getAll();


        lvCmt = findViewById(R.id.lv_cmt);

        ListViewMangaComment listViewMangaComment = new ListViewMangaComment(ManagementCommentActivity.this, list);

        lvCmt.setAdapter(listViewMangaComment);
    }
}