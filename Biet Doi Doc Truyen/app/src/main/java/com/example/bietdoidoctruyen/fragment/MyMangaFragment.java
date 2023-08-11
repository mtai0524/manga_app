package com.example.bietdoidoctruyen.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.bietdoidoctruyen.AddMangaViewActivity;
import com.example.bietdoidoctruyen.AddMangaViewActivity;
import com.example.bietdoidoctruyen.EditMangaActivity;
import com.example.bietdoidoctruyen.LoginActivity;
import com.example.bietdoidoctruyen.MangaListSingleton;
import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.ListDataAdapter;
import com.example.bietdoidoctruyen.dao.HistoryDAO;
import com.example.bietdoidoctruyen.dao.MangaDAO;
import com.example.bietdoidoctruyen.dao.RegisterDAO;
import com.example.bietdoidoctruyen.model.History;
import com.example.bietdoidoctruyen.model.ListData;
import com.example.bietdoidoctruyen.model.Manga;
import com.google.android.gms.cast.framework.media.ImagePicker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyMangaFragment extends Fragment {
    private RecyclerView rcv_data;
    private ListDataAdapter listDataAdapter;
    private Context context;
    private TextView tvUserName;
    private TextView tvLogout;
    private ImageView imgAvatarUser;
    private TextView addManga;

    private boolean isEdit;

    public boolean getEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }


    public static final Set<Manga> mangaHistoryList = new HashSet<>();
    private static final int REQUEST_IMAGE_PICKER = 1001;
    private Uri imageUri;
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                    super.onActivityResult(requestCode, resultCode, data);
                    if (requestCode == REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK && data != null) {
                        Uri selectedImageUri = data.getData();
                        if (selectedImageUri != null) {
                            // Gắn ảnh lên ImageView
                            imgAvatarUser.setImageURI(selectedImageUri);
                // Lưu giữ ảnh vào biến imageUri
                imageUri = selectedImageUri;

                int userId = LoginActivity.getUserId();
                RegisterDAO registerDAO = new RegisterDAO(context);
                boolean success = registerDAO.updateAvatarUri(userId, String.valueOf(imageUri));

                if (success) {
                    // Lưu địa chỉ ảnh thành công
                    Log.d("Save Avatar", "Image Uri saved successfully");
                } else {
                    // Lưu địa chỉ ảnh thất bại
                    Log.d("Save Avatar", "Failed to save Image Uri");
                }
            }

            // Xử lý ảnh được chọn ở đây
        }
    }

    private void openImagePicker() {
        com.github.dhaval2404.imagepicker.ImagePicker.Companion.with(this)
                                                                .crop() // Có thể crop ảnh sau khi chọn
                                                                .compress(1024) // Giới hạn kích thước ảnh (tính bằng KB)
                                                                .maxResultSize(1080, 1080) // Giới hạn kích thước ảnh sau khi crop
                                                                .start(REQUEST_IMAGE_PICKER);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (imageUri != null) {
            outState.putString("imageUri", imageUri.toString());
        }
    }

    private List<Manga> getMangaHistoryList() { // casting Set to List
        return new ArrayList<>(mangaHistoryList);
    }
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(LoginActivity.getUserId() == 1){
            view = inflater.inflate(R.layout.fragment_admin_manga, container, false);
            // admin
            TextView tv = view.findViewById(R.id.tv_admin_role);
            tv.setText("admin cdmm");
            addManga = view.findViewById(R.id.tv_addManga); //nó là của ad
            addManga.setOnClickListener(view1 -> {
                startActivity((new Intent(context, AddMangaViewActivity.class)));
            });

            TextView tvEditManga = view.findViewById(R.id.tv_edit_manga);
            tvEditManga.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isEdit = true;
                    startActivity(new Intent(context, EditMangaActivity.class));
                }
            });


            // them sua xoa chuyen vai tro
        }
        else{
            view = inflater.inflate(R.layout.fragment_my_manga, container, false);
        }

        // user => giao diện chung
        imgAvatarUser = view.findViewById(R.id.img_avatar_user);
        imgAvatarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();
            }
        });
        if (savedInstanceState != null) {
            String imageUriString = savedInstanceState.getString("imageUri");
            if (imageUriString != null) {
                imageUri = Uri.parse(imageUriString);
                // Gắn ảnh lên ImageView từ Uri đã lưu trước đó
                imgAvatarUser.setImageURI(imageUri);
                Log.i("AVATAR", imageUri.toString());
            }
        }
        else {
            // Nếu không có Uri của avatar trong savedInstanceState, lấy từ SQLite dựa vào userId
            int userId = LoginActivity.getUserId();
            RegisterDAO registerDAO = new RegisterDAO(context);
            String avatarUriString = registerDAO.getAvatarUriByUserId(userId);
            if (avatarUriString != null) {
                imageUri = Uri.parse(avatarUriString);
                // Gắn ảnh lên ImageView từ Uri đã lưu trong SQLite
                imgAvatarUser.setImageURI(imageUri);
                Log.i("AVATAR", imageUri.toString());
            }
        }

        tvUserName = view.findViewById(R.id.tv_username);
        String name = LoginActivity.username;
        tvUserName.setText(name);

        tvLogout = view.findViewById(R.id.tv_logout);
        tvLogout.setOnClickListener(v ->{
            startActivity(new Intent(context, LoginActivity.class));
        });




        rcv_data = view.findViewById(R.id.rcv_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rcv_data.setLayoutManager(linearLayoutManager);


        listDataAdapter = new ListDataAdapter(context);

        ListData listData = new ListData();
        listData.setListCategory(null);
        listData.setType(ListDataAdapter.TYPE_HISTORY_HORI);
        listDataAdapter.addData(listData);
        listDataAdapter.notifyDataSetChanged();

        rcv_data.setAdapter(listDataAdapter);

        return view;
    }
}
