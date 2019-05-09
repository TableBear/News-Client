package com.hzx.news.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hzx.news.R;

import org.w3c.dom.Text;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/5/9 15:46
 * @param:
 * @return:
 * @throws:
 */
public class CommentEditeDialog extends Dialog {

    EditText etCommentInput;

    Button btnPublish;

    InputMethodManager inputMethodManager;

    //点击发表，内容不为空时的回调
    public SendBackListener sendBackListener;

    public interface SendBackListener {
        void sendBack(String inputText);
    }

    private boolean isCancelable;//控制点击dialog外部是否dismiss
    private boolean isBackCancelable;//控制返回键是否dismiss
    private Context context;

    //这里的view其实可以替换直接传layout过来的 因为各种原因没传(lan)
    public CommentEditeDialog(Context context, boolean isCancelable, boolean isBackCancelable) {
        super(context, R.style.dialog);
        this.context = context;
        this.isCancelable = isCancelable;
        this.isBackCancelable = isBackCancelable;
        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment_editor);//这行一定要写在前面
        setCancelable(isCancelable);
        setCanceledOnTouchOutside(isBackCancelable);//点击外部不可dismiss
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        initView();
    }

    public void initView() {
        etCommentInput = findViewById(R.id.et_comment_input);
        btnPublish = findViewById(R.id.btn_publish);
        etCommentInput.requestFocus();
//        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_NOT_ALWAYS);
        btnPublish.setOnClickListener(v -> {
            if (TextUtils.isEmpty(etCommentInput.getText().toString())) {
                Toast.makeText(context, "输入内容为空", Toast.LENGTH_LONG).show();
                return;
            } else {
                if (sendBackListener != null)
                    sendBackListener.sendBack(etCommentInput.getText().toString());
//                inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, InputMethodManager.SHOW_FORCED);
                dismiss();
            }
        });
    }


    public void setSendBackListener(SendBackListener sendBackListener) {
        this.sendBackListener = sendBackListener;
    }

}
