package com.zswl.common.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zswl.common.R;


/**
 * Created by Administrator on 2018/6/20 0020.
 */

public class SelectSexDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private SelectSexListener listener;

    public SelectSexDialog(@NonNull Context context) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        init();
    }


    private void init() {
        setContentView(R.layout.select_sex_layout);

        setViewLocation();
        View man = findViewById(R.id.tv_man);
        View woman = findViewById(R.id.tv_woman);
//        View ser = findViewById(R.id.tv_ser);
        View cancel = findViewById(R.id.tv_cancle);
        man.setOnClickListener(this);
        woman.setOnClickListener(this);
//        ser.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    /**
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;

        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = height;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_man) {
            if (listener != null) {
                listener.sex("男");
            }

        } else if (i == R.id.tv_woman) {
            if (listener != null) {
                listener.sex("女");
            }

//            case R.id.tv_ser:
//                if (listener != null) {
//                    listener.ser();
//                }
//                break;
        }
        dismiss();
    }

    public void setListener(SelectSexListener listener) {
        this.listener = listener;
    }

    public interface SelectSexListener {
        void sex(String sex);

//        void woman(String );

//        void ser();
    }
}
