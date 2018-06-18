package com.example.android.hometask1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        numbersPaint.setTextAlign(Paint.Align.CENTER);

        clockBackgroundPaint = new Paint();
        clockBackgroundPaint.setAntiAlias(true);
        clockBackgroundPaint.setColor(Color.rgb(178,223,219));

        secondArrowPaint = new Paint();
        secondArrowPaint.setAntiAlias(true);
        secondArrowPaint.setColor(Color.RED);

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
        canvas.drawText("12",getWidth() * 0.5f, top * 0.75f, numbersPaint);
        canvas.drawText("3",right + left * 0.5f, (getHeight() + top) * 0.5f, numbersPaint);
        canvas.drawText("6",getWidth() * 0.5f, getHeight() - top * 0.25f, numbersPaint);
        canvas.drawText("9",left * 0.5f, (getHeight() + top) * 0.5f, numbersPaint);

        for(int i = 0; i < 12; i++){
            canvas.drawLine(getWidth() * 0.5f, top,
                    getWidth() * 0.5f, i%3 == 0? top * 1.75f : top * 1.5f, clockPaint);
            canvas.rotate(hourAngle, getWidth() * 0.5f, getHeight() * 0.5f);
        }
    }

    private void drawTime(Canvas canvas){
        DateFormat hourDateFormat, minuteDateFormat, secondDateFormat;
        Date date = new Date();
        hourDateFormat = new SimpleDateFormat("H");
        minuteDateFormat = new SimpleDateFormat("m");
        secondDateFormat = new SimpleDateFormat("s");

        int hours = Integer.parseInt(hourDateFormat.format(date));
        int minutes = Integer.parseInt(minuteDateFormat.format(date));
        int seconds = Integer.parseInt(secondDateFormat.format(date));

        float minAngle = 6;
        canvas.save();
        canvas.rotate((hours * hourAngle + minutes * 0.5f),
                getWidth() * 0.5f, getHeight() * 0.5f);
        canvas.drawLine(getWidth() * 0.5f, getHeight() * 0.5f,
                getWidth() * 0.5f, top*2.5f, clockPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(minutes * minAngle + seconds * 0.1f,
                getWidth() * 0.5f, getHeight() * 0.5f);
        canvas.drawLine(getWidth() * 0.5f, getHeight() * 0.5f,
                getWidth() * 0.5f, top * 1.8f, clockPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(seconds * minAngle, getWidth() * 0.5f, getHeight() * 0.5f);
        canvas.drawLine(getWidth() * 0.5f, getHeight() * 0.5f,
                getWidth() * 0.5f, top*2.1f, secondArrowPaint);
        canvas.restore();
    }
}