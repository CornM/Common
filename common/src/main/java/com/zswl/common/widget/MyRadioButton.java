package com.zswl.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import androidx.appcompat.widget.AppCompatRadioButton;

import com.zswl.common.R;


/**
 * Created by Administrator on 2018/7/7 0007.
 */

@SuppressLint("AppCompatCustomView")
public class MyRadioButton extends AppCompatRadioButton {
    private float mLeftWidth;
    private float mLeftHeight;
    private float mTopWidth;
    private float mTopHeight;
    private float mRightWidth;
    private float mRightHeight;
    private float mBottomWidth;
    private float mBottomHeight;

    public MyRadioButton(Context context) {
        super(context);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MyRadioButton);
        mLeftWidth = t.getDimension(R.styleable.MyRadioButton_rb_left_width, dip2px(context, 25));
        mLeftHeight = t.getDimension(R.styleable.MyRadioButton_rb_left_height, dip2px(context, 25));
        mTopWidth = t.getDimension(R.styleable.MyRadioButton_rb_top_width, dip2px(context, 25));
        mTopHeight = t.getDimension(R.styleable.MyRadioButton_rb_top_height, dip2px(context, 25));
        mRightWidth = t.getDimension(R.styleable.MyRadioButton_rb_right_width, dip2px(context, 25));
        mRightHeight = t.getDimension(R.styleable.MyRadioButton_rb_right_height, dip2px(context, 25));
        mBottomWidth = t.getDimension(R.styleable.MyRadioButton_rb_bottom_width, dip2px(context, 25));
        mBottomHeight = t.getDimension(R.styleable.MyRadioButton_rb_bottom_height, dip2px(context, 25));
        t.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //让RadioButton的图标可调大小 属性：
        Drawable drawableLeft = this.getCompoundDrawables()[0];//获得文字左侧图片
        Drawable drawableTop = this.getCompoundDrawables()[1];//获得文字顶部图片
        Drawable drawableRight = this.getCompoundDrawables()[2];//获得文字右侧图片
        Drawable drawableBottom = this.getCompoundDrawables()[3];//获得文字底部图片
        if (drawableLeft != null) {
            drawableLeft.setBounds(0, 0, (int) mLeftWidth, (int) mLeftHeight);
        }
        if (drawableTop != null) {
            drawableTop.setBounds(0, 0, (int) mTopWidth, (int) mTopHeight);
        }
        if (drawableRight != null) {
            drawableRight.setBounds(0, 0, (int) mRightWidth, (int) mRightHeight);
        }
        if (drawableBottom != null) {
            drawableBottom.setBounds(0, 0, (int) mBottomWidth, (int) mBottomHeight);
        }
        this.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);

    }

}
