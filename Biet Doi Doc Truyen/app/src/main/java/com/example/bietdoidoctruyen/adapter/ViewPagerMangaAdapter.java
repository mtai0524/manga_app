package com.example.bietdoidoctruyen.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bietdoidoctruyen.AddChapterFragment;
import com.example.bietdoidoctruyen.AddContentFragment;
import com.example.bietdoidoctruyen.AddMangaFragment;

public class ViewPagerMangaAdapter extends FragmentStatePagerAdapter {
    public ViewPagerMangaAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AddMangaFragment();
            case 1:
                return new AddChapterFragment();

            case 2:
                return new AddContentFragment();
            default:
                return new AddMangaFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "add manga";
                break;
            case 1:
                title = "add chapter";
                break;
            case 2:
                title = "add content";
                break;

        }
        return title;
    }
}
