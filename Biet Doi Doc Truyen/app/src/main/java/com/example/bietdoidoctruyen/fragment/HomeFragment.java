package com.example.bietdoidoctruyen.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.ListDataAdapter;
import com.example.bietdoidoctruyen.dao.CategoryMangaDAO;
import com.example.bietdoidoctruyen.dao.ListDataDAO;
import com.example.bietdoidoctruyen.dao.MangaDAO;
import com.example.bietdoidoctruyen.model.Manga;
import com.example.bietdoidoctruyen.model.ListData;

import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphCardView;

public class HomeFragment extends Fragment {
    private RecyclerView rcv_data;
    private ListDataAdapter listDataAdapter;
    private Context context;
    private NeumorphCardView cardItemSearch;


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
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        cardItemSearch = view.findViewById(R.id.card_item_search);
        cardItemSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
                viewPager.setCurrentItem(1);
            }
        });
        // Gọi đến ImageViewSlider trong layout của Fragment
        ImageSlider imageSlider = view.findViewById(R.id.image_slider);

        // Tiến hành setup danh sách ảnh và các thuộc tính khác cho ImageViewSlider
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.poster1, null, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.poster_onepiece, null, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.poster_zed, null, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.poster2, null, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.manga_titan, null, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.poster4, null, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.poster6, null, ScaleTypes.FIT));
        imageList.add(new SlideModel("https://cdn.tuoitre.vn/thumb_w/730/471584752817336320/2023/4/17/conanmovie-1681736137190248042145.jpg", null, ScaleTypes.FIT));

        // Đưa danh sách ảnh vào ImageViewSlider
        imageSlider.setImageList(imageList);

//         Layout main

        rcv_data = view.findViewById(R.id.rcv_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rcv_data.setLayoutManager(linearLayoutManager);

        MangaDAO mangaDAO = new MangaDAO(context);
        List<Manga> mangaList = mangaDAO.getAll();
        listDataAdapter = new ListDataAdapter(context);

        ListData listData = new ListData();
        listData.setListCategory(mangaList);
        listData.setType(ListDataAdapter.TYPE_CATEGORY);
        listData.setCatagoryName("Tên danh mục");
        listDataAdapter.addData(listData);
        listDataAdapter.notifyDataSetChanged();

        // grid layout
        ListData listDataGird = new ListData();
        listDataGird.setListCategory(mangaList);
        listDataGird.setType(ListDataAdapter.TYPE_ITEM);
        listDataGird.setCatagoryName("Tên danh mục");
        listDataAdapter.addData(listDataGird);
        listDataAdapter.notifyDataSetChanged();

        //
        int categoryCheck = 1;
        List<Manga> mangaListGood = new ArrayList<>();
        ListDataDAO listDataDAO = new ListDataDAO(context);

        CategoryMangaDAO categoryMangaDAO = new CategoryMangaDAO(context);

        mangaListGood = categoryMangaDAO.getMangaByCategoryId(categoryCheck);
        ListData listDataGood = new ListData();
        listDataGood.setListCategory(mangaListGood);
        listDataGood.setType(ListDataAdapter.TYPE_CATEGORY);

        String categoryName = listDataDAO.getCategoryNameById(categoryCheck);
        listDataGood.setCatagoryName(categoryName);
        listDataAdapter.addData(listDataGood);
        listDataAdapter.notifyDataSetChanged();


        categoryCheck = 3;
        List<Manga> mangaColor = new ArrayList<>();
        listDataDAO = new ListDataDAO(context);
        mangaColor = categoryMangaDAO.getMangaByCategoryId(categoryCheck);
        ListData listMangaColor = new ListData();
        listMangaColor.setListCategory(mangaColor);
        listMangaColor.setType(ListDataAdapter.TYPE_CATEGORY);

        categoryName = listDataDAO.getCategoryNameById(categoryCheck);
        listMangaColor.setCatagoryName(categoryName);
        listDataAdapter.addData(listMangaColor);
        listDataAdapter.notifyDataSetChanged();


        categoryCheck = 4;
        List<Manga> mangaChildren = new ArrayList<>();
        listDataDAO = new ListDataDAO(context);
        mangaChildren = listDataDAO.getMangaByCategoryId(categoryCheck);
        ListData listMangaChilren = new ListData();
        listMangaChilren.setListCategory(mangaChildren);
        listMangaChilren.setType(ListDataAdapter.TYPE_CATEGORY);

        categoryName = listDataDAO.getCategoryNameById(categoryCheck);
        listMangaChilren.setCatagoryName(categoryName);
        listDataAdapter.addData(listMangaChilren);
        listDataAdapter.notifyDataSetChanged();

        rcv_data.setAdapter(listDataAdapter);

        return view;
    }
}
