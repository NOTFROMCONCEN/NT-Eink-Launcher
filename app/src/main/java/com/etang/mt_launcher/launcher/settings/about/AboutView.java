package com.etang.mt_launcher.launcher.settings.about;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class AboutView extends View {

    private int height, width, radius;
    private Paint paint;


    public AboutView(Context context) {
        super(context);
        init();
    }


    public AboutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AboutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        height = measuredWidth / 2;
        radius = height - 10;
        width = measuredWidth;
        setMeasuredDimension(measuredWidth, height * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 200; i++) {
            canvas.save();
            canvas.rotate(i * 10, width / 2f, height);
            canvas.drawLine(width / 2f, height - radius, width / 2f, height - radius + 10, paint);
            canvas.restore();
        }
    }
}

