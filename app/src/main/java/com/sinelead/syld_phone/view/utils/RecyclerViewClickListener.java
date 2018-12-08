package com.sinelead.syld_phone.view.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerViewClickListener extends GestureDetector.SimpleOnGestureListener implements RecyclerView.OnItemTouchListener {
    private GestureDetectorCompat detector;
    private OnItemClickListener listener;
    private RecyclerView recyclerView;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public RecyclerViewClickListener(Context context) {
        detector = new GestureDetectorCompat(context, this);
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (recyclerView != null) {
            View item = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (item != null && listener != null) {
                listener.onItemClick(item, recyclerView.getChildLayoutPosition(item));
                return true;
            }
        }
        return super.onSingleTapUp(e);
    }

    @Override
    public void onLongPress(MotionEvent e) {
        if (recyclerView != null) {
            View item = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (item != null && listener != null) {
                listener.onItemLongClick(item, recyclerView.getChildLayoutPosition(item));
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        if (detector.onTouchEvent(motionEvent)) {
            return true;
        } else
            return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
