package com.example.bietdoidoctruyen.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.dao.ChapterDAO;
import com.example.bietdoidoctruyen.model.Chapter;
import com.example.bietdoidoctruyen.model.Register;

import java.util.List;

public class ListViewChapterAdapter extends BaseAdapter {
    Context context;
    List<Chapter> chapterList;
    public ListViewChapterAdapter(Context context, List<Chapter> chapterList){
        this.context = context;
        this.chapterList = chapterList;
    }
    @Override
    public int getCount() {
        return chapterList.size();
    }

    @Override
    public Object getItem(int i) {
        return chapterList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListViewChapterHolder holder;
        if(view == null){
            holder = new ListViewChapterHolder();
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.item_list_chapter, null);
            holder.tv_chapter_name_view = view.findViewById(R.id.tv_chapter_name_view);
            holder.btn_edit_chapter = view.findViewById(R.id.btn_edit_chapter);



//            RegisterDAO registerDAO = new RegisterDAO(context);
//
//
            view.setTag(holder);
        }
        else{
            holder = (ListViewChapterHolder)view.getTag();
        }
        ChapterDAO chapterDAO = new ChapterDAO(context);
        holder.tv_chapter_name_view.setText(chapterList.get(i).getChapterName());
        holder.btn_edit_chapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
class ListViewChapterHolder{
    TextView tv_chapter_name_view;
    Button btn_edit_chapter;
}
