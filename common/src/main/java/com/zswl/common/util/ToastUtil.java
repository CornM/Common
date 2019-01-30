package com.zswl.common.util;

import android.text.TextUtils;
import android.widget.Toast;

import com.zswl.common.base.BaseApplication;


/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class ToastUtil {
    private static Toast toast;


    public static void showShortToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getAppInstance(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }


}
