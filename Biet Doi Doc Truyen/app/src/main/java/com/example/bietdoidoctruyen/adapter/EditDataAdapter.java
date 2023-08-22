package com.example.bietdoidoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.model.ListData;
import com.example.bietdoidoctruyen.model.Manga;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EditDataAdapter extends RecyclerView.Adapter<EditDataAdapter.ListViewHolder> {
    public static final int TYPE_CATEGORY = 1;
    private Context mContext;

    private List<ListData> mListData = new ArrayList<>();
    private List<Manga> mangaList;
    Set<Manga> mangaHistoryList = new HashSet<>();
    private List<Manga> getMangaHistoryList() { // casting Set to List
        return new ArrayList<>(mangaHistoryList);
    }

    public EditDataAdapter(Context context) {
        mContext = context;
    }

    public void setData(Context mContext, List<ListData> list){
        this.mListData = list;
        this.mContext = mContext;
        notifyDataSetChanged();
    }
    public int getItemViewType(int position) {
        this.mContext = mContext;
        return mListData.get(position).getType();
    }

    public void addData(ListData data) {
        mListData.add(data);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_data_edit, parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ListData listData = mListData.get(position);
        if (listData == null) return;

        if (TYPE_CATEGORY == holder.getItemViewType()) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
            holder.rcv_item_edit.setLayoutManager(linearLayoutManager);
            EditMangaAdapter mangaAdapter = new EditMangaAdapter(mContext, listData.getListCategory()); // getListCategory
            mangaAdapter.setData(listData.getListCategory());
            holder.tvNameCategory.setText(listData.getCatagoryName());
            holder.rcv_item_edit.setAdapter(mangaAdapter);
            holder.rcv_item_edit.setFocusable(false);
        }
    }

    public void release(){
        mContext = null;
    }
    @Override
    public int getItemCount() {
        if(mListData != null){
            return mListData.size();
        }
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView rcv_item_edit;
        private TextView tvNameCategory;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            rcv_item_edit = itemView.findViewById(R.id.rcv_item_edit);
            tvNameCategory = itemView.findViewById(R.id.tv_category_name);
        }
    }
}
