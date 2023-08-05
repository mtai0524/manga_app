package com.example.bietdoidoctruyen.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.bietdoidoctruyen.DataChapters;
//import com.example.bietdoidoctruyen.DetailActivity;
import com.bumptech.glide.Glide;
import com.example.bietdoidoctruyen.DataChapters;
import com.example.bietdoidoctruyen.DetailActivity;
import com.example.bietdoidoctruyen.LoginActivity;
import com.example.bietdoidoctruyen.MangaListSingleton;
import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.dao.HistoryDAO;
import com.example.bietdoidoctruyen.fragment.HistoryFragment;
import com.example.bietdoidoctruyen.model.Manga;
import com.example.bietdoidoctruyen.model.Register;

import java.util.ArrayList;
import java.util.List;
public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.CategoryViewHolder> {
    private List<Manga> mListManga;
    private Context mConText;
    public MangaAdapter(Context context){
        this.mConText = context;
    }
    public MangaAdapter(){

    }
    public MangaAdapter(Context conText, List<Manga> mListManga) {
        this.mConText = conText;
        this.mListManga = mListManga;
    }


    public void setData(List<Manga> list){
        this.mListManga = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Manga manga = mListManga.get(position);
        if(manga == null){
            return;
        }

        String imageUrl = manga.getImage();

// Sử dụng Glide để tải hình ảnh từ URL và hiển thị lên ImageView
        Glide.with(mConText)
                .load(imageUrl)
                .into(holder.imgCategory);

//        String imgName = manga.getImage();
//        int res = 0;
//        if (mConText != null) {
//            res = mConText.getResources().getIdentifier(imgName, "drawable", mConText.getPackageName());
//        }
//        holder.imgCategory.setImageResource(res);


        holder.tvDescriptionItem.setText(manga.getMangaName());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mConText, "clicked", Toast.LENGTH_SHORT).show();
                onClickShowDetail(manga);
                HistoryFragment.mangaHistoryList.add(manga);
//                MangaListSingleton.getInstance().addManga(manga);
                int userId = LoginActivity.getUserId();
                int mangaId = manga.getIdManga(); // Điền mã manga của manga được click
                HistoryDAO historyDAO = new HistoryDAO(mConText);
                long result = historyDAO.addMangaToHistory(userId, mangaId);
                if (result != -1) {
                    Toast.makeText(mConText, "Manga added to history!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mConText, "Failed to add manga to history.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        DataChapters dataChapters = new DataChapters(mConText);
        dataChapters.showOnChaptersList(manga);
    }


    private void onClickShowDetail(Manga manga){
        Intent intent = new Intent(mConText, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", manga);
        bundle.putSerializable("object_chapters", new ArrayList<>(manga.getChapterList()));
        intent.putExtras(bundle);
        mConText.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(mListManga != null){
            return mListManga.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCategory;
        TextView tvDescriptionItem;
        LinearLayout layoutItem;
        TextView tvChapterName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.img_category);
            tvDescriptionItem = itemView.findViewById(R.id.tv_description_item);
            layoutItem = itemView.findViewById(R.id.layout_item);


        }
    }

}

