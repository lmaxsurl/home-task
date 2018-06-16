package com.example.android.hometask1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ClockView extends View {

    private Paint clockPaint;
    private Paint clockBackgroundPaint;
    private Paint secondArrowPaint;
    private Paint numbersPaint;
    private RectF oval;
    private float left;
    private float right;
    private float top;
    private float bottom;
    private final float hourAngle = 30;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int height = getMeasuredWidth();
        setMeasuredDimension(widthMeasureSpec, height);
    }

    private void init(){
        clockPaint = new Paint();
        clockPaint.setAntiAlias(true);
        clockPaint.setColor(Color.BLACK);
        clockPaint.setStyle(Paint.Style.STROKE);

        numbersPaint = new Paint();
        numbersPaint.setAntiAlias(true);
        numbersPaint.setColor(Color.BLACK);

        clockBackgroundPaint = new Paint();
        clockBackgroundPaint.setAntiAlias(true);
        clockBackgroundPaint.setColor(Color.rgb(178,223,219));

        secondArrowPaint = new Paint();
        secondArrowPaint.setAntiAlias(true);
        secondArrowPaint.setColor(Color.RED);

        left = getWidth()*0.1f;
        right = getWidth() - left;
        top = getHeight()*0.1f;
        bottom = getHeight() - top;

        oval = new RectF();
        oval.top = top;
        oval.left = left;
        oval.right = right;
        oval.bottom = bottom;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        left = getWidth()*0.1f;
        right = getWidth() - left;
        top = getHeight()*0.1f;
        bottom = getHeight() - top;

        oval = new RectF();
        oval.top = top;
        oval.left = left;
        oval.right = right;
        oval.bottom = bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawClock(canvas);
        drawTime(canvas);
    }

    private void drawClock(Canvas canvas){
        clockPaint.setStrokeWidth(top*0.075f);
        numbersPaint.setTextSize(top*0.9f);
        secondArrowPaint.setStrokeWidth(top*0.05f);
        canvas.drawCircle(getWidth()/2, getHeight()/2, (right-left)/2, clockBackgroundPaint);
        canvas.drawCircle(getWidth()/2, getHeight()/2, (right-left)/2, clockPaint);
        canvas.drawText("12",getWidth()/2 - left/2, top*0.75f, numbersPaint);
        canvas.drawText("3",right + left/4, getHeight()/2 + top/4, numbersPaint);
        canvas.drawText("6",getWidth()/2 - left/4, getHeight() - top/4, numbersPaint);
        canvas.drawText("9",left/4, getHeight()/2 + top/4, numbersPaint);
        for(int i = 0; i < 12; i++){
            canvas.drawLine(getWidth()/2, top, getWidth()/2, i%3 == 0? top*1.75f : top*1.5f, clockPaint);
            canvas.rotate(hourAngle, getWidth()/2, getHeight()/2);
        }
    }

    private void drawTime(Canvas canvas){
        Date date = Calendar.getInstance().getTime();
        float minuteAngle = 6;
        float secondAngle = 6;
        canvas.save();
        canvas.rotate(date.getHours()* hourAngle, getWidth()/2, getHeight()/2);
        canvas.drawLine(getWidth()/2, getHeight()/2, getWidth()/2, top*2.5f, clockPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(date.getMinutes()* minuteAngle, getWidth()/2, getHeight()/2);
        canvas.drawLine(getWidth()/2, getHeight()/2, getWidth()/2, top*2f, clockPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(date.getSeconds()* secondAngle, getWidth()/2, getHeight()/2);
        canvas.drawLine(getWidth()/2, getHeight()/2, getWidth()/2, top*2.1f, secondArrowPaint);
        canvas.restore();
    }
}