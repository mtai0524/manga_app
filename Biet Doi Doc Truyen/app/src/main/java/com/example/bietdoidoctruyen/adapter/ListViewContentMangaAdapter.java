package com.example.bietdoidoctruyen.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.dao.ChapterDAO;
import com.example.bietdoidoctruyen.dao.MangaContentDAO;
import com.example.bietdoidoctruyen.model.Chapter;

import java.util.List;

public class ListViewContentMangaAdapter extends BaseAdapter {
    Context context;
    List<Chapter> chapterList;
    public ListViewContentMangaAdapter(Context context, List<Chapter> chapterList){
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
        ListViewContentMangaViewHolder holder;
        if(view == null){
            holder = new ListViewContentMangaViewHolder();
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.item_add_content, null);
            holder.name = view.findViewById(R.id.tv_name_chapter);
            holder.path = view.findViewById(R.id.et_path_content);
            holder.btnContent = view.findViewById(R.id.btn_edit_add_content);
            holder.btnTxt = view.findViewById(R.id.btn_edit_add_content_txt);



//            RegisterDAO registerDAO = new RegisterDAO(context);
//
//
            view.setTag(holder);
        }
        else{
            holder = (ListViewContentMangaViewHolder)view.getTag();
        }
        ChapterDAO chapterDAO = new ChapterDAO(context);
        holder.name.setText(chapterList.get(i).getChapterName());
        MangaContentDAO mangaContentDAO = new MangaContentDAO(context);
        String getPathByChapterId = "";
        getPathByChapterId = mangaContentDAO.getImgContentByChapterContentId(chapterList.get(i).getChapterId());
        holder.path.setText(getPathByChapterId);
        if(holder.path.getText().toString().equals("")){
            getPathByChapterId = mangaContentDAO.getTxtContentByChapterContentId(chapterList.get(i).getChapterId());
            holder.path.setText(getPathByChapterId);
        }

        holder.btnContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.path.getText().toString().equals("")) {
                    String pathContent = holder.path.getText().toString();
                    mangaContentDAO.addOrUpdateContentByChapterId(chapterList.get(i).getChapterId(), pathContent, "");
                    String getPathByChapterId = mangaContentDAO.getImgContentByChapterContentId(chapterList.get(i).getChapterId());
                    holder.path.setText(getPathByChapterId);
                    notifyDataSetChanged();
                }
            }
        });

        holder.btnTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.path.getText().toString().equals("")) {
                    String pathContent = holder.path.getText().toString();
                    mangaContentDAO.addOrUpdateContentByChapterId(chapterList.get(i).getChapterId(), "", pathContent);
                    String getPathByChapterId = mangaContentDAO.getTxtContentByChapterContentId(chapterList.get(i).getChapterId());
                    holder.path.setText(getPathByChapterId);
                    notifyDataSetChanged();
                }
            }
        });

        return view;
    }
}
class ListViewContentMangaViewHolder{
    TextView name;
    EditText path;
    Button btnContent;

    Button btnTxt;
}