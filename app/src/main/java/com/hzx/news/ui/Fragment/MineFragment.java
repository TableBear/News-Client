package com.hzx.news.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzx.news.R;
import com.hzx.news.ui.base.LazyLoadFragment;
import com.socks.library.KLog;

import butterknife.ButterKnife;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/8 0:55
 * @param:
 * @return:
 * @throws:
 */
public class MineFragment extends LazyLoadFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        KLog.i("MineFragment:", "创建成功");
        ButterKnife.bind(view);
        return view;
    }
}
