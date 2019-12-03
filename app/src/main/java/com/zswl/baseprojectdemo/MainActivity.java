package com.zswl.baseprojectdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.print.PrintAttributes;
import android.print.PrintManager;

import androidx.annotation.RequiresApi;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.zswl.common.base.BasePhotoActivity;
import com.zswl.common.base.BasePhotoListActivity;
import com.zswl.common.base.ImageBean;
import com.zswl.common.util.BigImageUtil;
import com.zswl.common.util.GlideUtil;

import butterknife.BindView;
import butterknife.OnClick;

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
        String path= (String) imageView.getTag();
        if (TextUtils.isEmpty(path)){
            changeHeaderImg();
        }else {
            BigImageUtil.toBigNetImage(context,"/upload/goods/1.jpg");
        }
//        List<String> list=new ArrayList<>();
//        for (int i = 0; i < 12; i++) {
//            list.add(i+"测试");
//        }
//        SpinnerPopWindow<String> stringSpinnerPopWindow=new SpinnerPopWindow<>(context,list);
//        stringSpinnerPopWindow.showAsDropDown(textView);

    }

    //
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    @Override
    public void imagePath(String srcPath, String zipPath) {
        GlideUtil.showWithPath(srcPath, imageView);
        imageView.setTag(srcPath);

//        PrintHelper photoPrinter = new PrintHelper(this);
//        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);//设置填充的类型，填充的类型指的是在A4纸上打印时的填充类型，两种模式
//
//        //打印
//        Bitmap bitmap = BitmapFactory.decodeFile(srcPath);
//        photoPrinter.printBitmap("jpgTestPrint", bitmap);//这里的第一个参数是打印的jobName

//        String path= FileUtil.getSDCardPath()+"刘磊的简历.pdf";

//        onPrintDoc("材料清单", path);
    }

    //print picture in document
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void onPrintDoc(String jobName, String absPicturePath) {
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(PrintAttributes.COLOR_MODE_COLOR);//设置色彩模式，黑白或者彩色，不管用
        builder.setMinMargins(new PrintAttributes.Margins(300, 200, 300, 200));//设置页边距，就是打印的有效区域距离纸的边缘部分的距离，不管用
//  builder.setDuplexMode(DUPLEX_MODE_SHORT_EDGE);//设置按照横着打印还是竖着打印，不管用
        PrintAttributes.MediaSize temp = PrintAttributes.MediaSize.ISO_A4;//设置打印的纸张的类型，比如A4，A8等，不管用
        temp.asLandscape();
        Log.i("blb", "--------is portrait:" + temp.isPortrait());
        builder.setMediaSize(temp);//这个也不管用
//  builder.setResolution(new PrintAttributes.Resolution("white", "whiteRadish", 150, 150));//设置打印纸的分辨率，这个也不管用
        MyPrintAdapter myPrintAdapter = new MyPrintAdapter(this, absPicturePath);
        printManager.print(jobName, myPrintAdapter, builder.build());
    }

//    @Override
//    public void showImg(ImageBean imageBean, ImageView imageView) {
//        GlideUtil.showWithPath(imageBean.getImgPath(), imageView);
//        BigImageUtil.toBigNetImage(context,imageBean.getImgPath());
//    }


//    @Override
//    public void showImg(ImageBean imageBean, ImageView imageView) {
//        GlideUtil.showWithPath(imageBean.getImgPath(), imageView);
//    }
}
