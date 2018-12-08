package com.view.wheelview;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WheelView extends FrameLayout implements ViewPager.OnPageChangeListener, View.OnClickListener, View.OnTouchListener {
    private final int PAGERID = 1000;
    private final int INDICATORID = 1001;
    private final int TITLEID = 1002;
    private int mCurrent;
    private ViewPager viewPager;
    private LinearLayout indicator;
    private TextView title;
    private ViewAdapter adapter;
    private Handler handler;
    private ImageView[] indicatorViews;
    private Config config;
    private boolean DEBUG;
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(100);
        }
    };

    public WheelView(Context context) {
        this(context, null);
    }

    public WheelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setDEBUG(boolean DEBUG) {
        this.DEBUG = DEBUG;
    }

    private void init(Context context, AttributeSet attrs) {
        config = new Config(context, attrs);

        RelativeLayout rl = new RelativeLayout(context);
        LayoutParams flp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        rl.setLayoutParams(flp);
        rl.setBackgroundColor(Color.LTGRAY);

        viewPager = new ViewPager(context);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(rlp);
        viewPager.setId(PAGERID);
        rl.addView(viewPager);
        adapter = new ViewAdapter();
        adapter.setConfig(config);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
//        viewPager.setOnTouchListener(this);


        indicator = new LinearLayout(context);
        RelativeLayout.LayoutParams rlpi = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlpi.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rlpi.setMargins(0, 0, 0, config.indicatorBottom);
        indicator.setLayoutParams(rlpi);
        indicator.setGravity(Gravity.CENTER);
        indicator.setOrientation(LinearLayout.HORIZONTAL);
        indicator.setId(INDICATORID);
        rl.addView(indicator);

        title = new TextView(context);
        RelativeLayout.LayoutParams rlpt = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlpt.addRule(RelativeLayout.ABOVE, INDICATORID);
        rlpt.setMargins(0, 0, 0, config.titleBottom);
        title.setLayoutParams(rlpt);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(config.titleColor);
        title.setTextSize(config.titleSize);
        title.setId(TITLEID);
        rl.addView(title);

        if (!config.titleEnable) {
            title.setVisibility(View.GONE);
            indicator.setVisibility(View.GONE);
        }
        this.addView(rl);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                viewPager.setCurrentItem(mCurrent + 1, true);
                handler.postDelayed(runnable, config.duration);
            }
        };
    }


    public void setData(List<WheelData> datas) {
        List<View> views = new ArrayList<>();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        for (WheelData data : datas) {
            RelativeLayout rl = new RelativeLayout(config.mContext);
            ImageView view = new ImageView(config.mContext);
            view.setLayoutParams(params);
            rl.addView(view);
            Picasso.get().load(data.getUrl()).placeholder(config.placeDrawable).error(config.errorDrawable).fit().centerCrop().into(view);
            if (data.getTitle() != null)
                rl.setTag(data.getTitle());
            views.add(rl);
        }

        if (views.size() > 0)
            setViews(views);
    }

    public void setViews(List<View> allView) {
        mCurrent = 0;
        assert allView != null;
        indicator.removeAllViews();

        for (int i = 0; i < allView.size(); i++) {
            allView.get(i).setId(i);
        }

        if (config.selectID > 0 && config.unSelectID > 0) {
            indicatorViews = new ImageView[allView.size()];
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(config.indicatorWidth, config.indicatorHeight);
            params.setMargins(config.indicatorMargin, 0, config.indicatorMargin, 0);
            for (int i = 0; i < allView.size(); i++) {
                indicatorViews[i] = new ImageView(config.mContext);
                indicatorViews[i].setLayoutParams(params);
                indicatorViews[i].setId(i);
                indicatorViews[i].setOnClickListener(this);
                indicatorViews[i].setOnTouchListener(this);
                indicator.addView(indicatorViews[i]);
                allView.get(i).setOnTouchListener(this);
            }
        }
        if (allView.size() > 1 && config.isCycle) {
            config.views.clear();
            config.views.add(allView.get(allView.size() - 1));
            config.views.addAll(allView);
            config.views.add(allView.get(0));
            mCurrent = 1;
        } else {
            config.isCycle = false;
            config.views.addAll(allView);
            mCurrent = 0;
        }
        adapter.setConfig(config);
        adapter.notifyDataSetChanged();
        viewPager.setCurrentItem(mCurrent, false);
        handler.postDelayed(runnable, config.duration);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int p) {
        int max = config.views.size() - 1;
        int position = p;
        mCurrent = p;
        if (config.isCycle) {
            if (position == max) {
                mCurrent = 1;
            } else if (position == 0) {
                mCurrent = max - 1;
            }
        }
        if (config.views.get(mCurrent).getTag() instanceof String)
            title.setText(config.views.get(mCurrent).getTag().toString());

        setIndicator();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (config.isCycle) {
            if (state == 1) {
                return;
            } else if (state == 0) {
                viewPager.setCurrentItem(mCurrent, false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (config.isCycle)
            id += 1;
        viewPager.setCurrentItem(id, false);
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, config.duration);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handler.removeCallbacks(runnable);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                handler.postDelayed(runnable, config.duration);
            default:
                break;
        }
        if (DEBUG)
            Log.i("life", event.getAction() + "");
        return false;
    }

    public void setCycle(boolean cycle) {
        config.isCycle = cycle;
    }

    public void setTitleEnable(boolean titleEnable) {
        config.titleEnable = titleEnable;
        if (config.titleEnable) {
            title.setVisibility(View.VISIBLE);
            indicator.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
            indicator.setVisibility(View.GONE);
        }
    }

    public void setDuration(int duration) {
        config.duration = duration;
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, config.duration);
    }

    public void setTitleColor(int titleColor) {
        config.titleColor = titleColor;
        title.setTextColor(config.titleColor);
    }

    public void setTitleBottom(int titleBottom) {
        config.titleBottom = titleBottom;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) title.getLayoutParams();
        params.setMargins(0, 0, 0, config.titleBottom);
        title.setLayoutParams(params);
    }

    public void setTitleSize(float titleSize) {
        config.titleSize = titleSize;
        title.setTextSize(titleSize);
    }

    public void setIndicatorBottom(int indicatorBottom) {
        config.indicatorBottom = indicatorBottom;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) indicator.getLayoutParams();
        params.setMargins(0, 0, 0, config.indicatorBottom);
        indicator.setLayoutParams(params);
    }

    public void setSelectID(int selectID) {
        config.selectID = selectID;
        setIndicator();
    }

    public void setUnSelectID(int unSelectID) {
        config.unSelectID = unSelectID;
        setIndicator();
    }

    public void setIndicatorMargin(int indicatorMargin) {
        config.indicatorMargin = indicatorMargin;
        if (config.selectID > 0 && config.unSelectID > 0) {
            if (indicatorViews != null && indicatorViews.length > 0) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) indicatorViews[0].getLayoutParams();
                params.setMargins(config.indicatorMargin, 0, config.indicatorMargin, 0);
            }
        }
    }

    public void setIndicatorWidth(int indicatorWidth) {
        config.indicatorWidth = indicatorWidth;
        if (config.selectID > 0 && config.unSelectID > 0) {
            if (indicatorViews != null && indicatorViews.length > 0) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) indicatorViews[0].getLayoutParams();
                params.width = config.indicatorWidth;
            }
        }
    }

    public void setIndicatorHeight(int indicatorHeight) {
        config.indicatorHeight = indicatorHeight;
        if (config.selectID > 0 && config.unSelectID > 0) {
            if (indicatorViews != null && indicatorViews.length > 0) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) indicatorViews[0].getLayoutParams();
                params.height = config.indicatorHeight;
            }
        }
    }

    public void setOnPageClickListener(WheelView.OnPageClickListener onPageClickListener) {
        config.onPageClickListener = onPageClickListener;
        for (View view : config.views) {
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    config.onPageClickListener.onPageClick(v, v.getId());
                }
            });
        }
    }

    private void setIndicator() {
        int position;
        if (config.isCycle)
            position = mCurrent - 1;
        else
            position = mCurrent;
        if (indicatorViews != null && indicatorViews.length > 0) {
            for (int i = 0; i < indicatorViews.length; i++) {
                if (i == position)
                    indicatorViews[i].setImageResource(config.selectID);
                else
                    indicatorViews[i].setImageResource(config.unSelectID);
            }
        }
    }

    public interface OnPageClickListener {
        void onPageClick(View view, int position);
    }
}
