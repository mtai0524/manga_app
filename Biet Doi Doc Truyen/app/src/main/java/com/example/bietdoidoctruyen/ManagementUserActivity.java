package com.example.bietdoidoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.bietdoidoctruyen.adapter.ManagementUserAdapter;
import com.example.bietdoidoctruyen.dao.RegisterDAO;
import com.example.bietdoidoctruyen.model.Register;

import java.util.ArrayList;
import java.util.List;

public class ManagementUserActivity extends AppCompatActivity {
    private ListView lvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_user);
        RegisterDAO registerDAO = new RegisterDAO(ManagementUserActivity.this);

        List<Register> listUser = registerDAO.getAllRegisters2();
        for (Register user : listUser) {
            Log.i("User Info", "username: " + user.getUsername());
//            Log.i("User Info", "password: " + user.getPassword());
            Log.i("User Info", "avatarUri: " + user.getAvatarUri());
            Log.i("User Info", "role: " + user.getRole());
        }
        lvUser = findViewById(R.id.lv_user);

        ManagementUserAdapter managementUserAdapter = new ManagementUserAdapter(ManagementUserActivity.this, listUser);

        lvUser.setAdapter(managementUserAdapter);

    }
}