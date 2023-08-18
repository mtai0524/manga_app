package com.example.bietdoidoctruyen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.MangaContentAdapter;
import com.example.bietdoidoctruyen.dao.ChapterDAO;
import com.example.bietdoidoctruyen.dao.MangaContentDAO;
import com.example.bietdoidoctruyen.model.Chapter;
import com.example.bietdoidoctruyen.model.MangaContent;

import java.util.ArrayList;
import java.util.List;

public class MangaContentActivity extends AppCompatActivity {
    private LinearLayout layoutItemMangaContent;
    private ImageView imgMangaContent;
    private RecyclerView rcvMangaContent;
    private EditText tvMangaTxt;
    private Button btn_pre;
    private Button btn_next;
    int currentChapterIndex = 0;
    Chapter chapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_content);



        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        chapter = (Chapter) bundle.get("CHAPTER_ID");
        MangaContentDAO mangaContentDAOS = new MangaContentDAO(this);
        // khi adapter chỉ có img thì dùng list img còn khi có 2 control thì dùng cả list đối tượng
        List<MangaContent> mangaContentList = mangaContentDAOS.getContentByChapterId(chapter.getChapterId());
//        List<String> imgContentList = new ArrayList<>();
//        if (mangaContentList != null) {
//            // Lặp qua danh sách MangaContent để lấy trường imgContent
//            for (MangaContent mangaContent : mangaContentList) {
//                String imgContent = mangaContent.getImgContent();
//                imgContentList.add(imgContent);
//
//            }
//        } else {
//            Log.d("MangaContentDAO", "MangaContentList is empty or null.");
//        }

//        List<MangaContent> mangaContentListImg = new ArrayList<>();

// Tạo các đối tượng MangaContent từ imgContentList và thêm vào mangaContentList
//        for (String imgContent : imgContentList) {
//            MangaContent mangaContent = new MangaContent();
//            mangaContent.setImgContent(imgContent);
//            mangaContentListImg.add(mangaContent);
//        }



        for (MangaContent mangaContent : mangaContentList) {
            int contentId = mangaContent.getContentId();
            int chapterContentId = mangaContent.getChapterContentId();
            String imgContent = mangaContent.getImgContent();
            String txtContent = mangaContent.getMangaTxt();

            Log.d("COn me may thang android", "ContentId: " + contentId + ", ChapterContentId: " + chapterContentId + ", ImgContent: " + imgContent + "text: " + txtContent);
        }
        layoutItemMangaContent = findViewById(R.id.layout_container_btnChap);
//        imgMangaContent = findViewById(R.id.img_manga_content);
        rcvMangaContent = findViewById(R.id.rcv_manga_content);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvMangaContent.setLayoutManager(linearLayoutManager);

        MangaContentAdapter mangaContentAdapter = new MangaContentAdapter(this);
        //set data cho cả 2 thuộc tính nên dùng cả List manga
        mangaContentAdapter.setData(this, mangaContentList);

        rcvMangaContent.setAdapter(mangaContentAdapter);

        btn_next = findViewById(R.id.btn_next);
        btn_pre = findViewById(R.id.btn_pre);
        ChapterDAO chapterDAO = new ChapterDAO(MangaContentActivity.this);

        List<Integer> listIds = chapterDAO.getChapterIdsByMangaId(chapter.getMangaId());
        Log.i("CHAPTER CAC INTEGER", listIds.toString());
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentChapterIndex < listIds.size()) {
                    int id = listIds.get(currentChapterIndex);
                    List<MangaContent> mangaContentList = mangaContentDAOS.getContentByChapterId(id);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MangaContentActivity.this, RecyclerView.VERTICAL, false);
                    rcvMangaContent.setLayoutManager(linearLayoutManager);

                    MangaContentAdapter mangaContentAdapter = new MangaContentAdapter(MangaContentActivity.this);
                    mangaContentAdapter.setData(MangaContentActivity.this, mangaContentList);
                    rcvMangaContent.setAdapter(mangaContentAdapter);
                    // Tăng chỉ số currentChapterIndex lên để lấy id tiếp theo trong danh sách
                    currentChapterIndex++;
                }
            }
        });

        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentChapterIndex > 0) {
                    int id = listIds.get(currentChapterIndex - 1);
                    List<MangaContent> mangaContentList = mangaContentDAOS.getContentByChapterId(id);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MangaContentActivity.this, RecyclerView.VERTICAL, false);
                    rcvMangaContent.setLayoutManager(linearLayoutManager);

                    MangaContentAdapter mangaContentAdapter = new MangaContentAdapter(MangaContentActivity.this);
                    mangaContentAdapter.setData(MangaContentActivity.this, mangaContentList);
                    rcvMangaContent.setAdapter(mangaContentAdapter);

                    // Giảm chỉ số currentChapterIndex để lấy id trước đó trong danh sách
                    currentChapterIndex--;
                }
            }
        });
    }

    private List<MangaContent> showMangaContent() {
        List<MangaContent> mangaContentList = new ArrayList<>();
        mangaContentList.add(new MangaContent(R.drawable.conan1));
        mangaContentList.add(new MangaContent(R.drawable.conan2));
        mangaContentList.add(new MangaContent(R.drawable.conan3));
        mangaContentList.add(new MangaContent(R.drawable.conan4));
        return mangaContentList;
    }
}
