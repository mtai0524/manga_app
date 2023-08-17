package com.example.bietdoidoctruyen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.bietdoidoctruyen.adapter.ListViewChapterAdapter;
import com.example.bietdoidoctruyen.adapter.ListViewContentMangaAdapter;
import com.example.bietdoidoctruyen.dao.ChapterDAO;
import com.example.bietdoidoctruyen.dao.MangaDAO;
import com.example.bietdoidoctruyen.model.Chapter;

import java.util.List;

public class AddContentFragment extends Fragment {
    private ListView lvContent;
    private Context context;
    private int selectedMangaId;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_content, container, false);
        lvContent = view.findViewById(R.id.lv_content);
        Spinner spinnerManga = view.findViewById(R.id.spinner_manga_name);
        MangaDAO mangaDAO = new MangaDAO(context);
        List<String> listMangaName = mangaDAO.getAllMangaNames();
        List<Integer> listIdManga = mangaDAO.getAllMangaIds();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listMangaName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerManga.setAdapter(adapter);

        spinnerManga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedMangaName = listMangaName.get(i); // Tên manga đã được chọn
                selectedMangaId = listIdManga.get(i); // Id của manga đã được chọn

                // Truy vấn danh sách chapter dựa trên selectedMangaId
                ChapterDAO chapterDAO = new ChapterDAO(context);
                List<Chapter> chapters = chapterDAO.getChaptersForManga(selectedMangaId);
                ListViewContentMangaAdapter chapterAdapter = new ListViewContentMangaAdapter(context, chapters);
                lvContent.setAdapter(chapterAdapter);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { // do override abstract nên k xóa được
                // Không làm gì khi không có item nào được chọn
            }
        });
        return view;
    }
}