package com.view.wheelview;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ViewAdapter extends PagerAdapter {
    private Config config;
    private int currentPosition;

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public int getCount() {
        return config.views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (!config.isCycle)
            container.removeView(config.views.get(position));
        else if (config.views.get(currentPosition) != config.views.get(position) && position != 0 && position != config.views.size() - 1)
            container.removeView(config.views.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = config.views.get(position);
        try {
            container.addView(view);
        } catch (Exception e) {
            currentPosition = position;
        }
        return view;
    }
}
