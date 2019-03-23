package com.zswl.common.base;

import android.view.View;

import com.zswl.common.R;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public abstract class BackActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void init() {
        View backView = findViewById(R.id.iv_left);
        if (backView != null)
            backView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        finish();
    }
}
