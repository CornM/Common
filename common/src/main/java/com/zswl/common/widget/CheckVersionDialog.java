package com.zswl.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;

import com.zswl.common.R;

/**
 * Created by Administrator on 2018/6/23 0023.
 */

public class CheckVersionDialog extends Dialog implements View.OnClickListener {
    private String phone;
    private Context context;

    public CheckVersionDialog(@NonNull Context context, String phone) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.phone = phone;
        init();
    }


    private void init() {
        setContentView(R.layout.dialog_check_version_layout);
       findViewById(R.id.tv_confirm).setOnClickListener(this);
       findViewById(R.id.tv_cancle).setOnClickListener(this);

    }


    public void onClick(View view) {
        if (view.getId() == R.id.tv_confirm) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(phone));
            context.startActivity(intent);
        }
        dismiss();
    }
}
