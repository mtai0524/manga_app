package com.example.bietdoidoctruyen.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.adapter.ListDataAdapter;
import com.example.bietdoidoctruyen.adapter.ViewPagerAdapter;
import com.example.bietdoidoctruyen.fragment.FavoriteFragment;
import com.example.bietdoidoctruyen.fragment.HistoryFragment;
import com.example.bietdoidoctruyen.fragment.HomeFragment;
import com.example.bietdoidoctruyen.fragment.MyMangaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcv_data;
    private ListDataAdapter listDataAdapter;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Đây là lần đầu tiên Activity được tạo, hãy thêm fragment vào container
            MyMangaFragment newFragment = new MyMangaFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.view_pager, newFragment);
            transaction.addToBackStack(null); // Thêm fragment vào back stack
            transaction.commit();
        }

//         Bottom nav
        viewPager = findViewById(R.id.view_pager);
        setUpViewPager();
        onClickBottomNavItem(); // không đưa ra file khác được, phải implement
    }

    private void setUpViewPager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // phải override đủ các method
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_favorite).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_history).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.action_myManga).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void onClickBottomNavItem() {
        bottomNavigationView = findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_favorite:
                        viewPager.setCurrentItem(1);
                        break;

                    case R.id.action_history:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.action_myManga:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }
}