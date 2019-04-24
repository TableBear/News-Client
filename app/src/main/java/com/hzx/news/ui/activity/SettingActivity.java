package com.hzx.news.ui.activity;

import android.app.Dialog;
import android.widget.TextView;
import android.widget.Toast;

import com.hzx.news.R;
import com.hzx.news.app.NewsApp;
import com.hzx.news.model.entity.Token;
import com.hzx.news.model.entity.UpdateStatus;
import com.hzx.news.model.entity.User;
import com.hzx.news.presenter.SettingPresenter;
import com.hzx.news.presenter.View.SettingView;
import com.hzx.news.ui.base.BaseActivity;
import com.hzx.news.ui.dialog.ConfirmDialog;
import com.hzx.news.ui.dialog.InputDialog;

import org.litepal.LitePal;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_qq)
    TextView tvQQ;

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getUserInfo();
    }

    @OnClick(R.id.iv_back)
    public void backClcik() {
        finish();
    }

    @OnClick(R.id.iv_name)
    public void ivNameClick() {
        new InputDialog(getCurrentActivity(), R.style.dialog, new InputDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, String content, boolean confirm) {
                if (confirm) {
                    presenter.updateNick(content);
                    dialog.dismiss();
                }

            }
        }).setTitle("请输入新的用户名").show();
    }

    @OnClick(R.id.iv_email)
    public void ivEmailClick() {
        new InputDialog(getCurrentActivity(), R.style.dialog, new InputDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, String content, boolean confirm) {
                if (confirm) {
                    presenter.updateEmail(content);
                    dialog.dismiss();
                }

            }
        }).setTitle("请输入新的邮箱").show();
    }

    @OnClick(R.id.iv_phone)
    public void ivPhoneClick() {
        new InputDialog(getCurrentActivity(), R.style.dialog, new InputDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, String content, boolean confirm) {
                if (confirm) {
                    presenter.updatePhone(content);
                    dialog.dismiss();
                }

            }
        }).setTitle("请输入新的手机号码").show();
    }

    @OnClick(R.id.iv_qq)
    public void ivQQClick() {
        new InputDialog(getCurrentActivity(), R.style.dialog, new InputDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, String content, boolean confirm) {
                if (confirm) {
                    presenter.updateQQ(content);
                    dialog.dismiss();
                }

            }
        }).setTitle("请输入新的QQ号码").show();
    }

    @OnClick(R.id.iv_password)
    public void ivPasswordClick() {
        new InputDialog(getCurrentActivity(), R.style.dialog, new InputDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, String content, boolean confirm) {
                if (confirm) {
                    presenter.updatePassword(content);
                    dialog.dismiss();
                }

            }
        }).setTitle("输入新的密码").show();
    }


    @OnClick(R.id.bt_logout)
    public void logout() {
        new ConfirmDialog(getCurrentActivity(), R.style.dialog, "您确定退出登录成功？", new ConfirmDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    Toast.makeText(getCurrentActivity(), "点击确定", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    NewsApp.token = null;
                    LitePal.deleteAllAsync(Token.class).listen(new UpdateOrDeleteCallback() {
                        @Override
                        public void onFinish(int rowsAffected) {
                            if (rowsAffected > 0) {
                                Toast.makeText(getCurrentActivity(), "退出登录成功", Toast.LENGTH_SHORT);
                                finish();
                            }
                        }
                    });
                }

            }
        }).setTitle("提示").show();
    }

    @Override
    public void onSuccess(User user) {
        if (user.getUnick() != null) {
            tvName.setText(user.getUnick());
        }
        if (user.getEmail() != null) {
            tvEmail.setText(user.getEmail());
        }
        if (user.getPhone() != null) {
            tvPhone.setText(user.getPhone());
        }
        if (user.getQq() != null) {
            tvQQ.setText(user.getQq());
        }
    }

    @Override
    public void onError() {
        Toast.makeText(this, "出现未知异常，稍后重试", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateSucess(UpdateStatus updateStatus) {
        if (updateStatus.getCode().equals("200")) {
            if (updateStatus.getInfo().length() > 10) {
                LitePal.deleteAll(Token.class);
                NewsApp.token.setToken(updateStatus.getInfo());
                NewsApp.token.save();
            }
            initData();
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "修改失败，稍后重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdateError() {
        Toast.makeText(this, "出现未知异常，稍后重试", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
