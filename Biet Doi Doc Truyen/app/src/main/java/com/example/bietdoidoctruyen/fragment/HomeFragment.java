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
        CategoryMangaDAO categoryMangaDAO = new CategoryMangaDAO(context);
        rcv_data = view.findViewById(R.id.rcv_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rcv_data.setLayoutManager(linearLayoutManager);

        ListDataDAO listDataDAO = new ListDataDAO(context);
        List<ListData> categories = listDataDAO.getAllCategories();

        ListDataAdapter listDataAdapter = new ListDataAdapter(context);

        for (ListData category : categories) {
            List<Manga> mangaList = categoryMangaDAO.getMangaByCategoryId(category.getCategoryId());

            ListData listData = new ListData();
            listData.setListCategory(mangaList);
            listData.setType(category.getType());
            listData.setCatagoryName(category.getCatagoryName());

            listDataAdapter.addData(listData);
        }

        rcv_data.setAdapter(listDataAdapter);

        return view;
    }
}
