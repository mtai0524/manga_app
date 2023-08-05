package com.example.bietdoidoctruyen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> mListUsers;
    public void setData (List<User> list){
        this.mListUsers = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mListUsers.get(position);
        if(user == null){
            return;
        }
        holder.tvUserName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        if(mListUsers != null){
            return mListUsers.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView tvUserName;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
        }
    }
}
