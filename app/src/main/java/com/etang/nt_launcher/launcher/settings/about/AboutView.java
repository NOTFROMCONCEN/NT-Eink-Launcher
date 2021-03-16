package com.etang.nt_launcher.launcher.settings.about;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Package: com.etang.nt_launcher.launcher.settings.about
 * @ClassName: AboutView
 * @Description: 绘制AboutActivity（关于界面）中间的环形虚线
 * @CreateDate: 2021/3/16 8:55
 * @UpdateDate: 2021/3/16 8:55
 */
public class AboutView extends View {
    //高
    private int height = 0;
    //宽
    private int width = 0;
    //半径
    private int radius = 0;

    /**
     * 构造方法
     *
     * @param context
     */
    public AboutView(Context context) {
        super(context);
    }

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public AboutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public AboutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取画布高
        height = canvas.getHeight() / 2;
        //获取画布宽
        width = canvas.getWidth() / 2;
        //新建画笔
        Paint paint = new Paint();
        //设置画笔抗锯齿
        paint.setAntiAlias(true);
        //设置颜色
        paint.setColor(Color.GRAY);
        //设置画笔线条样式
        paint.setStyle(Paint.Style.STROKE);
        //设置画笔宽度
        paint.setStrokeWidth(4);
        //设置半径
        radius = height - 3;
        //循环绘制
        for (int i = 0; i < 200; i++) {
            canvas.save();// 保存画布
            canvas.rotate(i * 5, width, height);// 旋转画布
            // 绘制线条
            canvas.drawLine(width, height - radius, width,
                    height - radius + 20, paint);
            canvas.restore();// 恢复画布
        }
//        invalidate();
    }
}
