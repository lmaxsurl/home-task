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

import java.util.Random;

public class RoundDiagram extends View {

    private float[] numbers;
    private Paint[] diagPaints;
    private Paint numPaint;
    private RectF oval;
    private float left;
    private float right;
    private float top;
    private float bottom;

    public void setNumbers(float[] numbers){
        this.numbers = new float[numbers.length];
        if(this.numbers.length != 0) {
            System.arraycopy(numbers, 0, this.numbers, 0, numbers.length);
            initPaints();
        }
    }

    private void initPaints() {
        diagPaints = new Paint[numbers.length];
        Random rnd = new Random();
        int bound = 256;
        for (int i = 0; i < numbers.length; i++) {
            diagPaints[i] = new Paint();
            diagPaints[i].setAntiAlias(true);
            diagPaints[i].setStyle(Paint.Style.FILL);
            diagPaints[i].setColor(Color.rgb(rnd.nextInt(bound), rnd.nextInt(bound), rnd.nextInt(bound)));
        }
    }

    private void init(){
        numPaint = new Paint();
        numPaint.setAntiAlias(true);
        numPaint.setColor(Color.BLACK);
    }

    public RoundDiagram(Context context) {
        super(context);
        init();
    }

    public RoundDiagram(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundDiagram(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundDiagram(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int height = getMeasuredWidth();
        setMeasuredDimension(widthMeasureSpec, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        left = getWidth()*0.1f;
        right = getWidth() - left;
        top = getHeight()*0.1f;
        bottom = getHeight() - top;

        oval = new RectF(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(numbers != null) drawDiagram(canvas);
    }

    private void drawDiagram(Canvas canvas) {
        float arraySum = 0.0f;
        for(float num : numbers)
            arraySum += num;
        for (int i = 0; i < numbers.length; i++){
            canvas.drawArc(oval, 0, numbers[i]/arraySum * 360.0f, true, diagPaints[i]);
            canvas.rotate(numbers[i]/arraySum * 360.0f, getWidth()/2, getHeight()/2);
        }
    }
}
