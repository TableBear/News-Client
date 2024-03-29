package com.hzx.news.ui.Fragment;

import android.content.Intent;
import android.widget.TextView;

import com.auth0.jwt.JWT;
import com.hzx.news.R;
import com.hzx.news.app.NewsApp;
import com.hzx.news.ui.activity.LoginActivity;
import com.hzx.news.ui.activity.MessageActivity;
import com.hzx.news.ui.activity.RecordActivity;
import com.hzx.news.ui.activity.SettingActivity;
import com.hzx.news.ui.base.BaseFragment;
import com.hzx.news.ui.base.BasePresenter;
import com.socks.library.KLog;

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
    @BindView(R.id.tv_name)
    TextView tvName;

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

    @Override
    public void initData() {
        super.initData();
        if (NewsApp.token != null) {
            tvName.setText(NewsApp.token.getNick());
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (NewsApp.token != null) {
                tvName.setText(NewsApp.token.getNick());
                KLog.i(NewsApp.token.getNick());
            } else {
                tvName.setText("未登录");
            }
        } else {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NewsApp.token != null) {
            tvName.setText(NewsApp.token.getNick());
        } else {
            tvName.setText("未登录");
        }

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

    @OnClick(R.id.iv_to_profile)
    public void toProfileClick() {
        if (NewsApp.token == null) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getContext(), SettingActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.iv_message)
    public void messageClick() {
        Intent intent = new Intent(getContext(), MessageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_setting)
    public void settingClick() {
        Intent intent = new Intent(getContext(), SettingActivity.class);
        startActivity(intent);
    }
}
