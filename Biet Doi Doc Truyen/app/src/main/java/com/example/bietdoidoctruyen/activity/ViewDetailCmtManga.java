package com.example.bietdoidoctruyen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.ManagementCommentAdapter;
import com.example.bietdoidoctruyen.dao.CommentDAO;
import com.example.bietdoidoctruyen.model.CommentWithUserInfo;
import com.example.bietdoidoctruyen.model.Manga;

import java.util.List;

public class ViewDetailCmtManga extends AppCompatActivity {

    private ListView lvCmt;
    private Manga manga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail_cmt_manga);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        manga = (Manga) bundle.get("object_manga");
        CommentDAO commentDAO = new CommentDAO(ViewDetailCmtManga.this);
        List<CommentWithUserInfo> list = commentDAO.getCommentsWithUserInfo(manga.getIdManga());


        lvCmt = findViewById(R.id.lv_cmt);

        ManagementCommentAdapter managementCommentAdapter = new ManagementCommentAdapter(ViewDetailCmtManga.this, list);

        lvCmt.setAdapter(managementCommentAdapter);

    }
}