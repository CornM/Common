package com.zswl.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created by Administrator on 2018/7/16 0016.
 */
@SuppressLint("AppCompatCustomView")
public class MyWebView extends WebView {
    private final String TAG = "MyWebView";
    private LoadingDialog dialog;

    public MyWebView(Context context) {
        this(context, null);
    }

    @SuppressLint("JavascriptInterface")
    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        dialog = new LoadingDialog(context);
        getSettings().setJavaScriptEnabled(true);

        setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    dialog.dismiss();
                } else {
                    dialog.show();
                }
            }
        });
    }


}
