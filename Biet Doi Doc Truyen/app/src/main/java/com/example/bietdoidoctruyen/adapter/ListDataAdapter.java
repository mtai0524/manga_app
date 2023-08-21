package com.example.bietdoidoctruyen.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.bietdoidoctruyen.DataGenarator;
import com.example.bietdoidoctruyen.activity.LoginActivity;
import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.dao.HistoryDAO;
import com.example.bietdoidoctruyen.dao.MangaDAO;
import com.example.bietdoidoctruyen.model.History;
import com.example.bietdoidoctruyen.model.Manga;
import com.example.bietdoidoctruyen.model.ListData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListDataAdapter extends RecyclerView.Adapter<ListDataAdapter.ListViewHolder> {
    public static final int TYPE_CATEGORY = 1;
    public static final int TYPE_CATEGORY_VERTICAL = 3;
    public static final int TYPE_USER = 2;
    public static final int TYPE_ITEM = 4;
    public static final int TYPE_TEST = 5;
    public static final int TYPE_HISTORY = 6;
    public static final int TYPE_HISTORY_HORI = 7;
    public static final int TYPE_SEARCH = 8;
    private Context mContext;

    private List<ListData> mListData = new ArrayList<>();
    private List<Manga> mangaList;
    Set<Manga> mangaHistoryList = new HashSet<>();
    private List<Manga> getMangaHistoryList() { // casting Set to List
        return new ArrayList<>(mangaHistoryList);
    }

    public ListDataAdapter(Context context) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_data, parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ListData listData = mListData.get(position);
        if (listData == null) return;

        if (TYPE_CATEGORY == holder.getItemViewType()) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            holder.rcvItem.setLayoutManager(linearLayoutManager);
            MangaAdapter mangaAdapter = new MangaAdapter(mContext, listData.getListCategory()); // getListCategory
            mangaAdapter.setData(listData.getListCategory());
            holder.tvNameCategory.setText(listData.getCatagoryName());
            holder.rcvItem.setAdapter(mangaAdapter);
            holder.rcvItem.setFocusable(false);

        }
        else if(TYPE_CATEGORY_VERTICAL == holder.getItemViewType()){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
            holder.rcvItem.setLayoutManager(linearLayoutManager);
            MangaAdapter mangaAdapter = new MangaAdapter();
            mangaAdapter.setData(listData.getListCategory());
            holder.tvNameCategory.setText(listData.getCatagoryName());
            holder.rcvItem.setAdapter(mangaAdapter);
            holder.rcvItem.setFocusable(false);
        }
        else if(TYPE_ITEM == holder.getItemViewType()){
            int spanCount = 2; // Số cột trong lưới
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1; // Mỗi item chiếm 1 cột
                }
            });
            holder.rcvItem.setLayoutManager(gridLayoutManager);
            MangaAdapter mangaAdapter = new MangaAdapter(mContext, listData.getListCategory());
            mangaAdapter.setData(listData.getListCategory());
            holder.tvNameCategory.setText(listData.getCatagoryName());
            holder.rcvItem.setAdapter(mangaAdapter);
            holder.rcvItem.setFocusable(false);
        }
        else if(TYPE_SEARCH == holder.getItemViewType()){
            int spanCount = 2; // Số cột trong lưới
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1; // Mỗi item chiếm 1 cột
                }
            });
            holder.rcvItem.setLayoutManager(gridLayoutManager);

            MangaAdapter mangaAdapter = new MangaAdapter(mContext, listData.getListCategory());
            mangaAdapter.setData(listData.getListCategory());
            holder.tvNameCategory.setText(listData.getCatagoryName());
            holder.rcvItem.setAdapter(mangaAdapter);
            holder.rcvItem.setFocusable(false);
        }
        else if(TYPE_HISTORY == holder.getItemViewType()){
            int spanCount = 2; // Số cột trong lưới
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1; // Mỗi item chiếm 1 cột
                }
            });
            holder.rcvItem.setLayoutManager(gridLayoutManager);
            MangaAdapter mangaAdapter = new MangaAdapter(mContext);

            HistoryDAO historyDAO = new HistoryDAO(mContext);
            int userId = LoginActivity.getUserId();
            List<History> historyList = historyDAO.getHistoryByUserId(userId);

            // Tạo một danh sách để chứa các mangaId
            List<Integer> mangaIds = new ArrayList<>();

            // Lặp qua từng đối tượng History trong danh sách historyList và lấy mangaId
            for (History history : historyList) {
                int mangaId = history.getMangaId();
                mangaIds.add(mangaId);
            }

            for (int mangaId : mangaIds) {
                Log.d("Manga ID", String.valueOf(mangaId));
            }

            MangaDAO mangaDAO = new MangaDAO(mContext);
            for (int mangaId : mangaIds) {
                // Gọi hàm getCategoryById để lấy thông tin của manga dựa vào mangaId
                Manga manga = mangaDAO.getCategoryById(mangaId);
                // Đảm bảo rằng manga không null trước khi sử dụng
                if (manga != null) {
                    // Do something với manga (ví dụ: hiển thị thông tin lên màn hình)
                    mangaHistoryList.add(manga);
                }
            }

            mangaAdapter.setData(getMangaHistoryList());
            holder.tvNameCategory.setText(listData.getCatagoryName());
            holder.rcvItem.setAdapter(mangaAdapter);
            holder.rcvItem.setFocusable(false);
        }
        else if(TYPE_HISTORY_HORI == holder.getItemViewType()){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            holder.rcvItem.setLayoutManager(linearLayoutManager);
            MangaAdapter mangaAdapter = new MangaAdapter(mContext);

            HistoryDAO historyDAO = new HistoryDAO(mContext);
            int userId = LoginActivity.getUserId();
            List<History> historyList = historyDAO.getHistoryByUserId(userId);

            // Tạo một danh sách để chứa các mangaId
            List<Integer> mangaIds = new ArrayList<>();

            // Lặp qua từng đối tượng History trong danh sách historyList và lấy mangaId
            for (History history : historyList) {
                int mangaId = history.getMangaId();
                mangaIds.add(mangaId);
            }

            for (int mangaId : mangaIds) {
                Log.d("Manga ID", String.valueOf(mangaId));
            }

            MangaDAO mangaDAO = new MangaDAO(mContext);
            for (int mangaId : mangaIds) {
                // Gọi hàm getCategoryById để lấy thông tin của manga dựa vào mangaId
                Manga manga = mangaDAO.getCategoryById(mangaId);
                // Đảm bảo rằng manga không null trước khi sử dụng
                if (manga != null) {
                    // Do something với manga (ví dụ: hiển thị thông tin lên màn hình)
                    mangaHistoryList.add(manga);
                }
            }

            mangaAdapter.setData(getMangaHistoryList());
            holder.tvNameCategory.setText(listData.getCatagoryName());
            holder.rcvItem.setAdapter(mangaAdapter);
            holder.rcvItem.setFocusable(false);
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
        private RecyclerView rcvItem;
        private TextView tvNameCategory;
        private TextView tvTest;
        int type;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            rcvItem = itemView.findViewById(R.id.rcv_item);
            tvNameCategory = itemView.findViewById(R.id.tv_category_name);
            tvTest = itemView.findViewById(R.id.tv_test);
        }
    }
}
