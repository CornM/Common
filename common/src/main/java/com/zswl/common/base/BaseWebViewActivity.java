package com.zswl.common.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
        String title = getActionBarTitle();
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        String url = getUrl();
        myWebView.loadUrl(url);
        LogUtil.d(TAG, url);
        BaseWebJs baseWebJs = getWebJs();
        if (baseWebJs != null) {
            myWebView.addJavascriptInterface(baseWebJs, "android");
        }
        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    //Handle telephony Urls
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(url)));
                }
                return true;
            }

        });


    }

    /**
     * 获取标题
     *
     * @return
     */
    public String getActionBarTitle() {
        return null;
    }



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
