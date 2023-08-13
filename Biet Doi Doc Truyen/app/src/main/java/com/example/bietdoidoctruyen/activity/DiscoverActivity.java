package com.example.bietdoidoctruyen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bietdoidoctruyen.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DiscoverActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

//        bottomNavigationView.setSelectedItemId(R.id.action_favorite);
    }
}