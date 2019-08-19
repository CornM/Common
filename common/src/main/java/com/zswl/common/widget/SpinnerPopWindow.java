package com.zswl.common.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zswl.common.R;
import com.zswl.common.base.BaseRecycleViewAdapter;
import com.zswl.common.base.ViewHolder;

import java.util.List;

public class SpinnerPopWindow<T> extends PopupWindow implements PopupWindow.OnDismissListener {

    private LayoutInflater inflater;
    private RecyclerView recyclerView;
    private List<T> list;
    private Context context;
    private SpinnerItemClickListener<T> listener;
    private int popupWidth;
    private int popupHeight;

    public SpinnerPopWindow(Context context, List<T> list) {
        super(context);
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
        init();
    }

    public void setListener(SpinnerItemClickListener<T> listener) {
        this.listener = listener;
    }

    private void init() {
        recyclerView = new RecyclerView(context);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
        recyclerView.setLayoutParams(layoutParams);
        setContentView(recyclerView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00);
        setBackgroundDrawable(dw);


        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        BaseRecycleViewAdapter adapter = new BaseRecycleViewAdapter<T>(context, R.layout.spiner_item_layout) {

            @Override
            public void onBind(final T t, ViewHolder holder, int position) {

                TextView textView = holder.getView(R.id.tv_name);
                textView.setText(t.toString());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        if (listener != null) {
                            listener.onItemClick(t);
                        }
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.addData(list);
        setOnDismissListener(this);

        //获取自身的长宽高
        recyclerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupHeight = recyclerView.getMeasuredHeight();
        popupWidth = recyclerView.getMeasuredWidth();

    }

    @Override
    public void showAsDropDown(View anchor) {
        setWidth(anchor.getWidth());
        super.showAsDropDown(anchor);
    }


    public void showAsTop(View view) {
        //获取需要在其上方显示的控件的位置信息
        setWidth(view.getWidth());
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        //在控件上方显示
        showAtLocation(view, Gravity.NO_GRAVITY, view.getLeft(), location[1] - popupHeight);


    }

    @Override
    public void onDismiss() {

    }

    public interface SpinnerItemClickListener<T> {
        void onItemClick(T bean);
    }


}
