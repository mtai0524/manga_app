package com.example.bietdoidoctruyen.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.bietdoidoctruyen.DetailActivity;
//import com.example.bietdoidoctruyen.activity.MangaContentActivity;
import com.example.bietdoidoctruyen.activity.MangaContentActivity;
import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.model.Chapter;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {
    private List<Chapter> chapterList;
    private Context mContext;

    public ChapterAdapter(Context context, List<Chapter> list) {
        this.mContext = context;
        this.chapterList = list;
    }
    public ChapterAdapter(Context context){
        this.mContext = context;
    }
    public ChapterAdapter(){

    }
    public void setData(List<Chapter> list){
        this.chapterList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chapter chapter = chapterList.get(position);
        if(chapter == null) return;
        holder.tvChapterName.setText(chapter.getChapterName());
        holder.layoutContainerChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMangaContent(chapter);
            }
        });
    }
    private void showMangaContent(Chapter chapter) {
        Intent intent = new Intent(mContext, MangaContentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CHAPTER_ID", chapter); // Đóng gói chapterId vào Bundle
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(chapterList != null){
            return chapterList.size();
        }
        return 0;
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layoutContainerChapter;
        TextView tvChapterName;
        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapterName = itemView.findViewById(R.id.tv_chapter_name);
            layoutContainerChapter = itemView.findViewById(R.id.layout_container_chapter);
        }
    }
}
