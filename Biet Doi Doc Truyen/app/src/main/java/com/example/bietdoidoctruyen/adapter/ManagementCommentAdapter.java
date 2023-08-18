package com.example.bietdoidoctruyen.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.bietdoidoctruyen.dao.RegisterDAO;
import com.example.bietdoidoctruyen.model.Comment;
import com.example.bietdoidoctruyen.model.CommentWithUserInfo;

import java.util.List;

public class ManagementCommentAdapter extends BaseAdapter {

    List<CommentWithUserInfo> commentList;
    Context context;

public ManagementCommentAdapter (Context context, List<CommentWithUserInfo> list){
        this.commentList = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int i) {
        return commentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ManagementCommentViewHolder holder;

        if(view == null){
            holder = new ManagementCommentViewHolder();
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.item_management_comment, null);
            holder.tvName = view.findViewById(R.id.tv_name_user);
            holder.tvCmt = view.findViewById(R.id.tv_cmt_user);
            holder.imgAvatar = view.findViewById(R.id.img_avatar_user);
            view.setTag(holder);
        }
        else{
            holder = (ManagementCommentViewHolder)view.getTag();
        }
        RegisterDAO registerDAO = new RegisterDAO(context);
        String avatar = registerDAO.getAvatarUriByUserId(commentList.get(i).getUserId());
        String imageUrl = avatar;
        if (imageUrl == null) {
            imageUrl = "https://img.freepik.com/premium-vector/muzzle-funny-cute-cat-glasses-vector-flat-illustration-portrait-clever-feline-character-isolated-white-background-avatar-cheerful-furry-domestic-animal-adorable-pet-face_198278-9811.jpg";
        }

        Glide.with(context)
                .load(imageUrl)
                .error(Glide.with(context).load(imageUrl)) // Ảnh từ URL làm ảnh lỗi
                .into(holder.imgAvatar);
        String userName = registerDAO.getUsernameByUserId(commentList.get(i).getUserId());
        holder.tvName.setText(userName);
        holder.tvCmt.setText(commentList.get(i).getComment());

        return view;
    }
}
class ManagementCommentViewHolder {
    ImageView imgAvatar;
     TextView tvName;
     TextView tvCmt;

}
