package com.example.bietdoidoctruyen.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.ListDataAdapter;
import com.example.bietdoidoctruyen.model.ListData;
import com.example.bietdoidoctruyen.model.Manga;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HistoryFragment extends Fragment {
    private RecyclerView rcv_data;
    private ListDataAdapter listDataAdapter;
    private Context context;

    public static final Set<Manga> mangaHistoryList = new HashSet<>();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (listDataAdapter != null) {
            listDataAdapter.release();
        }
    }

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

    private List<Manga> getMangaHistoryList() { // casting Set to List
        return new ArrayList<>(mangaHistoryList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        rcv_data = view.findViewById(R.id.rcv_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rcv_data.setLayoutManager(linearLayoutManager);

        listDataAdapter = new ListDataAdapter(context);

        ListData listData = new ListData();
        listData.setListCategory(null);
        listData.setType(ListDataAdapter.TYPE_HISTORY);
        listData.setCatagoryName("Lịch sử truyện đã xem");
        listDataAdapter.addData(listData);
        listDataAdapter.notifyDataSetChanged();

        rcv_data.setAdapter(listDataAdapter);

        return view;
    }
}
