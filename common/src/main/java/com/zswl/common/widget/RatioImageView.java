package com.zswl.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.zswl.common.R;


/**
 * Created by Administrator on 2018/7/20 0020.
 */


public class RatioImageView extends AppCompatImageView {
      /* 优先级从大到小：
     mIsWidthFitDrawableSizeRatio mIsHeightFitDrawableSizeRatio
     mWidthRatio mHeightRatio
     即如果设置了mIsWidthFitDrawableSizeRatio为true，则优先级较低的三个值不生效 */

    private float mDrawableSizeRatio = -1f; // src图片(前景图)的宽高比例
    // 根据前景图宽高比例测量View,防止图片缩放变形
    private boolean mIsWidthFitDrawableSizeRatio; // 宽度是否根据src图片(前景图)的比例来测量（高度已知）
    private boolean mIsHeightFitDrawableSizeRatio; // 高度是否根据src图片(前景图)的比例来测量（宽度已知）
    // 宽高比例
    private float mWidthRatio = -1; // 宽度 = 高度*mWidthRatio
    private float mHeightRatio = -1; // 高度 = 宽度*mHeightRatio

    /**
     * 绘图的Paint
     */
    private Paint mBitmapPaint;
    /**
     * 圆角的半径
     */
    private float mRadius;
    /**
     * 3x3 矩阵，主要用于缩小放大
     */
    private Matrix mMatrix;
    /**
     * 渲染图像，使用图像为绘制图形着色
     */
    private BitmapShader mBitmapShader;

    private RectF mRoundRect;

    public RatioImageView(Context context) {
        this(context, null);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr); // 虽然此处会调用setImageDrawable，但此时成员变量还未被正确初始化

        mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

        init(attrs);
        // 一定要有此代码
        if (getDrawable() != null) {
            mDrawableSizeRatio = 1f * getDrawable().getIntrinsicWidth()
                    / getDrawable().getIntrinsicHeight();
        }
    }

    /**
     * 初始化变量
     */
    private void init(AttributeSet attrs) {

        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.RatioImageView);
        mIsWidthFitDrawableSizeRatio = a.getBoolean(R.styleable.RatioImageView_is_width_fix_drawable_size_ratio,
                mIsWidthFitDrawableSizeRatio);
        mIsHeightFitDrawableSizeRatio = a.getBoolean(R.styleable.RatioImageView_is_height_fix_drawable_size_ratio,
                mIsHeightFitDrawableSizeRatio);
        mHeightRatio = a.getFloat(
                R.styleable.RatioImageView_height_to_width_ratio, mHeightRatio);
        mWidthRatio = a.getFloat(
                R.styleable.RatioImageView_width_to_height_ratio, mWidthRatio);
        mRadius = a.getDimension(R.styleable.RatioImageView_radius, 0);
        a.recycle();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (getDrawable() != null) {
            mDrawableSizeRatio = 1f * getDrawable().getIntrinsicWidth()
                    / getDrawable().getIntrinsicHeight();
            if (mDrawableSizeRatio > 0
                    && (mIsWidthFitDrawableSizeRatio || mIsHeightFitDrawableSizeRatio)) {
                requestLayout();
            }
        }
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (getDrawable() != null) {
            mDrawableSizeRatio = 1f * getDrawable().getIntrinsicWidth()
                    / getDrawable().getIntrinsicHeight();
            if (mDrawableSizeRatio > 0
                    && (mIsWidthFitDrawableSizeRatio || mIsHeightFitDrawableSizeRatio)) {
                requestLayout();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 优先级从大到小：
        // mIsWidthFitDrawableSizeRatio mIsHeightFitDrawableSizeRatio
        // mWidthRatio mHeightRatio
        if (mDrawableSizeRatio > 0) {
            // 根据前景图宽高比例来测量view的大小
            if (mIsWidthFitDrawableSizeRatio) {
                mWidthRatio = mDrawableSizeRatio;
            } else if (mIsHeightFitDrawableSizeRatio) {
                mHeightRatio = 1 / mDrawableSizeRatio;
            }
        }

        if (mHeightRatio > 0 && mWidthRatio > 0) {
            throw new RuntimeException("高度和宽度不能同时设置百分比！！");
        }

        if (mWidthRatio > 0) { // 高度已知，根据比例，设置宽度
            int height = MeasureSpec.getSize(heightMeasureSpec);
            super.onMeasure(MeasureSpec.makeMeasureSpec(
                    (int) (height * mWidthRatio), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        } else if (mHeightRatio > 0) { // 宽度已知，根据比例，设置高度
            int width = MeasureSpec.getSize(widthMeasureSpec);
            super.onMeasure(MeasureSpec.makeMeasureSpec(width,
                    MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
                    (int) (width * mHeightRatio), MeasureSpec.EXACTLY));
        } else { // 系统默认测量
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRoundRect = new RectF(0, 0, getWidth(), getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        setUpShader();

        canvas.drawRoundRect(mRoundRect, dp2px((int) mRadius), dp2px((int) mRadius),
                mBitmapPaint);

    }

    /* 初始化BitmapShader
     */
    private void setUpShader() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        Bitmap bmp = drawableToBitamp(drawable);
        // 将bmp作为着色器，就是在指定区域内绘制bmp
        mBitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(getWidth() * 1.0f / bmp.getWidth(), getHeight()
                * 1.0f / bmp.getHeight());
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mBitmapPaint.setShader(mBitmapShader);
    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }


    public int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());

    }
}
