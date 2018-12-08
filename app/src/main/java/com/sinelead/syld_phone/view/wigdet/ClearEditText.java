package com.sinelead.syld_phone.view.wigdet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.sinelead.syld_phone.R;

public class ClearEditText extends AppCompatEditText implements TextWatcher, View.OnFocusChangeListener {
    protected Drawable drawable;
    protected boolean isFocus;
    private boolean down;
    private BackProcessListener process;
    private OnTextListener listener;

    public ClearEditText(Context context) {
        super(context);
        init(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setProcess(BackProcessListener process) {
        this.process = process;
    }


    public void setOnTextListener(OnTextListener listener) {
        this.listener = listener;
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context) {
        drawable = ContextCompat.getDrawable(context, R.drawable.delete);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && process != null)
            process.hideDown();

        return super.onKeyPreIme(keyCode, event);
    }

    protected void setClear(boolean visible) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable draw = visible ? drawable : null;
        if (drawable != null)
            drawable.setColorFilter(getTextColors().getColorForState(getDrawableState(), Color.WHITE), PorterDuff.Mode.MULTIPLY);
        setCompoundDrawables(drawables[0], drawables[1], draw, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down = event.getX() > getWidth() - getTotalPaddingRight() && event.getX() < getWidth() - getPaddingRight();
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getX() < getWidth() - getTotalPaddingRight() || event.getX() > getWidth() - getPaddingRight())
                    down = false;
                break;
            case MotionEvent.ACTION_UP:
                if (event.getX() > getWidth() - getTotalPaddingRight() && event.getX() < getWidth() - getPaddingRight())
                    if (down)
                        setText("");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isFocus)
            setClear(getText().length() > 0);
        if (listener != null)
            listener.onTextChanged(s, start, before, count);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        isFocus = hasFocus;
        boolean visible = hasFocus ? getText().length() > 0 ? true : false : false;
        setClear(visible);
        setCursorVisible(hasFocus);
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!hasFocus) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } else {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public interface BackProcessListener {
        void hideDown();
    }

    public interface OnTextListener {
        void onTextChanged(CharSequence s, int start, int before, int count);
    }

}
