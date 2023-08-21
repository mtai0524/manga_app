package com.example.bietdoidoctruyen.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.CheckBoxCateAdapter;
import com.example.bietdoidoctruyen.dao.CategoryMangaDAO;
import com.example.bietdoidoctruyen.dao.ListDataDAO;
import com.example.bietdoidoctruyen.dao.MangaDAO;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AddMangaFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private EditText insertMangaName;
    private EditText insertMangaImage;
    private EditText insertMangaDescription;
    private Button btnAddManga;
    String name = "";
    String image = "";
    String cate = "";
    String desc = "";
    MangaDAO mangaDAO;
    private Context context;

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }
    public void goToMainActivity() {
        // Lấy tham chiếu đến Activity chứa Fragment
        Activity activity = getActivity();
        if (activity != null) {
            // Sử dụng FragmentManager để quay trở lại Activity
            FragmentManager fragmentManager = activity.getFragmentManager();

            // Quay trở lại Fragment gốc (back stack sẽ bị xóa hết)
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_manga, container, false);

        insertMangaName = view.findViewById(R.id.et_mangaName);
        insertMangaImage = view.findViewById(R.id.et_mangaCover);
        insertMangaDescription = view.findViewById(R.id.et_mangaDescription);
        btnAddManga = view.findViewById(R.id.addBtn);
        mangaDAO = new MangaDAO(context);
        CategoryMangaDAO categoryMangaDAO = new CategoryMangaDAO(context);
        RecyclerView recyclerView = view.findViewById(R.id.rcv_cb_cate);
        ListDataDAO listDataDAO = new ListDataDAO(context);
        List<String> options = new ArrayList<>();
        List<Integer> listIdCategoryManga = new ArrayList<>();
        listDataDAO.getCategoryName().forEach(cate -> {
            options.add(cate.getCatagoryName());
//            listIdCategoryManga.add(cate.getCategoryId());
        });

        CheckBoxCateAdapter adapter = new CheckBoxCateAdapter(options);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);



        btnAddManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = insertMangaName.getText().toString();
                image = insertMangaImage.getText().toString();
                desc = insertMangaDescription.getText().toString();
                if(name.equals("") || image.equals("") || desc.equals("")){
                    Toast.makeText(context, "nhập thiếu kìa", Toast.LENGTH_SHORT).show();
                    return;
                }
                long idMangaNew =  mangaDAO.insertManga(name, image, desc); // tạo manga lưu vào sqlite , sau đó trả id vừa được tạo
                Log.i("data", String.format("Name: %s, Image: %s, Desc: %s", name, image, desc));


                boolean[] selectedOptions = adapter.getSelectedOptions();

                for (int i = 0; i < selectedOptions.length; i++) {
                    if (selectedOptions[i]) {
                        String selectedOption = options.get(i); // phieu luu
                        ///
                        int id = listDataDAO.getCategoryIdByName(selectedOption);
                        categoryMangaDAO.insertCategoryManga(id, Integer.parseInt(String.valueOf(idMangaNew)));
//
                        Log.d("Selected Category", selectedOption);
                        Log.d("id manga new cate", String.valueOf(id));
                    }
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(context, "thêm truyện thành công", Toast.LENGTH_SHORT).show();
                goToMainActivity();
            }
        });
        return view;
    }
}