package com.example.textclock2;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TextClock extends AppCompatTextView {

    private int clockTextColor,clockBorderColor,clockBorderWidth;
    private Interpolator mInterpolator;
    private int seconds;
    private int sample;
    private boolean type;
    private  boolean running=true;
    android.os.Handler handler=new android.os.Handler();
    public TextClock(Context context) {
        super(context);
        init();
    }

    public TextClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    //initaolise
    private void init() {
        clockBorderWidth=0;
       clockBorderColor=Color.parseColor("#841bf4");
       clockTextColor=Color.parseColor("#841bf4");
        seconds=60;
        type=false;
    }

    private void init(Context context, AttributeSet attrs) {
        mInterpolator = new DecelerateInterpolator();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Textview);
        try {
            clockTextColor=typedArray.getColor(R.styleable.Textview_clockTextColor, Color.parseColor("#841bf4"));
            clockBorderColor=typedArray.getColor(R.styleable.Textview_clockBorderColor, Color.parseColor("#841bf4"));
            clockBorderWidth=typedArray.getInteger(R.styleable.Textview_clockBorderWidth,0);
            seconds=typedArray.getInteger(R.styleable.Textview_seconds,60);
            type=typedArray.getBoolean(R.styleable.Textview_typeReverse,false);
        } finally {
            typedArray.recycle();
        }
    }

    public int getSeconds() {
        return seconds;
    }
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    public int getClockTextColor() {
        return clockTextColor;
    }
    public void setClockTextColor(int clockTextColor) {
        this.clockTextColor = clockTextColor;
    }
    public int getClockBorderColor() {
        return clockBorderColor;
    }
    public void setClockBorderColor(int clockBorderColor) {
        this.clockBorderColor = clockBorderColor;
    }
    public int getClockBorderWidth() {
        return clockBorderWidth;
    }
    public void setClockBorderWidth(int clockBorderWidth) {
        this.clockBorderWidth = clockBorderWidth;
    }
    public Interpolator getmInterpolator() {
        return mInterpolator;
    }

    public void startClock(){
        handler.removeMessages(0);
        running=true;
        if(type)
            sample=seconds;
        else
            sample=0;
        setPadding(20,5,20,5);
        setBackground(getAngleDrawable(new float[]{20, 20, 20, 20, 20, 20, 20, 20},clockBorderWidth,clockBorderColor));
        setTextColor(clockTextColor);
          handler.post(new Runnable() {
            @Override
            public void run() {
                int m=sample/60;
                int s=(sample%60);
                if(running) {
                    if (type) {
                        sample--;
                        if (sample == 0)
                            running=false;
                    } else {
                        sample++;
                        if(sample==seconds)
                            running=false;
                    }
                }
                if(m<10 && s<10)
                    setText("0"+m+" : 0"+s);
                else if(m<10)
                    setText("0"+m+" : "+s);
                else if(s<10)
                    setText("0"+m+" : 0"+s);
                else
                    setText(m+" : "+s);
                handler.postDelayed(this,1000);
            }
        });
    }
    public void pauseClock(){
       running=false;
    }
    public void resumeClock(){
        running=true;
    }
    public void resetClock(){
       if(type)
           sample=seconds;
       else
           sample=0;
    }
    public static GradientDrawable getAngleDrawable(float[] _radius, int strokeWidth, int strokeColor)
    {
        GradientDrawable gradientDrawable= new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadii(_radius);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        return gradientDrawable;
    }
}
