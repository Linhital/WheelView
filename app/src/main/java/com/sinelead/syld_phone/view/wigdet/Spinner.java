package com.sinelead.syld_phone.view.wigdet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v8.renderscript.RenderScript;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.sinelead.syld_phone.R;
import com.sinelead.syld_phone.view.adapter.SpinnerAdapter;
import com.sinelead.syld_phone.view.utils.RecyclerViewClickListener;


public class Spinner extends AppCompatTextView implements View.OnClickListener {
    private SpinnerPopwindow popwindow;
    private SpinnerAdapter adapter;
    private boolean isShow;
    private RecyclerView recyclerView;
    private int currentPosition;

    public Spinner(Context context) {
        super(context);
        init(context);
    }

    public Spinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public Spinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setFocus(boolean isFocus) {
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        adapter.setItemHeight(getMeasuredHeight());
        adapter.setPadingStart(getTotalPaddingLeft());
    }

    public void setAdapter(SpinnerAdapter adapter) {
        this.adapter = adapter;
        popwindow.setAdapter();
        if (adapter.getData().size() > 0) {
            this.setText(adapter.getData().get(0).toString());
            currentPosition = 0;
        }
    }

    @SuppressLint("WrongConstant")
    private void init(Context context) {
        popwindow = new SpinnerPopwindow(context);
        this.setOnClickListener(this);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        setFocusable(true);
        setFocusableInTouchMode(true);
        if (adapter == null || adapter.getData() == null || adapter.getData().size() == 0)
            return;
        popwindow.setWidth(this.getWidth());

        if (isShow == false) {
            popwindow.showAsDropDown(this);
        } else {
            popwindow.dismiss();
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int position) {
        if (position > 0 && position < adapter.getData().size()) {
            currentPosition = position;
            setText(adapter.getData().get(position).toString());
        }
    }

    public class SpinnerPopwindow extends PopupWindow implements RecyclerViewClickListener.OnItemClickListener {

        private LayoutInflater inflater;


        public SpinnerPopwindow(Context context) {
            super(context);
            this.inflater = LayoutInflater.from(context);
            init();
        }

        private void init() {
            View view = inflater.inflate(R.layout.spinner_layout, null);
            setContentView(view);
            setFocusable(true);
            setOutsideTouchable(true);
            this.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.color.colotTransparent));
            recyclerView = view.findViewById(R.id.menu_drop);
        }

        RenderScript rs = RenderScript.create(getContext());

        private void setAdapter() {
            recyclerView.setAdapter(adapter);
            RecyclerViewClickListener listener = new RecyclerViewClickListener(recyclerView.getContext());
            listener.setOnItemClickListener(this);
            listener.setRecyclerView(recyclerView);
            recyclerView.addOnItemTouchListener(listener);
        }

        @Override
        public void onItemClick(View view, int position) {
            dismiss();
            currentPosition = position;
            Spinner.this.setText(adapter.getData().get(position).toString());
        }

        @Override
        public void dismiss() {
            setFocusable(true);
            super.dismiss();
            isShow = false;
        }

        @Override
        public void showAsDropDown(View anchor) {
            super.showAsDropDown(anchor);
            isShow = true;

        }

        @Override
        public void setWidth(int width) {
            super.setWidth(width);
            if (adapter != null) {
                int size = adapter.getItemCount();
                if (size > 4) {
                    size = getMeasuredHeight() * 4;
                } else {
                    size = getMeasuredHeight() * size;
                }
                setHeight(size);
            }
        }

        @Override

        public void onItemLongClick(View view, int position) {

        }
    }
}
