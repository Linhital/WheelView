package com.sinelead.syld_phone.view.wigdet;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.sinelead.syld_phone.R;

public class NoticeView extends View {
    private int color;
    private float textSize;
    private String text;

    private Paint paint;
    private Paint paintColor;


    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        invalidate();
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    public NoticeView(Context context) {
        this(context, null);
    }

    public NoticeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoticeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getSize(heightMeasureSpec, 50);
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;
        canvas.drawCircle(r, r, r, paintColor);
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        // 文字宽
        float textWidth = paint.measureText(text);
        // 文字baseline在y轴方向的位置
        float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
        canvas.drawText(text, -textWidth / 2, baseLineY, paint);
        canvas.restore();
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextView);
        color = a.getColor(R.styleable.TextView_backgroundColor, Color.BLUE);
        textSize = a.getDimension(R.styleable.TextView_textSize, 16);
        text = a.getString(R.styleable.TextView_text);
        a.recycle();

        text = text.substring(0, 2);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(Color.WHITE);

        paintColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintColor.setColor(color);
    }

    private int getSize(int measureSpec, int defaultValue) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.AT_MOST:
                return defaultValue;
            case MeasureSpec.UNSPECIFIED:
                return defaultValue;
        }
        return size;
    }
}
