package com.zswl.common.widget;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zswl.common.R;
import com.zswl.common.util.ToastUtil;

/**
 * Created by Administrator on 2018/6/23 0023.
 */

public class UpdateValueDialog extends Dialog implements View.OnClickListener {

    private TextView tvTitle;
    private EditText etValue;
    private String title;
    private Context context;
    private UpdateListener listener;

    public UpdateValueDialog(@NonNull Context context, String title) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.title = title;
        init();
    }

    public void setListener(UpdateListener listener) {
        this.listener = listener;
    }

    private void init() {
        setContentView(R.layout.update_value_dialog_layout);
        tvTitle = findViewById(R.id.tv_title);
        etValue = findViewById(R.id.et_value);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.tv_cancle).setOnClickListener(this);
        tvTitle.setText(title);
    }


    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_confirm) {
            String value = etValue.getText().toString().trim();
            if (TextUtils.isEmpty(value)) {
                ToastUtil.showShortToast("更新值不能为空");
                return;
            }
            if (listener != null)
                listener.onConfirm(value);

        }
        dismiss();
    }

    @Override
    public void show() {
        etValue.setText("");
        super.show();
    }

    public interface UpdateListener {
        void onConfirm(String value);
    }
}
