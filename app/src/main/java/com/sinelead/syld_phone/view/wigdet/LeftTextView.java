package com.sinelead.syld_phone.view.wigdet;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.github.lzyzsd.randomcolor.RandomColor;
import com.sinelead.syld_phone.R;

public class LeftTextView extends View {

    private Paint paintText, paintGraph, paintBackground;
    private int color, backgroundColor;
    private String text;
    private float textSize;
    private int textX, textY;
    private int graphW, graphH;
    private Path path;
    private int position;


    public LeftTextView(Context context) {
        this(context, null);
    }

    public LeftTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public void setPosition(int position) {
        this.position = position;
        invalidate();
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        invalidate();
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextView);
        color = a.getColor(R.styleable.TextView_textColor, Color.BLACK);
        text = a.getString(R.styleable.TextView_text);
        textSize = a.getDimension(R.styleable.TextView_textSize, 16);
        a.recycle();
        RandomColor randomColor = new RandomColor();
        color = randomColor.randomColor();
        backgroundColor = ContextCompat.getColor(context, R.color.colorGrayWhite);
        setClickable(true);

        paintGraph = new Paint();
        paintGraph.setColor(color);
        paintGraph.setAlpha(100);
        paintGraph.setStyle(Paint.Style.FILL);
        paintGraph.setAntiAlias(true);

        paintText = new Paint();
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setColor(color);
        paintText.setTextSize(textSize);
        paintText.setAntiAlias(true);

        paintBackground = new Paint();
        paintBackground.setColor(backgroundColor);
        paintBackground.setAlpha(100);
        paintBackground.setStyle(Paint.Style.FILL);
        paintBackground.setAntiAlias(true);

        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);
        int modeH = MeasureSpec.getMode(heightMeasureSpec);
        int sizeH = MeasureSpec.getSize(heightMeasureSpec);
        sizeW = getSize(modeW, sizeW);
        sizeH = getSize(modeH, sizeH);

        setMeasuredDimension(sizeW, sizeH);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        textX = getMeasuredWidth() / 3;
        textY = getMeasuredHeight() * 4 / 5;
        graphW = getMeasuredWidth();
        graphH = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (text == null)
            text = "";
        canvas.drawText(text, textX, textY, paintText);
        switch (position % 4) {
            case 0:
                getPathSix();
                canvas.drawPath(path, paintGraph);
                break;
            case 1:
                getPathTwo();
                canvas.drawPath(path, paintGraph);
                getPathThree();
                canvas.drawPath(path, paintGraph);
                break;
            case 2:
                getPathfour();
                canvas.drawPath(path, paintGraph);
                getPathFive();
                canvas.drawPath(path, paintGraph);
                break;
            case 3:
                getPathOne();
                canvas.drawPath(path, paintGraph);
                break;
        }
        float r = (float) Math.sqrt(graphW * graphW + graphH * graphH);

    }

    private int getSize(int mode, int size) {
        switch (mode) {
            case MeasureSpec.AT_MOST:
                size = 300;
                break;
        }
        return size;
    }

    private void getPathOne() {
        path.reset();
        path.moveTo(graphW, 0);
        path.lineTo(graphW / 2, 0);
        path.quadTo(0, graphW / 2, graphW, graphH / 2);
        path.lineTo(graphW, graphH / 2);
        path.close();
    }

    private void getPathTwo() {
        path.reset();
        path.moveTo(graphW, 0);
        path.lineTo(graphW / 2, 0);
        path.quadTo(graphW / 2, graphH / 2, graphW, graphH / 2);
        path.lineTo(graphW, graphH / 2);
        path.close();
    }

    private void getPathThree() {
        path.reset();
        path.moveTo(graphW, 0);
        path.lineTo(graphW * 2 / 3, 0);
        path.quadTo(graphW * 2 / 3, graphH / 3, graphW, graphH / 3);
        path.lineTo(graphW, graphH / 3);
        path.close();
    }

    private void getPathfour() {
        path.reset();
        path.moveTo(graphW / 3, 0);
        path.cubicTo(graphW / 2, graphH / 3, graphW * 5 / 6, graphH / 2, graphW, 0);
        path.close();
    }

    private void getPathFive() {
        path.reset();
        path.moveTo(graphW, 0);
        path.lineTo(graphW * 2 / 3, 0);
        path.quadTo(graphW * 2 / 3, graphH * 2 / 3, graphW, graphH * 2 / 3);
        path.lineTo(graphW, graphH * 2 / 3);
        path.close();
    }

    public void getPathSix() {
        path.reset();
        path.moveTo(graphW, 0);
        path.lineTo(graphW / 3, 0);
        path.quadTo(graphW / 3, graphH * 2 / 3, graphW, graphH / 3);
        path.lineTo(graphW, graphH / 3);
        path.close();
    }
}
