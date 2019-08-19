package com.zswl.common.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.zswl.common.R;


/**
 * Created by Administrator on 2018/7/31 0031.
 */

public class LoadingDialog extends Dialog {

    private Context context;

    public LoadingDialog(@NonNull Context context) {
        this(context, 0);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.Loading);
        this.context = context;
        setContentView(R.layout.item_loading_dialog);
        // 设置Dialog参数
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    // TODO 封装Dialog消失的回调
    @Override
    public void onBackPressed() {
        // 回调
//        cancle();
        // 关闭Loading
        dismiss();
    }

    @Override
    public void show() {
        if (!((Activity) context).isFinishing())
            super.show();
    }
}
