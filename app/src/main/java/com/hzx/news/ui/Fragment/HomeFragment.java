package com.hzx.news.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hzx.news.Cates;
import com.hzx.news.R;
import com.hzx.news.ui.adapter.MainTabAdapter;
import com.hzx.news.ui.base.BaseFragment;
import com.hzx.news.ui.base.BasePresenter;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description: 主页Fragment
 * @Author: TableBear
 * @Date: 2019/4/8 0:42
 * @param:
 * @return:
 * @throws:
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.vp_content)
    ViewPager viewPager;

    MainTabAdapter mainTabAdapter;
    private List<Cates> cates = new ArrayList<>();
    private AppCompatActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void loadDate() {

    }

    @Override
    public void initView(View view) {
        super.initView(view);
        initTabLayout();
    }


    @Override
    public void initListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void initTabLayout() {
        String name[] = new String[]{"全部", "财经", "教育", "房产", "科技", "时尚", "体育", "游戏", "时政", "股票", "娱乐", "社会", "家居", "星座", "彩票"};
        int code[] = new int[]{-1, 0, 1, 2, 4, 5, 7, 8, 9, 10, 11, 12, 13, 3, 6};
        ArrayList<Fragment> fragments = new ArrayList<>(14);
        for (int i = 0; i < 15; i++) {
            NewsListFragment newsListFragment = new NewsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("cate", code[i] + "");
            newsListFragment.setArguments(bundle);
            fragments.add(newsListFragment);
            tabLayout.addTab(tabLayout.newTab());
        }
        mainTabAdapter = new MainTabAdapter(fragments, getChildFragmentManager());
        tabLayout.setupWithViewPager(viewPager, false);
        viewPager.setOffscreenPageLimit(name.length);
        viewPager.setAdapter(mainTabAdapter);
        for (int i = 0; i < name.length; i++) {
            tabLayout.getTabAt(i).setText(name[i]);
        }
    }
}
