package com.hzx.news.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.hzx.news.R;
import com.hzx.news.ui.Fragment.HomeFragment;
import com.hzx.news.ui.Fragment.MineFragment;
import com.hzx.news.ui.Fragment.RecomFragment;
import com.hzx.news.ui.adapter.MainTabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottom_bar)
    BottomBarLayout bottomBarLayout;
    @BindView(R.id.vp_content)
    ViewPager viewPager;

    private List<Fragment> fragments;
    private MainTabAdapter mainTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomBarLayout.setSmoothScroll(true);
        initData();
        initListener();
    }

    public void initListener() {
        mainTabAdapter = new MainTabAdapter(fragments, getSupportFragmentManager());
        viewPager.setAdapter(mainTabAdapter);
        viewPager.setOffscreenPageLimit(fragments.size());
        bottomBarLayout.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
        bottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int previousPosition, int currentPosition) {
                viewPager.setCurrentItem(currentPosition);
            }
        });
    }

    public void initData() {
        fragments = new ArrayList<Fragment>(3);
        fragments.add(new HomeFragment());
        fragments.add(new RecomFragment());
        fragments.add(new MineFragment());
    }
}
