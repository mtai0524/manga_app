package com.example.bietdoidoctruyen.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.ListDataAdapter;
import com.example.bietdoidoctruyen.dao.MangaDAO;
import com.example.bietdoidoctruyen.model.ListData;
import com.example.bietdoidoctruyen.model.Manga;

import java.util.List;

public class FavoriteFragment extends Fragment {
    private EditText etSearch;
    private Button btnSearch;
    private ListDataAdapter listDataAdapter;
    private Context context;
    private RecyclerView rcv_data;
    List<Manga> mangaList;

    private void searchManga(String searchText) {
        MangaDAO mangaDAO = new MangaDAO(context);
        mangaList = mangaDAO.getMangaByName(etSearch.getText().toString());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rcv_data.setLayoutManager(linearLayoutManager);
        listDataAdapter = new ListDataAdapter(context);
        ListData listData = new ListData();
        listData.setListCategory(mangaList);
        listData.setType(ListDataAdapter.TYPE_SEARCH);
        listDataAdapter.addData(listData);
        listDataAdapter.notifyDataSetChanged();
        rcv_data.setAdapter(listDataAdapter);
    }

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        etSearch = view.findViewById(R.id.et_search);
        rcv_data = view.findViewById(R.id.rcv_data);
        etSearch = view.findViewById(R.id.et_search);
        searchManga(" ");


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString();
                // Gọi hàm tìm kiếm truyện ở đây với chuỗi searchText
                searchManga(searchText);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        etSearch.requestFocus();
    }
}
