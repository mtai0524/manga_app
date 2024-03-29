package com.example.bietdoidoctruyen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.ChapterAdapter;
import com.example.bietdoidoctruyen.dao.ChapterDAO;
import com.example.bietdoidoctruyen.dao.CommentDAO;
import com.example.bietdoidoctruyen.dao.MangaDAO;
import com.example.bietdoidoctruyen.dao.RatingDAO;
import com.example.bietdoidoctruyen.dao.RegisterDAO;
import com.example.bietdoidoctruyen.model.Comment;
import com.example.bietdoidoctruyen.model.Manga;
import com.example.bietdoidoctruyen.model.Chapter;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView tvItem;
    ImageView imgItemDetail;
    RecyclerView rcvChapterList;
    private List<Chapter> mChapters;
    private TextView tvDescription;
    private TextView tvComment;
    private TextView tvRating;
    private MangaDAO mangaDAO = new MangaDAO(DetailActivity.this);
    Manga manga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        manga = (Manga) bundle.get("object_user");
        tvItem = findViewById(R.id.tv_detail_item);
        imgItemDetail = findViewById(R.id.img_item_detail);
        tvDescription = findViewById(R.id.tv_descripion);
        tvRating = findViewById(R.id.tv_rating);
        tvRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnOnPopupRating(Gravity.BOTTOM);
            }
        });
        tvComment = findViewById(R.id.tv_comment);
        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnOnPopupComment(Gravity.BOTTOM);
            }
        });

        tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnOnPopupLogo(Gravity.BOTTOM);
            }
        });

        tvItem.setText(manga.getMangaName());
        String imageUrl = manga.getImage();

// Sử dụng Glide để tải hình ảnh từ URL và hiển thị lên ImageView
        Glide.with(DetailActivity.this)
                .load(imageUrl)
                .into(imgItemDetail);


        mChapters = (List<Chapter>) bundle.getSerializable("object_chapters");
        if (mChapters != null) {
            mChapters.forEach(chapter -> Log.d("Chapter", chapter.getChapterName()));
        }



        ChapterDAO chapterDAO = new ChapterDAO(DetailActivity.this);
        List<Chapter> chapterList = chapterDAO.getChaptersForManga(manga.getIdManga());

        rcvChapterList = findViewById(R.id.rcv_chapter_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvChapterList.setLayoutManager(linearLayoutManager);
        ChapterAdapter chapterAdapter = new ChapterAdapter(this);
        chapterAdapter.setData(chapterList);
        rcvChapterList.setAdapter(chapterAdapter);
    }

    private void updateDialogContent() {
        List<Comment> commentList = commentDAO.getCommentByMangaId(manga.getIdManga());
        StringBuilder stringBuilder = new StringBuilder();
        RegisterDAO registerDAO = new RegisterDAO(DetailActivity.this);

        for (Comment comment : commentList) {
            String name = registerDAO.getUsernameByUserId(comment.getUserId());
            String myName = "tôi";
            String nameString = name;

            if (LoginActivity.getUserId() == comment.getUserId()) {
                nameString = myName;
            }

            SpannableString spannableName = new SpannableString(nameString);
            spannableName.setSpan(new RelativeSizeSpan(0.8f), 0, nameString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            stringBuilder.append(spannableName).append(" hiện lên và nói:\n");
            stringBuilder.append(comment.getComment()).append("\n\n");
        }

        String resultString = stringBuilder.toString();
        tvContent.setText(resultString);
    }
    TextView tvContent;
    CommentDAO commentDAO;
    RatingDAO ratingDAO;
    TextView tvUserCmt;
    RatingBar ratingBar;
    RegisterDAO registerDAO = new RegisterDAO(DetailActivity.this);
    float ratingStart;
    String getCmt = "";
    String cmt = "";

    private void turnOnPopupRating(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_rating);
        tvContent = dialog.findViewById(R.id.tvContent);
        tvUserCmt = dialog.findViewById(R.id.tv_user_cmt);
        ratingBar = dialog.findViewById(R.id.rating_bar);
        ratingStart = ratingBar.getRating();

        ratingDAO = new RatingDAO(DetailActivity.this);

        float getRating = ratingDAO.getRatingForMangaAndUser(LoginActivity.getUserId(), manga.getIdManga());
        if(getRating!=0){
            ratingBar.setRating(getRating);
        }

        getCmt = ratingDAO.getCommentForMangaAndUser(LoginActivity.getUserId(), manga.getIdManga());
        if(!getCmt.equals("")){
            tvContent.setText(getCmt);
        }

//        tvContent.setText(spannedText);
        Window window = dialog.getWindow();
        if (window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = gravity;
        window.setAttributes(windowAttr);
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        }
        EditText etCmt = dialog.findViewById(R.id.et_cmt);
        Button btCmt = dialog.findViewById(R.id.btn_cmt);
        btCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cmt = etCmt.getText().toString();
                ratingStart = ratingBar.getRating();

                if(!getCmt.equals("")){
                    ratingDAO.addOrUpdateRating(LoginActivity.getUserId(), manga.getIdManga(), ratingStart, cmt);
                    Toast.makeText(DetailActivity.this, "đã cập nhật đánh giá", Toast.LENGTH_SHORT).show();
                }
                else{
                    ratingDAO.insertRating(LoginActivity.getUserId(), manga.getIdManga(), ratingStart, cmt);
                    Log.i("userId", String.valueOf(LoginActivity.getUserId()));
                    Log.i("mangaId", String.valueOf(manga.getIdManga()));
                    Log.i("rating", String.valueOf(ratingStart));
                    Log.i("cmt", String.valueOf(cmt));
                    Toast.makeText(DetailActivity.this, "đã đánh giá", Toast.LENGTH_SHORT).show();

                }
                getCmt = ratingDAO.getCommentForMangaAndUser(LoginActivity.getUserId(), manga.getIdManga());
                tvContent.setText(getCmt);
                etCmt.setText("");
                etCmt.setFocusable(true);
            }
        });


        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    private void turnOnPopupComment(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_comment);
        tvContent = dialog.findViewById(R.id.tvContent);
        tvUserCmt = dialog.findViewById(R.id.tv_user_cmt);
        commentDAO = new CommentDAO(DetailActivity.this);

        updateDialogContent();

        tvUserCmt.setText("Bình luận\n" + registerDAO.getUsernameByUserId(LoginActivity.getUserId()));
        Window window = dialog.getWindow();
        if (window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = gravity;
        window.setAttributes(windowAttr);
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        }
        EditText etCmt = dialog.findViewById(R.id.et_cmt);
        Button btCmt = dialog.findViewById(R.id.btn_cmt);
        btCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmt = etCmt.getText().toString();
                commentDAO.addComment(LoginActivity.getUserId(), manga.getIdManga(), cmt);
                Toast.makeText(DetailActivity.this, "da binh luan", Toast.LENGTH_SHORT).show();
                updateDialogContent();
                etCmt.setText("");
                etCmt.setFocusable(true);
            }
        });
        dialog.show();
    }


    private void turnOnPopupLogo(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_popup);
        TextView tvContent = dialog.findViewById(R.id.tvContent);

        tvContent.setText(mangaDAO.getDescriptionByMangaId(manga.getIdManga()));
        Window window = dialog.getWindow();
        if (window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = gravity;
        window.setAttributes(windowAttr);
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        }
        dialog.show();
    }
}