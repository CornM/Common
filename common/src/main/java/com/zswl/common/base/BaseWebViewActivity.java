package com.zswl.common.base;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.zswl.common.R;
import com.zswl.common.util.LogUtil;
import com.zswl.common.widget.MyActionBar;
import com.zswl.common.widget.MyWebView;

public abstract class BaseWebViewActivity extends BackActivity {
    protected MyWebView myWebView;
    TextView tvTitle;
    protected TextView tvRight;
    protected MyActionBar actionBar;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_webview;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void init() {
        super.init();
        myWebView = findViewById(R.id.wv);
        tvTitle = findViewById(R.id.tv_action_bar_title);
        tvRight = findViewById(R.id.tv_action_bar_right_text);
        actionBar = findViewById(R.id.mab);
        if (getWebJs() != null) {
            myWebView.addJavascriptInterface(getWebJs(), "android");
        }
//        myWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(getUrl());
//                return true;
//            }
//        });
        myWebView.loadUrl(getUrl());
        LogUtil.d(TAG, getUrl());
        tvTitle.setText(getActionBarTitle());

    }

    /**
     * 获取标题
     *
     * @return
     */
    public abstract String getActionBarTitle();


    /**
     * 获取加载url
     *
     * @return
     */
    public abstract String getUrl();

    /**
     * 获取js
     *
     * @return
     */
    public abstract BaseWebJs getWebJs();


}
