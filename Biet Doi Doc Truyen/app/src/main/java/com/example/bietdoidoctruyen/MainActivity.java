package com.example.bietdoidoctruyen;

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
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        if (savedInstanceState == null) {
            // Đây là lần đầu tiên Activity được tạo, hãy thêm fragment vào container
            MyMangaFragment newFragment = new MyMangaFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.view_pager, newFragment);
            transaction.addToBackStack(null); // Thêm fragment vào back stack
            transaction.commit();
        }

        // check DAO getContentByChapterId OK
//        MangaContentDAO mangaContentDAO = new MangaContentDAO(this);
//        mangaContentDAO.getContentByChapterId(1);
//        List<MangaContent> mangaContents = mangaContentDAO.getContentByChapterId(1);

//        for (MangaContent mangaContent : mangaContents) {
//            int contentId = mangaContent.getContentId();
//            int chapterContentId = mangaContent.getChapterContentId();
//            String imgContent = mangaContent.getImgContent();
//
//            Log.d("MangaContentDAO", "ContentId: " + contentId + ", ChapterContentId: " + chapterContentId + ", ImgContent: " + imgContent);
//        }

        // check DAO getall() OK
//        mangaContents = mangaContentDAO.getAll();
//        for (MangaContent mangaContent : mangaContents) {
//            int contentId = mangaContent.getContentId();
//            int chapterContentId = mangaContent.getChapterContentId();
//            String imgContent = mangaContent.getImgContent();
//            Log.d("check getall DAO", "ContentId: " + contentId + ", ChapterContentId: " + chapterContentId + ", ImgContent: " + imgContent);
//        }





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
//                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_favorite:
                        viewPager.setCurrentItem(1);
//                        startActivity(new Intent(MainActivity.this, DiscoverActivity.class));
//                        Toast.makeText(MainActivity.this, "favorite", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_history:
                        viewPager.setCurrentItem(2);
//                        startActivity(new Intent(MainActivity.this, DiscoverActivity.class));
//                        Toast.makeText(MainActivity.this, "favorite", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_myManga:
                        viewPager.setCurrentItem(3);
//                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
////                        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
////                            @Override
////                            public boolean onMenuItemClick(MenuItem menuItem) {
////                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
////                                return true;
////                            }
////                        });
                        break;
                }
                return true;
            }
        });
    }
}