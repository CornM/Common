package com.zswl.common.base;

import android.view.View;

import com.zswl.common.R2;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public abstract class BackActivity extends BaseActivity {

    @OnClick(R2.id.iv_left)
    public void finishActivity(View view) {
        finish();
    }

}
