package com.hzx.news.ui.Fragment;

import android.content.Intent;
import android.widget.TextView;

import com.hzx.news.R;
import com.hzx.news.ui.activity.RecordActivity;
import com.hzx.news.ui.base.BaseFragment;
import com.hzx.news.ui.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 个人信息的Fragment
 * @Author: TableBear
 * @Date: 2019/4/8 0:55
 * @param:
 * @return:
 * @throws:
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_like)
    TextView tvLike;

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

    @OnClick(R.id.tv_history)
    public void historyClick() {
        Intent intent = new Intent(getContext(), RecordActivity.class);
        intent.putExtra("flag", 1);
        startActivity(intent);
    }

    @OnClick(R.id.tv_collect)
    public void collectClick() {
        Intent intent = new Intent(getContext(), RecordActivity.class);
        intent.putExtra("flag", 2);
        startActivity(intent);
    }

    @OnClick(R.id.tv_like)
    public void likeClick() {
        Intent intent = new Intent(getContext(), RecordActivity.class);
        intent.putExtra("flag", 3);
        startActivity(intent);
    }
}
