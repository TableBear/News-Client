package com.hzx.news.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzx.news.R;
import com.hzx.news.ui.base.BaseFragment;
import com.hzx.news.ui.base.BasePresenter;
import com.hzx.news.ui.base.LazyLoadFragment;
import com.socks.library.KLog;

import butterknife.ButterKnife;

/**
 * @Description: 个人信息的Fragment
 * @Author: TableBear
 * @Date: 2019/4/8 0:55
 * @param:
 * @return:
 * @throws:
 */
public class MineFragment extends BaseFragment {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void loadDate() {

    }
}
