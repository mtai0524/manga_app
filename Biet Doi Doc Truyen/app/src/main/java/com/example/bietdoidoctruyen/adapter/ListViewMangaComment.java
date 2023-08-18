package com.example.bietdoidoctruyen.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bietdoidoctruyen.DetailActivity;
import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.ViewDetailCmtManga;
import com.example.bietdoidoctruyen.activity.LoginActivity;
import com.example.bietdoidoctruyen.dao.RegisterDAO;
import com.example.bietdoidoctruyen.model.Manga;
import com.example.bietdoidoctruyen.model.Register;

import java.util.ArrayList;
import java.util.List;

public class ListViewMangaComment extends BaseAdapter {

    Context context;
    List<Manga> mangaList;

    public ListViewMangaComment(Context context, List<Manga> mangaList){
        this.context = context;
        this.mangaList = mangaList;
    }
    @Override
    public int getCount() {
        return mangaList.size();
    }

    @Override
    public Object getItem(int i) {
        return mangaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListViewMangaViewHolder holder;
        if(view == null){
            holder = new ListViewMangaViewHolder();
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.item_manga_cmt, null);
            holder.img_category = view.findViewById(R.id.img_category);
            holder.tv_description_item = view.findViewById(R.id.tv_description_item);
            holder.btn_cmt = view.findViewById(R.id.btn_cmt);
            view.setTag(holder);
        }
        else{
            holder = (ListViewMangaViewHolder)view.getTag();
        }

        String imageUrl = mangaList.get(i).getImage();
        Glide.with(context)
                .load(imageUrl)
                .into(holder.img_category);

        holder.tv_description_item.setText(mangaList.get(i).getMangaName());

        holder.btn_cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickShowDetail(mangaList.get(i));
            }
        });

        return view;
    }
    private void onClickShowDetail(Manga manga){
        Intent intent = new Intent(context, ViewDetailCmtManga.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_manga", manga);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
class ListViewMangaViewHolder{
    Button btn_cmt;
    ImageView img_category;
    TextView tv_description_item;
}
