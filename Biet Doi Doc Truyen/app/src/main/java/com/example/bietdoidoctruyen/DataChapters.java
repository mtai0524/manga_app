package com.example.bietdoidoctruyen;

import android.content.Context;

import com.example.bietdoidoctruyen.dao.MangaDAO;
import com.example.bietdoidoctruyen.dao.ChapterDAO;
import com.example.bietdoidoctruyen.model.Manga;
import com.example.bietdoidoctruyen.model.Chapter;

import java.util.List;

public class DataChapters {
    Context mContext;
    MangaDAO mangaDAO;
    ChapterDAO chapterDAO;
    List<Chapter> chaptersForManga;
    public DataChapters(Context context){
        this.mContext = context;
    }
    public void showOnChaptersList(Manga manga){
        mangaDAO = new MangaDAO(mContext);
        chapterDAO = new ChapterDAO(mContext);
        List<Manga> categories = mangaDAO.getAll();
        for (Manga c : categories) {
            int mangaIdFromCategory = manga.getIdManga();
             chaptersForManga = chapterDAO.getChaptersForManga(mangaIdFromCategory);
        }
        if (chaptersForManga != null) {
            manga.setChapterList(chaptersForManga);
        }
    }
}
