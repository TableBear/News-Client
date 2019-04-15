package com.hzx.news.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.hzx.news.R;
import com.hzx.news.app.NewsApp;
import com.hzx.news.model.entity.Token;
import com.hzx.news.ui.Fragment.HomeFragment;
import com.hzx.news.ui.Fragment.MineFragment;
import com.hzx.news.ui.Fragment.RecomFragment;
import com.hzx.news.ui.adapter.MainTabAdapter;
import com.hzx.news.ui.base.BaseActivity;
import com.hzx.news.ui.base.BasePresenter;
import com.socks.library.KLog;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_bar)
    BottomBarLayout bottomBarLayout;
    @BindView(R.id.vp_content)
    ViewPager viewPager;

    private List<Fragment> fragments;
    private MainTabAdapter mainTabAdapter;


    public void initListener() {
        bottomBarLayout.setSmoothScroll(true);
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

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    public void initData() {
        fragments = new ArrayList<Fragment>(3);
        fragments.add(new HomeFragment());
        fragments.add(new RecomFragment());
        fragments.add(new MineFragment());
        NewsApp.token = LitePal.findFirst(Token.class);
        KLog.i(NewsApp.token);
    }

}
