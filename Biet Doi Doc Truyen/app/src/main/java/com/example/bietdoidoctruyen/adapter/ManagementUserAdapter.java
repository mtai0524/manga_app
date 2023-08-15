package com.example.bietdoidoctruyen.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.activity.LoginActivity;
import com.example.bietdoidoctruyen.dao.ListDataDAO;
import com.example.bietdoidoctruyen.dao.RegisterDAO;
import com.example.bietdoidoctruyen.model.ListData;
import com.example.bietdoidoctruyen.model.Register;

import java.util.List;

public class ManagementUserAdapter extends BaseAdapter {
    Context context;
    List<Register> listUser;
    public ManagementUserAdapter(Context context, List<Register> listUser){
        this.context = context;
        this.listUser = listUser;
    }
    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int i) {
        return listUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ManagementUserViewHolder holder;
        if(view == null){
            holder = new ManagementUserViewHolder();
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.item_management_user, null);
            holder.avatar = view.findViewById(R.id.img_avatar_user);
            holder.name = view.findViewById(R.id.tv_name_user);

            holder.role = view.findViewById(R.id.tv_role_user);
            holder.btnEditUser = view.findViewById(R.id.btn_edit_user);
            holder.btnChangeRole = view.findViewById(R.id.btn_role_user);
            holder.btnDelUser = view.findViewById(R.id.btn_delete_user);

//            RegisterDAO registerDAO = new RegisterDAO(context);
//
//
            view.setTag(holder);
        }
        else{
            holder = (ManagementUserViewHolder)view.getTag();
        }
        String imageUrl = listUser.get(i).getAvatarUri();
        if (imageUrl == null) {
            imageUrl = "https://img.freepik.com/premium-vector/muzzle-funny-cute-cat-glasses-vector-flat-illustration-portrait-clever-feline-character-isolated-white-background-avatar-cheerful-furry-domestic-animal-adorable-pet-face_198278-9811.jpg";
        }

// Tải ảnh từ URL và sử dụng nó làm placeholder và error
        Glide.with(context)
                .load(imageUrl)
                .error(Glide.with(context).load(imageUrl)) // Ảnh từ URL làm ảnh lỗi
                .into(holder.avatar);

// Sử dụng Glide để tải hình ảnh từ URL và hiển thị lên ImageView

        RegisterDAO registerDAO = new RegisterDAO(context);
        holder.name.setText(registerDAO.getUsernameByUserId(listUser.get(i).getUserId()));
        holder.role.setText(registerDAO.getRoleByUserId(listUser.get(i).getUserId()));

        String role = registerDAO.getRoleByUserId(LoginActivity.getUserId());
//        if(role.equals("admin tối cao")){
//            holder.btnEditUser.setVisibility(View.VISIBLE);
//            holder.btnChangeRole.setVisibility(View.VISIBLE);
//            holder.btnDelUser.setVisibility(View.VISIBLE);
//        }
        if(LoginActivity.getUserId() == 1){
            if ( listUser.get(i).getUserId() != 1){
                holder.btnEditUser.setVisibility(View.VISIBLE);
                holder.btnChangeRole.setVisibility(View.VISIBLE);
                holder.btnDelUser.setVisibility(View.VISIBLE);
            }
            else if( listUser.get(i).getUserId() == 1){
                holder.btnEditUser.setVisibility(View.GONE);
                holder.btnChangeRole.setVisibility(View.GONE);
                holder.btnDelUser.setVisibility(View.GONE);
            }
        }
        else{
            holder.btnEditUser.setVisibility(View.GONE);
            holder.btnChangeRole.setVisibility(View.GONE);
            holder.btnDelUser.setVisibility(View.GONE);
        }

        holder.btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listUser.get(i).getRole().equals("admin")){
                    Toast.makeText(context, "Đã là admin rồi", Toast.LENGTH_SHORT).show();
                } else {
                    registerDAO.updateRoleByUserId(listUser.get(i).getUserId(), "admin");
                    Toast.makeText(context, "tư cách admin", Toast.LENGTH_SHORT).show();
                    listUser.get(i).setRole("admin"); // Cập nhật vai trò trong listUser
                    notifyDataSetChanged(); // Cập nhật giao diện
                }
            }
        });

        holder.btnChangeRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listUser.get(i).getRole().equals("user")){
                    Toast.makeText(context, "Đã là user rồi", Toast.LENGTH_SHORT).show();
                } else {
                    registerDAO.updateRoleByUserId(listUser.get(i).getUserId(), "user");
                    Toast.makeText(context, "tư cách user", Toast.LENGTH_SHORT).show();
                    listUser.get(i).setRole("user"); // Cập nhật vai trò trong listUser
                    notifyDataSetChanged(); // Cập nhật giao diện
                }
            }
        });


        holder.btnDelUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage(String.format("Bạn có chắc muốn xóa %s ?", listUser.get(i).getUsername()));
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // Xóa người dùng khi người dùng xác nhận
                        registerDAO.deleteUserById(listUser.get(i).getUserId());
                        listUser.remove(i); // Xóa người dùng khỏi danh sách
                        notifyDataSetChanged(); // Cập nhật giao diện
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return view;
    }
}
class ManagementUserViewHolder{
    ImageView avatar;
    TextView name;
    TextView role;
    Button btnEditUser;
    Button btnChangeRole;
    Button btnDelUser;
}
