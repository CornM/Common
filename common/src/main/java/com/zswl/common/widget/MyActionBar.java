package com.zswl.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zswl.common.R;


/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class MyActionBar extends LinearLayout {
    private boolean visibilityIvLeft;
    private boolean visibilityIvRight;
    private boolean visibilityTvRight;
    private String titleStr;
    private String rightStr;
    private int leftIvBg;
    private int rightIvBg;
    private int titleColor;
    private TextView title;
    private TextView rightTv;
    private ImageView leftIv;
    private ImageView rightIv;
    private View actionBar;
//    private int bgColor;
    private int bgDrawable;
    private int titleFontColor;
    private int rightFontColor;
    protected View rootView;
    public MyActionBar(Context context) {
        this(context, null);
//        init(context);
    }

    @SuppressLint("ResourceAsColor")
    public MyActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.action_bar_layout, this);
        leftIv = findViewById(R.id.iv_left);
        title = findViewById(R.id.tv_action_bar_title);
        rightIv = findViewById(R.id.iv_right);
        rightTv = findViewById(R.id.tv_action_bar_right_text);
        rootView = findViewById(R.id.rl_actionbar);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyActionBar);
        visibilityIvLeft = array.getBoolean(R.styleable.MyActionBar_visibility_iv_left, true);
        titleColor = array.getColor(R.styleable.MyActionBar_title_color, 0);
        visibilityTvRight = array.getBoolean(R.styleable.MyActionBar_visibility_tv_right, false);
        leftIvBg = array.getResourceId(R.styleable.MyActionBar_background_iv_left, 0);
        titleStr = array.getString(R.styleable.MyActionBar_title);
        rightStr = array.getString(R.styleable.MyActionBar_right_text);
        visibilityIvRight = array.getBoolean(R.styleable.MyActionBar_visibility_iv_right, false);
        rightIvBg = array.getResourceId(R.styleable.MyActionBar_background_iv_rigt, 0);
        bgDrawable = array.getResourceId(R.styleable.MyActionBar_background_drawable, 0);
//        bgColor = array.getColor(R.styleable.MyActionBar_background_color, context.getResources().getColor(R.color.color_white));
        titleFontColor = array.getColor(R.styleable.MyActionBar_title_font_color, context.getResources().getColor(R.color.color_title_actionbar));
        rightFontColor = array.getColor(R.styleable.MyActionBar_right_font_color, context.getResources().getColor(R.color.color_right_actionbar));
        rightTv.setText(rightStr);
        title.setText(titleStr);
        if (leftIvBg != 0){
            leftIv.setImageDrawable(context.getResources().getDrawable(leftIvBg));
        }
        if (rightIvBg != 0){
            rightIv.setImageDrawable(context.getResources().getDrawable(rightIvBg));
        }
        if (titleColor != 0) {
            title.setTextColor(titleColor);
        }
//        if (bgColor != 0)
//            rootView.setBackgroundColor(bgColor);
        if (bgDrawable != 0)
            rootView.setBackgroundResource(bgDrawable);
        if (titleFontColor != 0)
            title.setTextColor(titleFontColor);
        if (titleFontColor != 0)
            rightTv.setTextColor(rightFontColor);
        if (visibilityIvLeft) {
            leftIv.setVisibility(VISIBLE);
        } else {
            leftIv.setVisibility(INVISIBLE);
        }
        if (visibilityIvRight) {
            rightIv.setVisibility(VISIBLE);
        } else {
            rightIv.setVisibility(INVISIBLE);
        }
        if (visibilityTvRight) {
            rightTv.setVisibility(VISIBLE);
        } else {
            rightTv.setVisibility(INVISIBLE);
        }
        array.recycle();

    }


    private void setData() {
//        if (leftIvBg != 0)
//            leftIv.setBackgroundResource(leftIvBg);
//        if (rightIvBg != 0)
//            rightIv.setBackgroundResource(rightIvBg);
        rightTv.setText(rightStr);
        title.setText(titleStr);
//

//        if (visibilityIvRight) {
//            rightIv.setVisibility(VISIBLE);
//        } else {
//            rightIv.setVisibility(INVISIBLE);
//        }
//        if (visibilityTvRight) {
//            rightTv.setVisibility(VISIBLE);
//        } else {
//            rightTv.setVisibility(INVISIBLE);
//        }

    }

    public boolean getVisibilityIvLeft() {
        return visibilityIvLeft;
    }


    public boolean getVisibilityIvRight() {
        return visibilityIvRight;
    }


    public boolean getVisibilityTvRight() {
        return visibilityTvRight;
    }


    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getRightStr() {
        return rightStr;
    }

    public void setRightStr(String rightStr) {
        this.rightStr = rightStr;
    }

    public int getLeftIvBg() {
        return leftIvBg;
    }

    public void setLeftIvBg(int leftIvBg) {
        this.leftIvBg = leftIvBg;
    }

    public int getRightIvBg() {
        return rightIvBg;
    }

    public void setRightIvBg(int rightIvBg) {
        this.rightIvBg = rightIvBg;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getRightTv() {
        return rightTv;
    }

    public void setRightTv(TextView rightTv) {
        this.rightTv = rightTv;
    }

    public ImageView getLeftIv() {
        return leftIv;
    }

    public void setLeftIv(ImageView leftIv) {
        this.leftIv = leftIv;
    }

    public ImageView getRightIv() {
        return rightIv;
    }

    public void setRightIv(ImageView rightIv) {
        this.rightIv = rightIv;
    }

    public View getActionBar() {
        return actionBar;
    }

    public void setActionBar(View actionBar) {
        this.actionBar = actionBar;
    }
}
