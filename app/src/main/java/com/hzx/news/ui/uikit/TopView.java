package com.hzx.news.ui.uikit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hzx.news.R;
import com.hzx.news.ui.activity.SearchActivity;
import com.socks.library.KLog;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/25 10:11
 * @param:
 * @return:
 * @throws:
 */
public class TopView extends LinearLayout implements View.OnClickListener {

    private Context context;

    EditText editText;


    public TopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.top_view, this);
        initView();
    }

    public void initView() {
        editText = findViewById(R.id.et_search);
        KLog.i(editText);
        editText.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        KLog.i("点击了搜索框");
//        Toast.makeText(context, "点击了搜索框", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

}
