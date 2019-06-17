package com.zswl.baseprojectdemo;

import android.widget.ImageView;
import android.widget.TextView;

import com.zswl.common.api.ApiService;
import com.zswl.common.base.BaseObserver;
import com.zswl.common.base.BasePhotoActivity;
import com.zswl.common.util.GlideUtil;
import com.zswl.common.util.RxParamUtil;
import com.zswl.common.util.RxUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends BasePhotoActivity {

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
    }

    @OnClick(R.id.iv)
    public void clickImg() {
//        startActivity(new Intent(context, Main2Activity.class));
        changeHeaderImg();
//        List<String> list=new ArrayList<>();
//        for (int i = 0; i < 12; i++) {
//            list.add(i+"测试");
//        }
//        SpinnerPopWindow<String> stringSpinnerPopWindow=new SpinnerPopWindow<>(context,list);
//        stringSpinnerPopWindow.showAsDropDown(textView);

    }

    @Override
    public void imagePath(String srcPath, String zipPath) {
        GlideUtil.showWithPath(zipPath, imageView);

        RequestBody idBody= RxParamUtil.get("48a47ff1d306420fb4c14cc39afb83aa");
        MultipartBody.Part part=RxParamUtil.get(new File(zipPath));

        ApiService.getInstance().getApi(Api.class)
                .uploadImg(idBody,part)
                .compose(RxUtil.io_main(lifeSubject))
                .subscribe(new BaseObserver(context) {
                    @Override
                    public void receiveResult(Object result) {

                    }
                });

    }

//    @Override
//    public void showImg(ImageBean imageBean, ImageView imageView) {
//        GlideUtil.showWithPath(imageBean.getImgPath(), imageView);
//    }
}
