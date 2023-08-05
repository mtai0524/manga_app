package com.example.bietdoidoctruyen.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bietdoidoctruyen.fragment.FavoriteFragment;
import com.example.bietdoidoctruyen.fragment.HistoryFragment;
import com.example.bietdoidoctruyen.fragment.HomeFragment;
import com.example.bietdoidoctruyen.fragment.MyMangaFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new FavoriteFragment();
            case 2:
                return new HistoryFragment();
            case 3:
                return new MyMangaFragment();
            default:
                return new HomeFragment();
        }
    }
    @Override
    public int getCount() {
        return 4;
    }
}
