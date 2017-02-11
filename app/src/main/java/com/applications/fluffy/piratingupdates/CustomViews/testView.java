package com.applications.fluffy.piratingupdates.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.applications.fluffy.piratingupdates.R;

/**
 * Created by fluffy on 04/02/17.
 */

public class testView extends View{

    private boolean mShowText = false;
    private String mLabelText;
    private int mTextPos;
    private float mTextWidth = 0.0f;

    public testView(Context context, AttributeSet attrs){
        super(context,attrs);
        //init();
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.testView,
                0,0
        );

        try{
            mShowText = a.getBoolean(R.styleable.testView_showText, false);
            mTextPos = a.getInteger(R.styleable.testView_labelPosition, 0);
            mLabelText = a.getString(R.styleable.testView_labelText);
        }
        finally{
            a.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //Account for padding
        float xpad = (float)(getPaddingLeft() +  getPaddingRight());
        float ypad = (float)(getPaddingTop() + getPaddingBottom());


        //Account for the label
        if(mShowText) xpad += mTextWidth;

        float ww = (float)w - xpad;
        float hh = (float)h - ypad;

        float diameter = Math.min(ww,hh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Try for a width based on our minimum
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        // Whatever the width ends up being, ask for a height that would let the view
        // get as big as it can
        int minh = MeasureSpec.getSize(w) - (int)mTextWidth + getPaddingBottom() + getPaddingTop();
        int h = resolveSizeAndState(MeasureSpec.getSize(w) - (int)mTextWidth, heightMeasureSpec, 0);

        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super .onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        canvas.drawPaint(paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(10);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("TEST", 100, 100, paint);
    }

    public boolean isShowText(){
        return mShowText;
    }

    public void setShowText(boolean showText){
        mShowText = showText;
        invalidate();
        requestLayout();
    }
}
