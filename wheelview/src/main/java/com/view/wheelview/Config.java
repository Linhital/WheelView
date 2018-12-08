package com.view.wheelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public Context mContext;
    public List<View> views = new ArrayList<>();
    public boolean isCycle;//是否轮换
    public boolean titleEnable;
    public int duration;//轮转间隔的时间
    public int titleColor; //标题的颜色
    public int titleBottom; //标题距离指示器的距离
    public float titleSize; //标题字号大小
    public int indicatorBottom; //指示器距离底部的位置
    public int selectID;//指示器选中时的图标ID
    public int unSelectID;//指示器未选中时的图标ID
    public int indicatorMargin;//指示器之间的距离
    public int indicatorWidth;
    public int indicatorHeight;
    public int placeDrawable;
    public int errorDrawable;
    public WheelView.OnPageClickListener onPageClickListener;


    public Config(Context context, AttributeSet attrs) {
        this.mContext = context;
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CycleViewPager);
        indicatorBottom = a.getInteger(R.styleable.CycleViewPager_indicator_bottom, 20);
        titleColor = a.getColor(R.styleable.CycleViewPager_title_color, Color.WHITE);
        titleBottom = a.getInteger(R.styleable.CycleViewPager_title_bottom, 10);
        titleSize = a.getDimension(R.styleable.CycleViewPager_title_size, 16);
        isCycle = a.getBoolean(R.styleable.CycleViewPager_cycle, true);
        duration = a.getInteger(R.styleable.CycleViewPager_duration, 4000);
        selectID = a.getResourceId(R.styleable.CycleViewPager_indicator_select, -1);
        unSelectID = a.getResourceId(R.styleable.CycleViewPager_indicator_unselect, -1);
        indicatorMargin = (int) a.getDimension(R.styleable.CycleViewPager_indicator_margin, 10);
        titleEnable = a.getBoolean(R.styleable.CycleViewPager_title_enable, true);
        placeDrawable = a.getResourceId(R.styleable.CycleViewPager_place_drawable, R.drawable.ic_place);
        errorDrawable = a.getResourceId(R.styleable.CycleViewPager_error_drawable, R.drawable.ic_error);
        indicatorWidth = (int) a.getDimension(R.styleable.CycleViewPager_indicator_width, LinearLayout.LayoutParams.WRAP_CONTENT);
        indicatorHeight = (int) a.getDimension(R.styleable.CycleViewPager_indicator_height, LinearLayout.LayoutParams.WRAP_CONTENT);
        a.recycle();
    }
}
