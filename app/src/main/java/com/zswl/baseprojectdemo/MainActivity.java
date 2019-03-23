package com.zswl.baseprojectdemo;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.zswl.common.base.BasePhotoActivity;
import com.zswl.common.base.BasePhotoListActivity;
import com.zswl.common.base.ImageBean;
import com.zswl.common.base.ViewHolder;
import com.zswl.common.util.GlideUtil;
import com.zswl.common.widget.SpinnerPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BasePhotoListActivity {

    @BindView(R.id.tv)
    TextView textView;
    @BindView(R.id.iv)
    ImageView imageView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        textView.setText("baoyu hua");
        GlideUtil.showWithUrl("http://imglf0.nosdn0.126.net/img/Sk5OZVhRaUZtSFg5bVR3SGtOeTlIQzJCdFRRUUpQYUNRTllSUzNKVVpTcXBMSmNZU2Q5T1pRPT0.jpg", imageView);
    }

    @OnClick(R.id.iv)
    public void clickImg() {
//        startActivity(new Intent(context, Main2Activity.class));
//        changeHeaderImg();
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(i+"测试");
        }
        SpinnerPopWindow<String> stringSpinnerPopWindow=new SpinnerPopWindow<>(context,list);
        stringSpinnerPopWindow.showAsDropDown(textView);

    }

//    @Override
    public void imagePath(String srcPath, String zipPath) {
        GlideUtil.showWithPath(zipPath, imageView);
    }

    @Override
    public void showImg(ImageBean imageBean, ImageView imageView) {
        GlideUtil.showWithPath(imageBean.getImgPath(), imageView);
    }
}
