package com.hzx.news.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hzx.news.R;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/23 22:59
 * @param:
 * @return:
 * @throws:
 */
public class InputDialog extends Dialog implements View.OnClickListener {

    private EditText contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private Context mContext;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    public InputDialog(Context context) {
        super(context);
        this.mContext = context;
    }


    public InputDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public InputDialog(Context context, int themeResId, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.listener = listener;
    }

    protected InputDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public InputDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public InputDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    public InputDialog setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_input);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        contentTxt = findViewById(R.id.content);
        titleTxt = findViewById(R.id.title);
        submitTxt = findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = findViewById(R.id.cancel);
        cancelTxt.setOnClickListener(this);

        if (!TextUtils.isEmpty(positiveName)) {
            submitTxt.setText(positiveName);
        }

        if (!TextUtils.isEmpty(negativeName)) {
            cancelTxt.setText(negativeName);
        }

        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                if (listener != null) {
                    String content = contentTxt.getText().toString();
                    listener.onClick(this, content, false);
                }
                this.dismiss();
                break;
            case R.id.submit:
                if (listener != null) {
                    String content = contentTxt.getText().toString();
                    listener.onClick(this, content, true);
                }
                break;
        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, String content, boolean confirm);
    }

}
