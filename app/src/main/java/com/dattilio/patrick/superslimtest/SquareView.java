package com.dattilio.patrick.superslimtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pat on 2/18/15.
 */
public class SquareView extends View {
    public SquareView(Context context) {
        super(context);
    }

    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = getMeasuredWidth();
        setMeasuredDimension(size, size);
    }
}